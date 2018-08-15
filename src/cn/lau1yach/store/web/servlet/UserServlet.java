package cn.lau1yach.store.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import cn.lau1yach.store.web.base.BaseServlet;

@WebServlet(name = "UserServlet")
public class UserServlet extends BaseServlet {
    public String regisUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/jsp/register.jsp";
    }
    public String regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        接收表单的参数
        Map<String, String[]> map = request.getParameterMap();
//        遍历map中的数据
        Set<String> keySet = map.keySet();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()){
            String str = iterator.next();
            System.out.println(str);
            String[] strs = map.get(str);
            for (String string:strs) {
                System.out.println(string);
            }
            System.out.println();
            
        }

//        调用业务层注册功能
//        注册成功，向用户邮箱发送信息，跳转到提示页面
//        注册失败，跳转到提示页面
        return "";
    }

}
