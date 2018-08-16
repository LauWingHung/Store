package cn.lau1yach.store.web.servlet;

import cn.lau1yach.store.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "IndexServlet")
public class IndexServlet extends BaseServlet {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        调用业务层功能：获取全部分类信息，返回集合

//        将返回的集合放入request
//        转发到真实首页
        return "/jsp/index.jsp";
    }
}
