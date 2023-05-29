package com.android.personal_financial.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateExpenseRequestDTO {
    private BigDecimal expenseTarget;
    private LocalDate monthYear;

    // getters and setters
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
}
