/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.web.DR;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Administrator
 */
public class DRClickAction extends ActionSupport implements ServletRequestAware{
Logger log=Logger.getLogger(DRClickAction.class);
HttpServletRequest request;
public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }
public String execute()  {
        log.info("inside DRClickAction");
        HttpSession session = request.getSession();
        session.setAttribute("filtertype","1");
        return SUCCESS;
    }
}
