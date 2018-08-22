package cn.lau1yach.store.web.servlet;

import cn.lau1yach.store.domain.*;
import cn.lau1yach.store.utils.UUIDUtils;
import cn.lau1yach.store.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "OrderServlet" )
public class OrderServlet extends BaseServlet {
//    将购物车中的信息以订单的形式保存

    public String saveOrder(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//        确认用户登录状态
        User user= (User) req.getSession().getAttribute("loginUser");
        if (null==user){
            req.setAttribute("msg","请登录后再下单");
            return "/jsp/info.jsp";
        }
//        获取购物车
       Cart cart= (Cart) req.getSession().getAttribute("cart");
//        创建订单对象，为订单对象赋值
        Order order= new Order();
        order.setOid(UUIDUtils.getCode());
        order.setOrdertime(new Date());
        order.setTotal(cart.getTotal());
        order.setState(1);
        order.setUser(user);
//        遍历购物项的同时，创建订单项，为订单项赋值
        for (CartItem item:cart.getCartItems()) {
            OrderItem orderItem=new OrderItem();
            orderItem.setItemid(UUIDUtils.getCode());
            orderItem.setQuantity(item.getNum());
            orderItem.setTotal(item.getSubTotal());
            orderItem.setProduct(item.getProduct());
            orderItem.setOrder(order);
        }
//        调用业务层功能，保存订单
//        清空购物车
//        将订单放入request
//        转发/jsp/order_info.jsp
        return "/jsp/order_info.jsp";
    }
}
