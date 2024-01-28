package com.transaction.demo.service;

import com.transaction.demo.dto.TransactionRequest;
import com.transaction.demo.dto.TransactionResponse;

public interface TransactionService {

    TransactionResponse addBalance(TransactionRequest request);

    TransactionResponse makePurchase(TransactionRequest request);

    TransactionResponse makeRefund(TransactionRequest request);

}
