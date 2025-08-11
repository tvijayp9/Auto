/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import com.nexus.domain.JQGridRow;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.nexus.dao.hibernate.HibernateUtil;
import com.nexus.domain.Role;
import com.nexus.domain.User;
import com.nexus.util.DR.accessdbcomm.QueryResult;
import com.nexus.util.DR.traderoute.EdxBableBean;
import com.nexus.util.DR.traderoute.TransactionBean;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.zip.Deflater;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 *
 * @author Sunil
 */
public class DataResolutionDAOImpl implements DataResolutionDAO {

    Logger log=Logger.getLogger(DataResolutionDAOImpl.class);
    private SessionFactory sessionFactory;
    private BASE64Encoder _base64enc = null;
    private BASE64Decoder _base64dec = null;
    Session session;
    Connection conn;
    
    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public int findMyTransactionsListCount(String id,String userLoginId,String isSupplier, String search, String transactionNumber,String type) throws SQLException {
        //Session session = sessionFactory.getCurrentSession();
        //Connection connection = session.connection();
        int count=0;
        ResultSet rs = null;
        String transactionState="user";
        String selectStatement=null;
        try{
           //if(session.equals(null))
                session = HibernateUtil.getSessionFactory().openSession();
                conn = session.connection();
            
        PreparedStatement ps = null;

        if(type.equals("2"))
            transactionState="processed";

        log.info("transactionState findMyTransactionsListCount= "+transactionState+"..type="+type+"...userLoginId="+userLoginId+"...isSupplier="+isSupplier+"...transactionNumber="+transactionNumber);
        if(isSupplier.equalsIgnoreCase("true"))
            //selectStatement ="SELECT count(*) FROM TR_Transactions INNER JOIN TR_TransactionsExt ON TR_Transactions.TransactionId LIKE TR_TransactionsExt.TransactionId where TR_Transactions.TransactionState LIKE ? AND (TR_Transactions.Hidden NOT LIKE 'true') AND (TR_Transactions.TransactionType = 'Order' OR TR_Transactions.TransactionType = 'ChangeOrder') and sup_nexus_id = ?";
              selectStatement ="SELECT count(*) FROM TR_Transactions tr,TR_TransactionsExt tre WHERE tr.TransactionId = tre.TransactionId and tr.TransactionState = ? AND (tr.Hidden != 'true') AND (tr.TransactionType = 'Order' OR tr.TransactionType = 'ChangeOrder') AND tr.sup_nexus_id = ?";
        else
            //selectStatement ="SELECT  count(DISTINCT Transactionkey) as count FROM TR_Transactions tr, TR_TransactionsExt tre, tr_user_management um WHERE tr.TransactionId LIKE tre.TransactionId AND tr.TransactionState LIKE ? AND(tr.Hidden NOT LIKE 'true') AND(tr.TransactionType = 'Order' OR tr.TransactionType = 'ChangeOrder') AND sup_nexus_id = ? AND um.userId = "+new Integer(userLoginId).intValue()+" AND um.partner_name = tr.PartnerName";
            selectStatement ="SELECT  count(DISTINCT tr.Transactionkey) as count FROM TR_Transactions tr, TR_TransactionsExt tre, tr_user_management um WHERE tr.TransactionId = tre.TransactionId AND tr.TransactionState = ? AND (tr.Hidden != 'true') AND (tr.TransactionType = 'Order' OR tr.TransactionType = 'ChangeOrder') AND tr.sup_nexus_id = ? AND um.userId = "+new Integer(userLoginId).intValue()+"  AND um.partner_name = tr.PartnerName";

        //String selectStatement = "SELECT COUNT(distinct registration.ID) FROM registration,xy_partner_link,us_country where  registration.COUNTRY = us_country.N_COUNTRY_ID and (xy_partner_link.Buyer_Id=registration.id or xy_partner_link.Supplier_Id=registration.id) and (xy_partner_link.Buyer_Id = ? or xy_partner_link.Supplier_Id = ?) and (registration.id != ?)";
        if (search.equals("true")) {
            selectStatement += " and tr.TransactionNumber like ?";
        }
        ps = conn.prepareStatement(selectStatement);
        ps.setString(1, transactionState);
        ps.setInt(2, new Integer(id).intValue());
       // ps.setInt(3, new Integer(userLoginId).intValue());
        log.info("selectStatement = "+selectStatement);
        if (search.equals("true")) {
            ps.setString(3, "%" + transactionNumber + "%");
        }
        rs = ps.executeQuery();
        rs.next();
        count= rs.getInt(1);
        }finally{
            rs.close();
            conn.close();
           // //session.close();
        }
        return count;
    }

