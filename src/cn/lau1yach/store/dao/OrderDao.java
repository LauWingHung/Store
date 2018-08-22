package cn.lau1yach.store.dao;

import cn.lau1yach.store.domain.Order;
import cn.lau1yach.store.domain.OrderItem;

import java.sql.Connection;

public interface OrderDao {
    void saveOrder(Connection conn, Order order)throws Exception;

    void saveOrderItem(Connection conn, OrderItem item) throws Exception;
}
