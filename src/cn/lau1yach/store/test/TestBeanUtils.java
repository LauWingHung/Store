package cn.lau1yach.store.test;

import cn.lau1yach.store.domain.User;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2018/8/15
 * Time: 15:26
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class TestBeanUtils {
    @Test
    public void test01() throws Exception {
//        模拟request.getParameterMap();
        Map<String,String[]> map = new HashMap<String,String[]>();
        map.put("username",new String[]{"tom"});
        map.put("password",new String[]{"1234"});
        //        创建用户对象
        User user = new User();
        //        利用 BeanUtils自动填充数据
        BeanUtils.populate(user,map);
        System.out.println(user);
    }
    @Test
    public void test02() throws Exception {
//        模拟request.getParameterMap();
        Map<String,String[]> map = new HashMap<String,String[]>();
        map.put("username",new String[]{"tom"});
        map.put("password",new String[]{"1234"});
        map.put("birthday",new String[]{"1995-12-01"});
//        创建用户对象
        User user = new User();
//        BeanUtils找到User.class文件上有setBirthday这个方法，要执行，将“1995-12-1”转换成Date类型
//        BeanUtils不知道这个字符串的时间格式是什么，以下三行代码设置时间转换格式
        // 1_创建时间类型的转换器
        DateConverter dt = new DateConverter();
        // 2_设置转换的格式
        dt.setPattern("yyyy-MM-dd");
        // 3_注册转换器
        ConvertUtils.register(dt, java.util.Date.class);
//        利用 BeanUtils自动填充数据
        BeanUtils.populate(user,map);
        System.out.println(user);
    }
}