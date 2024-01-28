package com.gwpublic.demo.repository.impl;

import com.gwpublic.demo.client.customer.CustomerClient;
import com.gwpublic.demo.dto.Customer;
import com.gwpublic.demo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerClient customerClient;

    @Override
    public Customer createCustomer(Customer customer) {

        var createdCustomer = customerClient.createCustomer(customer);

        return Customer.builder()
                .name(createdCustomer.getName())
                .surname(createdCustomer.getSurname())
                .birthDate(createdCustomer.getBirthDate())
                .gsmNumber(createdCustomer.getGsmNumber())
                .password(createdCustomer.getPassword())
                .build();
    }

    @Override
    public Customer getCustomer(String gsmNumber) {
        var customer = customerClient.getCustomer(gsmNumber);

        return Customer.builder()
                .name(customer.getName())
                .surname(customer.getSurname())
                .birthDate(customer.getBirthDate())
                .gsmNumber(customer.getGsmNumber())
                .password(customer.getPassword())
                .build();
    }

}
