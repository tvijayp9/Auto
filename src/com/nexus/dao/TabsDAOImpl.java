/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import com.nexus.domain.JQGridRow;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Terry
 */
public class TabsDAOImpl implements TabsDAO {

    Logger log=Logger.getLogger(TabsDAOImpl.class);
    private SessionFactory sessionFactory;

    public int findTabsCount(int roleid) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count=0;
        log.info("findTabsCount..roleid."+roleid);
        //commented to get tabs count based on user_type
        //String selectStatement = "select count(*) from xy_tabs t inner join xy_section s on t.N_SECTION_ID=s.N_SECTION_ID where s.N_SITE_ID=?";
        String selectStatement = "select count(distinct t.ACTION_NAME) from xy_roles_tabs_mapping rtm,xy_roles r,xy_tabs t,xy_section s,user_login ul where ul.type = rtm.ROLE_ID and rtm.TAB_ID = t.N_TAB_ID and t.N_SECTION_ID = s.N_SECTION_ID and rtm.ROLE_ID = ?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, roleid);
        rs = ps.executeQuery();
        if(rs.next())
            count= rs.getInt(1);

        return count;
    }

    public List<JQGridRow> findTabs(int roleid, int start, int limit, String sidx, String sord) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<JQGridRow> rows = new ArrayList();
        log.info("findTabs..roleid."+roleid);
//        String selectStatement = "select t.N_TAB_ID as tab_id,t.N_SECTION_ID as section_id, t.VCH_DISPLAY_NAME as tab_name, s.VCH_DISPLAY_NAME as section_name " +
//                "from xy_tabs t inner join xy_section s on t.N_SECTION_ID=s.N_SECTION_ID where s.N_SITE_ID=? order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;
        String selectStatement = "select distinct t.N_TAB_ID as tab_id,t.N_SECTION_ID as section_id, t.VCH_DISPLAY_NAME as tab_name, s.VCH_DISPLAY_NAME as section_name from xy_roles_tabs_mapping rtm,xy_roles r,xy_tabs t,xy_section s,user_login ul  " +
                "where ul.type = rtm.ROLE_ID and rtm.TAB_ID = t.N_TAB_ID and t.N_SECTION_ID = s.N_SECTION_ID and rtm.ROLE_ID =? order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, roleid);
        rs = ps.executeQuery();
        while (rs.next()) {
            JQGridRow row = new JQGridRow();
            row.setId(rs.getInt("tab_id"));
            List<String> cell = new ArrayList();
            cell.add(rs.getString("tab_name"));
            cell.add(rs.getString("section_name"));
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }

    public int findTabsCountByRoleId(int roleId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findTabsCountByRoleId..roleid."+roleId);
        String selectStatement = "select count(*) from (xy_roles_tabs_mapping rtm inner join xy_tabs t on rtm.tab_id=t.n_tab_id) " +
                "inner join xy_section s on t.N_SECTION_ID=s.N_SECTION_ID where rtm.role_id=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, roleId);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public List<JQGridRow> findTabsByRoleId(int roleId, int start, int limit, String sidx, String sord) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<JQGridRow> rows = new ArrayList();
        log.info("findTabsByRoleId..roleid."+roleId);
        String selectStatement = "select t.N_TAB_ID as tab_id,t.N_SECTION_ID as section_id,t.VCH_DISPLAY_NAME as tab_name, s.VCH_DISPLAY_NAME as section_name " +
                "from (xy_roles_tabs_mapping rtm inner join xy_tabs t on rtm.tab_id=t.n_tab_id) " +
                "inner join xy_section s on t.N_SECTION_ID=s.N_SECTION_ID where rtm.role_id=? order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, roleId);
        rs = ps.executeQuery();
        while (rs.next()) {
            JQGridRow row = new JQGridRow();
            row.setId(rs.getInt("tab_id"));
            List<String> cell = new ArrayList();
            cell.add(rs.getString("tab_name"));
            cell.add(rs.getString("section_name"));
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }

    public int findtMoreTabsCountByRoleId(int roleId,int userType) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count=0;
        log.info("findtMoreTabsCountByRoleId..roleid."+roleId+"..userType.."+userType);
//        String selectStatement = "select count(*) from xy_tabs t inner join xy_section s on t.N_SECTION_ID=s.N_SECTION_ID " +
//                "where s.N_SITE_ID=? and t.n_tab_id not in (select t.n_tab_id from (xy_roles_tabs_mapping rtm " +
//                "inner join xy_tabs t on rtm.tab_id=t.n_tab_id) inner join xy_section s on t.N_SECTION_ID=s.N_SECTION_ID where rtm.role_id=?)";
        String selectStatement = "select count(distinct t.N_TAB_ID) from xy_roles_tabs_mapping rtm,xy_roles r,xy_tabs t,xy_section s,user_login ul " +
                " where ul.type = rtm.ROLE_ID and rtm.TAB_ID = t.N_TAB_ID and t.N_SECTION_ID = s.N_SECTION_ID and rtm.ROLE_ID = ? " +
                " and t.n_tab_id not in  " +
                " (select distinct t.N_TAB_ID as tab_id from xy_roles_tabs_mapping rtm,xy_tabs t,xy_section s " +
                " where rtm.TAB_ID = t.N_TAB_ID and t.N_SECTION_ID = s.N_SECTION_ID and rtm.ROLE_ID = ?)" ;
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, userType);
        ps.setInt(2, roleId);
        rs = ps.executeQuery();
        if(rs.next())
            count=rs.getInt(1);
        return count;
    }

    public List<JQGridRow> findMoreTabsByRoleId(int userType,int roleId, int start, int limit, String sidx, String sord) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<JQGridRow> rows = new ArrayList();
        log.info("findMoreTabsByRoleId..roleid."+roleId+"..userType.."+userType);
