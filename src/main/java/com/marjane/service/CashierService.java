package com.marjane.service;

import com.marjane.dto.CashierDto;
import com.marjane.model.Cashier;
import com.marjane.repository.CashierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CashierService {

    private final CashierRepository cashierRepository;

    @Autowired
    public CashierService(CashierRepository cashierRepository) {
        this.cashierRepository = cashierRepository;
    }

    public CashierDto getCashierById(Long cashierId) {
        Optional<Cashier> cashierOptional = cashierRepository.findById(cashierId);
        return cashierOptional.map(this::convertToDto).orElse(null);
    }

    public List<CashierDto> getAllCashiers() {
        List<Cashier> cashiers = cashierRepository.findAll();
        return cashiers.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public CashierDto createCashier(CashierDto cashierDto) {
        Cashier newCashier = convertToEntity(cashierDto);
        Cashier savedCashier = cashierRepository.save(newCashier);
        return convertToDto(savedCashier);
    }

    public CashierDto updateCashier(Long cashierId, CashierDto cashierDto) {
        Optional<Cashier> existingCashierOptional = cashierRepository.findById(cashierId);

        if (existingCashierOptional.isPresent()) {
            Cashier existingCashier = existingCashierOptional.get();
            existingCashier.setCenter(cashierDto.getCenter());
            existingCashier.setName(cashierDto.getName());
            existingCashier.setEmail(cashierDto.getEmail());

            Cashier updatedCashier = cashierRepository.save(existingCashier);
            return convertToDto(updatedCashier);
        } else {
            return null;
        }
    }

    public void deleteCashier(Long cashierId) {
        cashierRepository.deleteById(cashierId);
    }

    private CashierDto convertToDto(Cashier cashier) {
        CashierDto dto = new CashierDto();
        dto.setId(cashier.getId());
        dto.setCenter(cashier.getCenter());
        dto.setName(cashier.getName());
        dto.setEmail(cashier.getEmail());

        return dto;
    }

    private Cashier convertToEntity(CashierDto cashierDto) {
        Cashier entity = new Cashier();
        entity.setCenter(cashierDto.getCenter());
        entity.setName(cashierDto.getName());
        entity.setEmail(cashierDto.getEmail());

        return entity;
    }
}
