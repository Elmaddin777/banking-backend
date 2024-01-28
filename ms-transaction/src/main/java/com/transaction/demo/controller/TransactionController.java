package com.transaction.demo.controller;

import com.transaction.demo.dto.TransactionRequest;
import com.transaction.demo.dto.TransactionResponse;
import com.transaction.demo.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    @PostMapping("/deposit")
    public TransactionResponse deposit(@RequestBody TransactionRequest request) {
        return service.addBalance(request);
    }

    @PostMapping("/purchase")
    public TransactionResponse purchase(@RequestBody TransactionRequest request) {
        return service.makePurchase(request);
    }

    @PostMapping("/refund")
    public TransactionResponse refund(@RequestBody TransactionRequest request) {
        return service.makeRefund(request);
    }


}