    public List findMyTransactionsList(String id,String userLoginId,String isSupplier,String search, String transactionNumber, int start, int limit, String sidx, String sord,String type) throws SQLException {
       // Session session = sessionFactory.getCurrentSession();
        //Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
         List<JQGridRow> rows = new ArrayList();
         String transactionState="user";
         String selectStatement=null;
        try{
               session = HibernateUtil.getSessionFactory().openSession();
               conn = session.connection();
                if(type.equals("2"))
                    transactionState="processed";
            log.info("transactionState findMyTransactionsList= "+transactionState+"...type="+type+"..isSupplier="+isSupplier+"...sidx="+sidx+"..sord="+sord);
         
//        SELECT TR_Transactions.TransactionId,transactionnumber,partnername,TransactionType,processdate,userlist,openstate FROM TR_Transactions INNER JOIN TR_TransactionsExt ON TR_Transactions.TransactionId LIKE
//TR_TransactionsExt.TransactionId where TR_Transactions.TransactionState LIKE 'user' AND
//(TR_Transactions.Hidden NOT LIKE 'true') AND (TR_Transactions.TransactionType = 'Order' OR
//TR_Transactions.TransactionType = 'ChangeOrder') and sup_nexus_id = 22
        if(isSupplier.equalsIgnoreCase("true"))
            //selectStatement ="SELECT Transactionkey,TR_Transactions.TransactionId,transactionnumber,partnername,TransactionType,processdate,userlist,openstate FROM TR_Transactions INNER JOIN TR_TransactionsExt ON TR_Transactions.TransactionId LIKE TR_TransactionsExt.TransactionId where TR_Transactions.TransactionState LIKE ? AND (TR_Transactions.Hidden NOT LIKE 'true') AND (TR_Transactions.TransactionType = 'Order' OR TR_Transactions.TransactionType = 'ChangeOrder') and sup_nexus_id = ?";
            //selectStatement ="SELECT Transactionkey,TR_Transactions.TransactionId,transactionnumber,partnername,TransactionType,processdate,TransResponseDate,TotalTransactionAmount,TransAckComment,openstate FROM TR_Transactions INNER JOIN TR_TransactionsExt ON TR_Transactions.TransactionId LIKE TR_TransactionsExt.TransactionId where TR_Transactions.TransactionState LIKE ? AND (TR_Transactions.Hidden NOT LIKE 'true') AND (TR_Transactions.TransactionType = 'Order' OR TR_Transactions.TransactionType = 'ChangeOrder') and sup_nexus_id = ?";
            selectStatement ="SELECT tr.Transactionkey,tr.TransactionId,tr.transactionnumber,tr.partnername,tr.TransactionType,tr.processdate,tr.TransResponseDate,tre.TotalTransactionAmount,tr.TransAckComment,tre.openstate FROM TR_Transactions tr,TR_TransactionsExt tre where tr.TransactionId = tre.TransactionId and tr.TransactionState = ? AND (tr.Hidden != 'true') AND (tr.TransactionType = 'Order' OR tr.TransactionType = 'ChangeOrder') and tr.sup_nexus_id = ?";
        else
            //selectStatement ="SELECT DISTINCT Transactionkey,tr.TransactionId,transactionnumber,partnername,TransactionType,processdate,userlist,openstate FROM TR_Transactions tr,TR_TransactionsExt tre,tr_user_management um where tr.TransactionId LIKE tre.TransactionId and tr.TransactionState LIKE ? AND (tr.Hidden NOT LIKE 'true') AND (tr.TransactionType = 'Order' OR tr.TransactionType = 'ChangeOrder') and sup_nexus_id = ? AND um.userId="+new Integer(userLoginId).intValue()+" and um.partner_name=tr.PartnerName";
           // selectStatement ="SELECT DISTINCT Transactionkey,tr.TransactionId,transactionnumber,partnername,TransactionType,processdate,TransResponseDate,TotalTransactionAmount,TransAckComment,openstate FROM TR_Transactions tr,TR_TransactionsExt tre,tr_user_management um where tr.TransactionId LIKE tre.TransactionId and tr.TransactionState LIKE ? AND (tr.Hidden NOT LIKE 'true') AND (tr.TransactionType = 'Order' OR tr.TransactionType = 'ChangeOrder') and sup_nexus_id = ? AND um.userId="+new Integer(userLoginId).intValue()+" and um.partner_name=tr.PartnerName";
             selectStatement ="SELECT DISTINCT tr.Transactionkey,tr.TransactionId,tr.transactionnumber,tr.partnername,tr.TransactionType,tr.processdate,tr.TransResponseDate,tre.TotalTransactionAmount,tr.TransAckComment,tre.openstate FROM TR_Transactions tr,TR_TransactionsExt tre,tr_user_management um where tr.TransactionId = tre.TransactionId and tr.TransactionState = ? AND (tr.Hidden != 'true') AND (tr.TransactionType = 'Order' OR tr.TransactionType = 'ChangeOrder') and tr.sup_nexus_id = ? AND um.userId="+new Integer(userLoginId).intValue()+" and um.partner_name=tr.PartnerName";
        //String selectStatement = "SELECT distinct(registration.ID),registration.COMPANY,registration.EMAIL,registration.PHNO,registration.STATE,registration.COMPANY_URL,us_country.vch_country_name FROM registration,xy_partner_link,us_country where  registration.COUNTRY = us_country.N_COUNTRY_ID and (xy_partner_link.Buyer_Id=registration.id or xy_partner_link.Supplier_Id=registration.id) and (xy_partner_link.Buyer_Id = ? or xy_partner_link.Supplier_Id = ?) and (registration.id != ?)";
        if (search.equals("true")) {
            selectStatement += " and tr.TransactionNumber like ?";
        }
       // selectStatement += " order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;
          selectStatement += " order by tr.processdate DESC LIMIT " + start + "," + limit;
        ps = conn.prepareStatement(selectStatement);
        ps.setString(1, transactionState);
        ps.setInt(2, new Integer(id).intValue());
        //ps.setInt(3, new Integer(userLoginId).intValue());
        if (search.equals("true")) {
            ps.setString(3, "%" + transactionNumber + "%");
        }
        log.info("selectStatement = "+selectStatement);
        rs = ps.executeQuery();
        while (rs.next()) {
            JQGridRow row = new JQGridRow();
            row.setId(rs.getInt("Transactionkey"));
            List<String> cell = new ArrayList();

            cell.add(rs.getString("openstate"));
            //cell.add(rs.getString("userlist"));
            cell.add(rs.getString("transactionnumber"));
            cell.add(rs.getString("partnername"));
            cell.add(rs.getString("TransactionType"));
            cell.add(rs.getString("processdate"));
            cell.add(rs.getString("TransResponseDate"));
            cell.add(rs.getString("TotalTransactionAmount"));
            cell.add(rs.getString("TransAckComment"));
            row.setCell(cell);
            rows.add(row);
        }
        }
        finally{
            rs.close();
            conn.close();
           // //session.close();
        }
        return rows;
    }

    public List findProcessedTransactionsList(String id, String userLoginId, String isSupplier, String search, String transactionNumber, int start, int limit, String sidx, String sord) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<JQGridRow> rows = new ArrayList();
        String selectStatement = null;
        String AlternateTransactionNumber=null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            conn = session.connection();

            log.info("transactionState findProcessedTransactionsList...isSupplier=" + isSupplier + "...sidx=" + sidx + "..sord=" + sord);


