package com.nexus.struts.action;

import com.nexus.services.ServiceFinder;
import javax.servlet.http.HttpServletRequest;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

public class UserResetPasswordAction extends ActionSupport implements ServletRequestAware {

    HttpServletRequest request;
    private String currentpass = "";
    private String pass = "";
    private String pass1 = "";

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    public String execute() throws Exception {
        try {
            com.nexus.dao.SpringHibernateDAO springHibernateDAO = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            //String strUserName = getUsername();
            HttpSession session = request.getSession();
            String strUserName = session.getAttribute("userID").toString();
            if (pass.equalsIgnoreCase(pass1)) {
                int result = springHibernateDAO.resetUserForgetPassword(strUserName, pass,currentpass);
                if (result == 0) {
                    addActionError("InCorrect old password ");
                    return ERROR;
                }
            } else {
                addActionError("Password and confirm password does not match");
                return ERROR;
            }
        } catch (Exception e) {
            addActionError(e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String getCurrentpass() {
        return currentpass;
    }

    public void setCurrentpass(String currentpass) {
        this.currentpass = currentpass;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }
}
