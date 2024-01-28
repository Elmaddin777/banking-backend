package com.banking.demo.controller;

import com.banking.demo.dto.CustomerDto;
import com.banking.demo.dto.CustomerResponseDto;
import com.banking.demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping()
    public CustomerResponseDto create(@RequestBody CustomerDto customer) {
        return service.createCustomer(customer);
    }

    @GetMapping(value = "/{gsmNumber}")
    public CustomerResponseDto getCustomer(@RequestParam String gsmNumber) {
        return service.getCustomer(gsmNumber);
    }

}
