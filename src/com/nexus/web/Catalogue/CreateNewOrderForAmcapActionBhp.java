/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.Catalogue;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.dao.SpringHibernateDAO;
import com.nexus.domain.Partner;
import com.nexus.domain.ShoppingCartItem;
import com.nexus.services.ProductManagementService;
import com.nexus.services.ServiceFinder;
import com.nexus.services.TradingPartnerService;
import com.nexus.web.Constant;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author user
 */
public class CreateNewOrderForAmcapActionBhp extends ActionSupport {
    private List<String> sitenames;
    private List<String> categoryNames;
    private String sitename;
    private String categoryname;
    private TradingPartnerService tradingPartnerService;
    private ProductManagementService productManagementService;
    public String execute() throws SQLException {
        ActionContext ac = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get("com.opensymphony.xwork2.dispatcher.HttpServletRequest");
        SpringHibernateDAO partnerDao = (SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
        Map session = ac.getSession();
        String buyerId = "";
        String supplierId = (String) ac.getSession().get(Constant.SUPID);
        List<Partner> partnerList = tradingPartnerService.getMyPartnersList(Integer.parseInt(supplierId));
        for(Partner partner: partnerList){
            if(partner.getPartnerName().startsWith("BHP")){
                buyerId = partner.getPartnerId();
                break;
            }
        }
        String productTableName=partnerDao.findProducttable("PRODUCT TABLE NAME",Integer.parseInt(supplierId), Integer.parseInt(buyerId));
        if (session.get("shoppingcart") == null) {
            List<ShoppingCartItem> sci = new ArrayList();
            session.put("shoppingcart", sci);
            
        }
        session.put("PRODUCT_TABLE_NAME", productTableName);
        setSitenames(productManagementService.findSupplierProductsSiteNames(productTableName));
        setCategoryNames(new ArrayList<String>());
        return SUCCESS;
    }
    
    /**
     * @return the sitenames
     */
    //@JSON(serialize = false)
    public List<String> getSitenames() {
        return sitenames;
    }

    /**
     * @param sitenames the sitenames to set
     */
    public void setSitenames(List<String> sitenames) {
        this.sitenames = sitenames;
    }
    
    /**
     * @return the catalogueService
     */
    @JSON(serialize = false)
    public ProductManagementService getProductManagementService() {
        return productManagementService;
    }

    /**
     * @param catalogueService the catalogueService to set
     */
    public void setProductManagementService(ProductManagementService productManagementService) {
        this.productManagementService = productManagementService;
    }
    
    /**
     * @return the tradingPartnerService
     */
    @JSON(serialize = false)
    public TradingPartnerService getTradingPartnerService() {
        return tradingPartnerService;
    }

    /**
     * @param tradingPartnerService the tradingPartnerService to set
     */
    public void setTradingPartnerService(TradingPartnerService tradingPartnerService) {
        this.tradingPartnerService = tradingPartnerService;
    }

    /**
     * @return the categoryNames
     */
    public List<String> getCategoryNames() {
        return categoryNames;
    }

    /**
     * @param categoryNames the categoryNames to set
     */
    public void setCategoryNames(List<String> categoryNames) {
        this.categoryNames = categoryNames;
    }

    /**
     * @return the sitename
     */
    public String getSitename() {
        return sitename;
    }

    /**
     * @param sitename the sitename to set
     */
    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    /**
     * @return the categoryname
     */
    public String getCategoryname() {
        return categoryname;
    }

    /**
     * @param categoryname the categoryname to set
     */
    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }
    
}
