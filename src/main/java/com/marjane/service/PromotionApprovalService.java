package com.marjane.service;

import com.marjane.dto.PromotionApprovalDto;
import com.marjane.model.PromotionApproval;
import com.marjane.repository.PromotionApprovalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PromotionApprovalService {

    private final PromotionApprovalRepository promotionApprovalRepository;

    @Autowired
    public PromotionApprovalService(PromotionApprovalRepository promotionApprovalRepository) {
        this.promotionApprovalRepository = promotionApprovalRepository;
    }

    public PromotionApprovalDto getPromotionApprovalById(Long promotionApprovalId) {
        Optional<PromotionApproval> promotionApprovalOptional = promotionApprovalRepository.findById(promotionApprovalId);
        return promotionApprovalOptional.map(this::convertToDto).orElse(null);
    }

    public PromotionApproval PromotionApprovalById(Long promotionApprovalId) {
        Optional<PromotionApproval> promotionApprovalOptional = promotionApprovalRepository.findById(promotionApprovalId);
        return promotionApprovalOptional.get();
    }

    public List<PromotionApprovalDto> getAllPromotionApprovals() {
        List<PromotionApproval> promotionApprovals = promotionApprovalRepository.findAll();
        return promotionApprovals.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public PromotionApprovalDto createPromotionApproval(PromotionApprovalDto promotionApprovalDto) {
        PromotionApproval newPromotionApproval = convertToEntity(promotionApprovalDto);
        PromotionApproval savedPromotionApproval = promotionApprovalRepository.save(newPromotionApproval);
        return convertToDto(savedPromotionApproval);
    }

    public PromotionApprovalDto updatePromotionApproval(Long promotionApprovalId, PromotionApprovalDto promotionApprovalDto) {
        Optional<PromotionApproval> existingPromotionApprovalOptional = promotionApprovalRepository.findById(promotionApprovalId);

        if (existingPromotionApprovalOptional.isPresent()) {
            PromotionApproval existingPromotionApproval = existingPromotionApprovalOptional.get();
            existingPromotionApproval.setQuantity(promotionApprovalDto.getQuantity());
            existingPromotionApproval.setPromotion(this.PromotionApprovalById(promotionApprovalDto.getId()).getPromotion());
            existingPromotionApproval.setManager(this.PromotionApprovalById(promotionApprovalDto.getId()).getManager());

            PromotionApproval updatedPromotionApproval = promotionApprovalRepository.save(existingPromotionApproval);
            return convertToDto(updatedPromotionApproval);
        } else {
            return null;
        }
    }

    public void deletePromotionApproval(Long promotionApprovalId) {
        promotionApprovalRepository.deleteById(promotionApprovalId);
    }

    private PromotionApprovalDto convertToDto(PromotionApproval promotionApproval) {
        PromotionApprovalDto dto = new PromotionApprovalDto();
        dto.setId(promotionApproval.getId());
        dto.setQuantity(promotionApproval.getQuantity());
        dto.setPromotionId(promotionApproval.getPromotion().getId());
        dto.setDepartmentManagerId(promotionApproval.getManager().getId());

        return dto;
    }

    private PromotionApproval convertToEntity(PromotionApprovalDto promotionApprovalDto) {
        PromotionApproval entity = new PromotionApproval();
        entity.setQuantity(promotionApprovalDto.getQuantity());
        entity.setPromotion(this.PromotionApprovalById(promotionApprovalDto.getId()).getPromotion());
        entity.setManager(this.PromotionApprovalById(promotionApprovalDto.getId()).getManager());

        return entity;
    }
}
