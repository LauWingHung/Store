package cn.lau1yach.store.dao.daoImp;

import cn.lau1yach.store.dao.OrderDao;
import cn.lau1yach.store.domain.Order;
import cn.lau1yach.store.domain.OrderItem;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;

public class OrderDaoImp implements OrderDao {
    @Override
    public void saveOrder(Connection conn, Order order) throws Exception {
        String sql="INSERT INTO orders VALUES(?,?,?,?,?,?,?,?)";
        QueryRunner qr =new QueryRunner();
        Object[] params={order.getOid(),order.getOrdertime(),order.getTotal(),order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getUser().getUid()};
        qr.update(conn,sql,params);
    }

    @Override
    public void saveOrderItem(Connection conn, OrderItem item) throws Exception {
        String sql="INSERT INTO orderitem VALUES(?,?,?,?,?)";
        QueryRunner qr =new QueryRunner();
        Object[] params={item.getItemid(),item.getQuantity(),item.getTotal(),item.getProduct().getPid(),item.getOrder().getOid(),};
        qr.update(conn,sql,params);
    }
}
