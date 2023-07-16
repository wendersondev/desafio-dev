package com.desafio.dev.domain.helper;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

import java.util.UUID;

public class JobParameterBuilderHelper {

    public static final String FILE_PATH = "filePath";
    public static final String FILE_UUID = "fileUuid";
    public static final String FILE_NAME = "fileName";

    public static JobParameters create(final String filePath, final UUID id, final String fileName) {
        return new JobParametersBuilder()
                .addString(FILE_PATH, filePath)
                .addString(FILE_NAME, fileName)
                .addString(FILE_UUID, id.toString())
                .toJobParameters();
    }
}
