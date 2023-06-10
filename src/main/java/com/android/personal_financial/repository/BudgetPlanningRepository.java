package com.android.personal_financial.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.android.personal_financial.model.BudgetPlanning;

public interface BudgetPlanningRepository extends JpaRepository<BudgetPlanning, Integer> {

    // custom query methods
    @Query("SELECT bp FROM BudgetPlanning bp WHERE bp.monthYear >= :startDate AND bp.monthYear < :endDate AND bp.incomeTarget IS NOT NULL")
    List<BudgetPlanning> findByMonthYearYearAndMonthYearMonthAndIncomeTargetIsNotNull(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("SELECT bp FROM BudgetPlanning bp WHERE bp.monthYear >= :startDate AND bp.monthYear < :endDate AND bp.expenseTarget IS NOT NULL")
    List<BudgetPlanning> findByMonthYearYearAndMonthYearMonthAndExpenseTargetIsNotNull(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    // @Query("SELECT SUM(b.income) FROM BudgetPlanning b WHERE b.date >= :startDate AND b.date < :endDate")
    // Double getTotalIncome(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // @Query("SELECT SUM(b.expense) FROM BudgetPlanning b WHERE b.date >= :startDate AND b.date < :endDate")
    // Double getTotalExpense(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}