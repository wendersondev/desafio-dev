package com.desafio.dev.domain.business.impl.factory;

import com.desafio.dev.domain.enums.TransactionType;
import com.desafio.dev.infrastructure.entity.TransactionEntity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TransactionEntityFactory {

    public static List<TransactionEntity> factory(String id) {
        return Arrays.asList(
                TransactionEntity
                        .builder()
                        .id(1L)
                        .nameStore("Store 1")
                        .value(Double.valueOf("100"))
                        .typeTransaction(TransactionType.BOLETO.name())
                        .operation(TransactionType.BOLETO.getOperation())
                        .valueTypeTransaction(TransactionType.BOLETO.calcValue(100.00))
                        .hour("12:00")
                        .cardNumber("123456")
                        .onwnerStore("DONO 1")
                        .cpf("00315995412")
                        .date("20190301")
                        .build(),
                TransactionEntity
                        .builder()
                        .id(2L)
                        .nameStore("Store 1")
                        .value(Double.valueOf("100"))
                        .typeTransaction(TransactionType.CREDITO.name())
                        .operation(TransactionType.CREDITO.getOperation())
                        .valueTypeTransaction(TransactionType.CREDITO.calcValue(100.00))
                        .hour("12:00")
                        .cardNumber("123456")
                        .onwnerStore("DONO 1")
                        .cpf("00315995412")
                        .date("20190301")
                        .build(),

                TransactionEntity
                        .builder()
                        .id(3L)
                        .nameStore("Store 3")
                        .value(Double.valueOf("300"))
                        .typeTransaction(TransactionType.CREDITO.name())
                        .operation(TransactionType.CREDITO.getOperation())
                        .valueTypeTransaction(TransactionType.CREDITO.calcValue(300.00))
                        .hour("12:00")
                        .cardNumber("123456")
                        .onwnerStore("DONO 1")
                        .cpf("00315995412")
                        .date("20190301")
                        .build()
        );
    }

}
