package com.marjane.service;

import com.marjane.model.Promotion;
import com.marjane.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionRepository promotionRepository;

    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    public Promotion getPromotionById(Long id) {
        return promotionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promotion not found with id: " + id));
    }

    public Promotion createPromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    public Promotion updatePromotion(Long id, Promotion promotion) {
        Promotion existingPromotion = getPromotionById(id);
        existingPromotion.setProductCategory(promotion.getProductCategory());
        existingPromotion.setPromotionDescription(promotion.getPromotionDescription());
        return promotionRepository.save(existingPromotion);
    }

    public void deletePromotion(Long id) {
        promotionRepository.deleteById(id);
    }
    public boolean isPromotionValid(Promotion promotion) {
        LocalDateTime now = LocalDateTime.now();
        return promotion.getStartTime().isBefore(now)
                && promotion.getEndTime().isAfter(now)
                && promotion.getDiscountPercentage() <= 50
                && !(promotion.getQuantity() < 20 && promotion.getDiscountPercentage() > 70)
                && !(promotion.getProductCategory().equalsIgnoreCase("multimedia") && promotion.getDiscountPercentage() > 15);
    }
}
