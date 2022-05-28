package com.example.asignment.model;

import com.example.asignment.entity.Category;

import java.util.List;

public interface CategoryModel {
    Category save(Category category); // lưu thông tin.

    List<Category> findAll();

    Category findById(int id);

    Category update(int id, Category updateCategory);

    boolean delete(int id);
}
