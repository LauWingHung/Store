package cn.lau1yach.store.service.serviceImp;

import cn.lau1yach.store.dao.ProductDao;
import cn.lau1yach.store.dao.daoImp.ProductDaoImp;
import cn.lau1yach.store.domain.PageModel;
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

    @Override
    public Product findProductByPid(String pid) throws Exception {
        return productDao.findProductByPid(pid);

    }

    @Override
    public PageModel findProductsByCidWithPage(String cid, int curNum) throws Exception {
//        创建PageModel对象，目的：计算分页参数
//        统计当前分类下商品个数 select count(*) from product where cid=?
        int totalRecords=productDao.findTotalRecords(cid);
        PageModel pm= new PageModel(curNum,totalRecords,12);
//        关联集合 select * from product where cid =? limit ?,?
        List list=productDao.findProductsByCidWithPage(cid,pm.getStartIndex(),pm.getPageSize());
        pm.setList(list);
//        关联URL
        pm.setUrl("ProductServlet?method=findProductsByCidWithPage&cid="+cid);
        return pm;
    }
}