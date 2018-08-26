package cn.lau1yach.store.dao;

import cn.lau1yach.store.domain.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> getAllCats() throws  Exception;

    void addCategory(Category category) throws  Exception;
}
