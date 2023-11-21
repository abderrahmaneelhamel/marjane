package com.marjane.controller;

import com.marjane.model.Center;
import com.marjane.model.Product;
import com.marjane.model.Promotion;
import com.marjane.service.CenterService;
import com.marjane.service.ProductService;
import com.marjane.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/promotions")
@RequiredArgsConstructor
@Validated
public class PromotionController {

    private final PromotionService promotionService;
    private final CenterService centerService;
    private final ProductService productService;


    @GetMapping
    public ResponseEntity<List<Promotion>> getAllPromotions() {
        List<Promotion> promotions = promotionService.getAllPromotions();
        return ResponseEntity.ok(promotions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Promotion> getPromotionById(@PathVariable Long id) {
        Promotion promotion = promotionService.getPromotionById(id);
        return ResponseEntity.ok(promotion);
    }

    @PostMapping
    public ResponseEntity<Promotion> createPromotion(@Validated @RequestBody Map<String, String> request) {
        Center center = centerService.getCenterById(Long.parseLong(request.get("centerId")));
        Product product = productService.getProductById(Long.parseLong(request.get("multimediaProductId")));

        Promotion promotion = new Promotion(
                request.get("ProductCategory"),
                request.get("PromotionDescription"),
                LocalDateTime.parse(request.get("StartTime")),
                LocalDateTime.parse(request.get("EndTime")),
                Double.parseDouble(request.get("DiscountPercentage")),
                Integer.parseInt(request.get("Quantity")),
                Double.parseDouble(request.get("LoyaltyPointsEarned")),
                Integer.parseInt(request.get("QuantityThreshold")),
                center,
                product
        );

        Promotion createdPromotion = promotionService.createPromotion(promotion);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPromotion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Promotion> updatePromotion(@PathVariable Long id, @Validated @RequestBody Map<String, String> request) {
        Center center = centerService.getCenterById(Long.parseLong(request.get("centerId")));
        Product product = productService.getProductById(Long.parseLong(request.get("multimediaProductId")));

        Promotion promotion = new Promotion(
                request.get("ProductCategory"),
                request.get("PromotionDescription"),
                LocalDateTime.parse(request.get("StartTime")),
                LocalDateTime.parse(request.get("EndTime")),
                Double.parseDouble(request.get("DiscountPercentage")),
                Integer.parseInt(request.get("Quantity")),
                Double.parseDouble(request.get("LoyaltyPointsEarned")),
                Integer.parseInt(request.get("QuantityThreshold")),
                center,
                product
        );

        Promotion updatedPromotion = promotionService.updatePromotion(id, promotion);
        return ResponseEntity.ok(updatedPromotion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePromotion(@PathVariable Long id) {
        promotionService.deletePromotion(id);
        return ResponseEntity.noContent().build();
    }
}
