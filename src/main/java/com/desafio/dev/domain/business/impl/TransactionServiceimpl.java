package com.desafio.dev.domain.business.impl;

import com.desafio.dev.api.rest.v1.response.TransactionsResponse;
import com.desafio.dev.domain.business.TransactionService;
import com.desafio.dev.domain.mapper.TransactionsResponseMapper;
import com.desafio.dev.infrastructure.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TransactionServiceimpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public TransactionsResponse findTransactions(String name, Pageable pageable) {
        if (Objects.isNull(name)) {
            return TransactionsResponse
                    .builder()
                    .TransactionResponse(transactionRepository.findAll(pageable).map(TransactionsResponseMapper::mapper))
                    .build();
        }
        else {

        }
        return null;
    }
}
