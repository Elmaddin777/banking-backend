package com.gwpublic.demo.config;

import com.gwpublic.demo.mapper.GwMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public GwMapper getGwMapper() {
        return GwMapper.INSTANCE;
    }

}
