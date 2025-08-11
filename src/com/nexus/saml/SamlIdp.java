/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.saml;

import com.nexus.saml.SAMLAttribute;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableEntryException;
import java.util.Properties;
import java.util.UUID;
import java.security.cert.CertificateException;
import org.joda.time.DateTime;
import org.w3c.dom.Element;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import org.opensaml.DefaultBootstrap;

import org.opensaml.common.SAMLVersion;
import org.opensaml.saml2.core.impl.*;
import org.opensaml.saml2.core.*;
import org.opensaml.xml.Configuration;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.io.MarshallingException;
import org.opensaml.xml.schema.XSString;
import org.opensaml.xml.schema.impl.XSStringBuilder;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.security.x509.BasicX509Credential;
import org.opensaml.xml.signature.*;
import org.opensaml.xml.signature.impl.SignatureBuilder;
import org.opensaml.xml.util.Base64;
import org.opensaml.xml.util.XMLHelper;


/**
 *
 * @author vasanth
 */
public class SamlIdp {
   
    private String idpIssuer;
    private String destinationUrl;
    private String spEntityId;
    private String relayState;
    private final Credential signingCredential;
    
    public String getRelayState(){
        return this.relayState;
    }
    public String getDestinationUrl(){
        return this.destinationUrl;
    }
    public SamlIdp(ServletContext sc) throws FileNotFoundException, IOException, KeyStoreException, NoSuchAlgorithmException, UnrecoverableEntryException, CertificateException, ConfigurationException{
        Properties properties = new Properties();
        System.out.println(sc.getResource("/WEB-INF/saml.properties"));
        properties.load(sc.getResource("/WEB-INF/saml.properties").openStream());
        this.idpIssuer = properties.getProperty("saml.idp.issuer");
        this.destinationUrl = properties.getProperty("saml.sp.consumerService");
        this.spEntityId = properties.getProperty("saml.sp.entityId");
        this.relayState = properties.getProperty("saml.sp.relayState");;
        this.signingCredential = loadSigningCredential(
                properties.getProperty("saml.idp.privateKeyPassword"),
                properties.getProperty("saml.idp.privateKey")
        );
         DefaultBootstrap.bootstrap();
    }
    private Credential loadSigningCredential(String signingKeystorePassword, String signingKeystore) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableEntryException {
        char[] password = signingKeystorePassword.toCharArray();

        KeyStore store = KeyStore.getInstance("PKCS12");
        FileInputStream stream = new FileInputStream(signingKeystore);
        store.load(stream, password);
        

        KeyStore.ProtectionParameter protectionParameter = new KeyStore.PasswordProtection(password);
        KeyStore.PrivateKeyEntry pkEntry =
                (KeyStore.PrivateKeyEntry) store.getEntry("1", protectionParameter);
        PrivateKey pk = pkEntry.getPrivateKey();

        BasicX509Credential basicCredential = new BasicX509Credential();
        //basicCredential.setEntityCertificate(pkEntry.getCertificate());
        basicCredential.setPrivateKey(pk);
        return basicCredential;
    }
    
    public String buildEncodedRespponse(String userName, List<SAMLAttribute> attributes) throws Exception {
       Response response =  buildResponse(userName, attributes);
       ResponseMarshaller responseMarshaller = new ResponseMarshaller();
        Element el = responseMarshaller.marshall(response);

        String originalAssertionString = XMLHelper.nodeToString(el);
        System.out.format("%n***** Assertion XML ******%n%n");
        System.out.println(originalAssertionString);

        String samlResponse = Base64.encodeBytes(originalAssertionString.getBytes());
        
        return samlResponse;
       
    }
    public Response buildResponse(String userName, List<SAMLAttribute> attributes) throws Exception {
        
        DateTime authenticationTime = new DateTime();

        Response response = (Response) new ResponseBuilder().buildObject();
        response.setID(this.randomSAMLId());
        response.setIssueInstant(authenticationTime);
        response.setVersion(SAMLVersion.VERSION_20);
        response.setIssuer(this.buildIssuer());
        response.setDestination(this.destinationUrl);
        response.setStatus(buildStatus());
        response.getAssertions().add(this.buildAssertion(userName, attributes, authenticationTime));

        return signResponse(response);
    }
    private Assertion buildAssertion(String userName,List<SAMLAttribute> attributes, DateTime authenticationTime) throws MarshallingException, SignatureException, ConfigurationException {
        Assertion assertion = new AssertionBuilder().buildObject();
        assertion.setID(randomSAMLId());
        assertion.setIssuer(buildIssuer());
        assertion.setIssueInstant(authenticationTime);
        assertion.setVersion(SAMLVersion.VERSION_20);
        assertion.getAuthnStatements().add(buildAuthnStatement(authenticationTime));
        assertion.getAttributeStatements().add(buildAttributeStatement(attributes));
        assertion.setConditions(buildConditions());
        assertion.setSubject(buildSubject(userName, authenticationTime));
//        DefaultBootstrap.bootstrap();
        return signAssertion(assertion);
    }

//    private static AttributeStatement buildAttributeStatement(AuthnRequest input) {
//        AttributeStatementBuilder attributeStatementBuilder = AttributeStatementBuilder()
//        AttributeStatement attrStatement = attributeStatementBuilder.buildObject();
////        input.attributes.stream().map(AttributeConverter::convertAttribute).forEach(attrStatement.getAttributes()::add);
//        return attrStatement;
//    }
     private static AttributeStatement buildAttributeStatement(List<SAMLAttribute> attributes) {
     AttributeStatementBuilder attributeStatementBuilder = new AttributeStatementBuilder();
    AttributeStatement attributeStatement = attributeStatementBuilder.buildObject();

     for (SAMLAttribute attribute : attributes) {
         attributeStatement.getAttributes().add(buildAttribute(attribute.getName(),attribute.getValues()));
     }
    return attributeStatement;
  }

