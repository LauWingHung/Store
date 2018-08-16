package cn.lau1yach.store.service.serviceImp;

import cn.lau1yach.store.dao.CategoryDao;
import cn.lau1yach.store.dao.daoImp.CategoryDaoImp;
import cn.lau1yach.store.domain.Category;
import cn.lau1yach.store.service.CategoryService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2018/8/16
 * Time: 17:36
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class CategoryServiceImp implements CategoryService {
    @Override
    public List<Category> getAllCats() throws Exception {
        CategoryDao categoryDao= new CategoryDaoImp();
        return categoryDao.getAllCats();
    }
}