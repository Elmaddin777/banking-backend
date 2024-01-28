package com.banking.demo.service;

import com.banking.demo.dto.CustomerDto;
import com.banking.demo.dto.CustomerResponseDto;

public interface CustomerService {

    CustomerResponseDto createCustomer(CustomerDto customer);

    CustomerResponseDto getCustomer(String gsmNumber);

}
