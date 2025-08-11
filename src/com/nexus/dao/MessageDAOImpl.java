/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import com.nexus.web.common.MessagesList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Terry
 */
public class MessageDAOImpl implements MessageDAO {

     Logger log=Logger.getLogger(MessageDAOImpl.class);
    private SessionFactory sessionFactory;

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

    public int findInBoundMessageListCount(String id, String search, String vch_document_id, String company, String dt_received, String dt1_received) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findInBoundMessageListCount..id.."+id+"..search.."+search+"..vch_document_id.."+vch_document_id+"..company."+company);
        String selectStatement = "SELECT COUNT(xy_message.N_MESSAGE_ID) FROM xy_message, xy_txn_type, registration sender_tp,registration recipient_tp WHERE xy_txn_type.n_type_id = xy_message.N_TRANSACTION_TYPE and sender_tp.id = xy_message.N_SENDER_PARTNER_ID and recipient_tp.id = xy_message.N_RECIPIENT_PARTNER_ID and xy_txn_type.n_type_id = xy_message.N_TRANSACTION_TYPE and xy_message.b_status!='2' and xy_message.N_RECIPIENT_PARTNER_ID= ?";
        if (search.equals("true")) {
            if (vch_document_id != null) {
                selectStatement += " and xy_message.vch_document_id like '%" + vch_document_id + "%'";
            }
            if (company != null) {
                selectStatement += " and sender_tp.company like '%" + company + "%'";
            }
            if ((dt_received != null) && (dt1_received != null)) {
                selectStatement += " and DATE(xy_message.dt_received) between'" + dt_received + "' and '" + dt1_received + "'";
            }
            if ((dt_received != null) && (dt1_received == null)) {
                selectStatement += " and DATE(xy_message.dt_received) >='" + dt_received + "'";
            }
            if ((dt_received == null) && (dt1_received != null)) {
                selectStatement += " and DATE(xy_message.dt_received)<='" + dt1_received + "'";
            }
        }
        log.info("in findInBoundMessageListCount query : "+selectStatement);
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, new Integer(id).intValue());
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public int findInBoundArchivedMessageListCount(String id, String search, String vch_document_id, String company, String dt_received, String dt1_received) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findInBoundArchivedMessageListCount..id.."+id+"..search.."+search+"..vch_document_id.."+vch_document_id+"..company."+company);
        String selectStatement = "SELECT COUNT(xy_message_archive.N_MESSAGE_ID) FROM xy_message_archive, xy_txn_type, registration sender_tp,registration recipient_tp WHERE xy_txn_type.n_type_id = xy_message_archive.N_TRANSACTION_TYPE and sender_tp.id = xy_message_archive.N_SENDER_PARTNER_ID and recipient_tp.id = xy_message_archive.N_RECIPIENT_PARTNER_ID and xy_txn_type.n_type_id = xy_message_archive.N_TRANSACTION_TYPE and xy_message_archive.N_RECIPIENT_PARTNER_ID= ?";
        if (search.equals("true")) {
            if (vch_document_id != null) {
                selectStatement += " and xy_message_archive.vch_document_id like '%" + vch_document_id + "%'";
            }
            if (company != null) {
                selectStatement += " and sender_tp.company like '%" + company + "%'";
            }
            if ((dt_received != null) && (dt1_received != null)) {
                selectStatement += " and DATE(xy_message_archive.dt_received) between'" + dt_received + "' and '" + dt1_received + "'";
            }
            if ((dt_received != null) && (dt1_received == null)) {
                selectStatement += " and DATE(xy_message_archive.dt_received) >='" + dt_received + "'";
            }
            if ((dt_received == null) && (dt1_received != null)) {
                selectStatement += " and DATE(xy_message_archive.dt_received)<='" + dt1_received + "'";
            }

        }
        log.info(selectStatement);
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, new Integer(id).intValue());
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public List findInBoundMessageList(String id, String search, String vch_document_id, String company, String dt_received, String dt1_received, int start, int limit, String sidx, String sord) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List messageLists = new ArrayList();
        log.info("findInBoundMessageList..id.."+id+"..search.."+search+"..vch_document_id.."+vch_document_id+"..company."+company);
        String selectStatement = "SELECT xy_message.N_MESSAGE_ID,sender_tp.company,xy_message.dt_received,xy_txn_type.vch_description,xy_message.B_STATUS,xy_message.VCH_PATH,xy_message.vch_document_id FROM xy_message, xy_txn_type, registration sender_tp,registration recipient_tp WHERE xy_txn_type.n_type_id = xy_message.N_TRANSACTION_TYPE and sender_tp.id = xy_message.N_SENDER_PARTNER_ID and recipient_tp.id = xy_message.N_RECIPIENT_PARTNER_ID and xy_txn_type.n_type_id = xy_message.N_TRANSACTION_TYPE and xy_message.b_status!='2' and xy_message.N_RECIPIENT_PARTNER_ID= ?";
        if (search.equals("true")) {
            if (vch_document_id != null) {
                selectStatement += " and xy_message.vch_document_id like '%" + vch_document_id + "%'";
            }
            if (company != null) {
                selectStatement += " and sender_tp.company like '%" + company + "%'";
            }
            if ((dt_received != null) && (dt1_received != null)) {
                selectStatement += " and DATE(xy_message.dt_received) between'" + dt_received + "' and '" + dt1_received + "'";
            }
            if ((dt_received != null) && (dt1_received == null)) {
                selectStatement += " and DATE(xy_message.dt_received) >='" + dt_received + "'";
            }
            if ((dt_received == null) && (dt1_received != null)) {
                selectStatement += " and DATE(xy_message.dt_received)<='" + dt1_received + "'";
            }
        }
        selectStatement += " order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, new Integer(id).intValue());
        rs = ps.executeQuery();
        while (rs.next()) {
            MessagesList messageslist = new MessagesList();
            int messageId = rs.getInt("N_MESSAGE_ID");
            messageslist.setId(messageId);
            String status = rs.getString("B_STATUS");
            messageslist.setStatus(status);
            String docid = rs.getString("vch_document_id");
            messageslist.setDocid(docid);
            String ttype = rs.getString("vch_description");
            messageslist.setType(ttype);
            String from = rs.getString("sender_tp.company");
            messageslist.setFrom(from);
            String date = rs.getString("dt_received");
            messageslist.setDate(date);
            String fname = rs.getString("vch_path");
            messageslist.setFilename(fname);
            messageLists.add(messageslist);
        }
        return messageLists;
    }

    public List findInBoundArchivedMessageList(String id, String search, String vch_document_id, String company, String dt_received, String dt1_received, int start, int limit, String sidx, String sord) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List messageLists = new ArrayList();
        log.info("findInBoundArchivedMessageList..id.."+id+"..search.."+search+"..vch_document_id.."+vch_document_id+"..company."+company);
        String selectStatement = "SELECT xy_message_archive.N_MESSAGE_ID,sender_tp.company,xy_message_archive.dt_received,xy_txn_type.vch_description,xy_message_archive.B_STATUS,xy_message_archive.VCH_PATH,xy_message_archive.vch_document_id FROM xy_message_archive, xy_txn_type, registration sender_tp,registration recipient_tp WHERE xy_txn_type.n_type_id = xy_message_archive.N_TRANSACTION_TYPE and sender_tp.id = xy_message_archive.N_SENDER_PARTNER_ID and recipient_tp.id = xy_message_archive.N_RECIPIENT_PARTNER_ID and xy_txn_type.n_type_id = xy_message_archive.N_TRANSACTION_TYPE and xy_message_archive.N_RECIPIENT_PARTNER_ID= ?";
        if (search.equals("true")) {
            if (vch_document_id != null) {
                selectStatement += " and xy_message_archive.vch_document_id like '%" + vch_document_id + "%'";
            }
            if (company != null) {
                selectStatement += " and sender_tp.company like '%" + company + "%'";
            }
            if ((dt_received != null) && (dt1_received != null)) {
                selectStatement += " and DATE(xy_message_archive.dt_received) between'" + dt_received + "' and '" + dt1_received + "'";
            }
            if ((dt_received != null) && (dt1_received == null)) {
                selectStatement += " and DATE(xy_message_archive.dt_received) >='" + dt_received + "'";
            }
            if ((dt_received == null) && (dt1_received != null)) {
                selectStatement += " and DATE(xy_message_archive.dt_received)<='" + dt1_received + "'";
            }
        }
        selectStatement += " order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;
        log.info(selectStatement);
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, new Integer(id).intValue());
        rs = ps.executeQuery();
        while (rs.next()) {
            MessagesList messageslist = new MessagesList();
            int messageId = rs.getInt("N_MESSAGE_ID");
            messageslist.setId(messageId);
            String status = rs.getString("B_STATUS");
            messageslist.setStatus(status);
            String docid = rs.getString("vch_document_id");
            messageslist.setDocid(docid);
            String ttype = rs.getString("vch_description");
            messageslist.setType(ttype);
            String from = rs.getString("sender_tp.company");
            messageslist.setFrom(from);
            String date = rs.getString("dt_received");
            messageslist.setDate(date);
            String fname = rs.getString("vch_path");
            messageslist.setFilename(fname);
            messageLists.add(messageslist);
        }
        return messageLists;
    }

    public void updateMessageStatus(String id, int status) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("updateMessageStatus..id.."+id+"..status.."+status);
        String updateStatement = "UPDATE xy_message SET b_status = ? where n_message_id = ?";
        ps = connection.prepareStatement(updateStatement);
        ps.setInt(1, status);
        ps.setInt(2, new Integer(id).intValue());
        ps.executeUpdate();
    }

    public int findOutBoundMessageListCount(String id, String search, String vch_document_id, String company, String dt_received, String dt1_received) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findOutBoundMessageListCount..id.."+id+"..search.."+search+"..vch_document_id.."+vch_document_id+"..company."+company);
        String selectStatement = "SELECT COUNT(xy_message.N_MESSAGE_ID) FROM xy_message, xy_txn_type, registration sender_tp,registration recipient_tp WHERE xy_txn_type.n_type_id = xy_message.N_TRANSACTION_TYPE and sender_tp.id = xy_message.N_SENDER_PARTNER_ID and recipient_tp.id = xy_message.N_RECIPIENT_PARTNER_ID and xy_txn_type.n_type_id = xy_message.N_TRANSACTION_TYPE and xy_message.N_SENDER_PARTNER_ID= ?";
        if (search.equals("true")) {
            if (vch_document_id != null) {
                selectStatement += " and xy_message.vch_document_id like '%" + vch_document_id + "%'";
            }
            if (company != null) {
                selectStatement += " and recipient_tp.company like '%" + company + "%'";
            }
            if ((dt_received != null) && (dt1_received != null)) {
                selectStatement += " and DATE(xy_message.dt_received) between'" + dt_received + "' and '" + dt1_received + "'";
            }
            if ((dt_received != null) && (dt1_received == null)) {
                selectStatement += " and DATE(xy_message.dt_received) >='" + dt_received + "'";
            }
            if ((dt_received == null) && (dt1_received != null)) {
                selectStatement += " and DATE(xy_message.dt_received)<='" + dt1_received + "'";
            }
        }
        log.info(selectStatement);
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, new Integer(id).intValue());
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public int findOutBoundArchivedMessageListCount(String id, String search, String vch_document_id, String company, String dt_received, String dt1_received) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("1 findOutBoundArchivedMessageListCount..id.."+id+"..search.."+search+"..vch_document_id.."+vch_document_id+"..company."+company);
        String selectStatement = "SELECT COUNT(xy_message_archive.N_MESSAGE_ID) FROM xy_message_archive, xy_txn_type, registration sender_tp,registration recipient_tp WHERE xy_txn_type.n_type_id = xy_message_archive.N_TRANSACTION_TYPE and sender_tp.id = xy_message_archive.N_SENDER_PARTNER_ID and recipient_tp.id = xy_message_archive.N_RECIPIENT_PARTNER_ID and xy_txn_type.n_type_id = xy_message_archive.N_TRANSACTION_TYPE and xy_message_archive.N_SENDER_PARTNER_ID= ?";
        if (search.equals("true")) {
            if (vch_document_id != null) {
                selectStatement += " and xy_message_archive.vch_document_id like '%" + vch_document_id + "%'";
            }
            if (company != null) {
                selectStatement += " and recipient_tp.company like '%" + company + "%'";
            }
            if ((dt_received != null) && (dt1_received != null)) {
                selectStatement += " and DATE(xy_message_archive.dt_received) between'" + dt_received + "' and '" + dt1_received + "'";
            }
            if ((dt_received != null) && (dt1_received == null)) {
                selectStatement += " and DATE(xy_message_archive.dt_received) >='" + dt_received + "'";
            }
            if ((dt_received == null) && (dt1_received != null)) {
                selectStatement += " and DATE(xy_message_archive.dt_received)<='" + dt1_received + "'";
            }
        }
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, new Integer(id).intValue());
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public List findOutBoundMessageList(String id, String search, String vch_document_id, String company, String dt_received, String dt1_received, int start, int limit, String sidx, String sord) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List messageLists = new ArrayList();
        log.info("findOutBoundMessageList..id.."+id+"..search.."+search+"..vch_document_id.."+vch_document_id+"..company."+company);
        String selectStatement = "SELECT xy_message.N_MESSAGE_ID,recipient_tp.company,xy_message.dt_received,xy_txn_type.vch_description,xy_message.B_STATUS,xy_message.VCH_PATH,xy_message.vch_document_id FROM xy_message, xy_txn_type, registration sender_tp,registration recipient_tp WHERE xy_txn_type.n_type_id = xy_message.N_TRANSACTION_TYPE and sender_tp.id = xy_message.N_SENDER_PARTNER_ID and recipient_tp.id = xy_message.N_RECIPIENT_PARTNER_ID and xy_txn_type.n_type_id = xy_message.N_TRANSACTION_TYPE and xy_message.N_SENDER_PARTNER_ID= ?";
        if (search.equals("true")) {
            if (vch_document_id != null) {
                selectStatement += " and xy_message.vch_document_id like '%" + vch_document_id + "%'";
            }
            if (company != null) {
                selectStatement += " and recipient_tp.company like '%" + company + "%'";
            }
            if ((dt_received != null) && (dt1_received != null)) {
                selectStatement += " and DATE(xy_message.dt_received) between'" + dt_received + "' and '" + dt1_received + "'";
            }
            if ((dt_received != null) && (dt1_received == null)) {
                selectStatement += " and DATE(xy_message.dt_received) >='" + dt_received + "'";
            }
            if ((dt_received == null) && (dt1_received != null)) {
                selectStatement += " and DATE(xy_message.dt_received)<='" + dt1_received + "'";
            }
        }
        selectStatement += " order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;
        ps = connection.prepareStatement(selectStatement);
        log.info(selectStatement);
        ps.setInt(1, new Integer(id).intValue());
        rs = ps.executeQuery();
        while (rs.next()) {
            MessagesList messageslist = new MessagesList();
            int messageId = rs.getInt("N_MESSAGE_ID");
            messageslist.setId(messageId);
            String status = rs.getString("B_STATUS");
            messageslist.setStatus(status);
            String docid = rs.getString("vch_document_id");
            messageslist.setDocid(docid);
            String ttype = rs.getString("vch_description");
            messageslist.setType(ttype);
            String to = rs.getString("recipient_tp.company");
            messageslist.setTo(to);
            String date = rs.getString("dt_received");
            messageslist.setDate(date);
            String fname = rs.getString("vch_path");
            messageslist.setFilename(fname);
            messageLists.add(messageslist);
        }
        return messageLists;
    }

    public List findOutBoundArchivedMessageList(String id, String search, String vch_document_id, String company, String dt_received, String dt1_received, int start, int limit, String sidx, String sord) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List messageLists = new ArrayList();
        log.info(" 2 findOutBoundArchivedMessageList..id.."+id+"..search.."+search+"..vch_document_id.."+vch_document_id+"..company."+company);
        String selectStatement = "SELECT xy_message_archive.N_MESSAGE_ID,recipient_tp.company,xy_message_archive.dt_received,xy_txn_type.vch_description,xy_message_archive.B_STATUS,xy_message_archive.VCH_PATH,xy_message_archive.vch_document_id FROM xy_message_archive, xy_txn_type, registration sender_tp,registration recipient_tp WHERE xy_txn_type.n_type_id = xy_message_archive.N_TRANSACTION_TYPE and sender_tp.id = xy_message_archive.N_SENDER_PARTNER_ID and recipient_tp.id = xy_message_archive.N_RECIPIENT_PARTNER_ID and xy_txn_type.n_type_id = xy_message_archive.N_TRANSACTION_TYPE and xy_message_archive.N_SENDER_PARTNER_ID= ?";
        if (search.equals("true")) {
            if (vch_document_id != null) {
                selectStatement += " and xy_message_archive.vch_document_id like '%" + vch_document_id + "%'";
            }
            if (company != null) {
                selectStatement += " and recipient_tp.company like '%" + company + "%'";
            }
            if ((dt_received != null) && (dt1_received != null)) {
                selectStatement += " and DATE(xy_message_archive.dt_received) between'" + dt_received + "' and '" + dt1_received + "'";
            }
            if ((dt_received != null) && (dt1_received == null)) {
                selectStatement += " and DATE(xy_message_archive.dt_received) >='" + dt_received + "'";
            }
            if ((dt_received == null) && (dt1_received != null)) {
                selectStatement += " and DATE(xy_message_archive.dt_received)<='" + dt1_received + "'";
            }
        }
        selectStatement += " order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, new Integer(id).intValue());
        rs = ps.executeQuery();
        while (rs.next()) {
            MessagesList messageslist = new MessagesList();
            int messageId = rs.getInt("N_MESSAGE_ID");
            messageslist.setId(messageId);
            String status = rs.getString("B_STATUS");
            messageslist.setStatus(status);
            String docid = rs.getString("vch_document_id");
            messageslist.setDocid(docid);
            String ttype = rs.getString("vch_description");
            messageslist.setType(ttype);
            String to = rs.getString("recipient_tp.company");
            messageslist.setTo(to);
            String date = rs.getString("dt_received");
            messageslist.setDate(date);
            String fname = rs.getString("vch_path");
            messageslist.setFilename(fname);
            messageLists.add(messageslist);
        }
        return messageLists;
    }

    public List findInBoundMessageByStatus(int status, int id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List messages = new ArrayList();
        log.info(" findInBoundMessageByStatus..id.."+id+"..status.."+status);
        String selectStatement = "SELECT m.N_MESSAGE_ID FROM xy_message m inner join xy_order o on m.vch_document_id=o.orderno WHERE m.b_status=? and m.N_RECIPIENT_PARTNER_ID= ? order by m.N_MESSAGE_ID desc";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, status);
        ps.setInt(2, id);
        rs = ps.executeQuery();
        while (rs.next()) {
            messages.add(rs.getString("N_MESSAGE_ID"));
        }
        return messages;
    }

    public int findInBoundMessageCountByStatus(int status, int id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info(" findInBoundMessageCountByStatus..id.."+id+"..status.."+status);
        String selectStatement = "SELECT count(m.N_MESSAGE_ID) as countNumber FROM xy_message m inner join xy_order o on m.vch_document_id=o.orderno WHERE m.b_status=? and m.N_RECIPIENT_PARTNER_ID= ?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, status);
        ps.setInt(2, id);
        rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt("countNumber");
        return count;
    }

    public void insertMessage(String orderNumber, int id, int transactionType, int supId, String fileName) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info(" insertMessage..id.."+id+"..orderNumber.."+orderNumber+"..transactionType.."+transactionType+"..supId.."+supId+".fileName.."+fileName);
        String insertStatement = "insert into xy_message (VCH_DOCUMENT_ID, N_SENDER_PARTNER_ID,N_TRANSACTION_TYPE,N_RECIPIENT_PARTNER_ID,VCH_PATH) VALUES (?,?,?,?,?)";
        ps = connection.prepareStatement(insertStatement);
        ps.setString(1, orderNumber);
        ps.setInt(2, id);
        ps.setInt(3, transactionType);
        ps.setInt(4, supId);
        ps.setString(5, fileName);
        ps.executeUpdate();
    }

    public void insertCXMLMessage(String orderNumber, int id, int transactionType, int supId, String fileName,int orderid) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info(" insertMessage..id.."+id+"..orderNumber.."+orderNumber+"..transactionType.."+transactionType+"..supId.."+supId+".fileName.."+fileName);
        String insertStatement = "insert into xy_message (VCH_DOCUMENT_ID, N_SENDER_PARTNER_ID,N_TRANSACTION_TYPE,N_RECIPIENT_PARTNER_ID,VCH_PATH,ORDERID) VALUES (?,?,?,?,?,?)";
        ps = connection.prepareStatement(insertStatement);
        ps.setString(1, orderNumber);
        ps.setInt(2, id);
        ps.setInt(3, transactionType);
        ps.setInt(4, supId);
        ps.setString(5, fileName);
        ps.setInt(6, orderid);
        ps.executeUpdate();
    }
}
