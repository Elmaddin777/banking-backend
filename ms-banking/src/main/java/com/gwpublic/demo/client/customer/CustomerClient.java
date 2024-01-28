package com.gwpublic.demo.client.customer;

import com.gwpublic.demo.dto.Customer;
import com.gwpublic.demo.error.CustomerException;
import feign.Logger;
import feign.codec.ErrorDecoder;
import feign.error.AnnotationErrorDecoder;
import feign.error.ErrorHandling;
import feign.jackson.JacksonDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "customerClient",
        url = "${application.client.customer.url}",
        configuration = CustomerClient.FeignConfiguration.class
)
public interface CustomerClient {

    @PostMapping()
    @ErrorHandling(defaultException = CustomerException.class)
    com.gwpublic.demo.client.customer.model.Customer createCustomer(@RequestBody Customer customer);

    @GetMapping(value = "/{gsmNumber}")
    @ErrorHandling(defaultException = CustomerException.class)
    com.gwpublic.demo.client.customer.model.Customer getCustomer(@RequestParam String gsmNumber);

    @RequiredArgsConstructor
    class FeignConfiguration {

        @Bean
        Logger.Level feignLoggerLevel() {
            return Logger.Level.FULL;
        }

        @Bean
        public ErrorDecoder feignErrorDecoder() {
            return AnnotationErrorDecoder
                    .builderFor(CustomerClient.class)
                    .withResponseBodyDecoder(new JacksonDecoder())
                    .build();
        }

    }

}
