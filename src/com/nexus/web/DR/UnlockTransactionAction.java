/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.web.DR;
import com.nexus.services.DataResolutionService;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import com.googlecode.jsonplugin.annotations.JSON;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class UnlockTransactionAction extends ActionSupport {
    Logger log=Logger.getLogger(UnlockTransactionAction.class);
    private DataResolutionService dataResolutionService;
    private String transId=null;
    Integer noUpdated=0;
    public String execute() throws SQLException {
        //ActionContext ac = ActionContext.getContext();
       // String transId = (String) ac.getSession().get(Constant.ID);
        log.info("transId.."+transId);
        Integer noUpdated = dataResolutionService.unlockTransaction(transId);
        log.info("noUpdated.."+noUpdated);
        if(noUpdated>0){
            addActionMessage("Successfully Unlocked the Transaction");
        }
        else{
            addActionMessage("We couldn't find the Transaction.Please Try again");
        }
       return SUCCESS;
    }

     /**
     * @return the tradingPartnerService
     */
    @JSON(serialize = false)
    public DataResolutionService getDataResolutionService() {
        return dataResolutionService;
    }

    /**
     * @param tradingPartnerService the tradingPartnerService to set
     */
    public void setDataResolutionService(DataResolutionService dataResolutionService) {
        this.dataResolutionService = dataResolutionService;
    }

    /**
     * @return the transId
     */
    public String getTransId() {
        return transId;
    }

    /**
     * @param transId the transId to set
     */
    public void setTransId(String transId) {
        this.transId = transId;
    }

}
