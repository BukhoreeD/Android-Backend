package com.android.personal_financial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.android.personal_financial.model.BudgetPlanning;

public interface BudgetPlanningRepository extends JpaRepository<BudgetPlanning, Integer> {
    // custom query methods
}