            if (isSupplier.equalsIgnoreCase("true")) {
                //selectStatement = "SELECT Transactionkey,ResolvedState,transactionnumber,partnername,TransactionType,processdate,TransResponseDate,TotalTransactionAmount,TransAckComment,TransResponseComment,AlternateTransactionNumber FROM TR_Transactions INNER JOIN TR_TransactionsExt ON TR_Transactions.TransactionId LIKE TR_TransactionsExt.TransactionId where TR_Transactions.TransactionState LIKE 'processed' AND (TR_Transactions.Hidden NOT LIKE 'true') AND (TR_Transactions.TransactionType = 'Order' OR TR_Transactions.TransactionType = 'ChangeOrder') and sup_nexus_id = ?";
                selectStatement = "SELECT tr.Transactionkey,tre.ResolvedState,tr.transactionnumber,tr.partnername,tr.TransactionType,tr.processdate,tr.TransResponseDate,tre.TotalTransactionAmount,tr.TransAckComment,tr.TransResponseComment,tr.AlternateTransactionNumber FROM TR_Transactions tr,TR_TransactionsExt tre where tr.TransactionId = tre.TransactionId and tr.TransactionState = 'processed' AND (tr.Hidden != 'true') AND (tr.TransactionType = 'Order' OR tr.TransactionType = 'ChangeOrder') and tr.sup_nexus_id = ?";
            } else {
                //selectStatement = "SELECT DISTINCT Transactionkey,ResolvedState,transactionnumber,partnername,TransactionType,processdate,TransResponseDate,TotalTransactionAmount,TransAckComment,TransResponseComment,AlternateTransactionNumber FROM TR_Transactions tr,TR_TransactionsExt tre,tr_user_management um where tr.TransactionId LIKE tre.TransactionId and tr.TransactionState LIKE 'processed' AND (tr.Hidden NOT LIKE 'true') AND (tr.TransactionType = 'Order' OR tr.TransactionType = 'ChangeOrder') and sup_nexus_id = ? AND um.userId=" + new Integer(userLoginId).intValue() + " and um.partner_name=tr.PartnerName";
                selectStatement = "SELECT DISTINCT tr.Transactionkey,tre.ResolvedState,tr.transactionnumber,tr.partnername,tr.TransactionType,tr.processdate,tr.TransResponseDate,tre.TotalTransactionAmount,tr.TransAckComment,tr.TransResponseComment,tr.AlternateTransactionNumber FROM TR_Transactions tr,TR_TransactionsExt tre,tr_user_management um where tr.TransactionId = tre.TransactionId and tr.TransactionState = 'processed' AND (tr.Hidden != 'true') AND (tr.TransactionType = 'Order' OR tr.TransactionType = 'ChangeOrder') and tr.sup_nexus_id = ? AND um.userId=" + new Integer(userLoginId).intValue() + " and um.partner_name=tr.PartnerName";
            }
            if (search.equals("true")) {
                selectStatement += " and tr.TransactionNumber like ?";
            }
            selectStatement += " order by tr.processdate DESC LIMIT " + start + "," + limit;
            ps = conn.prepareStatement(selectStatement);
            ps.setInt(1, new Integer(id).intValue());
            if (search.equals("true")) {
                ps.setString(2, "%" + transactionNumber + "%");
            }
            log.info("selectStatement = " + selectStatement);
            rs = ps.executeQuery();
            while (rs.next()) {
                JQGridRow row = new JQGridRow();
                row.setId(rs.getInt("Transactionkey"));
                List<String> cell = new ArrayList();
                cell.add(rs.getString("ResolvedState"));
                cell.add(rs.getString("transactionnumber"));
                cell.add(rs.getString("partnername"));
                cell.add(rs.getString("TransactionType"));
                cell.add(rs.getString("processdate"));
                cell.add(rs.getString("TransResponseDate"));
                cell.add(rs.getString("TotalTransactionAmount"));
                cell.add(rs.getString("TransAckComment"));
                if(rs.getString("ResolvedState").equalsIgnoreCase("accepted")){
                    AlternateTransactionNumber=rs.getString("AlternateTransactionNumber");
                }else{
                    AlternateTransactionNumber=rs.getString("TransResponseComment");
                }
                cell.add(AlternateTransactionNumber);
                row.setCell(cell);
                rows.add(row);
            }
        } finally {
            rs.close();
            conn.close();
           // //session.close();
        }
        return rows;
    }

    

    public Integer unlockTransaction(String transId) throws SQLException {
        String query=null;
        session = HibernateUtil.getSessionFactory().openSession();
        conn = session.connection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        int noUpdated=0;
        String rootParentTransId=null;
        try{
            query="SELECT TR_Transactions.RootParentTransactionId FROM TR_Transactions INNER JOIN TR_TransactionsExt ON TR_Transactions.TransactionId LIKE TR_TransactionsExt.TransactionId WHERE TR_Transactions.TransactionNumber=?";
            ps=conn.prepareStatement(query);
            ps.setString(1, transId);
            rs=ps.executeQuery();
            if(rs.next()){
                rootParentTransId=rs.getString(1);
            }
           // rs.close();
           // ps.close();
            if(rootParentTransId!=null){
                session = HibernateUtil.getSessionFactory().openSession();
                conn = session.connection();
                query="UPDATE TR_Transactions, TR_TransactionsExt SET TR_TransactionsExt.OpenState = 'Open' where TR_TransactionsExt.TransactionId = TR_Transactions.TransactionId and TR_Transactions.RootParentTransactionId =?";
                ps=conn.prepareStatement(query);
                ps.setString(1, rootParentTransId);
                noUpdated=ps.executeUpdate();
            }
        }catch(SQLException sql){
            sql.printStackTrace();
        }
        finally{
            rs.close();
            conn.close();
            //session.close();
        }
        return noUpdated;
    }

    public byte[] getTransactionDocument(String transId) throws Exception {
        log.info("Getting Transaction Document: "+transId);
        ByteArrayOutputStream ret = new ByteArrayOutputStream();
       session = HibernateUtil.getSessionFactory().openSession();
                conn = session.connection();
        PreparedStatement ps = null;
        ResultSet rs=null;
         _base64dec = new BASE64Decoder();
        try {
            String sqlStr = "SELECT TransactionData FROM TR_TransactionData WHERE TransactionId = ?";
            log.info("Getting Transaction Document: "+sqlStr+"...transId="+transId);
            ps=conn.prepareStatement(sqlStr);
            ps.setString(1, transId);
            rs = ps.executeQuery();
            while (rs.next()) {
                log.info("in side resultset..");
                ret.write(_base64dec.decodeBuffer(rs.getString("TransactionData")));
                ret.flush();
            }
        } catch (Exception e) {
            throw (e);
        }
         finally{
            try{
                rs.close();
                ps.close();
                conn.close();
                ////session.close();
            }catch(SQLException sql){
                sql.printStackTrace();
            }
        }
        return ret.toByteArray();
    }

    public boolean saveTransactionDocument(Document doc,String transId) throws Exception {
        boolean flag=false;
        session = HibernateUtil.getSessionFactory().openSession();
                conn = session.connection();
        PreparedStatement ps = null;
        _base64enc = new BASE64Encoder();
         String docstr=_base64enc.encode(this.compressByteArray(this.doc2bytes(doc)));
        try {
            //String sqlStr = "update TR_transactiondata set TransactionData='"+docstr +"' WHERE TransactionId LIKE '"+transId+"'";
            String sqlStr = "update TR_transactiondata set TransactionData=? WHERE TransactionId=?";
            ps=conn.prepareStatement(sqlStr);
            ps.setString(1, docstr);
            ps.setString(2, transId);
            int n = ps.executeUpdate();
            //log.info("no of Documents updated: "+n);
            if(n>0)
                flag=true;
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
         finally{
            try{
                ps.close();
                conn.close();
                ////session.close();
            }catch(SQLException sql){
                sql.printStackTrace();
            }
        }
        return flag;
    }

    public EdxBableBean getEdxBable(String xpath,String partnerid) throws Exception {
        EdxBableBean eb = new EdxBableBean();
         session = HibernateUtil.getSessionFactory().openSession();
                conn = session.connection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        try {
            //String sqlStr = "SELECT * FROM TR_EDXBABLE where name = '" + xpath + "' and partnerid = '"+partnerid+"'";
            String sqlStr = "SELECT * FROM TR_EDXBABLE where name = ? and partnerid = ?";
            ps=conn.prepareStatement(sqlStr);
            ps.setString(1, xpath);
            ps.setString(2, partnerid);
            rs=ps.executeQuery();
            while (rs.next()) {
                eb.setEDXID(rs.getInt("EDXID"));
                eb.setKEYRULES(rs.getString("KEYRULES").trim());
                eb.setNAME(rs.getString("NAME").trim());
                eb.setORIG_VALUE(rs.getString("ORIG_VALUE").trim());
                eb.setORIG_DATATYPE(rs.getString("ORIG_DATATYPE").trim());
                eb.setPARENTID(rs.getString("PARENTID").trim());
                eb.setPARENTVALUE(rs.getString("PARENTVALUE").trim());
                eb.setENGLISHNAME(rs.getString("ENGLISHNAME").trim());
                eb.setHELPNOTE(rs.getString("HELPNOTE").trim());
                eb.setDATATYPE(rs.getString("DATATYPE").trim());
                eb.setREQUIRED(rs.getString("REQUIRED").trim());
                eb.setSTOREVALUEINBABLE(rs.getString("STOREVALUEINBABLE").trim());
                eb.setRESOLVETYPE(rs.getString("RESOLVETYPE").trim());
                eb.setCURRKEYVALUE(rs.getString("CURRKEYVALUE").trim());
                eb.setRESOLVEDVALUE(rs.getString("RESOLVEDVALUE").trim());
                eb.setVALIDATEDOK(rs.getString("VALIDATEDOK").trim());
                eb.setSCHEMATOUSE(rs.getString("SCHEMATOUSE").trim());
                eb.setBGCOLOUR(rs.getString("BGCOLOUR").trim());
                eb.setFONTCOLOUR(rs.getString("FONTCOLOUR").trim());
                eb.setFONTSIZE(rs.getString("FONTSIZE").trim());
                eb.setDISPLAYINTREE(rs.getString("DISPLAYINTREE").trim());
                eb.setDISPLAYINCONTENT(rs.getString("DISPLAYINCONTENT").trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw (e);
        }
        finally{
            try{
                rs.close();
                ps.close();
                conn.close();
                ////session.close();
            }catch(SQLException sql){
                sql.printStackTrace();
            }
        }
      return eb;
    }

    /**
     * Return the availability of a value pair based on the edxid
     * @param edxid, orig, parent
     * @throws Exception
     * @return
     */
    public String checkValuePair(int edxid, String orig, String parent) throws Exception {
        String destval=null;
        String sqlStr=null;
       session = HibernateUtil.getSessionFactory().openSession();
                conn = session.connection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        try {
//            sqlStr = "SELECT * FROM TR_EDX_VALUE_PAIR WHERE EDXID = " + edxid + " AND ORIG = '" + orig + "' AND PARENT = '" + parent + "'";
            sqlStr = "SELECT DEST FROM TR_EDX_VALUE_PAIR WHERE EDXID = ? AND ORIG = ? AND PARENT = ?";
            //log.info("SQLSTR = " + sqlStr);
            ps=conn.prepareStatement(sqlStr);
            ps.setInt(1, edxid);
            ps.setString(2, orig);
            ps.setString(3, parent);
            rs = ps.executeQuery();
            while (rs.next()) {
                destval=rs.getString("DEST");
                break;
            }
            log.info("DEST = " + destval);
        } catch (Exception e) {
            throw (e);
        }
        finally{
            try{
                rs.close();
                ps.close();
                conn.close();
                ////session.close();
            }catch(SQLException sql){
                sql.printStackTrace();
            }
        }
        return destval;
    }

    /**
     * Add a new value pair for a edxbable node
     * @param edxid, orig, dest, parent
     * @throws Exception
     * @return
     */
    public void newValuePair(int edxid, String orig, String dest, String parent) throws Exception {
        session = HibernateUtil.getSessionFactory().openSession();
                conn = session.connection();
        PreparedStatement ps = null;
        try {
            String sqlStr = "INSERT INTO TR_EDX_VALUE_PAIR (EDXID, ORIG, DEST, PARENT) VALUES(?,?,?,?)";
            ps=conn.prepareStatement(sqlStr);
            ps.setInt(1, edxid);
            ps.setString(2, orig);
            ps.setString(3, dest);
            ps.setString(4,parent);
            ps.execute();
        } catch (Exception e) {
            throw (e);
        }
        finally{
            try{
                ps.close();
                conn.close();
                ////session.close();
            }catch(SQLException sql){
                sql.printStackTrace();
            }
        }
    }

    /**
     * update destination value of a pair for a edxbable node
     * @param edxid, orig, dest, parent
     * @throws Exception
     * @return
     */
    public void updateValuePair(int edxid, String orig, String dest, String parent) throws Exception {
     session = HibernateUtil.getSessionFactory().openSession();
                conn = session.connection();
        PreparedStatement ps = null;
        try {
            String sqlStr = "UPDATE TR_EDX_VALUE_PAIR SET DEST = ? WHERE ORIG = ? AND EDXID = ? AND PARENT = ?";
            ps=conn.prepareStatement(sqlStr);
            ps.setString(1, dest);
            ps.setString(2, orig);
            ps.setInt(3, edxid);
            ps.setString(4,parent);
            ps.executeUpdate();
        } catch (Exception e) {
            throw (e);
        }
        finally{
            try{
                ps.close();
                conn.close();
                ////session.close();
            }catch(SQLException sql){
                sql.printStackTrace();
            }
        }
    }
    public byte[] compressByteArray(byte[] data)
    {
        Deflater compressor = new Deflater();
        compressor.setLevel(Deflater.BEST_COMPRESSION);

        compressor.setInput(data);
        compressor.finish();

        ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);

        byte[] buf = new byte[1024];

        while(!compressor.finished())
        {
            int count = compressor.deflate(buf);
            bos.write(buf, 0, count);
        }

        try{
            bos.close();
        }
        catch(IOException io)
        {
        }
        return bos.toByteArray();
    }

     public byte[] doc2bytes(Document doc) {
        try {
            Source source = new DOMSource(doc);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Result result = new StreamResult(out);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            transformer.transform(source, result);
            return out.toByteArray();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return null;
    }

     /**
     * Update the specified transactions alternate transaction number.
     * @param transAckId
     * @param altTransNumber
     * @throws Exception
     */
    public void updateAlternateTransactionNumber(String transAckId, String altTransNumber) throws Exception {
    session = HibernateUtil.getSessionFactory().openSession();
                conn = session.connection();
        PreparedStatement ps = null;
        try {
            //String sqlStr = "UPDATE TR_Transactions SET AlternateTransactionNumber = ' "+altTransNumber+"' WHERE TransactionId LIKE '"+transAckId+"'";
            log.info("transAckId="+transAckId+"..altTransNumber="+altTransNumber);
            String sqlStr = "UPDATE TR_Transactions SET AlternateTransactionNumber = ? WHERE TransactionId = ?";
            ps=conn.prepareStatement(sqlStr);
            ps.setString(1, altTransNumber);
            ps.setString(2, transAckId);
            ps.executeUpdate();
        } catch (Exception e) {
            throw (e);
        }
        finally{
            try{
                ps.close();
                conn.close();
                ////session.close();
            }catch(SQLException sql){
                sql.printStackTrace();
            }
        }
    }

    /**
     * return a Vector of all transaction's which have the same root id.
     * @param transactionId
     * @return
     */
    public Vector getTransactionHeirarchyList(String transactionId) throws Exception {
        Vector ret = new Vector();
        String rootId = "";
        session = HibernateUtil.getSessionFactory().openSession();
                conn = session.connection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        try {
            String sqlStr = "SELECT RootParentTransactionId FROM TR_Transactions WHERE TransactionId = ?";
            ps=conn.prepareStatement(sqlStr);
            ps.setString(1, transactionId);
            rs = ps.executeQuery();
            while (rs.next()) {
                rootId = rs.getString("RootParentTransactionId");
            }
            rs.close();
            conn.close();
            ////session.close();
        } catch (Exception e) {
            throw (e);
        }
        try {
            session = HibernateUtil.getSessionFactory().openSession();
                conn = session.connection();
            String sqlStr = "SELECT * FROM TR_Transactions WHERE RootParentTransactionId = ?";
            ps=conn.prepareStatement(sqlStr);
            ps.setString(1, rootId);
            rs = ps.executeQuery();
            while (rs.next()) {
                TransactionBean tBean = new TransactionBean();
                tBean.setId(rs.getString("TransactionId"));
                tBean.setTransType(rs.getString("TransactionType"));
                tBean.setNumber(rs.getString("TransactionNumber"));
                tBean.setSequenceNumber(rs.getString("SequenceNumber"));

                ret.add(tBean);
            }
        } catch (Exception e) {
            throw (e);
        }
        finally{
            try{
                rs.close();
                ps.close();
                conn.close();
                ////session.close();
            }catch(SQLException sql){
                sql.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * Set the specified transaction's open state in the database
     * @param transactionId
     * @param lockedState - Open or Locked
     * @throws Exception
     */
    public void setTransactionLockedState(String transactionId, String lockedState) throws Exception {
 session = HibernateUtil.getSessionFactory().openSession();
                conn = session.connection();
        PreparedStatement ps = null;
        try {
            String sqlStr = "UPDATE TR_TransactionsExt SET OpenState = ? WHERE TransactionId = ?";
            ps=conn.prepareStatement(sqlStr);
            ps.setString(1, lockedState);
            ps.setString(2, transactionId);
            ps.executeUpdate();
        } catch (Exception e) {
            throw (e);
        }
        finally{
            try{
                ps.close();
                conn.close();
                ////session.close();
            }catch(SQLException sql){
                sql.printStackTrace();
            }
        }
    }

    /**
     * Update the transaction state.
     * @param transId
     * @param transState
     * @throws Exception
     */
    public void updateTransactionState(String transId, String transState,String reason) throws Exception {
 session = HibernateUtil.getSessionFactory().openSession();
                conn = session.connection();
        //PreparedStatement ps = null;
        Statement stmt=null;
        try {
            log.info("updateTransactionState...transId="+transId+"...transState="+transState);
//            String sqlStr = "UPDATE TR_Transactions SET TransactionState = ? WHERE TransactionId = ?";
//            ps=conn.prepareStatement(sqlStr);
//            ps.setString(1, transState);
//            ps.setString(2, transId);
//            ps.executeUpdate();
           // String sqlStr = "UPDATE TR_Transactions SET TransactionState ='"+transState+"' WHERE TransactionId ='"+transId+"'";
            String sqlStr = "UPDATE TR_Transactions SET TransactionState ='"+transState+"',TransResponseComment='"+reason+"' WHERE TransactionId ='"+transId+"'";

            stmt=conn.createStatement();
            stmt.executeUpdate(sqlStr);
        } catch (Exception e) {
            throw (e);
        }
        finally{
            try{
                //ps.close();
                conn.close();
                //session.close();
            }catch(SQLException sql){
                sql.printStackTrace();
            }
        }
    }

    public void addInternalComment(String transId, String comment) throws Exception {
 session = HibernateUtil.getSessionFactory().openSession();
                conn = session.connection();
        Statement stmt=null;
        try {
            log.info("addInternalComment...transId="+transId+"...transState="+comment);
            String sqlStr = "update Tr_Transactions set TransAckComment='"+comment+"' WHERE TransactionId ='"+transId+"'";
            stmt=conn.createStatement();
            stmt.executeUpdate(sqlStr);
        } catch (Exception e) {
            throw (e);
        }
        finally{
            try{
                conn.close();
                ////session.close();
            }catch(SQLException sql){
                sql.printStackTrace();
            }
        }
    }

    /**
     * Update a transaction's resolved state.
     * @param transId
     * @param resolvedState
     * @throws Exception
     */
    public void updateResolvedState(String transId, String resolvedState,Integer nexusId) throws Exception {
session = HibernateUtil.getSessionFactory().openSession();
                conn = session.connection();
//        PreparedStatement ps = null;
        Statement stmt=null;
        try {
            log.info("updateResolvedState...transId="+transId+"...transState="+resolvedState);
//            String sqlStr = "UPDATE TR_TransactionsExt SET ResolvedState = ? WHERE TransactionId = ?";
//            ps=conn.prepareStatement(sqlStr);
//            ps.setString(1, resolvedState);
//            ps.setString(2, transId);
//            ps.executeUpdate();
            String sqlStr = "UPDATE TR_TransactionsExt SET ResolvedState ='"+resolvedState+"' WHERE TransactionId ='"+transId+"'";
             log.info("1 updateResolvedState...sqlStr="+sqlStr);
            stmt=conn.createStatement();
            stmt.executeUpdate(sqlStr);
            if(nexusId==500000)
                sqlStr = "UPDATE TR_Transactions SET TransactionState ='processing' WHERE TransactionId ='"+transId+"'";
            else
                sqlStr = "UPDATE TR_Transactions SET TransactionState ='Processed' WHERE TransactionId ='"+transId+"'";
             log.info("2 updateResolvedState...transId="+sqlStr);
            stmt=conn.createStatement();
            stmt.executeUpdate(sqlStr);
        } catch (Exception e) {
            throw (e);
        }
        finally{
            try{
//                ps.close();
                conn.close();
                ////session.close();
            }catch(SQLException sql){
                sql.printStackTrace();
            }
        }
    }

    public TransactionBean getFullTransactionInfoId(String transId) throws Exception {
session = HibernateUtil.getSessionFactory().openSession();
                conn = session.connection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        TransactionBean transactionBean=new TransactionBean();
        try {
                String sqlStr = "SELECT * FROM TR_Transactions INNER JOIN TR_TransactionsExt ON TR_Transactions.TransactionId LIKE TR_TransactionsExt.TransactionId WHERE TR_Transactions.TransactionId =?";
                //log.info("query in getFullTransactionInfoId..."+sqlStr);
                ps=conn.prepareStatement(sqlStr);
                ps.setString(1, transId);
                rs = ps.executeQuery();
                if(rs.next()){
                    transactionBean.setRootParentTransactionId(rs.getString("RootParentTransactionId"));
                    transactionBean.setState(rs.getString("TransactionState"));
                    transactionBean.setOpenState(rs.getString("OpenState"));
                    transactionBean.setUserList(rs.getString("UserList"));
                }
        } catch (Exception e) {
            throw (e);
        }
        finally{
            try{
                rs.close();
                ps.close();
                conn.close();
                ////session.close();
            }catch(SQLException sql){
                sql.printStackTrace();
            }
        }
        return transactionBean;
    }

    public void setTransactionHeirarchyState(String rootParentTransId, String state) throws Exception {
        session = HibernateUtil.getSessionFactory().openSession();
                conn = session.connection();
        PreparedStatement ps = null;
        try {
            log.info("state.."+state+"...rootParentTransId.."+rootParentTransId);
            String sqlStr = "UPDATE TR_Transactions, TR_TransactionsExt SET TR_TransactionsExt.OpenState = ? where TR_TransactionsExt.TransactionId = TR_Transactions.TransactionId and TR_Transactions.RootParentTransactionId = ?";
            //log.info("query..setTransactionHeirarchyState.."+sqlStr);
            ps=conn.prepareStatement(sqlStr);
            ps.setString(1, state);
            ps.setString(2, rootParentTransId);
            int count=ps.executeUpdate();
            log.info("count updated.setTransactionHeirarchyState."+count);
        } catch (Exception e) {
            throw (e);
        }
        finally{
            try{
                ps.close();
                conn.close();
                ////session.close();
            }catch(SQLException sql){
                sql.printStackTrace();
            }
        }
    }

    public TransactionBean getLastTransactionInHeirarchyList(String transactionId) throws Exception {
        TransactionBean tBean = null;
        String rootId = "";
session = HibernateUtil.getSessionFactory().openSession();
                conn = session.connection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        try {
            String sqlStr ="SELECT RootParentTransactionId FROM TR_Transactions WHERE TransactionId = ?";
            ps=conn.prepareStatement(sqlStr);
                ps.setString(1, transactionId);
                rs = ps.executeQuery();
            while (rs.next()) {
                rootId = rs.getString("RootParentTransactionId");
            }
                rs.close();
                ps.close();
                conn.close();
                //session.close();
        } catch (Exception e) {
            throw (e);
        }

        try {
            String sqlStr = "SELECT * FROM TR_Transactions WHERE (SequenceNumber = (SELECT MAX(SequenceNumber) AS Expr1 FROM (SELECT * FROM TR_Transactions WHERE (RootParentTransactionId LIKE ?)) DERIVEDTBL)) AND (RootParentTransactionId LIKE ?)";
            session = HibernateUtil.getSessionFactory().openSession();
                conn = session.connection();
            ps=conn.prepareStatement(sqlStr);
                ps.setString(1, rootId);
                ps.setString(2, rootId);
                rs = ps.executeQuery();
            while (rs.next()) {
                tBean = new TransactionBean();
                tBean.setId(rs.getString("TransactionId"));
                tBean.setTransType(rs.getString("TransactionType"));
                tBean.setNumber(rs.getString("TransactionNumber"));
                tBean.setSequenceNumber(rs.getString("SequenceNumber"));
                tBean.setIssueDate(rs.getString("IssueDate"));
                break;
            }
        } catch (Exception e) {
            throw (e);
        }
        finally{
            try{
                rs.close();
                ps.close();
                conn.close();
                //session.close();
            }catch(SQLException sql){
                sql.printStackTrace();
            }
        }
        return tBean;
    }

    public void updateUserList(String transactionId, String userName) throws Exception {
        String userList = "";
session = HibernateUtil.getSessionFactory().openSession();
                conn = session.connection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        try {
            String sqlStr = "SELECT * FROM TR_TransactionsExt WHERE TransactionId = ?";
            ps=conn.prepareStatement(sqlStr);
                ps.setString(1, transactionId);
                rs = ps.executeQuery();
            while (rs.next()) {
                userList = rs.getString("UserList");
            }
            if (userList.equalsIgnoreCase("None")) {
                userList = userName;
            } else {
                userList = userList + "," + userName;
            }
                rs.close();
                conn.close();
                //session.close();
        } catch (Exception e) {
            throw (e);
        }

        try {
            session = HibernateUtil.getSessionFactory().openSession();
                conn = session.connection();
            String sqlStr = "UPDATE TR_TransactionsExt SET UserList = ? WHERE TransactionId =?";
            ps=conn.prepareStatement(sqlStr);
            ps.setString(1, userList);
            ps.setString(2, transactionId);
            ps.executeUpdate();
        } catch (Exception e) {
            throw (e);
        }
        finally{
            try{
                rs.close();
                ps.close();
                conn.close();
                //session.close();
            }catch(SQLException sql){
                sql.printStackTrace();
            }
        }
    }

    public Vector getTransactions(String NexusID) {

        Vector tableData = new Vector();
        QueryResult result;
session = HibernateUtil.getSessionFactory().openSession();
                conn = session.connection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        try {
            String sqlStr = "SELECT * FROM TR_Transactions INNER JOIN TR_TransactionsExt ON TR_Transactions.TransactionId LIKE TR_TransactionsExt.TransactionId where TR_Transactions.TransactionState LIKE 'user' AND (TR_Transactions.Hidden NOT LIKE 'true') AND (TR_Transactions.TransactionType = 'Order' OR TR_Transactions.TransactionType = 'ChangeOrder') and sup_nexus_id = ?";
             ps=conn.prepareStatement(sqlStr);
                ps.setString(1, NexusID);
                rs = ps.executeQuery();
            while (rs.next()) {
                result = new QueryResult();
                result.setDocumentCreatorsTransactionID(rs.getString("TransactionId"));
                result.setTransactionNumber(rs.getString("TransactionNumber"));
                result.setCreationDate(rs.getDate("ProcessDate"));
                result.setUserID(rs.getString("UserList"));
                result.setOpenState(rs.getString("OpenState"));
                result.setPartnerName(rs.getString("partnerName"));
                result.setDocumentType(rs.getString("TransactionType"));
                tableData.addElement(result);
            }
        } catch (SQLException sqlEx) {
            log.info("[DbCommandsManager] -getTransactions- Error in:" + this.getClass().getName() + ": " + sqlEx.getMessage());
            log.info("SQLState (ClassSubclass) code:" + sqlEx.getSQLState());
        } catch (Exception e) {
            log.info("[DbCommandsManager] SendQuery1 Major Exception:");
            e.printStackTrace();
        }
        finally{
            try{
                rs.close();
                ps.close();
                conn.close();
                //session.close();
            }catch(SQLException sql){
                sql.printStackTrace();
            }
        }
        return tableData;
    }

    /**
     * Return the Destination value of the original value based on the edxid
     * @param edxid, orig, parent
     * @throws Exception
     * @return
     */
    public String getDestValue(int edxid, String orig, String parent) throws Exception {
        String ret = "";
        String sqlStr = "";
session = HibernateUtil.getSessionFactory().openSession();
                conn = session.connection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        try {
            if (parent.equalsIgnoreCase("")) {
                sqlStr = "SELECT dest FROM EDX_VALUE_PAIR WHERE EDXID = ? AND ORIG = ?";
                ps=conn.prepareStatement(sqlStr);
                ps.setInt(1, edxid);
                ps.setString(2, orig);
            } else {
                sqlStr = "SELECT dest FROM EDX_VALUE_PAIR WHERE EDXID = ? AND PARENT = ?";
                ps=conn.prepareStatement(sqlStr);
                ps.setInt(1, edxid);
                ps.setString(2, parent);
            }
            //log.info("SQLSTR getDestValue= " + sqlStr);
                rs = ps.executeQuery();
            while (rs.next()) {
                ret = rs.getString("dest").trim();
                break;
            }
        } catch (Exception e) {
            throw (e);
        }
        finally{
            try{
                rs.close();
                ps.close();
                conn.close();
                //session.close();
            }catch(SQLException sql){
                sql.printStackTrace();
            }
        }
        return ret;
    }

    public String getPartnerId(String transId) throws Exception {
        String partnerId=null;
         session = HibernateUtil.getSessionFactory().openSession();
                conn = session.connection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        try {
                String sqlStr = "SELECT PartnerId FROM TR_Transactions where TransactionId = ?";
               ps=conn.prepareStatement(sqlStr);
                ps.setString(1, transId);
                rs = ps.executeQuery();
                if(rs.next()){
                    partnerId=rs.getString("PartnerId");
                }
        } catch (Exception e) {
            throw (e);
        }
        finally{
            try{
                rs.close();
                ps.close();
                conn.close();
                //session.close();
            }catch(SQLException sql){
                sql.printStackTrace();
            }
        }
        return partnerId;
    }

    public String getTransactionId(String transkey) {
        String transId = null;
        session = HibernateUtil.getSessionFactory().openSession();
                conn = session.connection();
        PreparedStatement ps = null;
        ResultSet rs=null;

        try{
            String sqlStr = "SELECT TR_Transactions.TransactionId FROM TR_Transactions INNER JOIN TR_TransactionsExt ON TR_Transactions.TransactionId LIKE TR_TransactionsExt.TransactionId where TR_Transactions.TransactionState LIKE 'user' AND (TR_Transactions.Hidden NOT LIKE 'true') AND (TR_Transactions.TransactionType = 'Order' OR TR_Transactions.TransactionType = 'ChangeOrder') and transactionkey = ?";
            ps=conn.prepareStatement(sqlStr);
                ps.setString(1, transkey);
                rs = ps.executeQuery();
            if(rs.next())
                transId=rs.getString("TransactionId");
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                rs.close();
                ps.close();
                conn.close();
                //session.close();
            }catch(SQLException sql){
                sql.printStackTrace();
            }
        }
        return transId;
    }

    public HashMap getTransactionId(Integer transactionkey) {
        PreparedStatement ps = null;
        ResultSet rs=null;
        HashMap hm=new HashMap();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
                conn = session.connection();
            String sqlStr = "SELECT TransactionId,TransactionNumber,TransactionType FROM TR_Transactions WHERE transactionkey = ?";
            ps=conn.prepareStatement(sqlStr);
                ps.setInt(1, transactionkey);
                rs = ps.executeQuery();
            if(rs.next()){
                hm.put("transid", rs.getString("TransactionId"));
                hm.put("transnumber", rs.getString("TransactionNumber"));
                hm.put("transtype", rs.getString("TransactionType"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                rs.close();
                ps.close();
                conn.close();
                ////session.close();
            }catch(SQLException sql){
                sql.printStackTrace();
            }
        }
        return hm;
    }

    public String getTransactionType(String transkey) {
        String transType = null;
         session = HibernateUtil.getSessionFactory().openSession();
                conn = session.connection();
        PreparedStatement ps = null;
        ResultSet rs=null;

         try{
             String sqlStr = "SELECT TR_Transactions.TransactionType FROM TR_Transactions INNER JOIN TR_TransactionsExt ON TR_Transactions.TransactionId LIKE TR_TransactionsExt.TransactionId where TR_Transactions.TransactionState LIKE 'user' AND (TR_Transactions.Hidden NOT LIKE 'true') AND (TR_Transactions.TransactionType = 'Order' OR TR_Transactions.TransactionType = 'ChangeOrder') and transactionkey = ?";
                ps=conn.prepareStatement(sqlStr);
                ps.setString(1, transkey);
                rs = ps.executeQuery();
            if(rs.next())
                transType=rs.getString("TransactionType");
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                rs.close();
                ps.close();
                conn.close();
                ////session.close();
            }catch(SQLException sql){
                sql.printStackTrace();
            }
        }
        return transType;
    }
     public String getDRStoreFolder() throws java.sql.SQLException {
       session = HibernateUtil.getSessionFactory().openSession();
        conn = session.connection();
        Statement smt = conn.createStatement();
        ResultSet rs;
        String result = "";
        String query = "SELECT VCH_CONTENT  FROM sk_parameters where vch_name = 'TR_store'";
        log.info("query getUploadFolder= " + query);
        rs = smt.executeQuery(query);
        while (rs.next()) {
            result = rs.getString("VCH_CONTENT");
        }
        smt.close();
        rs.close();
        conn.close();
        return result;
    }
    public List<Role> getUsers(int nexusId){
       List<Role> userList = new ArrayList();
        session = HibernateUtil.getSessionFactory().openSession();
        conn = session.connection();
        PreparedStatement ps = null;
        ResultSet rs=null;
         try
         {
             System.out.println("nexusId.."+nexusId);
            String query="select id,Loginid from user_login where nexus_id=? and type>0";
            ps=conn.prepareStatement(query);
            ps.setInt(1, nexusId);
            rs = ps.executeQuery();
            while(rs.next()){
                userList.add(new Role(rs.getInt("id"), rs.getString("Loginid")));
            }
            System.out.println("size.."+userList.size());
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                rs.close();
                ps.close();
                conn.close();
               // //session.close();
            }catch(SQLException sql){
                sql.printStackTrace();
            }
        }
        return userList;
    }
//    public void assignPartner(int userId,String partnerNames){
//        session = HibernateUtil.getSessionFactory().openSession();
//        conn = session.connection();
//        PreparedStatement ps = null;
//        StringBuffer str=new StringBuffer();
//        try
//        {
//            str.append("insert into tr_user_management(userId,partner_name) values");
//            StringTokenizer st=new StringTokenizer(partnerNames, ",");
//                while(st.hasMoreTokens()){
//                    str.append("(");
//                    str.append(userId);
//                    str.append(",'");
//                    str.append(st.nextToken().trim());
//                    str.append("'),");
//               }
//           String query=str.substring(0, str.length()-1);
//            log.info("inside assignPartner..query="+query);
//            ps=conn.prepareStatement(query);
//            int count=ps.executeUpdate();
//            log.info("No of records inserted.."+count);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        finally{
//            try{
//                ps.close();
//                conn.close();
//                //session.close();
//            }catch(SQLException sql){
//                sql.printStackTrace();
//            }
//        }
//    }

    public void assignPartner(int userId,String partnerId,String partnerName){
        session = HibernateUtil.getSessionFactory().openSession();
        conn = session.connection();
        PreparedStatement ps = null;
        try
        {
            log.info("userId."+userId+"..partnerName="+partnerName+"..partnerId="+partnerId);
            String query="insert into tr_user_management(userId,partner_id,partner_name) values(?,?,?)";
            log.info("inside assignPartner..query="+query);
            ps=conn.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setString(2, partnerId);
            ps.setString(3, partnerName);
            int count=ps.executeUpdate();
            log.info("No of records inserted.."+count);
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                ps.close();
                conn.close();
                ////session.close();
            }catch(SQLException sql){
                sql.printStackTrace();
            }
        }
    }

    public void deAssignPartner(int userId,String partnerName){
        session = HibernateUtil.getSessionFactory().openSession();
        conn = session.connection();
        PreparedStatement ps = null;
        try
        {
            log.info("userId."+userId+"..partnerName="+partnerName);
            String query="delete from tr_user_management where userId=? and partner_name=?";
            log.info("inside deAssignPartner..query="+query);
            ps=conn.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setString(2, partnerName);
            int count=ps.executeUpdate();
            log.info("No of records deleted.."+count);
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                ps.close();
                conn.close();
               // //session.close();
            }catch(SQLException sql){
                sql.printStackTrace();
            }
        }
    }
    
    public List<JQGridRow> getAssignedPartners(Integer userId){
        session = HibernateUtil.getSessionFactory().openSession();
        conn = session.connection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        List<JQGridRow> rows=new ArrayList();
        int count=0;
        try
        {
            String query="select partner_id,partner_name from tr_user_management where userId=?";
            log.info("inside getAssignedPartners..query="+query+"..userId="+userId);
            ps=conn.prepareStatement(query);
            ps.setInt(1,userId);
            rs=ps.executeQuery();
            while(rs.next()){
                count++;
                JQGridRow row = new JQGridRow();
                row.setId(count);
                List<String> cell = new ArrayList();
                cell.add(rs.getString("partner_id"));
                cell.add(rs.getString("partner_name"));
                row.setCell(cell);
                rows.add(row);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                ps.close();
                conn.close();
                ////session.close();
            }catch(SQLException sql){
                sql.printStackTrace();
            }
        }
        return rows;
    }
     public int getUsersCountByNexusId(int nexusId) throws SQLException {
        session = HibernateUtil.getSessionFactory().openSession();
        conn = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("getUsersCountById..id.."+nexusId);
        String selectStatement = "select count(*) from user_login where nexus_id=? and type>0";
        ps = conn.prepareStatement(selectStatement);
        ps.setInt(1, nexusId);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public List<JQGridRow> getUsersByNexusId(int nexusId, int start, int limit, String sidx, String sord) throws SQLException {
        session = HibernateUtil.getSessionFactory().openSession();
        conn = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<JQGridRow> rows = new ArrayList();
        log.info("1 findUsersById..id.."+nexusId);
        String selectStatement = "select id,Loginid from user_login where nexus_id=? and type>0 order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;
        ps = conn.prepareStatement(selectStatement);
        ps.setInt(1, nexusId);
        rs = ps.executeQuery();
        while (rs.next()) {
            JQGridRow row = new JQGridRow();
            row.setId(rs.getInt("id"));
            List<String> cell = new ArrayList();
            cell.add(rs.getString("Loginid"));
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }

    public User getUserById(int id) throws SQLException {
        session = HibernateUtil.getSessionFactory().openSession();
        conn = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("getUserById...id."+id);
        String selectStatement = "SELECT loginid,password,name,type,email from user_login where id=?";
        ps = conn.prepareStatement(selectStatement);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        rs.next();
        return new User(rs.getString("loginid"), rs.getString("password"), rs.getString("name"), rs.getInt("type"), rs.getString("email"));
    }

    public void addComment(String comment,String transId){
        session = HibernateUtil.getSessionFactory().openSession();
        conn = session.connection();
        PreparedStatement ps = null;
        log.info("comment..."+comment+"..transId="+transId);
        String selectStatement = "SELECT loginid,password,name,type,email from user_login where id=?";
    }

    public String getTradeRouteFolderPath(String name){
         session = HibernateUtil.getSessionFactory().openSession();
        conn = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String folderPath="";
        String selectStatement = "SELECT FolderPath FROM xy_txn_comm_type where name=?";
        try {
             ps = conn.prepareStatement(selectStatement);
             ps.setString(1, name);
             rs = ps.executeQuery();
             if(rs.next())
                folderPath=rs.getString("FolderPath");
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DataResolutionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return folderPath;
    }

    public HashMap getNexusConfig(Integer nexusId){
         session = HibernateUtil.getSessionFactory().openSession();
        conn = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        HashMap nexusConfig=new HashMap();
        String selectStatement = "SELECT dealerId, storeNo, customerNo, poaDirPath, emailTo, emailServer FROM tr_nexus_config where supNexusId=?";
        try {
             ps = conn.prepareStatement(selectStatement);
             ps.setInt(1, nexusId);
             rs = ps.executeQuery();
             if(rs.next())
             {
                nexusConfig.put("dealerId", rs.getString("dealerId"));
                nexusConfig.put("storeNo", rs.getString("storeNo"));
                nexusConfig.put("customerNo", ""+rs.getInt("customerNo"));
                nexusConfig.put("poaDirPath", rs.getString("poaDirPath"));
                nexusConfig.put("emailTo", rs.getString("emailTo"));
                nexusConfig.put("emailServer", rs.getString("emailServer"));
             }
             System.out.println("getNexusCOnfig size=="+nexusConfig.size());
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DataResolutionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nexusConfig;
    }
    
    public HashMap getNexusParameters(Integer nexusId){
        System.out.println("getNexusParameters nexusId=="+nexusId);
        session = HibernateUtil.getSessionFactory().openSession();
        conn = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        HashMap nexusParameters=new HashMap();
        String selectStatement = "SELECT nexus_key,nexus_value FROM nexus_parameters where nexus_id = ?";
        try {
             ps = conn.prepareStatement(selectStatement);
             ps.setInt(1, nexusId);
             rs = ps.executeQuery();
             while(rs.next())
             {
                nexusParameters.put(rs.getString("nexus_key"), rs.getString("nexus_value"));
             }
             System.out.println("getNexusParameters size=="+nexusParameters.size());
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DataResolutionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nexusParameters;
    }
}
