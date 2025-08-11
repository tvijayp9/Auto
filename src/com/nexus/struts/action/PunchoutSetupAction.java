/**
 *
 * @author Vijay Thumma
 */
package com.nexus.struts.action;

import com.nexus.services.ServiceFinder;
import javax.servlet.http.HttpServletRequest;
import com.opensymphony.xwork2.ActionSupport;
import java.io.PrintWriter;
import org.apache.struts2.interceptor.ServletRequestAware;
import java.util.*;
import org.apache.log4j.Logger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class PunchoutSetupAction extends ActionSupport implements ServletRequestAware {

    Logger log = Logger.getLogger(PunchoutSetupAction.class);
    private HttpServletRequest request;
    private  HttpSession session = null;
    private SAXBuilder _xmlBuilder;
    public String execute() throws Exception {
        com.nexus.dao.SpringHibernateDAO partnerDao = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
        String result = null;
        String strCXML = null;
//        String strMySiteURL = "http://203.206.178.177:8080/Auto/login.action?username=ausdrill_admin&amp;pass=adminin";
        String strMySiteURL =null;
        String strTimeStamp = null;
        String attrval = null;
        String buyerCookie=null;
        String fromURL=null;
//        _xmlBuilder = new SAXBuilder();
        Document doc = null;
        try {
            strMySiteURL = partnerDao.getFolderPath("punchout");
             _xmlBuilder = new SAXBuilder();
//             ActionContext ac = ActionContext.getContext();
             session = request.getSession();
            ServletInputStream httpIn = request.getInputStream();
            log.info("getRemoteAddr="+request.getRemoteAddr()+"...getRemoteHost="+request.getRemoteHost()+"..getPathInfo.."+request.getRequestURL().toString());
            log.info("httpin available="+httpIn.available());
//            if(httpIn.available()!=0){
            _xmlBuilder.setReuseParser(false);
            doc = _xmlBuilder.build(httpIn);
            Element root = doc.getRootElement();
            log.info("root value="+root);
            
            buyerCookie=root.getChild("Request").getChild("PunchOutSetupRequest").getChildText("BuyerCookie");
            fromURL = root.getChild("Request").getChild("PunchOutSetupRequest").getChild("BrowserFormPost").getChild("URL").getValue();
            System.out.println("...buyerCookie=="+buyerCookie+"..fromURL=="+fromURL);

            partnerDao.insertPunchoutSetup(buyerCookie,fromURL);
            session.setAttribute("buyercookie", buyerCookie);
//            }
            Random random = new Random();
            int randomNumber = random.nextInt(10000000);
            attrval = randomNumber + "tty";
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("application/xml");
            PrintWriter out = response.getWriter();
            System.out.println("PunchoutSetupAction");
            strTimeStamp = getDate();

            strCXML = "<?xml version=" + "\"" + "1.0" + "\"" + " encoding=" + "\"" + "UTF-8" + "\"" + "?>";
            strCXML = strCXML + "<!DOCTYPE cXML SYSTEM " + "\"" + "http://xml.cxml.org/schemas/cXML/1.2.023/cXML.dtd" + "\"" + ">";
            strCXML = strCXML + " <cXML payloadID=" + "\"" + attrval + "\"" + " timestamp=" + "\"" + strTimeStamp + "\"" + ">";
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
        StringBuffer str = null;
        Date d = null;
        try {
            str = new StringBuffer();
            d = new Date();
            DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
            str.append(dateFormat1.format(d));
            str.append("T");
            str.append(dateFormat2.format(d));
            System.out.println("today=" + str.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str.toString();
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request=httpServletRequest;
    }
}
