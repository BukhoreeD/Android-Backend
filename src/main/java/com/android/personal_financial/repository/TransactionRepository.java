package com.android.personal_financial.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.android.personal_financial.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    // custom query methods
    @Query("SELECT COALESCE(SUM(CASE WHEN t.type.typeName = 'income' THEN t.transactionAmount ELSE -t.transactionAmount END), 0) FROM Transaction t")
    BigDecimal getTotalBalance();

    @Query("SELECT SUM(t.transactionAmount) FROM Transaction t WHERE t.transactionDate >= :startDate AND t.transactionDate < :endDate AND t.type.typeName = 'INCOME'")
    BigDecimal getTotalIncome(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("SELECT SUM(t.transactionAmount) FROM Transaction t WHERE t.transactionDate >= :startDate AND t.transactionDate < :endDate AND t.type.typeName = 'EXPENSE'")
    BigDecimal getTotalExpense(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}