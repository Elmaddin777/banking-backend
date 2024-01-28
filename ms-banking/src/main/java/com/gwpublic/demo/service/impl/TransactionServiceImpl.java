package com.gwpublic.demo.service.impl;

import com.gwpublic.demo.client.transaction.model.Transaction;
import com.gwpublic.demo.dto.TransactionRequest;
import com.gwpublic.demo.repository.TransactionRepository;
import com.gwpublic.demo.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;

    @Override
    public Transaction makeDeposit(TransactionRequest request) {
        return repository.makeDeposit(request);
    }

    @Override
    public Transaction makePurchase(TransactionRequest request) {
        return repository.makePurchase(request);
    }

    @Override
    public Transaction makeRefund(TransactionRequest request) {
        return repository.makeRefund(request);
    }

}
