/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import com.nexus.domain.JQGridRow;
import com.nexus.domain.User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Terry
 */
public interface UserDAO {

    void insertUser(int memberId,String username,String password,String contact,String email) throws SQLException;

        int findRoleAssignedToUserCount(int roleId) throws SQLException;

    int findAllUsersCountById(int id) throws SQLException;

    List<JQGridRow> findAllUsersById(int id, int start, int limit, String sidx, String sord) throws SQLException;

    int checkUserId(String userId) throws SQLException;

    void insertUserWithRole(int id, String userId, String password, String name, String email, int type) throws SQLException;

    void deleteUserById(int id) throws SQLException;

    User findUserById(int id) throws SQLException;

    void updateUser(int id,String password,String name,String email,int type) throws SQLException;
}
