package com.desafio.dev.domain.mapper;

import com.desafio.dev.infrastructure.entity.TransactionEntity;

public class TransactionEntityMapper {

    public static TransactionEntity mapper(String line) {
        return TransactionEntity
                .builder()
                .cpf(line.substring(19, 30))
                .value(Double.parseDouble(line.substring(9, 19)) / 100.00)
                .date(line.substring(1, 9))
                .hour(line.substring(42, 48))
                .cardNumber(line.substring(30, 42))
                .onwnerStore(line.substring(48, 62).trim())
                .nameStore(line.substring(62).trim())
                .build();
    }

}
