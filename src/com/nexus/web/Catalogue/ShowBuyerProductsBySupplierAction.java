/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * @author Vijay Thumma
 */
package com.nexus.web.Catalogue;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.dao.SpringHibernateDAO;
import com.nexus.domain.JQGridRow1;
import com.nexus.domain.Partner;
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
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Terry
 */
public class ShowBuyerProductsBySupplierAction extends ActionSupport {

    Logger log=Logger.getLogger(ShowBuyerProductsBySupplierAction.class);
    private int page;
    private int rows;
    private String sidx;
    private String sord;
    private int total_pages;
    private int count;
    private List<JQGridRow1> result;
    private TradingPartnerService tradingPartnerService;
    private ProductManagementService productManagementService;
    private String searchFor;
    private String searchIn;
    private List<Partner> partnerList = new ArrayList<Partner>();
    private String partnerId;
    private List<String> sitenames;
    private List<String> categoryNames;
    private String sitename;
    private String categoryname;
    private boolean categoriesNeeded;
    public String execute() throws SQLException {
        ActionContext ac = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get("com.opensymphony.xwork2.dispatcher.HttpServletRequest");
        SpringHibernateDAO partnerDao = (SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
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
        log.info("buyerId..."+buyerId+"..supplierId.in.ShowBuyerProductsBySupplierAction."+supplierId+"....product_table_name...."+productTableName+"...searchFor="+searchFor+"...searchIn="+searchIn);
        ac.getSession().put("productTableName", productTableName);
        log.info("sitename..."+sitename+"..categoryname."+categoryname);
        if (sitename != null && categoryname != null) {
            count = productManagementService.findSupplierProductsBySiteCount(sitename, categoryname, productTableName);
            if (count > 0) {
                total_pages = (int) Math.ceil((double) count / rows);
            } else {
                total_pages = 0;
            }
            if (page > total_pages) {
                page = total_pages;
            }
            int start = rows * page - rows;
            if (start >= 0) {
                result = productManagementService.findSupplierProductsBySiteName(sitename, categoryname, start, rows, sidx, sord, productTableName);
            } else {
                result = new ArrayList();
            }
        } else {
            count = productManagementService.findSupplierProductsCount(searchFor, searchIn, productTableName);
            if (count > 0) {
                total_pages = (int) Math.ceil((double) count / rows);
            } else {
                total_pages = 0;
            }
            if (page > total_pages) {
                page = total_pages;
            }
            int start = rows * page - rows;
            if (start >= 0) {
                result = productManagementService.findSupplierProducts(searchFor, searchIn, start, rows, sidx, sord, productTableName);
            } else {
                result = new ArrayList();
            }
        }
        return SUCCESS;
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
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @return the rows
     */
    @JSON(serialize = false)
    public int getRows() {
        return rows;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * @return the sidx
     */
    @JSON(serialize = false)
    public String getSidx() {
        return sidx;
    }

    /**
     * @param sidx the sidx to set
     */
    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    /**
     * @return the sord
     */
    @JSON(serialize = false)
    public String getSord() {
        return sord;
    }

    /**
     * @param sord the sord to set
     */
    public void setSord(String sord) {
        this.sord = sord;
    }

    /**
     * @return the total_pages
     */
    @JSON(name = "total")
    public int getTotal_pages() {
        return total_pages;
    }

    /**
     * @param total_pages the total_pages to set
     */
    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    /**
     * @return the count
     */
    @JSON(name = "records")
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return the result
     */
    @JSON(name = "rows")
    public List<JQGridRow1> getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(List<JQGridRow1> result) {
        this.result = result;
    }

    /**
     * @return the searchFor
     */
    @JSON(serialize = false)
    public String getSearchFor() {
        return searchFor;
    }

    /**
     * @param searchFor the searchFor to set
     */
    public void setSearchFor(String searchFor) {
        this.searchFor = searchFor;
    }

    /**
     * @return the searchIn
     */
    @JSON(serialize = false)
    public String getSearchIn() {
        return searchIn;
    }

    /**
     * @param searchIn the searchIn to set
     */
    public void setSearchIn(String searchIn) {
        this.searchIn = searchIn;
    }

    /**
     * @return the partnerList
     */
    @JSON(serialize = false)
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
     * @return the partnerId
     */
    @JSON(serialize = false)
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
     * @return the sitenames
     */
    @JSON(serialize = false)
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
     * @return the categoryNames
     */
    @JSON(serialize = false)
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
    @JSON(serialize = false)
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
    @JSON(serialize = false)
    public String getCategoryname() {
        return categoryname;
    }

    /**
     * @param categoryname the categoryname to set
     */
    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    /**
     * @return the categoriesNeeded
     */
    public boolean isCategoriesNeeded() {
        return categoriesNeeded;
    }

    /**
     * @param categoriesNeeded the categoriesNeeded to set
     */
    public void setCategoriesNeeded(boolean categoriesNeeded) {
        this.categoriesNeeded = categoriesNeeded;
    }

}
