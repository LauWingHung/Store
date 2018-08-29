package cn.lau1yach.store.service.serviceImp;

import cn.lau1yach.store.dao.UserDao;
import cn.lau1yach.store.dao.daoImp.UserDaoImp;
import cn.lau1yach.store.domain.User;
import cn.lau1yach.store.service.UserService;
import cn.lau1yach.store.utils.BeanFactory;

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
//    public static void main(String[] arg) {
//        System.out.println("hello world!");
//    }
    UserDao userDao = (UserDao) BeanFactory.createObject("UserDao");
    @Override
    public void userRegist(User user) throws SQLException {
//        实现注册

        userDao.userRegist(user);
    }

    @Override
    public boolean userActive(String code) throws SQLException {
//        实现注册
//        对DB发送select * from user where code=?
        User user=userDao.userActive(code);
        if (null!=user){
//        可以根据激活码查询到一个用户
//            修改用户的状态，清楚激活码
            user.setState(1);
            user.setCode(null);
//            对数据库执行一次真实的更行操作 update user set state=1,code=null where uid=?
//            update user set username=?,password=?,name=?,email=?,telephone=?,birthday=?,sex=?,state=?,code=?,where uid=?
            userDao.updateUser(user);
            return true;
        }else {
//            不可以根据激活码查询到一个用户
            return false;
        }

    }

    @Override
    public User userLogin(User user) throws SQLException {
//        此处：可以利用异常在模块直接传递数据
//        select * from user where username=? and password=?
        User uu=userDao.userLogin(user);
        if (null==uu){
            throw new RuntimeException("密码有误！");
        }else if (uu.getState()==0){
            throw new RuntimeException("账户未激活！");
        }else {
            return uu;
        }

    }


}