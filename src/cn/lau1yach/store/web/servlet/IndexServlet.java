package cn.lau1yach.store.web.servlet;

import cn.lau1yach.store.domain.Product;
import cn.lau1yach.store.service.ProductService;
import cn.lau1yach.store.service.serviceImp.ProductServiceImp;
import cn.lau1yach.store.web.base.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "IndexServlet",urlPatterns="/IndexServlet")
public class IndexServlet extends BaseServlet {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
////        调用业务层功能：获取全部分类信息，返回集合
//        CategoryService categoryService=new CategoryServiceImp();
//        List<Category>list=categoryService.getAllCats();
////        将返回的集合放入request
//        req.setAttribute("allCats",list);
//        调用业务层查询最新商品和最热商品，返回2个集合
        ProductService productService= new ProductServiceImp();
        List<Product>list1=productService.findHots();
        List<Product>list2=productService.findNews();
//        将两个集合放入request
        req.setAttribute("hots",list1);
        req.setAttribute("news",list2);
//        转发到真实首页
        return "/jsp/index.jsp";
    }
}
