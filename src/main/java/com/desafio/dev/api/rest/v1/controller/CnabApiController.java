package com.desafio.dev.api.rest.v1.controller;

import com.desafio.dev.domain.business.UploadFileService;
import com.desafio.dev.domain.helper.JobParameterBuilderHelper;
import com.desafio.dev.utils.Sanitizer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static com.desafio.dev.utils.ConstantsUtils.PATH_UPLOAD;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/v1/cnab/transactions")
public class CnabApiController {

    @Autowired
    private final Job job;

    @Autowired
    private final JobLauncher jobLauncher;

    @Autowired
    private UploadFileService uploadFileService;

    @PostMapping("/load")
    public ResponseEntity<Void> process(@RequestParam("file") MultipartFile file)  {
        try{
            UUID fileName = UUID.randomUUID();
            uploadFileService.upload(file, fileName.toString());
            final JobParameters jobParameters = JobParameterBuilderHelper
                    .create(PATH_UPLOAD, fileName, fileName.toString());

            final JobExecution jobExecution = jobLauncher.run(job, jobParameters);
            log.info("Job status : {} ", jobExecution.getStatus());
        }
        catch (JobInstanceAlreadyCompleteException | JobExecutionAlreadyRunningException | JobParametersInvalidException |
               IOException | JobRestartException e) {
            log.error("Job not started, status {} ", e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

}
