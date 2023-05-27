package com.android.personal_financial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.android.personal_financial.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // custom query methods
}