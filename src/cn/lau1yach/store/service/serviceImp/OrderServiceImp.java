package cn.lau1yach.store.service.serviceImp;

import cn.lau1yach.store.dao.OrderDao;
import cn.lau1yach.store.dao.daoImp.OrderDaoImp;
import cn.lau1yach.store.domain.Order;
import cn.lau1yach.store.domain.OrderItem;
import cn.lau1yach.store.service.OrderService;
import cn.lau1yach.store.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderServiceImp implements OrderService {
    @Override
    public void saveOrder(Order order) throws Exception {
//        保存订单和订单下所有的订单项（同时成功，失败）

//        try {
//            JDBCUtils.startTransaction();
//            OrderDao orderDao=new OrderDaoImp();
//            orderDao.saveOrder(order);
//            for (OrderItem item:order.getList()) {
//                orderDao.saveOrderItem(item);
//            }
//            JDBCUtils.commitAndClose();
//        } catch (SQLException e) {
//            JDBCUtils.rollbackAndClose();
//        }

        Connection conn=null;
        try {
//            获取连接
            conn=JDBCUtils.getConnection();
//            开启事务
            conn.setAutoCommit(false);
//            保存订单
            OrderDao orderDao=new OrderDaoImp();
            orderDao.saveOrder(conn,order);
//            保存订单项
            for (OrderItem item:order.getList()) {
                orderDao.saveOrderItem(conn,item);
            }
//            提交
            conn.commit();
        }catch (Exception e){
//            回滚
            conn.rollback();
        }
    }
}
