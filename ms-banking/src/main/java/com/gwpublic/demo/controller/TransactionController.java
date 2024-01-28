package com.gwpublic.demo.controller;

import com.gwpublic.demo.client.transaction.model.Transaction;
import com.gwpublic.demo.dto.TransactionRequest;
import com.gwpublic.demo.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    @PostMapping("/deposit")
    public Transaction makeDeposit(@RequestBody TransactionRequest request) {
       return service.makeDeposit(request);
    }

    @PostMapping("/refund")
    public Transaction makeRefund(@RequestBody TransactionRequest request) {
        return service.makeRefund(request);
    }

    @PostMapping("/purchase")
    public Transaction makePurchase(@RequestBody TransactionRequest request) {
        return service.makePurchase(request);
    }

}
