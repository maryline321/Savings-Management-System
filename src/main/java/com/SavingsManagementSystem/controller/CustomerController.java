package com.SavingsManagementSystem.controller;

import com.SavingsManagementSystem.Response.RestResponse;
import com.SavingsManagementSystem.model.Customer;
import com.SavingsManagementSystem.repository.CustomerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
@Slf4j

public class CustomerController {

    @Autowired
    CustomerRepo customerRepo;

    @GetMapping("/get_all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        try {
            List<Customer> customers = customerRepo.findAll();
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while retrieving customers: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get_customer/{customerId}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long customerId) {
        try {
            Optional<Customer> customer = customerRepo.findById(customerId);
            if (customer.isPresent()) {
                return new ResponseEntity<>(customer.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new RestResponse(true, "Customer not found"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error occurred while retrieving customer with ID {}: {}", customerId, e.getMessage(), e);
            return new ResponseEntity<>(new RestResponse(true, "Error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create_customer")
    public ResponseEntity<RestResponse> createCustomer(@RequestBody Customer customer) {
        try {
            log.info("Creating customer: {}", customer);
            customerRepo.save(customer);
            return new ResponseEntity<>(new RestResponse(false, "Customer created"), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while creating customer: {}", e.getMessage(), e);
            return new ResponseEntity<>(new RestResponse(true, "Error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update_customer")
    public ResponseEntity<RestResponse> updateCustomer(@RequestBody Customer customer) {
        try {
            log.info("Updating customer: {}", customer);
            customerRepo.save(customer);
            return new ResponseEntity<>(new RestResponse(false, "Customer updated"), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while updating customer: {}", e.getMessage(), e);
            return new ResponseEntity<>(new RestResponse(true, "Error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete_customer/{customerId}")
    public ResponseEntity<RestResponse> deleteCustomer(@PathVariable Long customerId) {
        try {
            log.info("Deleting customer with ID: {}", customerId);
            customerRepo.deleteById(customerId);
            return new ResponseEntity<>(new RestResponse(false, "Customer deleted"), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while deleting customer: {}", e.getMessage(), e);
            return new ResponseEntity<>(new RestResponse(true, "Error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}