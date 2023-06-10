package com.android.personal_financial.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.android.personal_financial.model.Transaction;
import com.android.personal_financial.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable int transactionId) {
        Transaction transaction = transactionService.getTransactionById(transactionId);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction createdTransaction = transactionService.createTransaction(transaction);
        return ResponseEntity.ok(createdTransaction);
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<Transaction> updateTransaction(
            @PathVariable int transactionId,
            @RequestBody Transaction transaction
    ) {
        Transaction updatedTransaction = transactionService.updateTransaction(transactionId, transaction);
        return ResponseEntity.ok(updatedTransaction);
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable int transactionId) {
        transactionService.deleteTransaction(transactionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/totalBalance")
    public ResponseEntity<BigDecimal> getTotalBalance() {
        BigDecimal totalBalance = transactionService.getTotalBalance();
        return ResponseEntity.ok(totalBalance);
    }

    @GetMapping("/income/total")
    public ResponseEntity<Double> getTotalIncome(
            @RequestParam(name = "month") int month,
            @RequestParam(name = "year") int year
    ) {
        Double totalIncome = transactionService.getTotalIncome(month, year);
        return ResponseEntity.ok(totalIncome);
    }

    @GetMapping("/expense/total")
    public ResponseEntity<Double> getTotalExpense(
            @RequestParam(name = "month") int month,
            @RequestParam(name = "year") int year
    ) {
        Double totalExpense = transactionService.getTotalExpense(month, year);
        return ResponseEntity.ok(totalExpense);
    }
}
