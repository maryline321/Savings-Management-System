package com.SavingsManagementSystem.service;

import com.SavingsManagementSystem.model.Customer;
import com.SavingsManagementSystem.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepo customerRepo;

    public void createCustomer(Customer customer) {
        customerRepo.save(customer);
    }

}
