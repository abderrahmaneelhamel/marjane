package com.marjane.service;

import com.marjane.dto.CustomerPurchaseDto;
import com.marjane.model.CustomerPurchase;
import com.marjane.repository.CustomerPurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerPurchaseService {

    private final CustomerPurchaseRepository customerPurchaseRepository;

    @Autowired
    public CustomerPurchaseService(CustomerPurchaseRepository customerPurchaseRepository) {
        this.customerPurchaseRepository = customerPurchaseRepository;
    }

    public CustomerPurchase CustomerPurchaseById(Long customerPurchaseId) {
        Optional<CustomerPurchase> customerPurchaseOptional = customerPurchaseRepository.findById(customerPurchaseId);
        return customerPurchaseOptional.get();
    }
    public CustomerPurchaseDto getCustomerPurchaseById(Long customerPurchaseId) {
        Optional<CustomerPurchase> customerPurchaseOptional = customerPurchaseRepository.findById(customerPurchaseId);
        return customerPurchaseOptional.map(this::convertToDto).orElse(null);
    }

    public List<CustomerPurchaseDto> getAllCustomerPurchases() {
        List<CustomerPurchase> customerPurchases = customerPurchaseRepository.findAll();
        return customerPurchases.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public CustomerPurchaseDto createCustomerPurchase(CustomerPurchaseDto customerPurchaseDto) {
        CustomerPurchase newCustomerPurchase = convertToEntity(customerPurchaseDto);
        CustomerPurchase savedCustomerPurchase = customerPurchaseRepository.save(newCustomerPurchase);
        return convertToDto(savedCustomerPurchase);
    }

    public CustomerPurchaseDto updateCustomerPurchase(Long customerPurchaseId, CustomerPurchaseDto customerPurchaseDto) {
        Optional<CustomerPurchase> existingCustomerPurchaseOptional = customerPurchaseRepository.findById(customerPurchaseId);

        if (existingCustomerPurchaseOptional.isPresent()) {
            CustomerPurchase existingCustomerPurchase = existingCustomerPurchaseOptional.get();
            existingCustomerPurchase.setCustomer(this.CustomerPurchaseById(customerPurchaseDto.getId()).getCustomer());
            existingCustomerPurchase.setCashier(this.CustomerPurchaseById(customerPurchaseDto.getId()).getCashier());

            CustomerPurchase updatedCustomerPurchase = customerPurchaseRepository.save(existingCustomerPurchase);
            return convertToDto(updatedCustomerPurchase);
        } else {
            return null;
        }
    }

    public void deleteCustomerPurchase(Long customerPurchaseId) {
        customerPurchaseRepository.deleteById(customerPurchaseId);
    }

    private CustomerPurchaseDto convertToDto(CustomerPurchase customerPurchase) {
        CustomerPurchaseDto dto = new CustomerPurchaseDto();
        dto.setId(customerPurchase.getId());
        dto.setCustomerId(customerPurchase.getCustomer().getId());
        dto.setCashierId(customerPurchase.getCashier().getId());

        return dto;
    }

    private CustomerPurchase convertToEntity(CustomerPurchaseDto customerPurchaseDto) {
        CustomerPurchase entity = new CustomerPurchase();
        entity.setId(customerPurchaseDto.getId());
        entity.setCustomer(this.CustomerPurchaseById(customerPurchaseDto.getId()).getCustomer());
        entity.setCashier(this.CustomerPurchaseById(customerPurchaseDto.getId()).getCashier());

        return entity;
    }
}
