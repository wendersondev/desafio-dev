package com.desafio.dev.domain.processors;

import com.desafio.dev.domain.business.ProcessFileService;
import com.desafio.dev.infrastructure.entity.TransactionEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class TransactionsProcessor implements ItemProcessor<String, List<TransactionEntity>> {

    @Autowired
    private ProcessFileService processFileService;

    @Override
    public List<TransactionEntity> process(String item) throws Exception {
        log.info("TransactionsProcessor - process item with lines {} ", item.lines().count());

        List<TransactionEntity> transactions = processFileService.processAndCreateTransactions(item);

        log.info("TransactionsProcessor - process item with {} transactions", transactions.size());

        return transactions;
    }
}
