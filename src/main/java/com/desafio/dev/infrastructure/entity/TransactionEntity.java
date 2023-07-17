package com.desafio.dev.infrastructure.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "transactions")
public class TransactionEntity {

    @Id
    private String id;
    private String date;
    private Double value;
    private String cpf;
    private String cardNumber;
    private String hour;
    private String onwnerStore;
    private String nameStore;
}
