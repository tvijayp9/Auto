/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.umg;

import com.nexus.domain.TemplateOrderItem;
import com.nexus.services.CatalogueService;
import com.nexus.web.Constant;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.nexus.domain.SubTotal;
import com.nexus.services.ServiceFinder;
import com.nexus.web.common.UsersList;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Terry
 */
public class SaveNewQuoteAction extends ActionSupport implements ServletRequestAware {

    Logger log = Logger.getLogger(SaveNewQuoteAction.class);
    private HttpServletRequest request;
    HttpSession session = null;
    private Collection col1;
    private CatalogueService catalogueService;
    private String templateName;
    private String email;
    private String comment;
    private String reciepentname;
    private BigDecimal totalPrice;
    private BigDecimal totalTax;
    private BigDecimal totalCost;
    List<TemplateOrderItem> toi;
    String quotenumber;
    String path;
    String logoname;
    String logopath;
    UsersList userslist;
    private Font catFont = new Font(Font.FontFamily.COURIER, 18,
            Font.BOLD, BaseColor.BLUE);
    private Font subFont = new Font(Font.FontFamily.COURIER, 16,
            Font.BOLD, BaseColor.DARK_GRAY);
    
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    public String execute() throws SQLException, Exception {
        session = request.getSession();
        log.info("SaveNewQuoteAction..");
        String supplierId = (String) session.getAttribute(Constant.SUPID);
        String id = (String) session.getAttribute(Constant.ID);
        logoname = (String) session.getAttribute("logoname");
        com.nexus.dao.SpringHibernateDAO dao = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
        col1 = dao.getMemberDetails(session.getAttribute(Constant.ID).toString());

        col1 = dao.getMemberDetails(session.getAttribute("ID").toString());
        ArrayList mlist = new ArrayList();
        mlist = (ArrayList) col1;
        userslist = new UsersList();
        userslist = (UsersList) mlist.get(0);

        log.info("templateName=" + templateName + "..email=" + email + "...comment=" + comment + "..reciepentname=" + reciepentname + "..logoname=" + logoname);
        toi = (List<TemplateOrderItem>) session.getAttribute("newQuote");
        SubTotal subtotal = catalogueService.getSubtotalforTemplate(toi);
        totalPrice = subtotal.getTotalPrice();
        totalTax = subtotal.getTotalTax();
        totalCost = subtotal.getTotalCost();
        quotenumber = catalogueService.saveNewQuote(templateName, toi, new Integer(id).intValue(), new Integer(supplierId).intValue());
        path = "\\partners\\" + id + "\\temp\\" + quotenumber + ".pdf";

        String realpath = ServletActionContext.getServletContext().getRealPath(path);
        logopath = ServletActionContext.getServletContext().getRealPath("\\images\\" + logoname);
        log.info("path=" + path + "..realpath=" + realpath + "..logopath=" + logopath);
        generatePDF(id, quotenumber, realpath, logopath, userslist);
        sendOrderMail(email, quotenumber, realpath, userslist);
        session.removeAttribute("newQuote");
        return SUCCESS;
    }

    public void generatePDF(String id, String quotenumber, String realpath, String logopath, UsersList usersList) throws Exception {

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(realpath));

        document.open();
        addContent(document, quotenumber, logopath, usersList);

        document.close();
    }

    private static Paragraph addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
        return paragraph;
    }

    private void addContent(Document document, String quotenumber, String logopath, UsersList usersList) throws DocumentException, Exception {
        Image image = Image.getInstance(logopath);
        document.add(image);


        Paragraph subPara = new Paragraph("Quote Details ", catFont);
        document.add(subPara);

        subPara = new Paragraph("Dealer: " + usersList.getCompany(), subFont);
        document.add(subPara);
        subPara = new Paragraph("Quote No: " + quotenumber, subFont);
        document.add(subPara);
        subPara = new Paragraph("Comment: " + comment, subFont);
        document.add(subPara);
        subPara = new Paragraph("Quote Creation Date: " + new Date(), subFont);
        document.add(subPara);

        Paragraph paragraph = new Paragraph();

        document.add(addEmptyLine(paragraph, 2));
        createTable(document);

        Paragraph p1 = new Paragraph("Total Price: $", subFont);
        p1.add(totalPrice.toString());
        Paragraph p2 = new Paragraph("Total Tax:$ ", subFont);
        p2.add(totalTax.toString());
        Paragraph p3 = new Paragraph("Total Cost: $", subFont);
        p3.add(totalCost.toString());
        document.add(p1);
        document.add(p2);
        document.add(p3);
        document.addCreationDate();
        document.getPageNumber();
    }

    private void createTable(Document document) throws BadElementException, Exception {
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);

        table.addCell("Product Item No");
        table.addCell("Product Description");
        table.addCell("Quantity");
        table.addCell("Price");
        table.addCell("Tax");
        table.addCell("Cost");

        for (TemplateOrderItem templateorder : toi) {
            table.addCell(templateorder.getProductCode());
            table.addCell(templateorder.getDescription());
            table.addCell(((Integer) templateorder.getQty()).toString());
            table.addCell(((BigDecimal) templateorder.getPrice()).toString());
            table.addCell(((BigDecimal) templateorder.getTotaltax()).toString());
            table.addCell(((BigDecimal) templateorder.getCost()).toString());

        }
        document.add(table);
    }

    public void sendOrderMail(String email, String quotenumber, String realpath, UsersList usersList) {
        try {
            com.nexus.web.common.SendMail mailBean = (com.nexus.web.common.SendMail) ServiceFinder.getContext(request).getBean(com.nexus.web.common.ProjectConstants.MAIL_BEAN);
            String from = com.nexus.web.common.ProjectConstants.FROM_MAIL;
            String[] reciepent = null;
            String filename = quotenumber + ".pdf";
            String emailmessage = "Mr/Mrs,";
            emailmessage += "\n\n Please find attached quote for your reference. Please send us your approval in order to go ahead with this work.";
            emailmessage += "\n\n Best regards,";
            emailmessage += "\n\n " + usersList.getCompany();
            if(usersList.getPhno()!=null)
                emailmessage += "\n " + usersList.getPhno();
            String emailsubject = "Quatation from " + usersList.getCompany();

            if (email.contains(";")) {
                reciepent = email.split(";");
            }
            else if (email.contains(",")) {
                reciepent = email.split(",");
            }
            else{
               String[] reciepent1 = {email};
               reciepent=reciepent1;
            }

            mailBean.sendAttachedMail(reciepent, emailsubject, emailmessage, from, realpath, filename);
            log.info("order mail sent ");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the catalogueSerivce
     */
    public CatalogueService getCatalogueService() {
        return catalogueService;
    }

    /**
     * @param catalogueSerivce the catalogueSerivce to set
     */
    public void setCatalogueService(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    /**
     * @return the templateName
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * @param templateName the templateName to set
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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

    /**
     * @return the name
     */
    public String getReciepentname() {
        return reciepentname;
    }

    /**
     * @param name the name to set
     */
    public void setReciepentname(String reciepentname) {
        this.reciepentname = reciepentname;
    }
}
