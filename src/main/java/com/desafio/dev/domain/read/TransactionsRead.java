package com.desafio.dev.domain.read;

import com.desafio.dev.domain.business.ProcessFileService;
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

    @Autowired
    private ProcessFileService processFileService;

    private  boolean processed;

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(!processed){
            log.info("TransactionsRead - Start read() with filename {} ", parameters.getFileName());

            String fileRead = processFileService.readFile(parameters.getFilePath().concat(parameters.getFileName()));

            log.info("TransactionsRead - Finalized read() with filename {} ", parameters.getFileName());

            processed = true;

            return fileRead;
        }
        return null;
    }
}