//        String selectStatement = "select t.N_TAB_ID as tab_id,t.N_SECTION_ID as section_id,t.VCH_DISPLAY_NAME as tab_name, s.VCH_DISPLAY_NAME as section_name " +
//                "from xy_tabs t inner join xy_section s on t.N_SECTION_ID=s.N_SECTION_ID where s.N_SITE_ID=? and t.n_tab_id not in " +
//                "(select t.n_tab_id from (xy_roles_tabs_mapping rtm inner join xy_tabs t on rtm.tab_id=t.n_tab_id) " +
//                "inner join xy_section s on t.N_SECTION_ID=s.N_SECTION_ID where rtm.role_id=?) order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;
        String selectStatement = "select distinct t.N_TAB_ID as tab_id,t.N_SECTION_ID as section_id, t.VCH_DISPLAY_NAME as tab_name,s.VCH_DISPLAY_NAME as section_name " +
                  " from xy_roles_tabs_mapping rtm,xy_roles r,xy_tabs t,xy_section s,user_login ul " +
                  " where ul.type = rtm.ROLE_ID and rtm.TAB_ID = t.N_TAB_ID and t.N_SECTION_ID = s.N_SECTION_ID and rtm.ROLE_ID = ? " +
                  " and t.n_tab_id not in " +
                  " (select distinct t.N_TAB_ID as tab_id from xy_roles_tabs_mapping rtm,xy_tabs t,xy_section s " +
                  " where rtm.TAB_ID = t.N_TAB_ID and t.N_SECTION_ID = s.N_SECTION_ID and rtm.ROLE_ID = ?)";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, userType);
        ps.setInt(2, roleId);
        rs = ps.executeQuery();
        while (rs.next()) {
            JQGridRow row = new JQGridRow();
            row.setId(rs.getInt("tab_id"));
            List<String> cell = new ArrayList();
            cell.add(rs.getString("tab_name"));
            cell.add(rs.getString("section_name"));
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }

    //public Properties getMySections(String loginId) throws SQLException {
    public Properties getMySections(int roleType) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
//        List sections = new ArrayList();
        Properties sections = new Properties();
        log.info("getMySections..roleType."+roleType);
        //String selectStatement = "Select N_SECTION_ID,VCH_DISPLAY_NAME,action_name,word_count from xy_section where N_SITE_ID=? order by N_SECTION_ID ";

//        String selectStatement = "select distinct(xy_section.ACTION_NAME) from xy_roles_tabs_mapping,xy_roles,xy_tabs,xy_section,user_login" +
//                " where user_login.LOGINid = ? and xy_roles.ID = user_login.type " +
//                " and user_login.Nexus_ID = xy_roles.NEXUS_ID and user_login.type != 0" +
//                " and xy_roles.ID = xy_roles_tabs_mapping.ROLE_ID" +
//                " and xy_roles_tabs_mapping.TAB_ID = xy_tabs.N_TAB_ID" +
//                " and xy_tabs.N_SECTION_ID = xy_section.N_SECTION_ID order by xy_section.N_SECTION_ID";
        String selectStatement = "select distinct(xy_section.ACTION_NAME) from xy_roles_tabs_mapping,xy_roles,xy_tabs,xy_section,user_login " +
                  " where xy_roles.ID =? and xy_roles.ID = xy_roles_tabs_mapping.ROLE_ID "+
                  " and xy_roles_tabs_mapping.TAB_ID = xy_tabs.N_TAB_ID "+
                  " and xy_tabs.N_SECTION_ID = xy_section.N_SECTION_ID order by xy_section.N_SECTION_ID";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, roleType);
        rs = ps.executeQuery();
        while (rs.next()) {
            String action = rs.getString("ACTION_NAME");

//            String selectStatement1 = "select xy_tabs.ACTION_NAME" +
//                    " from xy_roles_tabs_mapping,xy_roles,xy_tabs,xy_section,user_login" +
//                    " where user_login.LOGINid = ? " +
//                    " and xy_roles.ID = user_login.type " +
//                    " and user_login.Nexus_ID = xy_roles.NEXUS_ID " +
//                    " and user_login.type != 0" +
//                    " and xy_roles.ID = xy_roles_tabs_mapping.ROLE_ID" +
//                    " and xy_roles_tabs_mapping.TAB_ID = xy_tabs.N_TAB_ID" +
//                    " and xy_tabs.N_SECTION_ID = xy_section.N_SECTION_ID" +
//                    " and xy_section.ACTION_NAME = ? " +
//                    " order by xy_section.N_SECTION_ID,xy_roles_tabs_mapping.TAB_ID limit 1 ";
           
            String selectStatement1 = "select xy_tabs.ACTION_NAME from xy_roles_tabs_mapping,xy_roles,xy_tabs,xy_section,user_login "+
                " where xy_roles.ID = ? and xy_roles.ID = xy_roles_tabs_mapping.ROLE_ID "+
                " and xy_roles_tabs_mapping.TAB_ID = xy_tabs.N_TAB_ID "+
                " and xy_tabs.N_SECTION_ID = xy_section.N_SECTION_ID "+
                " and xy_section.ACTION_NAME = ? "+
                " order by xy_section.N_SECTION_ID,xy_roles_tabs_mapping.TAB_ID limit 1";
            log.info("selectStatement1 getMySections= " + selectStatement1);
            ps1 = connection.prepareStatement(selectStatement1);
            ps1.setInt(1, roleType);
            ps1.setString(2, action.trim());
            rs1 = ps1.executeQuery();
            while (rs1.next()) {
                String action1 = rs1.getString("ACTION_NAME");
                sections.setProperty(action, action1);
               // log.info("getmysections...action.."+action+"...action1.."+action1);
            }
        }
        return sections;
    }

    //public List getMyTabs(String loginid) throws SQLException {
      public List getMyTabs(int roleType) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List tabs = new ArrayList();
        log.info("getMyTabs..roleType."+roleType);
