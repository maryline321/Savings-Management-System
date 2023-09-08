package com.SavingsManagementSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", length = 60, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 60, nullable = false)
    private String lastName;

    @Column(name = "id_number", length = 60, unique = true, nullable = true)
    private int idNumber;

    @Column(name = "phone_number", length = 60, nullable = false, unique = true)
    private String phoneNumber;

    @Column(length = 80, unique = true)
    private String memberNumber;

    @Column(name = "email", length = 180, nullable = false, unique = true)
    private String email;

    @Column(length = 80, unique = true)
    private String accountNo;

}
