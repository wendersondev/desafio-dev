package com.desafio.dev.domain.business.impl;

import com.desafio.dev.domain.business.ProcessFileService;
import com.desafio.dev.domain.mapper.TransactionEntityMapper;
import com.desafio.dev.infrastructure.entity.TransactionEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcessFileServiceImpl implements ProcessFileService {

    @Override
    public String readFile(String pathFile) throws IOException {
        final Path pathCreated = Paths.get(pathFile);
        return Files.readString(pathCreated);
    }

    @Override
    public List<TransactionEntity> processAndCreateTransactions(String textFile) {
        return textFile.lines().map(TransactionEntityMapper::mapper).collect(Collectors.toList());
    }
}
