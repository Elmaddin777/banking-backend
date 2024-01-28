package com.gwpublic.demo.controller;

import static com.gwpublic.demo.constants.ApplicationConstants.CREATE_ACCOUNT_FAILED;

import com.gwpublic.demo.dto.Customer;
import com.gwpublic.demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Customer customer) {
        Customer newCustomer = service.createCustomer(customer);

        return (newCustomer == null) ?
                new ResponseEntity<>(CREATE_ACCOUNT_FAILED, HttpStatus.OK) :
                new ResponseEntity<>(newCustomer, HttpStatus.OK);
    }

}
