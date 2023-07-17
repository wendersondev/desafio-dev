package com.desafio.dev.api.rest.v1.controller;

import com.desafio.dev.api.rest.v1.response.TransactionsResponse;
import com.desafio.dev.domain.business.TransactionService;
import com.desafio.dev.domain.business.UploadFileService;
import com.desafio.dev.domain.helper.JobParameterBuilderHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private TransactionService transactionService;

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

    @GetMapping
    public ResponseEntity<TransactionsResponse> getTransactions(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return ResponseEntity.ok(transactionService.findTransactions(name, pageable));
    }

}
