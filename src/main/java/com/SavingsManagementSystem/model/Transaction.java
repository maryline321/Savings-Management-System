package com.SavingsManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_type", nullable = false, unique = true)
    private String transactionType;

    @Column(name = "transaction_date", nullable = false)
    private Date transactionDate;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @Column(name ="transaction_amount", nullable = false)
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;
}
