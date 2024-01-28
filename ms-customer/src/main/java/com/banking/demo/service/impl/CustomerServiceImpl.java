package com.banking.demo.service.impl;

import com.banking.demo.dto.CustomerDto;
import com.banking.demo.dto.CustomerResponseDto;
import com.banking.demo.entity.Customer;
import com.banking.demo.mapping.CustomerMapper;
import com.banking.demo.repository.CustomerRepository;
import com.banking.demo.service.CustomerService;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    @Override
    public CustomerResponseDto createCustomer(CustomerDto customer) {
        if (repository.existsByGsmNumber(customer.getGsmNumber())) {
            throw new RuntimeException("Customer with GSM number already exists");
        }

        var customerEntity = Customer.builder()
                .name(customer.getName())
                .surname(customer.getSurname())
                .password(customer.getPassword())
                .birthDate(customer.getBirthDate())
                .gsmNumber(customer.getGsmNumber())
                .balance(BigDecimal.valueOf(100.00))
                .build();

        var savedCustomer = repository.save(customerEntity);
        log.info("Customer with id : {} added successfully", savedCustomer.getId());
        return mapper.toDto(savedCustomer);
    }

    @Override
    public CustomerResponseDto getCustomer(String gsmNumber) {
        return mapper.toDto(repository.findCustomerByGsmNumber(gsmNumber));
    }

}
