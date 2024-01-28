package com.transaction.demo.service.impl;

import static com.transaction.demo.constant.TransactionType.DEPOSIT;
import static com.transaction.demo.constant.TransactionType.PURCHASE;
import static com.transaction.demo.constant.TransactionType.REFUND;

import com.transaction.demo.dto.TransactionRequest;
import com.transaction.demo.dto.TransactionResponse;
import com.transaction.demo.entity.Transaction;
import com.transaction.demo.repository.CustomerRepository;
import com.transaction.demo.repository.TransactionRepository;
import com.transaction.demo.service.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public TransactionResponse addBalance(TransactionRequest request) {
        var customer = customerRepository.findCustomerById(request.getId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        customer.setBalance(customer.getBalance().add(request.getAmount()));
        var saved = customerRepository.save(customer);

        var transaction = Transaction.builder()
                .userId(request.getId())
                .amount(request.getAmount())
                .transactionType(DEPOSIT.toString())
                .build();

        transactionRepository.save(transaction);

        return new TransactionResponse(saved.getBalance());
    }

    @Override
    @Transactional
    public TransactionResponse makePurchase(TransactionRequest request) {
        var customer = customerRepository.findCustomerById(request.getId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (customer.getBalance().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException("Insufficient funds for the purchase");
        }

        customer.setBalance(customer.getBalance().subtract(request.getAmount()));
        var saved = customerRepository.save(customer);

        var transaction = Transaction.builder()
                .userId(request.getId())
                .amount(request.getAmount())
                .transactionType(PURCHASE.toString())
                .build();

        transactionRepository.save(transaction);

        return new TransactionResponse(saved.getBalance());
    }

    @Override
    @Transactional
    public TransactionResponse makeRefund(TransactionRequest request) {
        var customer = customerRepository.findCustomerById(request.getId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (customer.getBalance().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException("Insufficient funds for the purchase");
        }

        customer.setBalance(customer.getBalance().add(request.getAmount()));
        var saved = customerRepository.save(customer);

        var transaction = Transaction.builder()
                .userId(request.getId())
                .amount(request.getAmount())
                .transactionType(REFUND.toString())
                .build();

        transactionRepository.save(transaction);

        return new TransactionResponse(saved.getBalance());
    }

}
