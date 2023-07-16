package com.desafio.dev.domain.writer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class TransactionsWriter implements ItemWriter<String> {

    @Override
    public void write(List<? extends String> items) throws Exception {
        log.info("TransactionsWriter - {} ", items);
    }
}
