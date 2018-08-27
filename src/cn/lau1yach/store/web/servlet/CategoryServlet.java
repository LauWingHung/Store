package cn.lau1yach.store.web.servlet;

import cn.lau1yach.store.domain.Category;
import cn.lau1yach.store.service.CategoryService;
import cn.lau1yach.store.service.serviceImp.CategoryServiceImp;
import cn.lau1yach.store.utils.JedisUtils;
import cn.lau1yach.store.web.base.BaseServlet;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "CategoryServlet",urlPatterns="/CategoryServlet")
public class CategoryServlet extends BaseServlet {

    public String findAllCats(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//        在redis中获取全部分类信息
        Jedis jedis=JedisUtils.getJedis();
        String jsonStr=jedis.get("allCats");
        if (null==jsonStr||"".equals(jsonStr)){
//        调用业务层获取全部分类
            CategoryService categoryService =new CategoryServiceImp();
            List<Category> list = categoryService.getAllCats();
//        将全部分类转换为JSon格式的数据
            jsonStr=JSONArray.fromObject(list).toString();
            System.out.println(jsonStr);
//            将获取到的JSON格式的数据存入redis
            jedis.set("allCats",jsonStr);
            System.out.println("redis缓存中没数据");
//        将全部分类信息响应到客户端
//        告诉浏览器本次响应的数据是JSON数据的字符串
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().print(jsonStr);
        }else {
            System.out.println("redis缓存中有数据");
            //        将全部分类信息响应到客户端
//        告诉浏览器本次响应的数据是JSON数据的字符串
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().print(jsonStr);
        }
        JedisUtils.closeJedis(jedis);
        return null;
    }
}
