package cn.lau1yach.store.dao;

import cn.lau1yach.store.domain.User;

import java.sql.SQLException;

/**
 * @author lau1yach
 */
public interface UserDao {
    void userRegist(User user) throws SQLException;
    User userActive(String code) throws SQLException;
    void updateUser(User user) throws SQLException;
    User userLogin(User user) throws SQLException;
}
