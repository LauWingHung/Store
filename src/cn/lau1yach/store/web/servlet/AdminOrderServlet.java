package cn.lau1yach.store.web.servlet;

import cn.lau1yach.store.domain.Order;
import cn.lau1yach.store.service.OrderService;
import cn.lau1yach.store.service.serviceImp.OrderServiceImp;
import cn.lau1yach.store.web.base.BaseServlet;


import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

@WebServlet(name = "AdminOrderServlet", urlPatterns = "/AdminOrderServlet")
public class AdminOrderServlet extends BaseServlet {

    public String findOrders(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        OrderService orderService=new OrderServiceImp();
        List<Order> list=null;
        String st= req.getParameter("state");
        if (null==st||"".equals(st)) {
//        获取到全部订单
            list = orderService.finAllOrders();
        }else {
            list=orderService.finAllOrders(st);
        }
//        将全部订单放入req
        req.setAttribute("allOrders",list);
//        转发到/admin/order/list.jsp
        return "/admin/order/list.jsp";
    }
}
