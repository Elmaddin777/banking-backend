package com.banking.demo.repository;

import com.banking.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByGsmNumber(String gsmNumber);

    Customer findCustomerByGsmNumber(String gsmNumber);

}
