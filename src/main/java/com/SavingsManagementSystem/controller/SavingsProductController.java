package com.SavingsManagementSystem.controller;

import com.SavingsManagementSystem.Response.RestResponse;
import com.SavingsManagementSystem.model.SavingsProduct;
import com.SavingsManagementSystem.repository.SavingsProductRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/saving")
@Slf4j
public class SavingsProductController {

    @Autowired
    SavingsProductRepo savingsProductRepo;

    @GetMapping("/get_allsavings")
    public ResponseEntity<?> getSavingsProduct(){
        try{
            return new ResponseEntity(savingsProductRepo.findAll(), HttpStatus.OK);
        }catch (Exception e){
            log.error("Error occurred while retrieving Savings Product ", e);
            return new ResponseEntity(new RestResponse( true, "Error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get_saving/{id}")
    public ResponseEntity<?> getSavingById(@PathVariable Long id) {
        try {

            SavingsProduct saving = savingsProductRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Savings Product not found with id: " + id));

            return new ResponseEntity<>(saving, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while retrieving Savings Product", e);
            return new ResponseEntity<>(new RestResponse(true, "Error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create_saving")
    public ResponseEntity<?> createSaving(@RequestBody SavingsProduct savingsProduct){
        try{
            log.info("Creating {}", savingsProduct);
            savingsProductRepo.save(savingsProduct);
            return new ResponseEntity(new RestResponse(false, "Savings Product created"), HttpStatus.OK);
        }catch (Exception e){
            log.error("Error occurred while creating Savings Product ", e);
            return new ResponseEntity(new RestResponse( true, "Error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update_saving/{id}")
    public ResponseEntity<SavingsProduct> updateSaving(@PathVariable long id,@RequestBody SavingsProduct savingsProduct){
        SavingsProduct updateSaving = savingsProductRepo.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Saving does not exist with id:" + id));
        updateSaving.setProductName(savingsProduct.getProductName());
        updateSaving.setDescription(savingsProduct.getDescription());
        updateSaving.setMinimumDeposit(savingsProduct.getMinimumDeposit());
        updateSaving.setMaximumDeposit(savingsProduct.getMaximumDeposit());
        updateSaving.setStatus(savingsProduct.getStatus());
        updateSaving.setProductCode(savingsProduct.getProductCode());

        savingsProductRepo.save(updateSaving);

        return ResponseEntity.ok(updateSaving);
    }


    @DeleteMapping("/delete_Saving/{id}")
    public ResponseEntity<?> deleteSaving(@PathVariable Long id) {

        try {
            SavingsProduct saving = savingsProductRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Savings Product not found with id: " + id));

            savingsProductRepo.delete(saving);

            return new ResponseEntity<>(new RestResponse(false, "Savings Product deleted"), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while deleting Savings Product", e);
            return new ResponseEntity<>(new RestResponse(true, "Error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}