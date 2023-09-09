package com.SavingsManagementSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Product")

public class SavingsProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "product_name", length = 100, nullable = false)
    private String productName;

    @Column(name = "description", length = 200, nullable = false)
    private String description;

    @Column(name = "minimum_deposit", length = 100, nullable = false)
    private double minimumDeposit;

    @Column(name = "maximum_deposit", length = 100, nullable = false)
    private double maximumDeposit;

    @Column(name = "status", length = 100, nullable = false)
    private String status;

    @Column(name = "product_code", length = 180, nullable = false, unique = true)
    private String productCode;
}
