package com.desafio.dev.config;

import com.desafio.dev.domain.processors.TransactionsProcessor;
import com.desafio.dev.domain.read.TransactionsRead;
import com.desafio.dev.domain.writer.TransactionsWriter;
import com.desafio.dev.job.TransactionStepListener;
import com.desafio.dev.job.TransactionsJobListener;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import static com.desafio.dev.utils.ConstantsUtils.*;

@Configuration
@RequiredArgsConstructor
public class TransactionsJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final TransactionsJobListener transactionsJobListener;
    private final StepBuilderFactory stepBuilderFactory;
    private final TransactionStepListener transactionStepListener;

    @Bean
    @Qualifier(QUALIFIER_JOB)
    public JobLauncher jobLauncherTransactions(JobRepository jobRepository) {
        final SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
        simpleJobLauncher.setJobRepository(jobRepository);
        simpleJobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return simpleJobLauncher;
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get(DEFAULT_JOB_STEP)
                .incrementer(new RunIdIncrementer())
                .listener(transactionsJobListener)
                .flow(step())
                .end()
                .build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get(DEFAULT_STEP)
                .<String, String>chunk(1000)
                .reader(createTransactionRead())
                .processor(createTransactionsProcessor())
                .writer(createTransactionsWriter())
                .listener(transactionStepListener)
                .build();
    }

    @Bean
    @StepScope
    public TransactionsRead createTransactionRead() {
        return new TransactionsRead();
    }

    @Bean
    public TransactionsProcessor createTransactionsProcessor() {
        return new TransactionsProcessor();
    }

    @Bean
    public TransactionsWriter createTransactionsWriter() {
        return new TransactionsWriter();
    }

}
