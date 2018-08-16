package cn.lau1yach.store.web.servlet;

import cn.lau1yach.store.domain.Category;
import cn.lau1yach.store.service.CategoryService;
import cn.lau1yach.store.service.serviceImp.CategoryServiceImp;
import cn.lau1yach.store.web.base.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "IndexServlet")
public class IndexServlet extends BaseServlet {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//        调用业务层功能：获取全部分类信息，返回集合
        CategoryService categoryService=new CategoryServiceImp();
        List<Category>list=categoryService.getAllCats();
//        将返回的集合放入request
        req.setAttribute("all Cats",list);
//        转发到真实首页
        return "/jsp/index.jsp";
    }
}
