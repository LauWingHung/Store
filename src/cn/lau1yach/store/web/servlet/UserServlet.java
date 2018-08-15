package cn.lau1yach.store.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import cn.lau1yach.store.web.base.BaseServlet;

@WebServlet(name = "UserServlet")
public class UserServlet extends BaseServlet {
    public String regisUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/jsp/register.jsp";
    }

}
