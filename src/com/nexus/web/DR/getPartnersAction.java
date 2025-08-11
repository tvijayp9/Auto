/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.web.DR;
import com.nexus.services.DataResolutionService;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.domain.JQGridRow;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
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
public class getPartnersAction extends ActionSupport implements ServletRequestAware{
    Logger log=Logger.getLogger(UserManagementAction.class);
     Element transInfoNode = null;
    private Document transResDom;
    protected SAXBuilder xmlBuilder;
    HttpServletRequest request;
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }
    private DataResolutionService dataResolutionService;
    private String id=null;
    private List<JQGridRow> partnerList=new ArrayList();
    public String execute() throws IOException,JDOMException,SQLException {
        HttpSession session = request.getSession();
        id=session.getId();
        log.info("Id in getPartnersAction.."+session.getId());
        getPartners(id);
        return SUCCESS;
    }

    public void getPartners(String id)throws IOException,JDOMException,SQLException {
        int count=0;
        Element partnerspecificMapping = null;
        xmlBuilder = new SAXBuilder();
        xmlBuilder.setIgnoringElementContentWhitespace(true);
        String folder=dataResolutionService.getDRStoreFolder();
        System.out.println("folder in getPartners().."+folder);
        transResDom = xmlBuilder.build(new File(folder));
        transInfoNode = (Element) XPath.newInstance("TRANSACTION[ROOT_NAME = 'Order']").selectSingleNode(transResDom.getRootElement());
        Element partnerspecificMappings = transInfoNode.getChild("SUPPLIER_BUYER_SPECIFIC_MAPPINGS");
        //partnerspecificMapping = (Element) XPath.newInstance("PARTNER_SPECIFIC_MAPPINGS[contains(SUPPLIER_NEXUS_ID, '500000')]").selectSingleNode(partnerspecificMappings);
        partnerspecificMapping = (Element) XPath.newInstance("PARTNER_SPECIFIC_MAPPINGS[contains(SUPPLIER_NEXUS_ID, '"+id+"')]").selectSingleNode(partnerspecificMappings);
        List partnerMapping = (List) partnerspecificMapping.getChildren("PARTNER_MAPPING");
        Iterator i = partnerMapping.iterator();
        while (i.hasNext()) {
            count++;
            Element element = (Element) i.next();
            String name = element.getChild("PARTNER_INFO").getChild("NAME").getTextTrim();
            JQGridRow row = new JQGridRow();
                row.setId(count);
                List<String> cell = new ArrayList();
                cell.add(name);
                row.setCell(cell);
                partnerList.add(row);
        }
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
     * @return the partnerList
     */
     @JSON(name = "rows")
    public List<JQGridRow> getPartnerList() {
        return partnerList;
    }

    /**
     * @param partnerList the partnerList to set
     */
    public void setPartnerList(List partnerList) {
        this.partnerList = partnerList;
    }

}
