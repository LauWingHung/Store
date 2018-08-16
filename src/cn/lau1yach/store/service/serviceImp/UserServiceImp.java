package cn.lau1yach.store.service.serviceImp;

import cn.lau1yach.store.dao.UserDao;
import cn.lau1yach.store.dao.daoImp.UserDaoImp;
import cn.lau1yach.store.domain.User;
import cn.lau1yach.store.service.UserService;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2018/8/15
 * Time: 11:17
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class UserServiceImp implements UserService {
    public static void main(String[] arg) {
        System.out.println("hello world!");
    }

    @Override
    public void userRegist(User user) throws SQLException {
//        实现注册
        UserDao userDao =new UserDaoImp();
        userDao.userRegist(user);
    }

    @Override
    public boolean userActive(String code) throws SQLException {
//        实现注册
        UserDao userDao =new UserDaoImp();
//        对DB发送select * from user where code=?
        User user=userDao.userActive(code);
        if (null!=user){
//        可以根据激活码查询到一个用户
//            修改用户的状态，清楚激活码
            user.setState(1);
            user.setCode(null);
//            对数据库执行一次真实的更行操作
            userDao.updateUser(user);
            return true;
        }else {
//            不可以根据激活码查询到一个用户
            return false;
        }

    }


}