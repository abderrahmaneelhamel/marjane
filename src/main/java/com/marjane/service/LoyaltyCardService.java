package com.marjane.service;

import com.marjane.dto.LoyaltyCardDto;
import com.marjane.model.LoyaltyCard;
import com.marjane.repository.LoyaltyCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoyaltyCardService {

    private final LoyaltyCardRepository loyaltyCardRepository;

    @Autowired
    public LoyaltyCardService(LoyaltyCardRepository loyaltyCardRepository) {
        this.loyaltyCardRepository = loyaltyCardRepository;
    }

    public LoyaltyCardDto getLoyaltyCardById(Long loyaltyCardId) {
        Optional<LoyaltyCard> loyaltyCardOptional = loyaltyCardRepository.findById(loyaltyCardId);
        return loyaltyCardOptional.map(this::convertToDto).orElse(null);
    }

    public LoyaltyCard LoyaltyCardById(Long loyaltyCardId) {
        Optional<LoyaltyCard> loyaltyCardOptional = loyaltyCardRepository.findById(loyaltyCardId);
        return loyaltyCardOptional.get();
    }

    public LoyaltyCardDto createLoyaltyCard(LoyaltyCardDto loyaltyCardDto) {
        LoyaltyCard newLoyaltyCard = convertToEntity(loyaltyCardDto);
        LoyaltyCard savedLoyaltyCard = loyaltyCardRepository.save(newLoyaltyCard);
        return convertToDto(savedLoyaltyCard);
    }

    public LoyaltyCardDto updateLoyaltyCard(Long loyaltyCardId, LoyaltyCardDto loyaltyCardDto) {
        Optional<LoyaltyCard> existingLoyaltyCardOptional = loyaltyCardRepository.findById(loyaltyCardId);

        if (existingLoyaltyCardOptional.isPresent()) {
            LoyaltyCard existingLoyaltyCard = existingLoyaltyCardOptional.get();
            existingLoyaltyCard.setCustomer(this.LoyaltyCardById(loyaltyCardDto.getCustomerId()).getCustomer());
            existingLoyaltyCard.setCardCode(loyaltyCardDto.getCardCode());

            LoyaltyCard updatedLoyaltyCard = loyaltyCardRepository.save(existingLoyaltyCard);
            return convertToDto(updatedLoyaltyCard);
        } else {
            return null;
        }
    }

    public void deleteLoyaltyCard(Long loyaltyCardId) {
        loyaltyCardRepository.deleteById(loyaltyCardId);
    }

    private LoyaltyCardDto convertToDto(LoyaltyCard loyaltyCard) {
        LoyaltyCardDto dto = new LoyaltyCardDto();
        dto.setId(loyaltyCard.getId());
        dto.setCustomerId(loyaltyCard.getCustomer().getId());
        dto.setCardCode(loyaltyCard.getCardCode());

        return dto;
    }

    private LoyaltyCard convertToEntity(LoyaltyCardDto loyaltyCardDto) {
        LoyaltyCard entity = new LoyaltyCard();
        entity.setId(loyaltyCardDto.getId());
        entity.setCustomer(this.LoyaltyCardById(loyaltyCardDto.getCustomerId()).getCustomer());
        entity.setCardCode(loyaltyCardDto.getCardCode());

        return entity;
    }
}
