package com.gwpublic.demo.service;

import com.gwpublic.demo.dto.Customer;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomerService {

    Customer createCustomer(Customer customer);

    UserDetailsService userDetailsService();

    Customer getCustomer(String gsmNumber);

}
