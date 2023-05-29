package com.android.personal_financial.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Budget_Planning")
public class BudgetPlanning {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    // constructors
    public BudgetPlanning() {}

    public BudgetPlanning(BigDecimal incomeTarget, BigDecimal expenseTarget, LocalDate monthYear,
            UserAccount user) {
        this.incomeTarget = incomeTarget;
        this.expenseTarget = expenseTarget;
        this.monthYear = monthYear;
        this.user = user;
    }

    // getters and setters
    public int getBudgetId() {
        return budgetId;
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