//        String selectStatement = "select xy_tabs.ACTION_NAME from xy_roles_tabs_mapping,xy_roles,xy_tabs,xy_section,user_login" +
//                " where user_login.LOGINid = ? and xy_roles.ID = user_login.type " +
//                " and user_login.Nexus_ID = xy_roles.NEXUS_ID" +
//                " and xy_roles.ID = xy_roles_tabs_mapping.ROLE_ID " +
//                " and xy_roles_tabs_mapping.TAB_ID = xy_tabs.N_TAB_ID and xy_tabs.N_SECTION_ID = xy_section.N_SECTION_ID ";
        String selectStatement = "select distinct xy_tabs.ACTION_NAME from xy_roles_tabs_mapping,xy_roles,xy_tabs,xy_section,user_login "+
                  " where user_login.type = xy_roles_tabs_mapping.ROLE_ID "+
                  " and xy_roles_tabs_mapping.TAB_ID = xy_tabs.N_TAB_ID "+
                  " and xy_tabs.N_SECTION_ID = xy_section.N_SECTION_ID "+
                  " and xy_roles_tabs_mapping.ROLE_ID = ?";
        log.info("selectStatement getMyTabs= " + selectStatement);
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, roleType);
        rs = ps.executeQuery();

        while (rs.next()) {
            String action = rs.getString("ACTION_NAME");
//            log.info(" N_TAB_ID = "+id +" and value = "+value +" and value = "+action );
           // log.info("getmytabs...action.."+action);
            tabs.add(action);
        }
        return tabs;
    }

    //public String getDefaultAction(String loginid) throws SQLException {
      public String getDefaultAction(int roleType) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("getDefaultAction..roleType."+roleType);
//        String selectStatement = "select xy_tabs.N_TAB_ID,xy_tabs.ACTION_NAME " +
//                " from xy_roles_tabs_mapping,xy_roles,xy_tabs,xy_section,user_login " +
//                " where user_login.LOGINid = ? and xy_roles.ID = user_login.type " +
//                " and user_login.Nexus_ID = xy_roles.NEXUS_ID and user_login.type != 0" +
//                " and xy_roles.ID = xy_roles_tabs_mapping.ROLE_ID and xy_roles_tabs_mapping.TAB_ID = xy_tabs.N_TAB_ID" +
//                " and xy_tabs.N_SECTION_ID = xy_section.N_SECTION_ID" +
//                " order by xy_section.N_SECTION_ID,xy_roles_tabs_mapping.TAB_ID LIMIT 1";
        String selectStatement = "select xy_tabs.N_TAB_ID,xy_tabs.ACTION_NAME  " +
                  " from xy_roles_tabs_mapping,xy_roles,xy_tabs,xy_section,user_login " +
                  " where xy_roles.ID =? "+
                  " and xy_roles.ID = xy_roles_tabs_mapping.ROLE_ID and xy_roles_tabs_mapping.TAB_ID = xy_tabs.N_TAB_ID " +
                  " and xy_tabs.N_SECTION_ID = xy_section.N_SECTION_ID " +
                  " order by xy_section.N_SECTION_ID,xy_roles_tabs_mapping.TAB_ID LIMIT 1 ";
        ps = connection.prepareStatement(selectStatement);
        log.info("sql = " + selectStatement);
       
        ps.setInt(1, roleType);
        rs = ps.executeQuery();
//        log.info("Hello tabs");
        String action = "";
        while (rs.next()) {
            action = rs.getString("ACTION_NAME");
        }
        return action;
    }

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
}
