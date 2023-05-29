package com.android.personal_financial.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.android.personal_financial.model.Transaction;
import com.android.personal_financial.repository.TransactionRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(int transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found"));
    }

    public Transaction createTransaction(Transaction transaction) {
        // Perform any necessary validations or business logic
        // before saving the transaction
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(int transactionId, Transaction transaction) {
        // Retrieve the existing transaction from the database
        Transaction existingTransaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found"));

        // Update the properties of the existing transaction
        existingTransaction.setTransactionDate(transaction.getTransactionDate());
        existingTransaction.setTransactionAmount(transaction.getTransactionAmount());
        existingTransaction.setTransactionNote(transaction.getTransactionNote());
        existingTransaction.setCategory(transaction.getCategory());
        existingTransaction.setType(transaction.getType());
        existingTransaction.setUser(transaction.getUser());

        // Save the updated transaction
        return transactionRepository.save(existingTransaction);
    }

    public void deleteTransaction(int transactionId) {
        // Delete the transaction with the given transactionId
        transactionRepository.deleteById(transactionId);
    }

    public BigDecimal getTotalBalance() {
        // Retrieve the total balance by summing the transaction amounts
        return transactionRepository.getTotalBalance();
    }
}
