package cn.lau1yach.store.service;

import cn.lau1yach.store.domain.PageModel;
import cn.lau1yach.store.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> findHots() throws Exception;
    List<Product> findNews() throws Exception;
    Product findProductByPid(String pid)throws Exception;
    PageModel findProductsByCidWithPage(String cid, int curNum)throws Exception;

    PageModel findAllProductsWithPage(int curNum)throws Exception;

    void saveProduct(Product product)throws Exception;
}
