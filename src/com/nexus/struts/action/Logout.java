package com.nexus.struts.action;

import com.nexus.domain.Microcat;
import com.nexus.services.AccountService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;

public class Logout extends ActionSupport implements ServletRequestAware {

    private HttpServletRequest request;
    private AccountService accountService;
    private String logoutpage;
//    SessionCheckFilter fil = new SessionCheckFilter();

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    public String execute() throws Exception {
       
        try {
            HttpSession session = request.getSession(false);
            Object o = session.getAttribute("microcat");
            if (o != null) {
                Microcat cat = (Microcat) o;
                accountService.updateMicrocatStatus(cat.getId());
            }
            Object oo = session.getAttribute("loginpage");
            if (oo != null) {
                String type = (String) oo;
                logoutpage=type+".jsp";

//                if (type.equals("newtown")) {
//                    navigation = "newtown";
//                } else if (type.equals("kalamunda")) {
//                    navigation = "kalamunda";
//                }else if (type.equals("prosser")) {
//                    navigation = "prosser";
//                }
            } else {
                logoutpage = "sessionTimeout.jsp";
            }
            if (session != null) {
                session.invalidate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "input";
    }

    /**
     * @return the accountService
     */
    public AccountService getAccountService() {
        return accountService;
    }

    /**
     * @param accountService the accountService to set
     */
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * @return the logoutpage
     */
    public String getLogoutpage() {
        return logoutpage;
    }

    /**
     * @param logoutpage the logoutpage to set
     */
    public void setLogoutpage(String logoutpage) {
        this.logoutpage = logoutpage;
    }
}

