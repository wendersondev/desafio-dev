package com.desafio.dev.api.rest.v1.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponse {

    @ApiModelProperty(value = "Código interno da transação")
    private String id;

    @ApiModelProperty(value = "Data da transação")
    private String date;

    @ApiModelProperty(value = "Valor da transação")
    private Double value;

    @ApiModelProperty(value = "Cpf comprador")
    private String cpf;

    @ApiModelProperty(value = "Número do cartão usado na transação")
    private String cardNumber;

    @ApiModelProperty(value = "Horário da transação")
    private String hour;

    @ApiModelProperty(value = "Nome do dono da loja")
    private String onwnerStore;

    @ApiModelProperty(value = "Nome da loja")
    private String nameStore;

    @ApiModelProperty(value = "Tipo da transação")
    private String typeTransaction;

    @ApiModelProperty(value = "operação da transação")
    private String operation;

}
