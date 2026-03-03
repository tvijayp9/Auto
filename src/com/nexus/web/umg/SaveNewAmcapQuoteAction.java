/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.umg;

import com.nexus.domain.TemplateOrderItem;
import com.nexus.web.Constant;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import com.nexus.services.ProductManagementService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Vijay Thumma
 */
public class SaveNewAmcapQuoteAction extends ActionSupport implements ServletRequestAware {

    Logger log = Logger.getLogger(SaveNewAmcapQuoteAction.class);
    private HttpServletRequest request;
    HttpSession session = null;
    private ProductManagementService productManagementService;
    private String quoteName;
    private String comment;
    List<TemplateOrderItem> toi;
    String quotenumber;
    
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    public String execute() throws SQLException, Exception {
        session = request.getSession();
        String supplierId = (String) session.getAttribute(Constant.SUPID);
        String id = (String) session.getAttribute(Constant.ID);
        toi = (List<TemplateOrderItem>) session.getAttribute("newQuote");
        log.info("templateName=" + getQuoteName() + "..quote size=" + toi.size());
        quotenumber = productManagementService.saveNewQuote(getQuoteName(), toi, new Integer(id).intValue(), new Integer(supplierId).intValue(), getComment());
        session.removeAttribute("newQuote");
        session.removeAttribute("quoteName");
        session.removeAttribute("comment");
        return SUCCESS;
    }
    
    /**
     * @return the productManagementService
     */
    public ProductManagementService getProductManagementService() {
        return productManagementService;
    }

    /**
     * @param productManagementService the productManagementService to set
     */
    public void setProductManagementService(ProductManagementService productManagementService) {
        this.productManagementService = productManagementService;
    }

    /**
     * @return the quoteName
     */
    public String getQuoteName() {
        return quoteName;
    }

    /**
     * @param quoteName the quoteName to set
     */
    public void setQuoteName(String quoteName) {
        this.quoteName = quoteName;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
}
