package com.desafio.dev.api.rest.v1.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionBalanceResponse {

    @ApiModelProperty(value = "Valor total de débitos")
    private BigDecimal valueDebit;

    @ApiModelProperty(value = "Valor total de créditos")
    private BigDecimal valueCredit;

    @ApiModelProperty(value = "Valor total")
    private BigDecimal valueTotal;

}
