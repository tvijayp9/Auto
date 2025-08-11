/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.web.DR;

import com.nexus.services.DataResolutionService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class DrPartnerAction extends ActionSupport {
    Logger log=Logger.getLogger(DrPartnerAction.class);
    private DataResolutionService dataResolutionService;
    private String loginId;
    private int userId;
    private String moreUsers;
    private String existingUsers;
    ArrayList partners=new ArrayList();
    List assignedPartners=new ArrayList();
    public String execute() throws SQLException {
        ActionContext ac = ActionContext.getContext();
        partners= (ArrayList) ac.getSession().get("partners");
        assignedPartners=(ArrayList) ac.getSession().get("assignedpartners");
        log.info("size.."+assignedPartners.size()+"..existingUsers="+existingUsers);
        dataResolutionService.assignPartnersToUser(loginId, userId, moreUsers, existingUsers,partners,assignedPartners);
        return SUCCESS;
    }

    /**
     * @return the dataResolutionService
     */
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
     * @return the loginId
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * @param loginId the loginId to set
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the moreUsers
     */
    public String getMoreUsers() {
        return moreUsers;
    }

    /**
     * @param moreUsers the moreUsers to set
     */
    public void setMoreUsers(String moreUsers) {
        this.moreUsers = moreUsers;
    }

    /**
     * @return the existingUsers
     */
    public String getExistingUsers() {
        return existingUsers;
    }

    /**
     * @param existingUsers the existingUsers to set
     */
    public void setExistingUsers(String existingUsers) {
        this.existingUsers = existingUsers;
    }
}
