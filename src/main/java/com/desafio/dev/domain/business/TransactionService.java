package com.desafio.dev.domain.business;

import com.desafio.dev.api.rest.v1.response.TransactionsResponse;
import org.springframework.data.domain.Pageable;

public interface TransactionService {

    TransactionsResponse findTransactions(String name, Pageable pageable);

}
