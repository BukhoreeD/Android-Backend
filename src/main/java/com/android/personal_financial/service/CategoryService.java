package com.android.personal_financial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.android.personal_financial.model.Category;
import com.android.personal_financial.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category getCategoryById(int categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        return category.orElse(null);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}

