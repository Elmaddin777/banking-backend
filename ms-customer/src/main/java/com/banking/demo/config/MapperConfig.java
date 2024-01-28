package com.banking.demo.config;

import com.banking.demo.mapping.CustomerMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public CustomerMapper getCustomerMapper() {
        return CustomerMapper.INSTANCE;
    }

}
