package cn.lau1yach.store.web.filter;

import cn.lau1yach.store.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = " PriviledgeFilter")
public class PriviledgeFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest myReq= (HttpServletRequest) req;
//        判断当前的session中是否存在登录成功的用户
        User user= (User) myReq.getSession().getAttribute("userLogin");

        if (null!=user){
//        如果存在，放行
            chain.doFilter(req, resp);
        }
//        如果不存在，跳转到提示页面
        myReq.setAttribute("msg","请用户登录之后再访问！");
        myReq.getRequestDispatcher("/jsp/info.jsp").forward(req,resp);

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
