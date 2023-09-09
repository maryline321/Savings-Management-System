package com.SavingsManagementSystem.repository;

import com.SavingsManagementSystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.customer.id = :customerId")
    List<Transaction> findByCustomerId(Long customerId);
}
