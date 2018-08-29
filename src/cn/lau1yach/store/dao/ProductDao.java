package cn.lau1yach.store.dao;

import cn.lau1yach.store.domain.Product;

import java.util.List;

public interface ProductDao {
    int findTotalRecords(String cid) throws Exception;
    List<Product> findHots() throws Exception;
    List<Product> findHNews() throws Exception;
    Product findProductByPid(String pid) throws Exception;

    List findProductsByCidWithPage(String cid, int startIndex, int pageSize) throws Exception;

    int findTotalRecords() throws Exception;

    List<Product> findAllProductsWithPage(int startIndex, int pageSize) throws Exception;

    void saveProduct(Product product)throws Exception;
}
