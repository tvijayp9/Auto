/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * @author Vijay Thumma
 */
package com.nexus.struts.action;

import com.nexus.dao.SpringHibernateDAO;
import com.nexus.domain.Partner;
import com.nexus.services.ProductManagementService;
import com.nexus.services.ServiceFinder;
import com.nexus.web.Constant;
import static com.opensymphony.xwork2.Action.ERROR;
import com.opensymphony.xwork2.ActionContext;
import java.io.File;
import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

public class FileUploadAction extends ActionSupport implements ServletRequestAware {

    Logger log = Logger.getLogger(FileUploadAction.class);
    private HttpServletRequest request;
    private File fileUpload;
    private String fileUploadContentType;
    private String fileUploadFileName;
    private ProductManagementService productManagementService;  
    private String partnerId;
    private String errorLoginURL;
    private List<Partner> partnerList = new ArrayList<Partner>();
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    public String getFileUploadContentType() {
        return fileUploadContentType;
    }

    public void setFileUploadContentType(String fileUploadContentType) {
        this.fileUploadContentType = fileUploadContentType;
    }

    public String getFileUploadFileName() {
        return fileUploadFileName;
    }

    public void setFileUploadFileName(String fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }

    public File getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(File fileUpload) {
        this.fileUpload = fileUpload;
    }
    
    public String execute() throws Exception {
        log.info("inside file upload action.." + fileUploadFileName+"..fileUpload.."+fileUpload+"...fileUploadContentType.."+fileUploadContentType+"..partnerId="+partnerId);
        
        try {
            ActionContext ac = ActionContext.getContext();
            setPartnerList((List<Partner>) ac.getSession().get("partners"));
            if(partnerId.equals("0")){
                addActionError("You should select atleast one partner from dropdown");
                this.errorLoginURL="fileupload.jsp";
                return ERROR;
            }
            if(fileUploadFileName == null){
                addActionError("You should select price file");
                this.errorLoginURL="fileupload.jsp";
                return ERROR;
            }
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get("com.opensymphony.xwork2.dispatcher.HttpServletRequest");
            String supplierId = (String) ac.getSession().get(Constant.SUPID);
            SpringHibernateDAO partnerDao = (SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            String productTableName=partnerDao.findProducttable("PRODUCT TABLE NAME",Integer.parseInt(supplierId), Integer.parseInt(partnerId));
            log.info("supplierId.." + supplierId+"..productTableName.."+productTableName+"...partnerId.."+partnerId);
            String outFileName = new StringBuilder().append(System.currentTimeMillis()).append("-").append(fileUploadFileName).toString();
            String uploadFolder = partnerDao.getUploadFolder();
            String filePath = new StringBuilder(uploadFolder).append("upload/").toString();

            log.info("File Location:" + filePath);//see the server console for actual location  
            File fileToCreate = new File(filePath, outFileName);
            FileUtils.copyFile(fileUpload, fileToCreate);//copying source file to new file  
            log.info("Absolute Path Location:" + fileToCreate.getAbsolutePath());
            boolean updatedRecords = getProductManagementService().uploadParts(fileToCreate, filePath, supplierId, productTableName);
            if(updatedRecords){
                addActionMessage("You have successfully uploaded the price file");
            } else{
                addActionError("Upload of price file is failed. Please check the price file and upload again.");
                this.errorLoginURL="fileupload.jsp";
                return ERROR;
            }
            
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public String display() {
        return NONE;
    }

    /**
     * @return the partnerId
     */
    public String getPartnerId() {
        return partnerId;
    }

    /**
     * @param partnerId the partnerId to set
     */
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    /**
     * @return the errorLoginURL
     */
    public String getErrorLoginURL() {
        return errorLoginURL;
    }

    /**
     * @param errorLoginURL the errorLoginURL to set
     */
    public void setErrorLoginURL(String errorLoginURL) {
        this.errorLoginURL = errorLoginURL;
    }

    /**
     * @return the partnerList
     */
    public List<Partner> getPartnerList() {
        return partnerList;
    }

    /**
     * @param partnerList the partnerList to set
     */
    public void setPartnerList(List<Partner> partnerList) {
        this.partnerList = partnerList;
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
    
}