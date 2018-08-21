package cn.lau1yach.store.web.servlet;

import cn.lau1yach.store.domain.PageModel;
import cn.lau1yach.store.domain.Product;
import cn.lau1yach.store.service.ProductService;
import cn.lau1yach.store.service.serviceImp.ProductServiceImp;
import cn.lau1yach.store.web.base.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductServlet")
public class ProductServlet extends BaseServlet {
//    findProductByPid
public String findProductByPid(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//    获取商品PID
    String pid= req.getParameter("pid");
//    根据商品PID查询商品信息
    ProductService productService= new ProductServiceImp();
    Product product=productService.findProductByPid(pid);

//    将获取到的商品放入request
    req.setAttribute("product",product);
//    转发到/jsp/product_info.jsp
    return "/jsp/product_info.jsp";
}
public String findProductsByCidWithPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//    获取cid，num
    String cid=req.getParameter("cid");
    int curNum=Integer.parseInt(req.getParameter("num"));
//    调用业务层功能：以分页形式查询当前类别下的商品信息
    ProductService productService=new ProductServiceImp();
    PageModel pm=productService.findProductsByCidWithPage(cid,curNum);
//    返回PageModel对象

//    将PageModel对象放入request
    req.setAttribute("page",pm);
//    转发到/jsp/product_list.jsp
    return "/jsp/product_list.jsp";

}
}

