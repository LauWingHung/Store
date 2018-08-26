package cn.lau1yach.store.dao.daoImp;

import cn.lau1yach.store.dao.CategoryDao;
import cn.lau1yach.store.domain.Category;
import cn.lau1yach.store.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2018/8/16
 * Time: 17:37
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class CategoryDaoImp implements CategoryDao {
    @Override
    public List<Category> getAllCats() throws Exception {
        String sql="select * from category";
        QueryRunner qr= new QueryRunner(JDBCUtils.getDataSource());
        return qr.query(sql,new BeanListHandler<Category>(Category.class));
    }

    @Override
    public void addCategory(Category category) throws Exception {
        String sql="insert into category values (? ,?)";
        QueryRunner qr= new QueryRunner(JDBCUtils.getDataSource());
        qr.update(sql,category.getCid(),category.getCname());
    }
}