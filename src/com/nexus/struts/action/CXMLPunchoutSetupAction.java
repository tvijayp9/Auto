/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.struts.action;

import com.nexus.services.ServiceFinder;
import javax.servlet.http.HttpServletRequest;
import com.opensymphony.xwork2.ActionSupport;
import java.io.PrintWriter;
import org.apache.struts2.interceptor.ServletRequestAware;
import java.util.*;
import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author tvijayp
 */
public class CXMLPunchoutSetupAction extends ActionSupport implements ServletRequestAware {
    
    Logger log = Logger.getLogger(CXMLPunchoutSetupAction.class);
    private HttpServletRequest request;
    private  HttpSession session = null;
    private SAXBuilder _xmlBuilder;
    public String execute() throws Exception {
        com.nexus.dao.SpringHibernateDAO partnerDao = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
        String result = null;
        String strCXML = null;
        String strMySiteURL =null;
        String attrval = null;
        String buyerCookie=null;
        String fromURL=null;
        Document doc = null;
        try {
            strMySiteURL = partnerDao.getFolderPath("mrl_punchout");
             _xmlBuilder = new SAXBuilder();

             session = request.getSession();
            ServletInputStream httpIn = request.getInputStream();
            
            
            _xmlBuilder.setReuseParser(false);
            doc = _xmlBuilder.build(httpIn);
             
            Element root = doc.getRootElement();
                        
            buyerCookie=root.getChild("Request").getChild("PunchOutSetupRequest").getChildText("BuyerCookie");
            fromURL = root.getChild("Request").getChild("PunchOutSetupRequest").getChild("BrowserFormPost").getChild("URL").getValue();
            log.info("...buyerCookie=="+buyerCookie+"..fromURL=="+fromURL);
            
            partnerDao.insertPunchoutSetup(buyerCookie,fromURL, "MRL");
            session.setAttribute("buyercookie", buyerCookie);
            Random random = new Random();
            int randomNumber = random.nextInt(1000000000);
            attrval = 1000000000 + randomNumber + "tty";
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("application/xml");
            PrintWriter out = response.getWriter();
            
            strCXML = "<?xml version=" + "\"" + "1.0" + "\"" + " encoding=" + "\"" + "UTF-8" + "\"" + "?>";
            strCXML = strCXML + "<!DOCTYPE cXML SYSTEM " + "\"" + "http://xml.cxml.org/schemas/cXML/1.2.023/cXML.dtd" + "\"" + ">";
            strCXML = strCXML + " <cXML payloadID=" + "\"" + attrval + "\"" + " timestamp=" + "\"" + getDate() + "\"" + ">";
            strCXML = strCXML + "<Response>";
            strCXML = strCXML + "   <Status code=" + "\"" + 200 + "\"" + " text=" + "\"" + "OK" + "\"" + ">" + "</Status>";
            strCXML = strCXML + "   <PunchOutSetupResponse> ";
            strCXML = strCXML + "     <StartPage>";
            strCXML = strCXML + "       <URL>" + strMySiteURL + "</URL>";
            strCXML = strCXML + "     </StartPage>";
            strCXML = strCXML + "   </PunchOutSetupResponse>";
            strCXML = strCXML + " </Response>";
            strCXML = strCXML + "</cXML>";

            out.write(strCXML);

        } catch (Exception e) {
            log.error("Exception Message:", e);
            e.printStackTrace();
        }
        return result;
    }

    public String getDate() throws Exception {
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            return sdf.format(now);
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request=httpServletRequest;
    }
}
