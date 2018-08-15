package cn.lau1yach.store.dao;

import cn.lau1yach.store.domain.User;

import java.sql.SQLException;

public interface UserDao {
    void userRegist(User user) throws SQLException;
}
