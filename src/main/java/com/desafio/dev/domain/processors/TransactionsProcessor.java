package com.desafio.dev.domain.processors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransactionsProcessor implements ItemProcessor<String, String> {

    @Override
    public String process(String item) throws Exception {
        log.info("TransactionsProcessor - process item {} ", item);
        return null;
    }
}
