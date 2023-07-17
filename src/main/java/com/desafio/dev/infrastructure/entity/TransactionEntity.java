package com.desafio.dev.infrastructure.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "transactions")
@Entity
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private Double value;
    private String cpf;
    private String cardNumber;
    private String hour;
    private String onwnerStore;
    private String nameStore;
    private String typeTransaction;
    private Double valueTypeTransaction;
    private String operation;
}
