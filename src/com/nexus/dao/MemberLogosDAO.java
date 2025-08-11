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
public interface MemberLogosDAO {

    String findLogoName(int nexusId) throws SQLException;
}
