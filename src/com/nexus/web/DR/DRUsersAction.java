/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.web.DR;
import com.nexus.services.DataResolutionService;
import com.nexus.web.Constant;
import com.opensymphony.xwork2.ActionSupport;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
/**
 *
 * @author Administrator
 */
public class DRUsersAction extends ActionSupport implements ServletRequestAware{

     Logger log=Logger.getLogger(ShowAssignedPartnersAction.class);
    HttpServletRequest request;
     private List userList=new LinkedList();
    HttpSession session;
    private DataResolutionService dataResolutionService;
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    public String execute() {
         session = request.getSession();
         String id = (String) session.getAttribute(Constant.ID);
        log.info("Id in UserManagementAction.."+id);
        userList=dataResolutionService.getUsers(new Integer(id).intValue());
        session.setAttribute("userList", userList);
        log.info("size.."+userList.size());
        return SUCCESS;
    }

}
