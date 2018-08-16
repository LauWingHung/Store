package cn.lau1yach.store.dao.daoImp;

import cn.lau1yach.store.dao.UserDao;
import cn.lau1yach.store.domain.User;
import cn.lau1yach.store.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2018/8/15
 * Time: 11:14
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class UserDaoImp implements UserDao {
    public static void main(String[] arg) {
        System.out.println("hello world!");
    }

    @Override
    public void userRegist(User user) throws SQLException {
        String sql="INSERT INTO USER VALUES(?,?,?,?,?,?,?,?,?,?)";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        Object[] params ={user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode()};
        qr.update(sql,params);
    }

    @Override
    public User userActive(String code) throws SQLException {
        String sql="select * from user where code=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        User user=qr.query(sql,new BeanHandler<User>(User.class),code);
        return user;
    }

    @Override
    public void updateUser(User user) throws SQLException {

    }
}