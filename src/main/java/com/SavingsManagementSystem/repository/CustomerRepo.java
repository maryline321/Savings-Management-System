package com.SavingsManagementSystem.repository;

import com.SavingsManagementSystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    Customer findByLastName(String lastName);
    Customer findByFirstName(String firstname);
    Customer findByEmail(String email);
    Customer findByMemberNumber(String memberNumber);
    Customer findByPhoneNumber(String phoneNumber);
    Customer findByIdNumber(int idNumber);

}
