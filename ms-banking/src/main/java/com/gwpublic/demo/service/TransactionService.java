package com.gwpublic.demo.service;

import com.gwpublic.demo.client.transaction.model.Transaction;
import com.gwpublic.demo.dto.TransactionRequest;
import org.springframework.stereotype.Service;


public interface TransactionService {

    Transaction makeDeposit(TransactionRequest request);

    Transaction makePurchase(TransactionRequest request);

    Transaction makeRefund(TransactionRequest customer);

}
