package com.desafio.dev.domain.read;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@StepScope
@Slf4j
public class TransactionsRead implements ItemReader<String> {

    @Autowired
    private Parameters parameters;

    private  boolean processed;

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(!processed){
            log.info("TransactionsRead - Start read()");
            log.info("dddd " + parameters.getFileName());
            log.info("dddd " + parameters.getFilePath());
            log.info("dddd " + parameters.getFileUuid());
            processed = true;
            return "Teste funcional";
        }
        return null;
    }
}
