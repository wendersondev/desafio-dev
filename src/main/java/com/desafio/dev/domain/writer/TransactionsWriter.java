package com.desafio.dev.domain.writer;

import com.desafio.dev.domain.read.Parameters;
import com.desafio.dev.infrastructure.entity.TransactionEntity;
import com.desafio.dev.infrastructure.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@StepScope
@Component
@Slf4j
public class TransactionsWriter implements ItemWriter<List<TransactionEntity>> {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private Parameters parameters;

    @Override
    public void write(List<? extends List<TransactionEntity>> items) throws Exception {
        log.info("TransactionsWriter - {} ", items);

        transactionRepository.saveAll(items.get(0));

        log.info("TransactionsWriter - finish with {} insertions ", items.get(0).size());

        File fileDel = new File(parameters.getFilePath().concat(parameters.getFileName()));
        var isDelete = fileDel.delete();

        log.info("TransactionsWriter - file deleted {} ", isDelete);
    }
}
