package com.desafio.dev.domain.mapper;

import com.desafio.dev.api.rest.v1.response.TransactionResponse;
import com.desafio.dev.infrastructure.entity.TransactionEntity;

public class TransactionsResponseMapper {

    public static TransactionResponse mapper(TransactionEntity entity) {
        return TransactionResponse
                .builder()
                .id(entity.getId())
                .cpf(entity.getCpf())
                .value(entity.getValue())
                .hour(entity.getHour())
                .date(entity.getDate())
                .cardNumber(entity.getCardNumber())
                .typeTransaction(entity.getTypeTransaction())
                .nameStore(entity.getNameStore())
                .onwnerStore(entity.getOnwnerStore())
                .operation(entity.getOperation())
                .build();
    }

}
