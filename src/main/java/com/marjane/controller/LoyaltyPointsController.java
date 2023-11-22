package com.marjane.controller;

import com.marjane.dto.LoyaltyPointsDto;
import com.marjane.service.LoyaltyPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loyalty-points")
public class LoyaltyPointsController {

    private final LoyaltyPointsService loyaltyPointsService;

    @Autowired
    public LoyaltyPointsController(LoyaltyPointsService loyaltyPointsService) {
        this.loyaltyPointsService = loyaltyPointsService;
    }

    @GetMapping("/{loyaltyPointsId}")
    public ResponseEntity<LoyaltyPointsDto> getLoyaltyPoints(@PathVariable Long loyaltyPointsId) {
        return ResponseEntity.ok(loyaltyPointsService.getLoyaltyPointsById(loyaltyPointsId));
    }

    @PostMapping
    public ResponseEntity<LoyaltyPointsDto> createLoyaltyPoints(@RequestBody LoyaltyPointsDto loyaltyPointsDto) {
        LoyaltyPointsDto createdLoyaltyPoints = loyaltyPointsService.createLoyaltyPoints(loyaltyPointsDto);
        return new ResponseEntity<>(createdLoyaltyPoints, HttpStatus.CREATED);
    }

    @PutMapping("/{loyaltyPointsId}")
    public ResponseEntity<LoyaltyPointsDto> updateLoyaltyPoints(
            @PathVariable Long loyaltyPointsId,
            @RequestBody LoyaltyPointsDto loyaltyPointsDto
    ) {
        LoyaltyPointsDto updatedLoyaltyPoints = loyaltyPointsService.updateLoyaltyPoints(loyaltyPointsId, loyaltyPointsDto);
        return ResponseEntity.ok(updatedLoyaltyPoints);
    }

    @DeleteMapping("/{loyaltyPointsId}")
    public ResponseEntity<Void> deleteLoyaltyPoints(@PathVariable Long loyaltyPointsId) {
        loyaltyPointsService.deleteLoyaltyPoints(loyaltyPointsId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
