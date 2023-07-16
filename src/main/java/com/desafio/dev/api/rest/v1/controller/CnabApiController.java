package com.desafio.dev.api.rest.v1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/v1/cnab/transactions")
public class CnabApiController {

    @Autowired
    private final Job job;

    @Autowired
    private final JobLauncher jobLauncher;

    @PostMapping("/load")
    public void process() {

    }

}
