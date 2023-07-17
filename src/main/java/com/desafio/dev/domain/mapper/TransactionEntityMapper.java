package com.desafio.dev.domain.mapper;

import com.desafio.dev.domain.enums.TransactionType;
import com.desafio.dev.infrastructure.entity.TransactionEntity;

public class TransactionEntityMapper {

    public static TransactionEntity mapper(String line) {
        TransactionType type = TransactionType.getType(Integer.parseInt(line.substring(0, 1)));
        Double value = Double.parseDouble(line.substring(9, 19)) / 100.00;
        return TransactionEntity
                .builder()
                .cpf(line.substring(19, 30))
                .value(value)
                .date(line.substring(1, 9))
                .hour(line.substring(42, 48))
                .cardNumber(line.substring(30, 42))
                .onwnerStore(line.substring(48, 62).trim())
                .nameStore(line.substring(62).trim())
                .typeTransaction(type.getTransactionTypeDescription())
                .operation(type.getOperation())
                .valueTypeTransaction(type.calcValue(value))
                .build();
    }

}
