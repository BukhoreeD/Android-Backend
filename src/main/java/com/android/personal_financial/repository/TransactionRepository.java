package com.android.personal_financial.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.android.personal_financial.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query("SELECT COALESCE(SUM(CASE WHEN t.type.typeName = 'income' THEN t.transactionAmount ELSE -t.transactionAmount END), 0) FROM Transaction t")
    BigDecimal getTotalBalance();
    // custom query methods
}