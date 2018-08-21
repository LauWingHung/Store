package cn.lau1yach.store.dao.daoImp;

import cn.lau1yach.store.dao.ProductDao;
import cn.lau1yach.store.domain.Product;
import cn.lau1yach.store.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

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
    public int findTotalRecords(String cid) throws Exception {
        String sql="select count(*) from product where cid =?";
        QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
        Long num=(Long) qr.query(sql,new ScalarHandler(),cid);
        return num.intValue();
    }

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

    @Override
    public Product findProductByPid(String pid) throws Exception {
        String sql="select * from product where pid=?";
        QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
        return qr.query(sql,new BeanHandler<Product>(Product.class),pid);
    }

    @Override
    public List findProductsByCidWithPage(String cid, int startIndex, int pageSize) throws Exception {
        String sql ="select * from product where cid =? limit ?,?";
        QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
        return qr.query(sql,new BeanListHandler<Product>(Product.class),cid,startIndex,pageSize);

    }
}