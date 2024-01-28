package com.gwpublic.demo.repository;

import com.gwpublic.demo.client.transaction.model.Transaction;
import com.gwpublic.demo.dto.TransactionRequest;

public interface TransactionRepository {

    Transaction makeDeposit(TransactionRequest customer);
    Transaction makePurchase(TransactionRequest gsmNumber);
    Transaction makeRefund(TransactionRequest customer);

}
