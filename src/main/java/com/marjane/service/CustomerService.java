package com.marjane.service;

import com.marjane.dto.CustomerDto;
import com.marjane.model.Customer;
import com.marjane.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDto getCustomerById(Long customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        return customerOptional.map(this::convertToDto).orElse(null);
    }

    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer newCustomer = convertToEntity(customerDto);
        Customer savedCustomer = customerRepository.save(newCustomer);
        return convertToDto(savedCustomer);
    }

    public CustomerDto updateCustomer(Long customerId, CustomerDto customerDto) {
        Optional<Customer> existingCustomerOptional = customerRepository.findById(customerId);

        if (existingCustomerOptional.isPresent()) {
            Customer existingCustomer = existingCustomerOptional.get();
            existingCustomer.setName(customerDto.getName());
            existingCustomer.setEmail(customerDto.getEmail());

            Customer updatedCustomer = customerRepository.save(existingCustomer);
            return convertToDto(updatedCustomer);
        } else {
            return null;
        }
    }

    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    private CustomerDto convertToDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());

        return dto;
    }

    private Customer convertToEntity(CustomerDto customerDto) {
        Customer entity = new Customer();
        entity.setId(customerDto.getId());
        entity.setName(customerDto.getName());
        entity.setEmail(customerDto.getEmail());

        return entity;
    }
}
