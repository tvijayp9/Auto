/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.web.DR;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Administrator
 */
public class FilterTransactionsAction extends ActionSupport implements ServletRequestAware{

    Logger log=Logger.getLogger(FilterTransactionsAction.class);
    
    private String type;
    HttpServletRequest request;
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }
    public String execute() throws SQLException {
        //ActionContext ac = ActionContext.getContext();
        HttpSession session = request.getSession();
        log.info("type in FilterTransactionsAction="+getType());
        //ac.getSession().put("filtertype",getType());
        session.setAttribute("filtertype",getType());
        if(getType().equals("2")){
            log.info("type in FilterTransactionsAction value="+getType());
            return INPUT;
        }
        return SUCCESS;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

}
