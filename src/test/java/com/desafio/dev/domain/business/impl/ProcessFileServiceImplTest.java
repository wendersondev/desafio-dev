package com.desafio.dev.domain.business.impl;

import com.desafio.dev.infrastructure.entity.TransactionEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProcessFileServiceImplTest {

    @Mock
    private Path mockPath;

    @InjectMocks
    private ProcessFileServiceImpl processFileService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReadFile() throws IOException {
        final Path pathCreated = Paths.get("src/test/resources/uploads/CNAB.txt");
        var expected = Files.readString(pathCreated);

        String result = processFileService.readFile("src/test/resources/uploads/CNAB.txt");

        assertEquals(expected, result);
    }

    @Test
    void testProcessAndCreateTransactions() throws IOException {
        final Path pathCreated = Paths.get("src/test/resources/uploads/CNAB.txt");
        var textFile = Files.readString(pathCreated);

        List<TransactionEntity> result = processFileService.processAndCreateTransactions(textFile);

        assertEquals(21, result.size());
    }

}
