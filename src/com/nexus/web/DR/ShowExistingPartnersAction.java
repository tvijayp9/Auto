/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.web.DR;
import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.domain.JQGridRow;
import com.nexus.domain.Partner;
import com.nexus.web.Constant;
import com.nexus.services.DataResolutionService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import java.io.File;
import org.jdom.xpath.XPath;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
/**
 *
 * @author Administrator
 */
public class ShowExistingPartnersAction extends ActionSupport{

     Logger log=Logger.getLogger(ShowExistingPartnersAction.class);
    private DataResolutionService dataResolutionService;
       Element transInfoNode = null;
    private Document transResDom;
    protected SAXBuilder xmlBuilder;
    private List<JQGridRow> partnerList;
    ArrayList partners=new ArrayList();
    Partner partner;
    ActionContext ac=null;
      private int userId;
    
    public String execute() throws IOException,JDOMException,SQLException{
         ac = ActionContext.getContext();
        String id = (String) ac.getSession().get(Constant.ID);
        log.info("Id in ShowExistingPartnersAction.."+id+"..userId.."+userId);
        getPartners(id);
        return SUCCESS;
    }
    public void getPartners(String id) throws IOException,JDOMException,SQLException {
        int count=0;
        
        partnerList=new ArrayList();
        Element partnerspecificMapping = null;
        xmlBuilder = new SAXBuilder();
        xmlBuilder.setIgnoringElementContentWhitespace(true);
        String folder=getDataResolutionService().getDRStoreFolder();
        log.info("folder in getPartners().."+folder);
        transResDom = xmlBuilder.build(new File(folder));
        transInfoNode = (Element) XPath.newInstance("TRANSACTION[ROOT_NAME = 'Order']").selectSingleNode(transResDom.getRootElement());
        Element partnerspecificMappings = transInfoNode.getChild("SUPPLIER_BUYER_SPECIFIC_MAPPINGS");
        //partnerspecificMapping = (Element) XPath.newInstance("PARTNER_SPECIFIC_MAPPINGS[contains(SUPPLIER_NEXUS_ID, '500000')]").selectSingleNode(partnerspecificMappings);
        partnerspecificMapping = (Element) XPath.newInstance("PARTNER_SPECIFIC_MAPPINGS[contains(SUPPLIER_NEXUS_ID, '"+id+"')]").selectSingleNode(partnerspecificMappings);
        List partnerMapping = (List) partnerspecificMapping.getChildren("PARTNER_MAPPING");
        Iterator i = partnerMapping.iterator();
        while (i.hasNext()) {
            partner=new Partner();
            count++;
            Element element = (Element) i.next();
            String name = element.getChild("PARTNER_INFO").getChild("NAME").getTextTrim();
            String pid= element.getChild("PARTNER_INFO").getChild("ID").getTextTrim();
            JQGridRow row = new JQGridRow();
            row.setId(count);
            List<String> cell = new ArrayList();
            cell.add(pid);
            cell.add(name);
            row.setCell(cell);
            partnerList.add(row);
            partner.setPartnerId(pid);
            partner.setPartnerName(name);
            partners.add(partner);
        }
        log.info("partners size.."+partnerList.size());
        ac.getSession().put("partnerList",getPartnerList());
         ac.getSession().put("partners",partners);
    }

    /**
     * @return the partnerList
     */
    @JSON(name = "rows")
    public List<JQGridRow> getPartnerList() {
        return partnerList;
    }

    /**
     * @param partnerList the partnerList to set
     */
    public void setPartnerList(List<JQGridRow> partnerList) {
        this.partnerList = partnerList;
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
