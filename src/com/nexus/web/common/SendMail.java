package com.nexus.web.common;

import java.io.UnsupportedEncodingException;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import org.apache.log4j.Logger;

public class SendMail
{
    Logger log=Logger.getLogger(SendMail.class);
	private String strSmtp;
        private String username;
        private String password;
        private String smtpPort;
    public void setStrSmtp(String strSmtp){
		this.strSmtp=strSmtp;
	  }

	public String getStrSmtp(){
		return this.strSmtp;
	}

    public void sendMail(String recipients[], String subject, String msgText, String from) throws MessagingException, UnsupportedEncodingException {

        log.info("sendMail.smtp..."+getStrSmtp()+"...port.."+getSmtpPort()+"..username.."+getUsername());
        
        Properties props = new Properties();
        props.put("mail.smtp.host", getStrSmtp());
        props.put("mail.smtp.port", getSmtpPort());
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", getStrSmtp());


        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(getUsername(),getPassword());
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("IVBPlus TECH TEAM <vthumma@ivbplus.com>"));
            InternetAddress[] addressBcc = new InternetAddress[recipients.length - 1];
            InternetAddress addressTo = new InternetAddress(recipients[0]);

        log.info("addressTo="+addressTo+"..addressBcc="+addressBcc+".....recipients.length="+recipients.length+"...from=="+from);
            for (int i = 1; i < recipients.length; i++) {
                addressBcc[i - 1] = new InternetAddress(recipients[i]);
            }
            
            message.setRecipient(Message.RecipientType.TO, addressTo);
            message.setRecipients(Message.RecipientType.BCC, addressBcc);
            message.setSubject(subject);
            message.setText(msgText);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    /*	public void sendMail( String recipients[ ], String subject, String message , String from) throws MessagingException
	{
		boolean debug = false;

		 //Set the host smtp address

		 Properties props = new Properties();
		 props.put("mail.smtp.host", getStrSmtp());

		 

		// create some properties and get the default Session

		Session session = Session.getDefaultInstance(props, null);
		session.setDebug(debug);


		// create a message
		Message msg = new MimeMessage(session);


		// set the from and to address
		InternetAddress addressFrom = new InternetAddress(from);
		msg.setFrom(addressFrom);

		InternetAddress[] addressBcc = new InternetAddress[recipients.length-1];
        InternetAddress addressTo = new InternetAddress(recipients[0]);

        log.info("addressTo="+addressTo+"..addressBcc="+addressBcc+".....recipients.length="+recipients.length);
        for (int i = 1; i < recipients.length; i++)
		{
			addressBcc[i-1] = new InternetAddress(recipients[i]);
		}
        msg.setRecipient(Message.RecipientType.TO, addressTo);
		msg.setRecipients(Message.RecipientType.BCC, addressBcc);
   


		// Optional : You can also set your custom headers in the Email if you Want
		//msg.addHeader("MyHeaderName", "myHeaderValue");

		// Setting the Subject and Content Type
		msg.setSubject(subject);
		msg.setContent(message, "text/plain");

		//send message
		//Transport.send(msg);
        Transport.send(msg);
	} 

    public void sendAttachedMail( String recipients[ ], String subject, String message , String from,String realpath,String filename) throws MessagingException
	{
		boolean debug = false;

		 //Set the host smtp address

		 Properties props = new Properties();
		 props.put("mail.smtp.host", getStrSmtp());



		// create some properties and get the default Session

		Session session = Session.getDefaultInstance(props, null);
		session.setDebug(debug);


		// create a message
		Message msg = new MimeMessage(session);


		// set the from and to address
		InternetAddress addressFrom = new InternetAddress(from);
		msg.setFrom(addressFrom);

		InternetAddress[] addressBcc = new InternetAddress[recipients.length-1];
        InternetAddress addressTo = new InternetAddress(recipients[0]);

        log.info("addressTo="+addressTo+"..addressBcc="+addressBcc+".....recipients.length="+recipients.length);
        for (int i = 1; i < recipients.length; i++)
		{
			addressBcc[i-1] = new InternetAddress(recipients[i]);
		}
        msg.setRecipient(Message.RecipientType.TO, addressTo);
		msg.setRecipients(Message.RecipientType.BCC, addressBcc);


        // create the message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();

            //fill message
            messageBodyPart.setText(message);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(realpath);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);


		// Optional : You can also set your custom headers in the Email if you Want
		//msg.addHeader("MyHeaderName", "myHeaderValue");

		// Setting the Subject and Content Type
		msg.setSubject(subject);
		msg.setContent(multipart);

		//send message
		Transport.send(msg);
	}*/
    
    public void sendAttachedMail(String recipients[], String subject, String msgText, String from, String realpath, String filename) throws MessagingException, UnsupportedEncodingException {
log.info("sendAttachedMail");
//        final String username = "vthumma@ivbplus.com.au";
//        final String password = "vijay125";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", getStrSmtp());
        props.put("mail.smtp.port", getSmtpPort());
//        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password);
                return new PasswordAuthentication(getUsername(),getPassword());
            }
        });

        try {
            Message message = new MimeMessage(session);
            //message.setFrom(new InternetAddress(from));
            message.setFrom(new InternetAddress(from,"IVBPlus TECH TEAM"));
            InternetAddress[] addressBcc = new InternetAddress[recipients.length - 1];
            InternetAddress addressTo = new InternetAddress(recipients[0]);
            for (int i = 1; i < recipients.length; i++) {
                addressBcc[i - 1] = new InternetAddress(recipients[i]);
            }

            message.setRecipient(Message.RecipientType.TO, addressTo);
            message.setRecipients(Message.RecipientType.BCC, addressBcc);

            // create the message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();

            //fill message
            messageBodyPart.setText(msgText);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(realpath);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Optional : You can also set your custom headers in the Email if you Want
            //msg.addHeader("MyHeaderName", "myHeaderValue");
            // Setting the Subject and Content Type
            message.setSubject(subject);
            message.setContent(multipart);

            //send message
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the smtpPort
     */
    public String getSmtpPort() {
        return smtpPort;
    }

    /**
     * @param smtpPort the smtpPort to set
     */
    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }
}