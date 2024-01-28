package com.gwpublic.demo.repository.impl;

import com.gwpublic.demo.client.transaction.TransactionClient;
import com.gwpublic.demo.client.transaction.model.Transaction;
import com.gwpublic.demo.dto.TransactionRequest;
import com.gwpublic.demo.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    private final TransactionClient client;

    @Override
    public Transaction makeDeposit(TransactionRequest request) {
        return client.makeDeposit(request);
    }

    @Override
    public Transaction makePurchase(TransactionRequest request) {
        return client.makePurchase(request);
    }

    @Override
    public Transaction makeRefund(TransactionRequest request) {
        return client.makeRefund(request);
    }

}
