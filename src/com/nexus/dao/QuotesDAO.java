/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import com.nexus.domain.Quote;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Terry
 */
public interface QuotesDAO {

    String checkQRN(int id,int supplierId) throws SQLException;

        void insertQuote(String qrn, String quoteName, int id, int supplierId) throws SQLException;

        int findQId(String qrn, String quoteName,int id, int supplierId) throws SQLException;

        int findQuotesListCount(int id, int supplierId) throws SQLException;

    List findQuotesList(int id, int supplierId, int start, int limit, String sidx, String sord) throws SQLException;

        void deleteQuotes(int id) throws SQLException;

        String findQRNByQuoteId(int quoteId) throws SQLException;

        void getParametersForPrintQuote(int qid,HashMap reportParams) throws SQLException;

        Quote findQuoteDetailsByQid(int qid) throws SQLException;
}
