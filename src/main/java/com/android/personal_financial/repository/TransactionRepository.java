package com.android.personal_financial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.android.personal_financial.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    // custom query methods
}