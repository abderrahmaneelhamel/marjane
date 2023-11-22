package com.marjane.service;

import com.marjane.dto.LoyaltyPointsDto;
import com.marjane.model.LoyaltyPoints;
import com.marjane.repository.LoyaltyPointsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoyaltyPointsService {

    private final LoyaltyPointsRepository loyaltyPointsRepository;

    @Autowired
    public LoyaltyPointsService(LoyaltyPointsRepository loyaltyPointsRepository) {
        this.loyaltyPointsRepository = loyaltyPointsRepository;
    }

    public LoyaltyPointsDto getLoyaltyPointsById(Long loyaltyPointsId) {
        Optional<LoyaltyPoints> loyaltyPointsOptional = loyaltyPointsRepository.findById(loyaltyPointsId);
        return loyaltyPointsOptional.map(this::convertToDto).orElse(null);
    }
    public LoyaltyPoints LoyaltyPointsById(Long loyaltyPointsId) {
        Optional<LoyaltyPoints> loyaltyPointsOptional = loyaltyPointsRepository.findById(loyaltyPointsId);
        return loyaltyPointsOptional.get();
    }

    public LoyaltyPointsDto createLoyaltyPoints(LoyaltyPointsDto loyaltyPointsDto) {
        LoyaltyPoints newLoyaltyPoints = convertToEntity(loyaltyPointsDto);
        LoyaltyPoints savedLoyaltyPoints = loyaltyPointsRepository.save(newLoyaltyPoints);
        return convertToDto(savedLoyaltyPoints);
    }

    public LoyaltyPointsDto updateLoyaltyPoints(Long loyaltyPointsId, LoyaltyPointsDto loyaltyPointsDto) {
        Optional<LoyaltyPoints> existingLoyaltyPointsOptional = loyaltyPointsRepository.findById(loyaltyPointsId);

        if (existingLoyaltyPointsOptional.isPresent()) {
            LoyaltyPoints existingLoyaltyPoints = existingLoyaltyPointsOptional.get();
            existingLoyaltyPoints.setPoints(loyaltyPointsDto.getPoints());
            existingLoyaltyPoints.setLoyaltyCard(this.LoyaltyPointsById(loyaltyPointsDto.getId()).getLoyaltyCard());

            LoyaltyPoints updatedLoyaltyPoints = loyaltyPointsRepository.save(existingLoyaltyPoints);
            return convertToDto(updatedLoyaltyPoints);
        } else {
            return null;
        }
    }

    public void deleteLoyaltyPoints(Long loyaltyPointsId) {
        loyaltyPointsRepository.deleteById(loyaltyPointsId);
    }

    private LoyaltyPointsDto convertToDto(LoyaltyPoints loyaltyPoints) {
        LoyaltyPointsDto dto = new LoyaltyPointsDto();
        dto.setId(loyaltyPoints.getId());
        dto.setPoints(loyaltyPoints.getPoints());
        dto.setLoyaltyCardId(loyaltyPoints.getLoyaltyCard().getId());

        return dto;
    }

    private LoyaltyPoints convertToEntity(LoyaltyPointsDto loyaltyPointsDto) {
        LoyaltyPoints entity = new LoyaltyPoints();
        entity.setId(loyaltyPointsDto.getId());
        entity.setPoints(loyaltyPointsDto.getPoints());
        entity.setLoyaltyCard(this.LoyaltyPointsById(loyaltyPointsDto.getId()).getLoyaltyCard());

        return entity;
    }
}
