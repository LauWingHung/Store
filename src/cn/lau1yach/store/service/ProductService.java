package cn.lau1yach.store.service;

import cn.lau1yach.store.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> findHots() throws Exception;
    List<Product> findNews() throws Exception;
}
