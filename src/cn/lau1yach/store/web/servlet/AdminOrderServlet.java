package cn.lau1yach.store.web.servlet;

import cn.lau1yach.store.domain.Order;
import cn.lau1yach.store.service.OrderService;
import cn.lau1yach.store.service.serviceImp.OrderServiceImp;
import cn.lau1yach.store.web.base.BaseServlet;
import net.sf.json.JSONArray;


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
//    findOrderByOidWithAjax
    public String findOrderByOidWithAjax(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//        服务端获取到订单ID
        String oid=req.getParameter("id");
//        查询这个订单下所有订单及订单项以及对应的商品信息,返回集合
        OrderService orderService =new OrderServiceImp();
        Order order=orderService.findOrderByOid(oid);
//        将返回的集合转换为JSon格式字符串,
        String jsonStr= JSONArray.fromObject(order.getList()).toString();
//        响应到客户端
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().println(jsonStr);
        return null;
    }
//    updateOrderByOid
    public String updateOrderByOid(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//        获取订单ID
        String oid=req.getParameter("oid");
//        根据订单ID查询订单
        OrderService orderService=new OrderServiceImp();
        Order order=orderService.findOrderByOid(oid);
//        设置订单状态
        order.setState(3);
//        修改订单信息
        orderService.updateOrder(order);
//        重定向到查询已发货订单
        resp.sendRedirect("/Store/AdminOrderServlet?method=findOrders&state=3");
        return null;
    }
}
