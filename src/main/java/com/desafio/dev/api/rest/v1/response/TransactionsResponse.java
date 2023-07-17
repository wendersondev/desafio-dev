package com.desafio.dev.api.rest.v1.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.domain.Page;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionsResponse {

    @ApiModelProperty(value = "Lista de transações paginada")
    private Page<TransactionResponse> transactionResponse;

    @ApiModelProperty(value = "Valores gerados de débitos e créditos da loja")
    private TransactionBalanceResponse balanceResponse;

}
