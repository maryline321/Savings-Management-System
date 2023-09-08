package com.SavingsManagementSystem.controller;

import com.SavingsManagementSystem.Response.RestResponse;
import com.SavingsManagementSystem.model.Customer;
import com.SavingsManagementSystem.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/customer")
@Slf4j
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/get_allCustomers")
    public ResponseEntity<List<Customer>> getAllCustomers() {

        try{
            List<Customer> customers = customerService.getAllCustomers();
            return new ResponseEntity<>(customers, HttpStatus.OK);

        }catch (Exception e){
            log.error("Error occurred while retrieving customers ", e);
            return new ResponseEntity(new RestResponse( true, "Error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/get_customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/create_customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {

        try{
            Customer createdCustomer = customerService.createCustomer(customer);
            return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
        }catch (Exception e){
            log.error("Error occurred while creating Customer", e);
            return new ResponseEntity(new RestResponse( true, "Error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update_customer/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable Long id,
            @RequestBody Customer updatedCustomer) {

        try{
            Customer updated = customerService.updateCustomer(id, updatedCustomer);
            return new ResponseEntity(new RestResponse(false, "Customer Updated successfully"), HttpStatus.OK);
        }catch (Exception e){
            log.error("Error occurred while creating Customer ", e);
            return new ResponseEntity(new RestResponse( true, "Error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete_customer/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {

        try{
            customerService.deleteCustomer(id);
            return new ResponseEntity(new RestResponse(false, "Customer deleted Successfully"), HttpStatus.OK);
        }catch (Exception e){
            log.error("Error occurred while deleting customer ", e);
            return new ResponseEntity(new RestResponse(true, "Error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

