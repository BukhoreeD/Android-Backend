package com.android.personal_financial.dto;

import java.math.BigDecimal;

public class UpdateExpenseRequestDTO {
    private BigDecimal expenseTarget;

    // getters and setters
    public BigDecimal getExpenseTarget() {
        return expenseTarget;
    }

    public void setExpenseTarget(BigDecimal expenseTarget) {
        this.expenseTarget = expenseTarget;
    }
}
