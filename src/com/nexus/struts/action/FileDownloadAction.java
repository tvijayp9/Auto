/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.struts.action;

import java.io.InputStream;

import java.util.Collection;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.Action;
import org.apache.struts2.interceptor.ServletRequestAware;
import javax.servlet.http.HttpServletRequest;
import com.nexus.services.ServiceFinder;
import com.nexus.web.common.MessagesList;
import java.io.File;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 * Demonstrates file resource download.
 * Set filePath to the local file resource to download,
 * relative to the application root ("/images/struts.gif").
 *
 */
public class FileDownloadAction implements Action, ServletRequestAware {

    Logger log=Logger.getLogger(FileDownloadAction.class);
    private Collection col2;
    private HttpServletRequest request;

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }
    private String name;
    private InputStream inputStream;
    private String downloadName;

    //holds the content size of the downloaded file//method for downloading file

    public String execute() throws Exception {
        {
            String messageid = request.getParameter("inqmessageid");
            try {
                com.nexus.dao.SpringHibernateDAO partnerDao = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
                col2 = partnerDao.getMessage(messageid);
            } catch (Exception e) {
            }
            ArrayList list = new ArrayList();
            list = (ArrayList) col2;
            MessagesList mlist = new MessagesList();
            mlist = (MessagesList) list.get(0);
            String filename = mlist.getFilename();
            //setContentDisposition(filename);
            String fromid = mlist.getFrom();
            filename = "partners/" + fromid + "/OUTQ/" + filename.trim();
            File theFile = new File(filename);
            log.info("filepath " + theFile.getAbsolutePath());
            log.info("filename " + getName());
            //setName("/images/test.pdf");
            setName(filename);

            //this.inputStream = ServletActionContext.getServletContext().getResourceAsStream(getName());
            if (!getName().equalsIgnoreCase("")) {
                return Action.SUCCESS;
            } else {
                //handle error            
                return Action.ERROR;
            }
        }
    }

    public String downloadOUTQFile() {
        String messageid = request.getParameter("inqmessageid");
        log.info("messageid " + messageid);
        try {
            com.nexus.dao.SpringHibernateDAO partnerDao = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            col2 = partnerDao.getMessage(messageid);
        } catch (Exception e) {
        }
        ArrayList list = new ArrayList();
        list = (ArrayList) col2;
        MessagesList mlist = new MessagesList();
        mlist = (MessagesList) list.get(0);
        downloadName = mlist.getFilename();
        //setContentDisposition("filename="+filename);
        //System.out.println("filename " + filename);
        String formid = mlist.getFrom();
        name = "/partners/" + formid + "/OUT/" + downloadName.trim();
        File theFile = new File("webapps/Nexus" + name);
        log.info("downloadOUTQFile..filepath " + theFile.getAbsolutePath());
        log.info("downloadOUTQFile..filename " + getName());
        //this.inputStream = ServletActionContext.getServletContext().getResourceAsStream(getName());
        if ((!getName().equalsIgnoreCase("")) && (theFile.exists())) {
            return Action.SUCCESS;
        } else {
            //handle error            
            return Action.ERROR;
        }
    }//write setter getter methods

    public String downloadINQFile() {
        com.nexus.dao.SpringHibernateDAO partnerDao = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
        String messageid = request.getParameter("inqmessageid");
        //String extName = "";
        log.info("downloadINQFile..messageid " + messageid);
        try {
            col2 = partnerDao.getMessage(messageid);
        } catch (Exception e) {
            log.error("Exception Message:", e);
        }
        ArrayList list = new ArrayList();
        list = (ArrayList) col2;
        MessagesList mlist = new MessagesList();
        mlist = (MessagesList) list.get(0);
        /*try {
        extName = partnerDao.getExtName(mlist.getTo(), mlist.getType(), "1");
        } catch (Exception e) {
        e.printStackTrace();
        }*/
        downloadName = mlist.getFilename();
        //System.out.println("filename " + filename);
        String toid = mlist.getTo();
        /*if(extName.equalsIgnoreCase(""))
        {
        name = "Nexus/partners/" + toid + "/IN/" + downloadName.trim();
        }
        else
        {
        name = "Nexus/partners/" + toid + "/IN/" + downloadName.trim() +"."+ extName;
        }*/

        //setContentDisposition("filename="+filename);
        name = "/partners/" + toid + "/IN/" + downloadName.trim();
        File theFile = new File("webapps/Nexus" + name);
        log.info("downloadINQFile..filepath " + theFile.getAbsolutePath());
        log.info("downloadINQFile..filename " + getName());
        //setName(filename);
        if ((!getName().equalsIgnoreCase(""))&& (theFile.exists())) {
            return Action.SUCCESS;
        } else {
            return Action.ERROR;
        }
    }//write setter getter methods

    public InputStream getInputStream() throws Exception {
        return ServletActionContext.getServletContext().getResourceAsStream(getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the downloadName
     */
    public String getDownloadName() {
        return downloadName;
    }

    /**
     * @param downloadName the downloadName to set
     */
    public void setDownloadName(String downloadName) {
        this.downloadName = downloadName;
    }
}

