/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import java.sql.SQLException;

/**
 *
 * @author Terry
 */
public interface RulesDAO {

   String findRuleValueMapping(String rule,int buyerid,int supid) throws SQLException;
   public String findRuleValueMapping(String rule, int nexusid) throws SQLException;
}
