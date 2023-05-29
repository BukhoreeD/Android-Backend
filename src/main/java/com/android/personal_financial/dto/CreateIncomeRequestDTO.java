package com.android.personal_financial.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateIncomeRequestDTO {
    private BigDecimal incomeTarget;
    private LocalDate monthYear;

    // getters and setters
    public BigDecimal getIncomeTarget() {
        return incomeTarget;
    }

    public void setIncomeTarget(BigDecimal incomeTarget) {
        this.incomeTarget = incomeTarget;
    }

    public LocalDate getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(LocalDate monthYear) {
        this.monthYear = monthYear;
    }
}
