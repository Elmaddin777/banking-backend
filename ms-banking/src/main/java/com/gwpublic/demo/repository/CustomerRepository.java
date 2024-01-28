package com.gwpublic.demo.repository;

import com.gwpublic.demo.dto.Customer;

public interface CustomerRepository {

    Customer createCustomer(Customer customer);

    Customer getCustomer(String gsmNumber);

}
