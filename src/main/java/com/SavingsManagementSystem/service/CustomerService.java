package com.SavingsManagementSystem.service;

import com.SavingsManagementSystem.model.Customer;
import com.SavingsManagementSystem.repository.CustomerRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
    }

    public Customer createCustomer(Customer customer) {

        return customerRepo.save(customer);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer existingCustomer = getCustomerById(id);


        existingCustomer.setFirstName(updatedCustomer.getFirstName());
        existingCustomer.setLastName(updatedCustomer.getLastName());
        existingCustomer.setIdNumber(updatedCustomer.getIdNumber());
        existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setMemberNumber(updatedCustomer.getMemberNumber());

        return customerRepo.save(existingCustomer);
    }

    public void deleteCustomer(Long id) {
        Customer existingCustomer = getCustomerById(id);
        customerRepo.delete(existingCustomer);
    }
}
