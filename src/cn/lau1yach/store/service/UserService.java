package cn.lau1yach.store.service;

import cn.lau1yach.store.domain.User;

import java.sql.SQLException;

public interface UserService {
    void userRegist(User user) throws SQLException;
    boolean userActive(String code) throws SQLException;
    User userLogin(User user) throws SQLException;
}
