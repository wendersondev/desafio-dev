package com.desafio.dev.domain.business.impl.factory;

import com.desafio.dev.api.rest.v1.response.TransactionResponse;
import com.desafio.dev.domain.enums.TransactionType;

import java.util.Arrays;
import java.util.List;

public class TransactionResponseFactory {

    public static List<TransactionResponse> factory(String id) {
        return Arrays.asList(
                TransactionResponse
                        .builder()
                        .id(id)
                        .nameStore("Store 1")
                        .value(Double.valueOf("100"))
                        .typeTransaction(TransactionType.BOLETO.name())
                        .operation(TransactionType.BOLETO.getOperation())
                        .hour("12:00")
                        .cardNumber("123456")
                        .onwnerStore("DONO 1")
                        .cpf("00315995412")
                        .date("20190301")
                        .build(),
                TransactionResponse
                        .builder()
                        .id(id)
                        .nameStore("Store 1")
                        .value(Double.valueOf("100"))
                        .typeTransaction(TransactionType.CREDITO.name())
                        .operation(TransactionType.CREDITO.getOperation())
                        .hour("12:00")
                        .cardNumber("123456")
                        .onwnerStore("DONO 1")
                        .cpf("00315995412")
                        .date("20190301")
                        .build(),

                TransactionResponse
                        .builder()
                        .id(id)
                        .nameStore("Store 3")
                        .value(Double.valueOf("300"))
                        .typeTransaction(TransactionType.CREDITO.name())
                        .operation(TransactionType.CREDITO.getOperation())
                        .hour("12:00")
                        .cardNumber("123456")
                        .onwnerStore("DONO 1")
                        .cpf("00315995412")
                        .date("20190301")
                        .build()
        );
    }

}
