package com.android.personal_financial.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.android.personal_financial.repository.BudgetPlanningRepository;

@RestController
@RequestMapping("/budgets")
public class BudgetPlanningController {
    private final BudgetPlanningRepository budgetRepository;

    public BudgetPlanningController(BudgetPlanningRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    // REST API endpoints for budget operations
}
