package com.gwpublic.demo.client.transaction;

import com.gwpublic.demo.client.customer.CustomerClient;
import com.gwpublic.demo.client.transaction.model.Transaction;
import com.gwpublic.demo.dto.Customer;
import com.gwpublic.demo.dto.TransactionRequest;
import com.gwpublic.demo.error.CustomerException;
import com.gwpublic.demo.error.TransactionException;
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
        url = "${application.client.transaction.url}",
        configuration = CustomerClient.FeignConfiguration.class
)
public interface TransactionClient {

    @PostMapping("/deposit")
    @ErrorHandling(defaultException = TransactionException.class)
    Transaction makeDeposit(@RequestBody TransactionRequest request);

    @PostMapping(value = "/purchase")
    @ErrorHandling(defaultException = TransactionException.class)
    Transaction makePurchase(@RequestParam TransactionRequest request);

    @PostMapping(value = "/refund")
    @ErrorHandling(defaultException = TransactionException.class)
    Transaction makeRefund(@RequestBody TransactionRequest request);

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
