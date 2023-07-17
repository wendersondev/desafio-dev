package com.desafio.dev.api.rest.v1.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionBalanceResponse {

    private BigDecimal valueDebit;
    private BigDecimal valueCredit;
    private BigDecimal valueTotal;

}
