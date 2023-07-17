package com.desafio.dev.api.rest.v1.response;

import lombok.*;
import org.springframework.data.domain.Page;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionsResponse {

    private Page<TransactionResponse> TransactionResponse;
    private TransactionBalanceResponse balanceResponse;

}
