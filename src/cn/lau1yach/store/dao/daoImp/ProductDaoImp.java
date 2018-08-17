package cn.lau1yach.store.dao.daoImp;

import cn.lau1yach.store.dao.ProductDao;
import cn.lau1yach.store.domain.Product;
import cn.lau1yach.store.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2018/8/17
 * Time: 14:08
 * To change this template use File | Settings | File Templates.
 * Description:
 * @author lau1yach
 */
public class ProductDaoImp implements ProductDao {
    @Override
    public List<Product> findHots() throws Exception {
        String sql="SELECT * FROM product WHERE pflag=0 AND is_hot=1 ORDER BY pdate DESC LIMIT 0,9";
        QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
        return qr.query(sql,new BeanListHandler<Product>(Product.class));

    }

    @Override
    public List<Product> findHNews() throws Exception {
        String sql="SELECT * FROM product WHERE pflag=0 ORDER BY pdate DESC LIMIT 0,9";
        QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
        return qr.query(sql,new BeanListHandler<Product>(Product.class));

    }
}