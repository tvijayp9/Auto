/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.web.DR;

import com.nexus.services.DataResolutionService;
import com.nexus.util.DR.commonsource.BableEngine;
import com.nexus.util.DR.commonsource.XMLFunctions;
import com.nexus.util.DR.commonsource.XSLTFunctions;
import com.nexus.util.DR.misc.ConfigValuesList;
import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.zip.Inflater;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.log4j.Logger;
import org.jdom.input.DOMBuilder;
import org.jdom.transform.JDOMSource;
import org.w3c.dom.Document;
/**
 *
 * @author user
 */
public class ProcessedPrintTransactionAction extends ActionSupport{
 Logger log=Logger.getLogger(ProcessedPrintTransactionAction.class);
    private DataResolutionService dataResolutionService;
    private String ptid;
    private XMLFunctions xmlInterface = null;
    private ConfigValuesList dirList = null;
     private String printDir;

    public String execute() throws SQLException {
        //ActionContext ac = ActionContext.getContext();
         log.info("ptid.."+getPtid());
        try {
            
            getDocument(getPtid());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return SUCCESS;
    }

     public void getDocument(String transId) throws Exception {
        System.out.println("getDocument..transId="+transId);
        Document ret = null;
        HashMap hm=null;
       try
            {
           xmlInterface = new XMLFunctions();
           hm=dataResolutionService.getTransactionId(Integer.parseInt(transId));
        byte[] compressedData = dataResolutionService.getTransactionDocument(hm.get("transid").toString());
         System.out.println("compressedData..length="+compressedData.length);
        byte[] docData = this.decompressByteArray(compressedData);
        //Document ret = readFile(docData);
        //ret = db.parse(new ByteArrayInputStream(docData));
         ret = xmlInterface.readFile(docData);
        createPrintFile(ret,hm.get("transtype").toString(),hm.get("transnumber").toString());
        }
            catch(Exception e)
            {
                e.printStackTrace();
            }
       // return ret;
    }

     public byte[] decompressByteArray(byte[] data) throws Exception {
        Inflater decompressor = new Inflater();
        System.out.println("data length..."+data.length);
        decompressor.setInput(data);
        ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
        byte[] buf = new byte[1024];
        while (!decompressor.finished()) {
            int count = decompressor.inflate(buf);
            bos.write(buf, 0, count);
        }
        try {
            bos.close();
        } catch (IOException io) {
        }
        return bos.toByteArray();
    }

    public void createPrintFile(Document document,String documentType,String filename) {
        try {

            //converting org.w3c.Document to org.jdom.Document    Vijay   06/05/2010
            DOMBuilder builder = new DOMBuilder();
            org.jdom.Document jdomDoc = builder.build(document);

            String stylesheetPath = "";
            printDir=dataResolutionService.getTradeRouteFolderPath("PrintFolder");
            System.out.println("printDir==="+printDir);
            
            if (documentType.equalsIgnoreCase("Order")) {
                System.out.println("documentType" + documentType);
                /** find out which partner this file is from */
               //stylesheetPath =System.getProperty("catalina.base")+ "\\webapps\\Auto\\xsl\\print_po.xsl";
                 stylesheetPath =dataResolutionService.getTradeRouteFolderPath("xsl_po_print_path");
               
            } else {
               // stylesheetPath = System.getProperty("catalina.base")+ "\\webapps\\Auto\\xsl\\print_co.xsl";
                 stylesheetPath =dataResolutionService.getTradeRouteFolderPath("xsl_co_print_path");
            }
             log.info("Using Generic Print StyleSheet: " + stylesheetPath+"...tempDir=="+printDir);

            if (stylesheetPath.equals("")) {
                throw new Exception("Could not find print stylesheet config property for ");
            }
            JDOMSource source = new JDOMSource(jdomDoc);
            Transformer transformer = TransformerFactory.newInstance().newTransformer(new StreamSource(new File(stylesheetPath)));
            //File resultTempFile = new File(System.getProperty("catalina.base")+"\\webapps\\Auto\\temp\\print_" + filename + ".htm");
             File resultTempFile = new File(printDir+"/print_" + filename + ".htm");
            System.out.println("2resultfile get absolutepath.." + resultTempFile.getAbsolutePath() + "..path.." + resultTempFile.getPath());
            FileOutputStream fout = new FileOutputStream(resultTempFile.getAbsolutePath());
            StreamResult result = new StreamResult(fout);
            transformer.transform(source, result);
            fout.flush();
            fout.close();
            // String url="http://203.206.178.177:8080/Auto/temp/print_20130417041414-3.htm";
            //Desktop.getDesktop().open(resultTempFile.getAbsoluteFile());
            //Desktop.getDesktop().browse(new URI(url));
            //if ((new File(resultTempFile.getAbsolutePath()).exists())) {
            //Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+url);
			//p.waitFor();
    		//}
//            else {
//            System.out.println("File is not exists.."+resultTempFile.getAbsolutePath());
//            }
        } catch (Exception e) {
            e.printStackTrace();
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

    /**
     * @return the ptid
     */
    public String getPtid() {
        return ptid;
    }

    /**
     * @param ptid the ptid to set
     */
    public void setPtid(String ptid) {
        this.ptid = ptid;
    }

    

  
  
}
