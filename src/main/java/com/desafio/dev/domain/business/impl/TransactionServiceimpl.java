package com.desafio.dev.domain.business.impl;

import com.desafio.dev.api.rest.v1.response.TransactionBalanceResponse;
import com.desafio.dev.api.rest.v1.response.TransactionsResponse;
import com.desafio.dev.domain.business.TransactionService;
import com.desafio.dev.domain.mapper.TransactionsResponseMapper;
import com.desafio.dev.infrastructure.entity.TransactionEntity;
import com.desafio.dev.infrastructure.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
                    .transactionResponse(transactionRepository.findAll(pageable).map(TransactionsResponseMapper::mapper))
                    .build();
        }
        else {
            Page<TransactionEntity> transactions = transactionRepository.findAllByNameStore(name, pageable);

            BigDecimal valueDebit = BigDecimal.valueOf(transactions
                    .stream()
                    .filter(transaction -> transaction.getValueTypeTransaction() < 0)
                    .mapToDouble(TransactionEntity::getValueTypeTransaction)
                    .sum());

            BigDecimal valueCredit = BigDecimal.valueOf(transactions
                    .stream()
                    .filter(transaction -> transaction.getValueTypeTransaction() > 0)
                    .mapToDouble(TransactionEntity::getValueTypeTransaction)
                    .sum());

            return TransactionsResponse
                    .builder()
                    .balanceResponse(TransactionBalanceResponse
                            .builder()
                            .valueCredit(valueCredit)
                            .valueDebit(valueDebit)
                            .valueTotal(valueCredit.add(valueDebit))
                            .build())
                    .transactionResponse(transactions.map(TransactionsResponseMapper::mapper))
                    .build();
        }
    }
}
