package com.example.assignment02.model;

import com.example.assignment02.entity.Category;

import java.util.List;

public interface CategoryModel {
    Category save(Category category); // lưu thông tin.

    List<Category> findAll();

    Category findById(int id);

    Category update(int id, Category updateCategory);

    boolean delete(int id);
}
