/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.struts.action;

import java.io.*;
import java.util.*;
import javax.servlet.http.*;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author User
 */
public class SessionCheckFilter implements Filter {
    Logger log=Logger.getLogger(SessionCheckFilter.class);
    private ServletContext sc;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest hsrequest = (HttpServletRequest) request;
        HttpServletResponse hsresponse = (HttpServletResponse) response;
        HttpSession hs = hsrequest.getSession();
        boolean pass = false;
        if (hs.getAttribute("ID") == null) {
            String url = hsrequest.getRequestURI();
            Set<String> s = (Set<String>) sc.getAttribute("uncheckRequest");
            for (String element : s) {
                //if (url.endsWith(element)) {
                if (url.contains(element)) {
                    pass = true;
                    break;
                }
            }
        } else {
            pass = true;
        }
        if (pass) {
            chain.doFilter(request, response);
        } else {
            hsresponse.sendRedirect("sessionTimeout.jsp");
        }
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        sc = filterConfig.getServletContext();
    }
}
