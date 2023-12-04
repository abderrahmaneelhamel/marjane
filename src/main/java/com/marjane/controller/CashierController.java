package com.marjane.controller;

import com.marjane.dto.CashierDto;
import com.marjane.service.CashierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v3/cashiers")
public class CashierController {

    private final CashierService cashierService;

    @Autowired
    public CashierController(CashierService cashierService) {
        this.cashierService = cashierService;
    }

    @GetMapping("/{cashierId}")
    public ResponseEntity<CashierDto> getCashier(@PathVariable Long cashierId) {
        return ResponseEntity.ok(cashierService.getCashierById(cashierId));
    }

    @PostMapping
    public ResponseEntity<CashierDto> createCashier(@RequestBody CashierDto cashierDto) {
        CashierDto createdCashier = cashierService.createCashier(cashierDto);
        return new ResponseEntity<>(createdCashier, HttpStatus.CREATED);
    }

    @PutMapping("/{cashierId}")
    public ResponseEntity<CashierDto> updateCashier(
            @PathVariable Long cashierId,
            @RequestBody CashierDto cashierDto
    ) {
        CashierDto updatedCashier = cashierService.updateCashier(cashierId, cashierDto);
        return ResponseEntity.ok(updatedCashier);
    }

    @DeleteMapping("/{cashierId}")
    public ResponseEntity<Void> deleteCashier(@PathVariable Long cashierId) {
        cashierService.deleteCashier(cashierId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
