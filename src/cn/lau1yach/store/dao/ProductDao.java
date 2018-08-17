package cn.lau1yach.store.dao;

import cn.lau1yach.store.domain.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findHots() throws Exception;
    List<Product> findHNews() throws Exception;
}
