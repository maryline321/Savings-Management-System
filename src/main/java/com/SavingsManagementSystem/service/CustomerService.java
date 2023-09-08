package com.SavingsManagementSystem.service;

import com.SavingsManagementSystem.model.Customer;
import com.SavingsManagementSystem.repository.CustomerRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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

        Customer firstname = customerRepo.findByFirstName(customer.getFirstName());
        if (firstname != null) {throw new DuplicateKeyException("Firstname already exists" );}

        Customer lastName = customerRepo.findByLastName(customer.getLastName());
        if (lastName != null) {throw new DuplicateKeyException("Lastname already exists" );}

        Customer email = customerRepo.findByEmail(customer.getEmail());
        if (email != null) { throw new DuplicateKeyException("email already exists. ");}

        Customer memberNumber = customerRepo.findByMemberNumber(customer.getMemberNumber());
        if (memberNumber != null) {
            throw new DuplicateKeyException("member number already exists. " );}

        Customer phoneNumber = customerRepo.findByPhoneNumber(customer.getPhoneNumber());
        if (phoneNumber != null) {
            throw new DuplicateKeyException("Phone number already exists."); }

        Customer idNumber = customerRepo.findByIdNumber(customer.getIdNumber());
        if (idNumber != null) {
            throw new DuplicateKeyException("ID number already exists");}


        return customerRepo.save(customer);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer currentCustomer = getCustomerById(id);

        currentCustomer.setFirstName(updatedCustomer.getFirstName());
        currentCustomer.setLastName(updatedCustomer.getLastName());
        currentCustomer.setIdNumber(updatedCustomer.getIdNumber());
        currentCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
        currentCustomer.setEmail(updatedCustomer.getEmail());
        currentCustomer.setMemberNumber(updatedCustomer.getMemberNumber());

        return customerRepo.save(currentCustomer);
    }

    public void deleteCustomer(Long id) {
        Customer currentCustomer = getCustomerById(id);
        customerRepo.delete(currentCustomer);
    }
}
