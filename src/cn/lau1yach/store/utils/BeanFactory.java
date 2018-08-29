package cn.lau1yach.store.utils;

import cn.lau1yach.store.dao.UserDao;
import cn.lau1yach.store.domain.User;
import org.dom4j.Document;

import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

/**
 * @author lyhpl
 * @Title: BeanFactory
 * @ProjectName Store
 * @Description: TODO
 * @date 8/29/2018  5:15 PM
 */
public class BeanFactory {
//    解析XML
    public static  Object createObject(String name){
        try {
//        通过传递过来的name获取application.xml中的name对应的class的值

//        获取到document对象
            SAXReader reader=new SAXReader();
//        如果获取application.xml文件的输入流(application.xml必须在src目录下)
            InputStream is=BeanFactory.class.getClassLoader().getResourceAsStream("application.xml");
            Document doc=reader.read(is);
//        通过document对象获取根节点 beans
            Element rootElement = doc.getRootElement();
//        通过根节点获取到根节点下面的子节点bean返回集合
            List<Element> list = rootElement.elements();
//        遍历集合,判断元素上的id是否和当前name一致
            for (Element element : list) {
    //            element相当于beans节点下的每个bean
    //            获取到当前节点的属性
    //        如果一致,获取到当前元素上class属性值
                String id=element.attributeValue("id");
                if(id.equals(name)){
                    String str=element.attributeValue("class");
    //        通过反射创建对象并且返回
                    Class clazz=Class.forName(str);
    //        通过class值通过反射创建对象返回
                    return  clazz.newInstance();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
        UserDao userDao=(UserDao)BeanFactory.createObject("UserDao");
        User user=new User();
        user.setUsername("aaa");
        user.setPassword("aaa");
        User us = userDao.userLogin(user);
        System.out.println(us);
    }
}
