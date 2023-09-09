package com.SavingsManagementSystem.service;

import com.SavingsManagementSystem.model.Customer;
import com.SavingsManagementSystem.model.Transaction;
import com.SavingsManagementSystem.repository.TransactionRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found with id: " + id));
    }

    public Transaction customertransaction(Long customerId, Transaction transaction) {
        Customer customer = getTransactionById(customerId).getCustomer();
        transaction.setCustomer(customer);
        return transactionRepo.save(transaction);
    }

    public Double getTotalSavingsAmountForCustomer(Long customerId) {

        List<Transaction> transactions = transactionRepo.findByCustomerId(customerId);

        Double totalSavings = transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();

        return totalSavings;
    }

}
