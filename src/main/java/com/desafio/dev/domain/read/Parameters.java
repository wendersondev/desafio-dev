package com.desafio.dev.domain.read;

import lombok.Getter;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
@StepScope
public class Parameters {

    @Value("#{jobParameters['filePath']}")
    private String filePath;

    @Value("#{jobParameters['fileUuid']}")
    private String fileUuid;

    @Value("#{jobParameters['fileName']}")
    private String fileName;
}
