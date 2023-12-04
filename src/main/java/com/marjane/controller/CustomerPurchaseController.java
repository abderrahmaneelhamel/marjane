package com.marjane.controller;

import com.marjane.dto.CustomerPurchaseDto;
import com.marjane.service.CustomerPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v3/customer-purchases")
public class CustomerPurchaseController {

    private final CustomerPurchaseService customerPurchaseService;

    @Autowired
    public CustomerPurchaseController(CustomerPurchaseService customerPurchaseService) {
        this.customerPurchaseService = customerPurchaseService;
    }

    @GetMapping("/{customerPurchaseId}")
    public ResponseEntity<CustomerPurchaseDto> getCustomerPurchase(@PathVariable Long customerPurchaseId) {
        return ResponseEntity.ok(customerPurchaseService.getCustomerPurchaseById(customerPurchaseId));
    }

    @PostMapping
    public ResponseEntity<CustomerPurchaseDto> createCustomerPurchase(@RequestBody CustomerPurchaseDto customerPurchaseDto) {
        CustomerPurchaseDto createdCustomerPurchase = customerPurchaseService.createCustomerPurchase(customerPurchaseDto);
        return new ResponseEntity<>(createdCustomerPurchase, HttpStatus.CREATED);
    }

    @PutMapping("/{customerPurchaseId}")
    public ResponseEntity<CustomerPurchaseDto> updateCustomerPurchase(
            @PathVariable Long customerPurchaseId,
            @RequestBody CustomerPurchaseDto customerPurchaseDto
    ) {
        CustomerPurchaseDto updatedCustomerPurchase = customerPurchaseService.updateCustomerPurchase(customerPurchaseId, customerPurchaseDto);
        return ResponseEntity.ok(updatedCustomerPurchase);
    }

    @DeleteMapping("/{customerPurchaseId}")
    public ResponseEntity<Void> deleteCustomerPurchase(@PathVariable Long customerPurchaseId) {
        customerPurchaseService.deleteCustomerPurchase(customerPurchaseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
