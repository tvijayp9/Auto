/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *
 * @author Terry
 */
public class SessionCheckListener implements ServletContextListener {

    private ServletContext sc;

    public void contextInitialized(ServletContextEvent event) {
        sc = event.getServletContext();
        InputStream is = sc.getResourceAsStream(sc.getInitParameter("uncheckSession"));
        SAXReader reader = new SAXReader();
        try {
            Set<String> s = new HashSet();
            Document document = reader.read(is);
            Iterator i = document.getRootElement().elementIterator();
            while (i.hasNext()) {
                Element e = (Element) i.next();
                s.add(e.getText());
            }
            sc.setAttribute("uncheckRequest", s);
        } catch (DocumentException de) {
            de.printStackTrace();
        }

    }

    public void contextDestroyed(ServletContextEvent event) {
        sc.removeAttribute("uncheckRequest");
    }
}
