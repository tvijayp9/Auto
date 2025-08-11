package com.nexus.struts.action;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.nexus.services.ServiceFinder;
import com.nexus.web.common.IndustryType;
import org.apache.struts2.ServletActionContext;
import com.nexus.web.common.UsersList;
import com.opensymphony.xwork2.util.ValueStack;
import org.apache.log4j.Logger;

public class AdminAction extends ActionSupport implements ServletRequestAware {

    Logger log=Logger.getLogger(AdminAction.class);
    private HttpServletRequest request;
    private Collection col1;
    private Collection col2;
    private Collection col3;
    HttpSession session = null;
    String firstname = "";
    String lastname = "";
    String username = "";
    String pass = "";
    String email = "";
    private Collection countries;
    private ArrayList industryTypeList;
    private ArrayList industryNameList;
    Map industryNameMap = new HashMap();
    String industryType = "";
    String industryName = "";

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public List getIndustryTypeList() {
        return industryTypeList;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    public List getIndustryNameList() {
        ValueStack stack = ServletActionContext.getValueStack(ServletActionContext.getRequest());
        Object IndustryType = stack.findValue("top");
        if (IndustryType != null && IndustryType instanceof IndustryType) {
            List l = (List) industryNameMap.get(IndustryType);
            return l;
        }
        return Collections.EMPTY_LIST;
    }

    public Collection getCountries() {
        return countries;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String execute() throws Exception {
        try {
            session = request.getSession();
            com.nexus.dao.SpringHibernateDAO messages = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            col1 = messages.getMessageList(session.getAttribute("ID").toString());
            request.setAttribute("messagelist", col1);
            if(col1!=null)
            log.info("messagelist size:" + col1.size());
            return SUCCESS;
        } catch (Exception e) {
            addActionError("Invalid user name or password! Please try again!");
            return ERROR;
        }
    }

    public String userlist() throws Exception {
        try {
            session = request.getSession();
            com.nexus.dao.SpringHibernateDAO dao = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            col1 = dao.getMyUsersList(session.getAttribute("ID").toString());
            if(col1!=null)
                log.info("userlist size:" + col1.size());
        } catch (Exception e) {
            addActionError("Invalid user name or password! Please try again!");
            log.error("Exception Message:", e);
            return ERROR;
        }
        return SUCCESS;
    }

    public String mydetail() throws Exception {
        try {
            session = request.getSession();
            com.nexus.dao.SpringHibernateDAO dao = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            col1 = dao.getMemberDetails(session.getAttribute("ID").toString());
            ArrayList mlist = new ArrayList();
            mlist = (ArrayList) col1;
            UsersList userslist = new UsersList();
            userslist = (UsersList) mlist.get(0);
            
            this.setUsername(userslist.getLoginid());
            this.setIndustryType((""+userslist.getSiccode()).toString().substring(0,2));
            this.setIndustryName((""+userslist.getSiccode()).toString().substring(0,2));
            log.info(this.getUsername());
            countries = dao.getCountryList();
            industryTypeList = (ArrayList) dao.getIndustryGroupList();
            for (int i = 0; i < industryTypeList.size(); i++) {
                IndustryType list = new IndustryType();
                list = (IndustryType) industryTypeList.get(i);
                String groupid = list.getKey().toString();
                industryNameList = (ArrayList) dao.getIndustryNameList(groupid);
                industryNameMap.put(list, industryNameList);
            }
        } catch (Exception e) {
            addActionError("Invalid user name or password! Please try again!");
            log.error("Exception Message:", e);
            return ERROR;
        }
        return SUCCESS;
    }

    public String create() throws Exception {
        try {
            session = request.getSession();
            String name = request.getParameter("firstname") + " " + request.getParameter("lastname");
            String userid = request.getParameter("username");
            String pass = request.getParameter("pass");
            String email = request.getParameter("email");
            log.info("userid=" + userid);
            com.nexus.dao.SpringHibernateDAO dao = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            boolean ValidUsernameStatus = dao.checkValidUserName(userid);
            log.info("ValidUsernameStatus=" + ValidUsernameStatus);
            if ((ValidUsernameStatus == false) && (!userid.equalsIgnoreCase(""))) {
                dao.addUser(session.getAttribute("ID").toString(), userid, pass, name, -1, email);
            } else {
                addActionError("Duplicate username ! Please try with different username!");
                return ERROR;
            }
        } catch (Exception e) {
            addActionError("Invalid user name or password! Please try again!");
            log.error("Exception Message:", e);
            return ERROR;
        }
        return SUCCESS;
    }

    public String edit() throws Exception {
        try {
            session = request.getSession();
            String id = request.getParameter("userid");
            session.setAttribute("userid", id);
            com.nexus.dao.SpringHibernateDAO dao = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            col1 = dao.getMyUsersList(session.getAttribute("ID").toString());
            col2 = dao.getUserDetail(id);
            ArrayList list = new ArrayList();
            list = (ArrayList) col2;
            UsersList userList = new UsersList();
            for (int i = 0; i < list.size(); i++) {
                userList = (UsersList) list.get(i);
                String name = userList.getContact();
                if (name.contains(" ")) {
                    this.setFirstname(name.substring(0, name.indexOf(" ")));
                    this.setLastname(name.substring(name.indexOf(" ") + 1));
                } else {
                    this.setFirstname(name);
                }
                this.setUsername(userList.getLoginid());
                this.setEmail(userList.getEmail());
                this.setPass(userList.getCompany());
            }
        } catch (Exception e) {
            addActionError("Invalid user name or password! Please try again!");
           log.error("Exception Message:", e);
            return ERROR;
        }
        return SUCCESS;
    }

    public String update() throws Exception {
        try {
            session = request.getSession();
            String id = session.getAttribute("userid").toString();
            com.nexus.dao.SpringHibernateDAO dao = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            boolean ValidUsernameStatus = dao.checkValidUserName(this.getUsername());
            log.info("ValidUsernameStatus=" + ValidUsernameStatus);
            if ((ValidUsernameStatus == true)) {
                dao.updateUserDetail(id, this.getFirstname() + " " + this.getLastname(), this.getUsername(), this.getPass(), this.getEmail());
                col1 = dao.getMyUsersList(session.getAttribute("ID").toString());
            }
        } catch (Exception e) {
            addActionError("Invalid user name or password! Please try again!");
            log.error("Exception Message:", e);
            return ERROR;
        }
        return SUCCESS;
    }

    public String delete() throws Exception {
        try {
            session = request.getSession();
            String id = session.getAttribute("userid").toString();
            com.nexus.dao.SpringHibernateDAO dao = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            dao.deleteUserDetail(id);
            col1 = dao.getMyUsersList(session.getAttribute("ID").toString());
        } catch (Exception e) {
            addActionError("Invalid user name or password! Please try again!");
           log.error("Exception Message:", e);
            return ERROR;
        }
        return SUCCESS;
    }

    public String downloadlist() throws Exception {
        try {
            session = request.getSession();
            com.nexus.dao.SpringHibernateDAO dao = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            col1 = dao.getDownloadList();
        } catch (Exception e) {
            addActionError("Invalid user name or password! Please try again!");
           log.error("Exception Message:", e);
            return ERROR;
        }
        return SUCCESS;
    }

    public Collection getCol1() {
        log.info("messagelist size:" + col1.size());
        return col1;
    }

    public Collection getCol2() {
        log.info("inmessagelist size:" + col2.size());
        return col2;
    }

    public Collection getCol3() {
        log.info("messagelist size:" + col3.size());
        return col3;
    }
}
