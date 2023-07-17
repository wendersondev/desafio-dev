package com.desafio.dev.domain.business.impl;

import com.desafio.dev.api.rest.v1.response.TransactionBalanceResponse;
import com.desafio.dev.api.rest.v1.response.TransactionResponse;
import com.desafio.dev.api.rest.v1.response.TransactionsResponse;
import com.desafio.dev.domain.business.impl.factory.TransactionEntityFactory;
import com.desafio.dev.domain.business.impl.factory.TransactionResponseFactory;
import com.desafio.dev.infrastructure.entity.TransactionEntity;
import com.desafio.dev.infrastructure.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceimplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceimpl transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindTransactionsWithoutName() {
        String id = UUID.randomUUID().toString();
        List<TransactionEntity> transactionEntities = TransactionEntityFactory.factory(id);

        Pageable pageable = Pageable.ofSize(10).withPage(0);

        Page<TransactionResponse> transactionPage = mock(Page.class);
        when(transactionPage.getContent()).thenReturn(TransactionResponseFactory.factory(id));
        when(transactionPage.getTotalElements()).thenReturn(3L);

        PageImpl<TransactionEntity> page = new PageImpl<>(transactionEntities);
        when(transactionRepository.findAll(any(Pageable.class))).thenReturn(page);

        TransactionsResponse expectedResponse = TransactionsResponse.builder()
                .transactionResponse(transactionPage)
                .build();

        TransactionsResponse result = transactionService.findTransactions(null, pageable);

        verify(transactionRepository, times(1)).findAll(pageable);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(expectedResponse.getTransactionResponse().getTotalElements(), result.getTransactionResponse().getTotalElements()),
                () -> assertEquals(expectedResponse.getTransactionResponse().getContent().get(0).getValue(),
                        result.getTransactionResponse().getContent().get(0).getValue()),
                () -> assertEquals(expectedResponse.getTransactionResponse().getContent().get(0).getTypeTransaction(),
                        result.getTransactionResponse().getContent().get(0).getTypeTransaction())
        );
    }

    @Test
    void testFindTransactionsWithName() {
        Pageable pageable = Pageable.ofSize(10).withPage(0);

        String id = UUID.randomUUID().toString();

        Page<TransactionResponse> transactionPage = mock(Page.class);
        when(transactionPage.getContent()).thenReturn(TransactionResponseFactory.factory(id));
        when(transactionPage.getTotalElements()).thenReturn(3L);


        PageImpl<TransactionEntity> page = new PageImpl<>(TransactionEntityFactory.factory(id));
        when(transactionRepository.findAllByNameStore(anyString(), any(Pageable.class))).thenReturn(page);

        TransactionsResponse expectedResponse = TransactionsResponse.builder()
                .balanceResponse(TransactionBalanceResponse.builder()
                        .valueCredit(BigDecimal.valueOf(400.0))
                        .valueDebit(BigDecimal.valueOf(-100.0))
                        .valueTotal(BigDecimal.valueOf(300.0))
                        .build())
                .transactionResponse(transactionPage)
                .build();

        TransactionsResponse result = transactionService.findTransactions("Store 1", pageable);

        verify(transactionRepository, times(1)).findAllByNameStore("Store 1", pageable);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(expectedResponse.getTransactionResponse().getTotalElements(), result.getTransactionResponse().getTotalElements()),
                () -> assertEquals(expectedResponse.getTransactionResponse().getContent().get(0).getValue(),
                        result.getTransactionResponse().getContent().get(0).getValue()),
                () -> assertEquals(expectedResponse.getTransactionResponse().getContent().get(0).getTypeTransaction(),
                        result.getTransactionResponse().getContent().get(0).getTypeTransaction()),

                () -> assertNotNull(result.getBalanceResponse()),
                () -> assertEquals(expectedResponse.getBalanceResponse().getValueCredit(), result.getBalanceResponse().getValueCredit()),
                () -> assertEquals(expectedResponse.getBalanceResponse().getValueDebit(), result.getBalanceResponse().getValueDebit()),
                () -> assertEquals(expectedResponse.getBalanceResponse().getValueTotal(), result.getBalanceResponse().getValueTotal())
        );
    }

}
