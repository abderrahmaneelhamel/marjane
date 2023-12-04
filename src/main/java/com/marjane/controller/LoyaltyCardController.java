package com.marjane.controller;

import com.marjane.dto.LoyaltyCardDto;
import com.marjane.service.LoyaltyCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v3/loyalty-cards")
public class LoyaltyCardController {

    private final LoyaltyCardService loyaltyCardService;

    @Autowired
    public LoyaltyCardController(LoyaltyCardService loyaltyCardService) {
        this.loyaltyCardService = loyaltyCardService;
    }

    @GetMapping("/{loyaltyCardId}")
    public ResponseEntity<LoyaltyCardDto> getLoyaltyCard(@PathVariable Long loyaltyCardId) {
        return ResponseEntity.ok(loyaltyCardService.getLoyaltyCardById(loyaltyCardId));
    }

    @PostMapping
    public ResponseEntity<LoyaltyCardDto> createLoyaltyCard(@RequestBody LoyaltyCardDto loyaltyCardDto) {
        LoyaltyCardDto createdLoyaltyCard = loyaltyCardService.createLoyaltyCard(loyaltyCardDto);
        return new ResponseEntity<>(createdLoyaltyCard, HttpStatus.CREATED);
    }

    @PutMapping("/{loyaltyCardId}")
    public ResponseEntity<LoyaltyCardDto> updateLoyaltyCard(
            @PathVariable Long loyaltyCardId,
            @RequestBody LoyaltyCardDto loyaltyCardDto
    ) {
        LoyaltyCardDto updatedLoyaltyCard = loyaltyCardService.updateLoyaltyCard(loyaltyCardId, loyaltyCardDto);
        return ResponseEntity.ok(updatedLoyaltyCard);
    }

    @DeleteMapping("/{loyaltyCardId}")
    public ResponseEntity<Void> deleteLoyaltyCard(@PathVariable Long loyaltyCardId) {
        loyaltyCardService.deleteLoyaltyCard(loyaltyCardId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
