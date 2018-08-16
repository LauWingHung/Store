package cn.lau1yach.store.web.servlet;

import cn.lau1yach.store.domain.User;
import cn.lau1yach.store.service.UserService;
import cn.lau1yach.store.service.serviceImp.UserServiceImp;
import cn.lau1yach.store.utils.MailUtils;
import cn.lau1yach.store.utils.MyBeanUtils;
import cn.lau1yach.store.utils.UUIDUtils;
import cn.lau1yach.store.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "UserServlet")
public class UserServlet extends BaseServlet {
    public String regisUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/jsp/register.jsp";
    }

    public UserServlet() {
    }

    public String regist(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        接收表单的参数
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        MyBeanUtils.populate(user, map);
//        为用户的其它属性赋值
        user.setUid(UUIDUtils.getId());
        user.setState(0);
        user.setCode(UUIDUtils.getCode());
        System.out.println(user);

//        遍历map中的数据
//        Set<String> keySet = map.keySet();
//        Iterator<String> iterator = keySet.iterator();
//        while (iterator.hasNext()){
//            String str = iterator.next();
//            System.out.println(str);
//            String[] strs = map.get(str);
//            for (String string:strs) {
//                System.out.println(string);
//            }
//            System.out.println();
//
//        }

//        调用业务层注册功能
        UserService userService =new UserServiceImp();
        try {
            userService.userRegist(user);
//        注册成功，向用户邮箱发送信息，跳转到提示页面
            MailUtils.sendMail(user.getEmail(),user.getCode());
            request.setAttribute("msg","用户注册成功，请激活！");
        } catch (Exception e) {
//        注册失败，跳转到提示页面
            request.setAttribute("msg","用户注册失败，请重新注册！");


        }
        return "/jsp/info.jsp";

    }
    public String active(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        获取激活码
        String code=request.getParameter("code");
//        调用业务层激活功能
        UserService userService=new UserServiceImp();
        boolean flag=userService.userActive(code);
//        进行激活提示
        if (flag==true){
//            用户激活成功，向request放入提示信息，转发到登录页面
            request.setAttribute("msg","用户激活成功，请登录！");
            return "/jsp/login.jsp";
        }else{
//            用户激活失败，向request放入提示信息，转发到提示页面
            request.setAttribute("msg","用户激活失败，请重新激活！");
            return "/jsp/info.jsp";
        }


    }



}
