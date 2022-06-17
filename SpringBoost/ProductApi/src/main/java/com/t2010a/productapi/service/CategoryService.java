package com.t2010a.productapi.service;

import com.t2010a.productapi.entity.Category;
import com.t2010a.productapi.entity.Order;
import com.t2010a.productapi.repository.CategoryRepository;
import com.t2010a.productapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Iterable<Category> findAll(){
        Iterable<Category> listCategory = categoryRepository.findAll();
        ArrayList<Category> validListCategory = new ArrayList<>();
        for (Category category: listCategory
        ) {
            if (category.getStatus() == 1){
                validListCategory.add(category);
            }
        }
        return validListCategory;
    }

    public Optional<Category> findById(UUID id){
        Optional<Category> foundCategory = categoryRepository.findById(id);
        if (!foundCategory.isPresent()){
            ResponseEntity.badRequest().build();
        }
        Category validCategory = foundCategory.get();
        if (validCategory.getStatus() == 1){
            return foundCategory;
        }else {
            return Optional.empty();
        }
    }

    public Category save(Category category){
        return categoryRepository.save(category);
    }

    public void deleteById(UUID id){
        Optional<Category> deleteCategory = categoryRepository.findById(id);
        if (!deleteCategory.isPresent()){
            ResponseEntity.badRequest().build();
        }
        Category deletedCategory = deleteCategory.get();
        deletedCategory.setStatus(0);
        categoryRepository.save(deletedCategory);
    }
}