  private static Attribute buildAttribute(String name, List<String> values) {
    XSStringBuilder stringBuilder = (XSStringBuilder) Configuration.getBuilderFactory().getBuilder(XSString.TYPE_NAME);

    Attribute attribute = new AttributeBuilder().buildObject();
    attribute.setName(name);
    attribute.setNameFormat("urn:oasis:names:tc:SAML:2.0:attrname-format:uri");
    List<XSString> xsStringList = new ArrayList<XSString>();
    for (String value : values) {
        XSString stringValue = stringBuilder.buildObject(AttributeValue.DEFAULT_ELEMENT_NAME, XSString.TYPE_NAME);
        stringValue.setValue(value);
        xsStringList.add(stringValue);
    }

    attribute.getAttributeValues().addAll(xsStringList);
    return attribute;
  }

    private AuthnStatement buildAuthnStatement(DateTime authenticationTime) {
        AuthnStatement authnStatement = new AuthnStatementBuilder().buildObject();

        authnStatement.setAuthnInstant(authenticationTime);
        authnStatement.setSessionIndex(this.randomSAMLId());
        authnStatement.setSessionNotOnOrAfter(authenticationTime.plusMinutes(5));

        AuthnContext authnContext = new AuthnContextBuilder().buildObject();

        AuthnContextClassRef authnContextClassRef = new AuthnContextClassRefBuilder().buildObject();
        authnContextClassRef.setAuthnContextClassRef(AuthnContext.PASSWORD_AUTHN_CTX);

        authnContext.setAuthnContextClassRef(authnContextClassRef);
        authnStatement.setAuthnContext(authnContext);
        return authnStatement;
    }

    private  Conditions buildConditions() {
        Conditions conditions = new ConditionsBuilder().buildObject();
        Condition condition = new OneTimeUseBuilder().buildObject();
        conditions.getConditions().add(condition);

        AudienceRestriction audienceRestriction = new AudienceRestrictionBuilder().buildObject();

        Audience audience = new AudienceBuilder().buildObject();
        audience.setAudienceURI(this.spEntityId);

        audienceRestriction.getAudiences().add(audience);

        conditions.getAudienceRestrictions().add(audienceRestriction);
        return conditions;
    }

    private Subject buildSubject(String userName, DateTime authenticationTime) {
        SubjectConfirmationData confirmationData = new SubjectConfirmationDataBuilder().buildObject();
        confirmationData.setNotBefore(authenticationTime);
        confirmationData.setNotOnOrAfter(authenticationTime.plusMinutes(5));
        confirmationData.setRecipient(this.destinationUrl);

        SubjectConfirmation subjectConfirmation = new SubjectConfirmationBuilder().buildObject();
        subjectConfirmation.setSubjectConfirmationData(confirmationData);
        subjectConfirmation.setMethod(SubjectConfirmation.METHOD_BEARER);

        Subject subject = new SubjectBuilder().buildObject();
        subject.setNameID(buildNameId(userName));
        subject.getSubjectConfirmations().add(subjectConfirmation);
        return subject;
    }
    
    private Assertion signAssertion(Assertion assertion) throws MarshallingException, SignatureException {
        SignatureBuilder builder = new SignatureBuilder();
        Signature signature = builder.buildObject();

        signature.setSigningCredential(signingCredential);
        signature.setSignatureAlgorithm(SignatureConstants.ALGO_ID_SIGNATURE_RSA_SHA256);
        signature.setCanonicalizationAlgorithm(SignatureConstants.ALGO_ID_C14N_EXCL_OMIT_COMMENTS);

        assertion.setSignature(signature);
        
        AssertionMarshaller marshaller = new AssertionMarshaller();
        marshaller.marshall(assertion);
        Signer.signObject(signature);

        return assertion;
    }
    private Response signResponse(Response response) throws MarshallingException, SignatureException {
        SignatureBuilder builder = new SignatureBuilder();
        Signature signature = builder.buildObject();

        signature.setSigningCredential(signingCredential);
        signature.setSignatureAlgorithm(SignatureConstants.ALGO_ID_SIGNATURE_RSA_SHA256);
        signature.setCanonicalizationAlgorithm(SignatureConstants.ALGO_ID_C14N_EXCL_OMIT_COMMENTS);

        response.setSignature(signature);
        
        ResponseMarshaller responseMarshaller = new ResponseMarshaller();
        responseMarshaller.marshall(response);
        Signer.signObject(signature);

        return response;
    }
    
    private static NameID buildNameId(String userName) {
        NameID nameId = new NameIDBuilder().buildObject();
        nameId.setValue(userName);
        return nameId;
    }
    
    private String randomSAMLId() {
        return "_" + UUID.randomUUID().toString();
    }
    private Issuer buildIssuer() {
        Issuer issuer = new IssuerBuilder().buildObject();
        issuer.setValue(idpIssuer);
        return issuer;
    }

    private Status buildStatus() {
        StatusCode statusCode = new StatusCodeBuilder().buildObject();
        statusCode.setValue(StatusCode.SUCCESS_URI);
        Status status = new StatusBuilder().buildObject();
        status.setStatusCode(statusCode);
        return status;
    }
}
