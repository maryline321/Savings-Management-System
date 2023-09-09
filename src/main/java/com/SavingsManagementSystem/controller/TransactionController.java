package com.SavingsManagementSystem.controller;

import com.SavingsManagementSystem.Response.RestResponse;
import com.SavingsManagementSystem.model.Transaction;
import com.SavingsManagementSystem.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@Slf4j
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/get_allTransactions")
    public ResponseEntity<List<Transaction>> getAllTransactions() {

        try{
            List<Transaction> transactions = transactionService.getAllTransactions();
            return new ResponseEntity<>(transactions, HttpStatus.OK);

        }catch (Exception e){
            log.error("Error occurred while retrieving customers ", e);
            return new ResponseEntity(new RestResponse( true, "Error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/get_transaction/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Transaction transaction = transactionService.getTransactionById(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }
    @PostMapping("/customer/{customerId}")
    public ResponseEntity<Transaction> createTransactionForCustomer(
            @PathVariable Long customerId,
            @RequestBody Transaction transaction) {

        try{
            Transaction createdTransaction = transactionService.customertransaction(customerId, transaction);
            return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);

        }catch (Exception e){
            log.error("Error occurred while creating transaction ", e);
            return new ResponseEntity(new RestResponse( true, "Error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{customerId}/total-savings")
    public ResponseEntity<Double> getTotalSavingsForCustomer(@PathVariable Long customerId) {
        Double totalSavings = transactionService.getTotalSavingsAmountForCustomer(customerId);
        return new ResponseEntity<>(totalSavings, HttpStatus.OK);
    }



}
