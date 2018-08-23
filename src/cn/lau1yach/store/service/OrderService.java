package cn.lau1yach.store.service;

import cn.lau1yach.store.domain.Order;
import cn.lau1yach.store.domain.PageModel;
import cn.lau1yach.store.domain.User;

public interface OrderService {
    void saveOrder(Order order)throws Exception;

    PageModel findMyOrdersWithPage(User user, int curNum)throws Exception;
}
