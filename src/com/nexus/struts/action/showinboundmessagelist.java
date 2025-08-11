package com.nexus.struts.action;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import com.opensymphony.xwork2.ActionSupport;

import com.nexus.services.ServiceFinder;
import org.apache.log4j.Logger;


public class showinboundmessagelist extends ActionSupport
{
    Logger log=Logger.getLogger(showinboundmessagelist.class);
   
    HttpServletRequest request=null;
        public String execute() throws Exception {
    try{
        com.nexus.dao.SpringHibernateDAO user = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
        //latest messages
        Collection col = user.getUsersList();
        request.setAttribute("inboundmessagelist",col);
        log.info("col size:"+col.size());
    }catch(Exception e){
        addActionError("Invalid user name or password! Please try again!");
        return ERROR;
    }
    return SUCCESS;
}    
}
