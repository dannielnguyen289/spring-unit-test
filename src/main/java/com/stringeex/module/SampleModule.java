package com.stringeex.module;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Component
@Configurable
public class SampleModule {
    private static final String JOB_NAME = "SAMPLE_JOB";
    private static final String STEP_NAME = "SAMPLE_JOB_TASKLET_STEP";

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SampleTasklet sampleTasklet;

    @Bean
    public Job sampleJob() {
        Step sampleFirstStep = new StepBuilder(STEP_NAME, this.jobRepository)
                .tasklet(sampleTasklet, this.transactionManager).build();

        return new JobBuilder(JOB_NAME, this.jobRepository)
                .start(sampleFirstStep)
                .build();
    }
}
