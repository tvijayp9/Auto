/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.web.DR;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.domain.JQGridRow;
import com.nexus.services.DataResolutionService;
import com.nexus.web.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class ShowAssignedPartnersAction extends ActionSupport {

     Logger log=Logger.getLogger(ShowAssignedPartnersAction.class);
    private DataResolutionService dataResolutionService;
    private List<JQGridRow> result;
      private int userId;
     ArrayList userresult=new ArrayList();
    public String execute() {
         //session = request.getSession();
        ActionContext ac = ActionContext.getContext();
        String id = (String) ac.getSession().get(Constant.ID);
        // String id = (String) session.getAttribute(Constant.ID);
         String userloginid = (String) ac.getSession().get("userLoginId");
        log.info("Id in ShowAssignedPartnersAction.."+id+"..userloginid="+userloginid+"..userId="+userId);
        result=getDataResolutionService().getAssignedPartners(new Integer(userId));
        log.info("before iteration.."+result.size());
         ac.getSession().put("assignedpartners",result);
//        for(int i=0;i<=result.size();i++){
//            JQGridRow jQGridRow=(JQGridRow)result.get(i);
//            log.info("jQGridRow Id.."+jQGridRow.getId());
//            //userresult=(ArrayList)jQGridRow.getCell();
//            //log.info("assigned userId.."+userresult.get(0).toString()+"..name.."+userresult.get(1).toString());
//        }
        
        return SUCCESS;
    }

    /**
     * @return the result
     */
    @JSON(name = "rows")
    public List<JQGridRow> getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(List<JQGridRow> result) {
        this.result = result;
    }

    /**
     * @return the dataResolutionService
     */
      @JSON(serialize = false)
    public DataResolutionService getDataResolutionService() {
        return dataResolutionService;
    }

    /**
     * @param dataResolutionService the dataResolutionService to set
     */
    public void setDataResolutionService(DataResolutionService dataResolutionService) {
        this.dataResolutionService = dataResolutionService;
    }

    /**
     * @return the userId
     */
     @JSON(serialize = false)
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }


}
