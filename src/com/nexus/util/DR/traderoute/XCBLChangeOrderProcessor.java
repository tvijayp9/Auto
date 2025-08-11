/*
 * XCBLChangeOrderProcessor.java
 *
 * Created on 12 October 2004, 11:03
 */

package com.nexus.util.DR.traderoute;

import com.nexus.services.DataResolutionService;
import com.nexus.services.DataResolutionServiceImpl;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.xpath.XPath;


/**
 *
 * @author  user
 */
public class XCBLChangeOrderProcessor
{
  // private static DbCommunicatorImpl dbCommunicatorImpl = null;
    
    private DataResolutionService dataResolutionService=new DataResolutionServiceImpl();
    /** Creates a new instance of XCBLChangeOrderProcessor */
    public XCBLChangeOrderProcessor() throws Exception
    {
        //dbCommunicatorImpl=DbCommunicatorImpl.getInstance("C:\\TradeRoute\\config_files\\edxconfig.xml");
    }
    
    public void postProcess(Document doc) throws Exception
    {       
        String transId = doc.getRootElement().getChild("DOCDATA").getChild("TRADEROUTEVALUES").getChild("TRANSACTION_ID").getTextTrim();
        String alternateTransNumber = doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("ChangeOrder").getChild("ChangeOrderHeader").getChild("ChangeOrderNumber").getChild("SellerChangeOrderNumber").getChild("EDXBABLE").getChild("ORIGINATOR").getChild("VALUE").getTextTrim();
        
//        dataResolutionService.updateAlternateTransactionNumber(transId, alternateTransNumber);
    }
    
    public void accept(Document doc,String invoiceNo) throws Exception
    {
        String transId = doc.getRootElement().getChild("DOCDATA").getChild("TRADEROUTEVALUES").getChild("TRANSACTION_ID").getTextTrim();
        doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("ChangeOrder").getChild("ChangeOrderHeader").getChild("TempItemCoded").getChild("EDXBABLE").getChild("ORIGINATOR").getChild("VALUE").setText("Accepted");
        if(invoiceNo!="")
             doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("ChangeOrder").getChild("ChangeOrderHeader").getChild("ChangeOrderNumber").getChild("SellerChangeOrderNumber").getChild("EDXBABLE").getChild("ORIGINATOR").getChild("VALUE").setText(invoiceNo);

        /** set all line items to Accepted status */
        List lineItems = XPath.newInstance("//EDXDATA/DOCDATA/BUSOBJ/ChangeOrder/ChangeOrderDetail/ListOfChangeOrderItemDetail/ChangeOrderItemDetail/ItemDetailChanges/ItemDetail").selectNodes(doc.getRootElement());
        Iterator it = lineItems.iterator();
        
        while(it.hasNext())
        {
            Element currLineItem = (Element)it.next();
            currLineItem.getChild("TempItemCoded").getChild("EDXBABLE").getChild("ORIGINATOR").getChild("VALUE").setText("ItemAccepted");
        }
        dataResolutionService.updateAlternateTransactionNumber(transId, invoiceNo);
    }
    
    public void reject(Document doc,String reason) throws Exception
    {
        doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("ChangeOrder").getChild("ChangeOrderHeader").getChild("TempItemCoded").getChild("EDXBABLE").getChild("ORIGINATOR").getChild("VALUE").setText("NotAccepted");
        if(reason!="")
            doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("ChangeOrder").getChild("ChangeOrderHeader").getChild("TempOrderResponseHeaderNote").getChild("EDXBABLE").getChild("ORIGINATOR").getChild("VALUE").setText(reason);
        /** set all line items to Accepted status */
        List lineItems = XPath.newInstance("//EDXDATA/DOCDATA/BUSOBJ/ChangeOrder/ChangeOrderDetail/ListOfChangeOrderItemDetail/ChangeOrderItemDetail/ItemDetailChanges/ItemDetail").selectNodes(doc.getRootElement());
        Iterator it = lineItems.iterator();
        
        while(it.hasNext())
        {
            Element currLineItem = (Element)it.next();
            currLineItem.getChild("TempItemCoded").getChild("EDXBABLE").getChild("ORIGINATOR").getChild("VALUE").setText("ItemRejected");
        }  
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
    
}
