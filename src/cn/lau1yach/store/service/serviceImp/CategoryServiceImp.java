package cn.lau1yach.store.service.serviceImp;

import cn.lau1yach.store.dao.CategoryDao;
import cn.lau1yach.store.dao.daoImp.CategoryDaoImp;
import cn.lau1yach.store.domain.Category;
import cn.lau1yach.store.service.CategoryService;
import cn.lau1yach.store.utils.BeanFactory;
import cn.lau1yach.store.utils.JedisUtils;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lyhp
 * Date: 2018/8/16
 * Time: 17:36
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class CategoryServiceImp implements CategoryService {
    CategoryDao categoryDao= (CategoryDao) BeanFactory.createObject("CategoryDao");
    @Override
    public List<Category> getAllCats() throws Exception {

        return categoryDao.getAllCats();
    }

    @Override
    public void addCategory(Category category)  throws Exception{
//        本质是向mysql插入一条数据
        categoryDao.addCategory(category);
//        更新redis缓存
        Jedis jedisu=JedisUtils.getJedis();
        jedisu.del("allCats");
        JedisUtils.closeJedis(jedisu);
    }
}