package com.nexus.struts.action;

import com.nexus.services.ServiceFinder;
import javax.servlet.http.HttpServletRequest;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

public class UserForgetPasswordAction extends ActionSupport implements ServletRequestAware {

    Logger log=Logger.getLogger(UserForgetPasswordAction.class);
    HttpServletRequest request;
    private String UserName = "";
    private String Email = "";

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    public String execute() throws Exception {
        try {
            com.nexus.dao.SpringHibernateDAO springHibernateDAO = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            com.nexus.web.common.SendMail mailBean = (com.nexus.web.common.SendMail) ServiceFinder.getContext(request).getBean(com.nexus.web.common.ProjectConstants.MAIL_BEAN);
            String strUserName = getUsername();
            String strEmail = getEmail();
            log.info("user:" + getUsername()+".email.."+strEmail);
            if ((strUserName.equalsIgnoreCase("")) && (strEmail.equalsIgnoreCase(""))) {
                addActionError("Please provide either username or Email address to get the password.");
                return INPUT;
            } else {
                String[] strPasswordEmail = springHibernateDAO.retriveUserForgetPassword(strUserName, strEmail);
                log.info("email:" + strPasswordEmail[1]);
                //sending mail
                String[] reciepent = {strPasswordEmail[1]};
                String username = strPasswordEmail[2];
                String subject = "Your username & password ";
                String message = "Hi," + username;
                message += "\n Your username is " + username + ".";
                message += "\n Your password is " + strPasswordEmail[0] + ".";
                message += "\n Please login to the web site with your username and password.";
                message += "\n \n Thanks";
                message += "\n \n \n Regards";
                String from = com.nexus.web.common.ProjectConstants.FROM_MAIL;
                mailBean.sendMail(reciepent, subject, message, from);
            }
        } catch (Exception e) {
            addActionError("Invalid input values, Please try again later...");
            return INPUT;
        }
        return SUCCESS;
    }

    public String getUsername() {
        return UserName;
    }

    public void setUsername(String UserName) {
        this.UserName = UserName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
}
