package cn.lau1yach.store.service.serviceImp;

import cn.lau1yach.store.dao.OrderDao;
import cn.lau1yach.store.dao.daoImp.OrderDaoImp;
import cn.lau1yach.store.domain.Order;
import cn.lau1yach.store.domain.OrderItem;
import cn.lau1yach.store.domain.PageModel;
import cn.lau1yach.store.domain.User;
import cn.lau1yach.store.service.OrderService;
import cn.lau1yach.store.utils.BeanFactory;
import cn.lau1yach.store.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderServiceImp implements OrderService {
    OrderDao orderDao= (OrderDao) BeanFactory.createObject("OrderDao");

    @Override
    public void saveOrder(Order order) throws SQLException {
        //保存订单和订单下所有的订单项(同时成功,失败)
		/*try {
			JDBCUtils.startTransaction();
			OrderDao orderDao=new OrderDaoImp();
			orderDao.saveOrder(order);
			for(OrderItem item:order.getList()){
				orderDao.saveOrderItem(item);
			}
			JDBCUtils.commitAndClose();
		} catch (Exception e) {
			JDBCUtils.rollbackAndClose();
		}
		*/

        Connection conn=null;
        try {
            //获取连接
            conn=JDBCUtils.getConnection();
            //开启事务
            conn.setAutoCommit(false);
            //保存订单

            orderDao.saveOrder(conn,order);
            //保存订单项
            for (OrderItem item : order.getList()) {
                orderDao.saveOrderItem(conn,item);
            }
            //提交
            conn.commit();
        } catch (Exception e) {
            //回滚
            conn.rollback();
        }
    }

    @Override
    public PageModel findMyOrdersWithPage(User user, int curNum) throws Exception {
        //1_创建PageModel对象,目的:计算并且携带分页参数
        //select count(*) from orders where uid=?
        int totalRecords=orderDao.getTotalRecords(user);
        PageModel pm=new PageModel(curNum, totalRecords, 3);
        //2_关联集合  select * from orders where uid=? limit ? ,?
        List list=orderDao.findMyOrdersWithPage(user,pm.getStartIndex(),pm.getPageSize());
        pm.setList(list);
        //3_关联url
        pm.setUrl("OrderServlet?method=findMyOrdersWithPage");
        return pm;
    }

    @Override
    public Order findOrderByOid(String oid) throws Exception {
        return orderDao.findOrderByOid(oid);
    }

    @Override
    public void updateOrder(Order order) throws Exception {
        orderDao.updateOrder(order);
    }

    @Override
    public List<Order> finAllOrders() throws Exception {
        return orderDao.finAllOrders();
    }

    @Override
    public List<Order> finAllOrders(String st) throws Exception {
        return orderDao.finAllOrders(st);
    }


}
