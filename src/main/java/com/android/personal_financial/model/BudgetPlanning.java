package com.android.personal_financial.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Budget_Planning")
public class BudgetPlanning {
    @Id
    @Column(name = "budget_id")
    private int budgetId;

    @Column(name = "income_target", precision = 10, scale = 2)
    private BigDecimal incomeTarget;

    @Column(name = "expense_target", precision = 10, scale = 2)
    private BigDecimal expenseTarget;

    @Column(name = "month_year", nullable = false)
    private LocalDate monthYear;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserAccount user;

    // constructors, getters, and setters

    public BudgetPlanning(int budgetId, BigDecimal incomeTarget, BigDecimal expenseTarget, LocalDate monthYear,
            UserAccount user) {
        this.budgetId = budgetId;
        this.incomeTarget = incomeTarget;
        this.expenseTarget = expenseTarget;
        this.monthYear = monthYear;
        this.user = user;
    }

    public int getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }

    public BigDecimal getIncomeTarget() {
        return incomeTarget;
    }

    public void setIncomeTarget(BigDecimal incomeTarget) {
        this.incomeTarget = incomeTarget;
    }

    public BigDecimal getExpenseTarget() {
        return expenseTarget;
    }

    public void setExpenseTarget(BigDecimal expenseTarget) {
        this.expenseTarget = expenseTarget;
    }

    public LocalDate getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(LocalDate monthYear) {
        this.monthYear = monthYear;
    }

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }
}