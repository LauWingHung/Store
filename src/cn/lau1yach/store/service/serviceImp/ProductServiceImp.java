package cn.lau1yach.store.service.serviceImp;

import cn.lau1yach.store.dao.ProductDao;
import cn.lau1yach.store.dao.daoImp.ProductDaoImp;
import cn.lau1yach.store.domain.Product;
import cn.lau1yach.store.service.ProductService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2018/8/17
 * Time: 14:06
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class ProductServiceImp implements ProductService {

    ProductDao productDao = new ProductDaoImp();
    @Override
    public List<Product> findHots() throws Exception {
        return productDao.findHots();
    }

    @Override
    public List<Product> findNews() throws Exception {
        return productDao.findHNews();
    }
}