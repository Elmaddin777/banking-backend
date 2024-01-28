package com.gwpublic.demo.service.impl;

import com.gwpublic.demo.dto.Customer;
import com.gwpublic.demo.repository.CustomerRepository;
import com.gwpublic.demo.service.CustomerService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        var createdCustomer = customerRepository.createCustomer(customer);

        return Customer.builder()
                .name(createdCustomer.getName())
                .surname(createdCustomer.getSurname())
                .birthDate(createdCustomer.getBirthDate())
                .gsmNumber(createdCustomer.getGsmNumber())
                .password(createdCustomer.getPassword())
                .build();
    }

    @Override
    public UserDetailsService userDetailsService() {
          return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String gsmNumber) {
                return getCustomer(gsmNumber);
            }
        };
    }

    @Override
    public Customer getCustomer(String gsmNumber) {
        var customer = customerRepository.getCustomer(gsmNumber);

        if (Objects.isNull(customer)) {
            throw new UsernameNotFoundException("Customer not found");
        }

        return Customer.builder()
                .name(customer.getName())
                .surname(customer.getSurname())
                .gsmNumber(customer.getGsmNumber())
                .password(customer.getPassword())
                .birthDate(customer.getBirthDate())
                .build();
    }

}
