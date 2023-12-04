package com.marjane.controller;

import com.marjane.dto.PromotionApprovalDto;
import com.marjane.service.PromotionApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v3/promotion-approvals")
public class PromotionApprovalController {

    private final PromotionApprovalService promotionApprovalService;

    @Autowired
    public PromotionApprovalController(PromotionApprovalService promotionApprovalService) {
        this.promotionApprovalService = promotionApprovalService;
    }

    @GetMapping("/{promotionApprovalId}")
    public ResponseEntity<PromotionApprovalDto> getPromotionApproval(@PathVariable Long promotionApprovalId) {
        return ResponseEntity.ok(promotionApprovalService.getPromotionApprovalById(promotionApprovalId));
    }

    @PostMapping
    public ResponseEntity<PromotionApprovalDto> createPromotionApproval(@RequestBody PromotionApprovalDto promotionApprovalDto) {
        PromotionApprovalDto createdPromotionApproval = promotionApprovalService.createPromotionApproval(promotionApprovalDto);
        return new ResponseEntity<>(createdPromotionApproval, HttpStatus.CREATED);
    }

    @PutMapping("/{promotionApprovalId}")
    public ResponseEntity<PromotionApprovalDto> updatePromotionApproval(
            @PathVariable Long promotionApprovalId,
            @RequestBody PromotionApprovalDto promotionApprovalDto
    ) {
        PromotionApprovalDto updatedPromotionApproval = promotionApprovalService.updatePromotionApproval(promotionApprovalId, promotionApprovalDto);
        return ResponseEntity.ok(updatedPromotionApproval);
    }

    @DeleteMapping("/{promotionApprovalId}")
    public ResponseEntity<Void> deletePromotionApproval(@PathVariable Long promotionApprovalId) {
        promotionApprovalService.deletePromotionApproval(promotionApprovalId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
