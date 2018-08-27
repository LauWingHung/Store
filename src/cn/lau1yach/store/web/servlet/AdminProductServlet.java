package cn.lau1yach.store.web.servlet;

import cn.lau1yach.store.domain.PageModel;
import cn.lau1yach.store.service.ProductService;
import cn.lau1yach.store.service.serviceImp.ProductServiceImp;
import cn.lau1yach.store.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminProductServlet",urlPatterns="/AdminProductServlet")
public class AdminProductServlet extends BaseServlet {
    public String findAllProductsWithPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//        获取当前页
        int curNum=Integer.parseInt(req.getParameter("num"));
//        调用业务层全部商品信息返回PageModel
        ProductService productService=new ProductServiceImp();
        PageModel pm=productService.findAllProductsWithPage(curNum);
//        将PageModel放入req
        req.setAttribute("page",pm);
//        转发到/admin/product/list.jsp
        return "/admin/product/list.jsp";
    }
}
