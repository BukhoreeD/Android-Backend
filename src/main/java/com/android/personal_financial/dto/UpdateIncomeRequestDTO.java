package com.android.personal_financial.dto;

import java.math.BigDecimal;

public class UpdateIncomeRequestDTO {
    private BigDecimal incomeTarget;

    // getters and setters
    public BigDecimal getIncomeTarget() {
        return incomeTarget;
    }

    public void setIncomeTarget(BigDecimal incomeTarget) {
        this.incomeTarget = incomeTarget;
    }
}
