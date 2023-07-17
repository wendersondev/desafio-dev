package com.desafio.dev.api.rest.v1.controller;

import com.desafio.dev.api.rest.v1.response.TransactionsResponse;
import com.desafio.dev.domain.business.TransactionService;
import com.desafio.dev.domain.business.UploadFileService;
import com.desafio.dev.domain.helper.JobParameterBuilderHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/v1/cnab/transactions")
@Api("Api de controle de Transações CNAB")
public class CnabApiController {

    @Autowired
    private final Job job;

    @Autowired
    private final JobLauncher jobLauncher;

    @Autowired
    private UploadFileService uploadFileService;

    @Autowired
    private TransactionService transactionService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retona apenas sucesso"),
            @ApiResponse(code = 400, message = "Retorna uma exception de bath não incializado")
    })
    @PostMapping("/load")
    @ApiOperation(value = "Importação de transações por meio de arquivo txt, inicializando o batch")
    public ResponseEntity<Void> postImportTransactions(@RequestParam("file") MultipartFile file)  {
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

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna Lista paginada e outra lista com valores")
    })
    @ApiOperation(value = "Lista de transações paginadas e quando buscada pelo nome da empresa retorna tbm objeto com valores")
    @GetMapping
    public ResponseEntity<TransactionsResponse> getTransactions(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        log.info("getTransactions : {} ", page);
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return ResponseEntity.ok(transactionService.findTransactions(name, pageable));
    }

}
