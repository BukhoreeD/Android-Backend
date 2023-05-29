package com.android.personal_financial.service;

import org.springframework.stereotype.Service;

import com.android.personal_financial.dto.CreateExpenseRequestDTO;
import com.android.personal_financial.dto.CreateIncomeRequestDTO;
import com.android.personal_financial.dto.UpdateExpenseRequestDTO;
import com.android.personal_financial.dto.UpdateIncomeRequestDTO;
import com.android.personal_financial.exception.NotFoundException;
import com.android.personal_financial.model.BudgetPlanning;
import com.android.personal_financial.repository.BudgetPlanningRepository;

@Service
public class BudgetPlanningService {
    private final BudgetPlanningRepository budgetPlanningRepository;

    public BudgetPlanningService(BudgetPlanningRepository budgetPlanningRepository) {
        this.budgetPlanningRepository = budgetPlanningRepository;
    }

    public BudgetPlanning createIncomeTarget(CreateIncomeRequestDTO request) {
        BudgetPlanning budgetPlanning = new BudgetPlanning();
        budgetPlanning.setIncomeTarget(request.getIncomeTarget());
        budgetPlanning.setMonthYear(request.getMonthYear());
        budgetPlanning.setUser(request.getUser());
        return budgetPlanningRepository.save(budgetPlanning);
    }

    public BudgetPlanning createExpenseTarget(CreateExpenseRequestDTO request) {
        BudgetPlanning budgetPlanning = new BudgetPlanning();
        budgetPlanning.setExpenseTarget(request.getExpenseTarget());
        budgetPlanning.setMonthYear(request.getMonthYear());
        budgetPlanning.setUser(request.getUser());
        return budgetPlanningRepository.save(budgetPlanning);
    }

    public BudgetPlanning updateIncomeTarget(int budgetId, UpdateIncomeRequestDTO request) {
        BudgetPlanning budgetPlanning = budgetPlanningRepository.findById(budgetId)
                .orElseThrow(() -> new NotFoundException("Budget planning not found"));
        budgetPlanning.setIncomeTarget(request.getIncomeTarget());
        // Update other properties as needed
        return budgetPlanningRepository.save(budgetPlanning);
    }

    public BudgetPlanning updateExpenseTarget(int budgetId, UpdateExpenseRequestDTO request) {
        BudgetPlanning budgetPlanning = budgetPlanningRepository.findById(budgetId)
                .orElseThrow(() -> new NotFoundException("Budget planning not found"));
        budgetPlanning.setExpenseTarget(request.getExpenseTarget());
        // Update other properties as needed
        return budgetPlanningRepository.save(budgetPlanning);
    }

    public void deleteBudgetPlanning(int budgetId) {
        BudgetPlanning budgetPlanning = budgetPlanningRepository.findById(budgetId)
                .orElseThrow(() -> new NotFoundException("Budget planning not found"));
        budgetPlanningRepository.delete(budgetPlanning);
    }
}

