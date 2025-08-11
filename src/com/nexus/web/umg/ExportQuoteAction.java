/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.umg;

import com.nexus.domain.Quote;
import com.nexus.domain.TemplateOrderItem;
import com.nexus.services.CatalogueService;
import com.nexus.web.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Terry
 */
public class ExportQuoteAction extends ActionSupport {

    Logger log=Logger.getLogger(ExportQuoteAction.class);
    private CatalogueService catalogueService;
    private InputStream inputStream;
    private String downloadName;
    private int qid;
    private String path;

    public String execute() throws SQLException, IOException {
        ActionContext ac = ActionContext.getContext();
        String supplierId = (String) ac.getSession().get(Constant.SUPID);
        Quote quote = catalogueService.getQuoteDetailsByQid(qid);
        List<TemplateOrderItem> items = quote.getItems();
        downloadName = quote.getQrn() + ".csv";

        path = "\\partners\\" + supplierId + "\\temp\\" + downloadName;
        log.info("supplierId.."+supplierId+"..downloadName.."+downloadName+"..path.."+path);
        String realpath = ServletActionContext.getServletContext().getRealPath(path);
        File file = new File(realpath);
        if (!file.exists()) {
            file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write("QuoteReferenceNumber,QuoteName,CreateDate,Qty,ProductCode,ProductDescription,UnitPrice,Units,Tax,Status\n");
            for (TemplateOrderItem item : items) {
                String description = item.getDescription();
                String[] array = description.split("\\,");
                String finalDes = "";
                if (array.length > 0) {
                    for (String word : array) {
                        finalDes =finalDes+word.trim() + " ";
                    }
                } else {
                    finalDes = description;
                }
                bw.write(quote.getQrn() + "," + quote.getQname() + "," + quote.getCreateDate() + "," + item.getQty() + "," + item.getProductCode() + "," + finalDes + "," + item.getUnitPrice() + "," + item.getUnits() + "," + item.getTax() + "," + item.getStatus() + "\n");
            }
            bw.close();
        }
        return SUCCESS;
    }

    /**
     * @return the catalogueService
     */
    public CatalogueService getCatalogueService() {
        return catalogueService;
    }

    /**
     * @param catalogueService the catalogueService to set
     */
    public void setCatalogueService(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    /**
     * @return the inputStream
     */
    public InputStream getInputStream() {
        return ServletActionContext.getServletContext().getResourceAsStream(path);
    }

    /**
     * @return the downloadName
     */
    public String getDownloadName() {
        return downloadName;
    }

    /**
     * @param downloadName the downloadName to set
     */
    public void setDownloadName(String downloadName) {
        this.downloadName = downloadName;
    }

    /**
     * @return the qid
     */
    public int getQid() {
        return qid;
    }

    /**
     * @param qid the qid to set
     */
    public void setQid(int qid) {
        this.qid = qid;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }
}
