package cn.lau1yach.store.web.servlet;

import cn.lau1yach.store.domain.*;
import cn.lau1yach.store.service.OrderService;
import cn.lau1yach.store.service.serviceImp.OrderServiceImp;
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
        User user= (User) req.getSession().getAttribute("userLogin");
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
//            设置当前的订单项属于哪个订单：程序的角度体验订单项和订单对应关系
            orderItem.setOrder(order);
            order.getList().add(orderItem);
        }
//        调用业务层功能，保存订单
        OrderService orderService=new OrderServiceImp();
//        将订单数据，用户的数据，订单下所有的订单项都传递到service层
        orderService.saveOrder(order);
//        清空购物车
        cart.clearCart();
//        将订单放入request
        req.setAttribute("order",order);
//        转发/jsp/order_info.jsp
        return "/jsp/order_info.jsp";
    }
    public String findMyOrdersWithPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//        获取用户信息
        User user= (User) req.getSession().getAttribute("userLogin");
//        获取当前页
        int curNum=Integer.parseInt(req.getParameter("num"));
//        调用业务层功能：查询当前用户订单信息，返回PageModel
        OrderService orderService=new OrderServiceImp();
//        select * form orders where uid=? limit ?,?
//        PageModel:1分页参数，2url，3当前用户的当前页订单（集合），每笔订单上对应的订单项，以及订单项对应的商品信息
        PageModel pm=orderService.findMyOrdersWithPage(user,curNum);
//        将PageModel放入req
        req.setAttribute("page",pm);
//        转发到/jsp/order_list.jsp
        return "/jsp/order_list.jsp";
    }
}
