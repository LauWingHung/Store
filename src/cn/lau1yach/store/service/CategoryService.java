package cn.lau1yach.store.service;

import cn.lau1yach.store.domain.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCats() throws Exception;

    void addCategory(Category category) throws Exception;
}
