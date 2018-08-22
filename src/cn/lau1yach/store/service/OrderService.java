package cn.lau1yach.store.service;

import cn.lau1yach.store.domain.Order;

public interface OrderService {
    void saveOrder(Order order)throws Exception;
}
