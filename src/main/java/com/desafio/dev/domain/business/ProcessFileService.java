package com.desafio.dev.domain.business;

import com.desafio.dev.infrastructure.entity.TransactionEntity;

import java.io.IOException;
import java.util.List;

public interface ProcessFileService {

    String readFile(String pathFile) throws IOException;

    List<TransactionEntity> processAndCreateTransactions(String textFile);
}
