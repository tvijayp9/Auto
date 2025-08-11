/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.struts.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.util.StrutsTypeConverter;

/**
 *
 * @author Terry
 */
public class OrderDateConverter extends StrutsTypeConverter {

    private DateFormat dateFormatter;

    public OrderDateConverter() {
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    }

    public Object convertFromString(Map context, String[] values, Class toClass) {
        if (values.length ==1) {
            String orderDate = values[0];
            Date date = null;
            try {
                date = dateFormatter.parse(orderDate);
            } catch (ParseException pe) {
                pe.fillInStackTrace();
            }
            return date;
        } else {
            return null;
        }
    }

    public String convertToString(Map context, Object o) {
        if (o != null) {
            Date orderDate = (Date) o;
            return dateFormatter.format(orderDate);
        } else {
            return "";
        }
    }
}
