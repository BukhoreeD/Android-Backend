package com.android.personal_financial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.android.personal_financial.model.Type;

public interface TypeRepository extends JpaRepository<Type, String> {
    // custom query methods
}