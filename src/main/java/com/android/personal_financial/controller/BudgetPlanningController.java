package com.android.personal_financial.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.android.personal_financial.dto.CreateExpenseRequestDTO;
import com.android.personal_financial.dto.CreateIncomeRequestDTO;
import com.android.personal_financial.dto.UpdateExpenseRequestDTO;
import com.android.personal_financial.dto.UpdateIncomeRequestDTO;
import com.android.personal_financial.model.BudgetPlanning;
import com.android.personal_financial.service.BudgetPlanningService;

@RestController
@RequestMapping("/budgets")
public class BudgetPlanningController {
    private final BudgetPlanningService budgetPlanningService;

    public BudgetPlanningController(BudgetPlanningService budgetPlanningService) {
        this.budgetPlanningService = budgetPlanningService;
    }

    @PostMapping("/income")
    public ResponseEntity<BudgetPlanning> createIncomeTarget(@RequestBody CreateIncomeRequestDTO request) {
        BudgetPlanning budgetPlanning = budgetPlanningService.createIncomeTarget(request);
        return ResponseEntity.ok(budgetPlanning);
    }

    @PostMapping("/expense")
    public ResponseEntity<BudgetPlanning> createExpenseTarget(@RequestBody CreateExpenseRequestDTO request) {
        BudgetPlanning budgetPlanning = budgetPlanningService.createExpenseTarget(request);
        return ResponseEntity.ok(budgetPlanning);
    }

    @PutMapping("/income/{budgetId}")
    public ResponseEntity<BudgetPlanning> updateIncomeTarget(
            @PathVariable int budgetId,
            @RequestBody UpdateIncomeRequestDTO request
    ) {
        BudgetPlanning budgetPlanning = budgetPlanningService.updateIncomeTarget(budgetId, request);
        return ResponseEntity.ok(budgetPlanning);
    }

    @PutMapping("/expense/{budgetId}")
    public ResponseEntity<BudgetPlanning> updateExpenseTarget(
            @PathVariable int budgetId,
            @RequestBody UpdateExpenseRequestDTO request
    ) {
        BudgetPlanning budgetPlanning = budgetPlanningService.updateExpenseTarget(budgetId, request);
        return ResponseEntity.ok(budgetPlanning);
    }

    @DeleteMapping("/{budgetId}")
    public ResponseEntity<Void> deleteBudgetPlanning(@PathVariable int budgetId) {
        budgetPlanningService.deleteBudgetPlanning(budgetId);
        return ResponseEntity.noContent().build();
    }
}

