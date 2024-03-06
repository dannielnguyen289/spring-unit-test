package com.stringeex.module;

import com.stringeex.core.consts.JobParameterName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SampleTasklet implements Tasklet {
    private final Log LOGGER = LogFactory.getLog(getClass());

    @Autowired
    SampleService sampleService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // Get job parameters
        JobParameters jobParameters = chunkContext.getStepContext().getStepExecution().getJobParameters();
        Long timestamp = jobParameters.getLong(JobParameterName.JOB_TIMESTAMP);

        this.LOGGER.debug(String.format("SAMPLE TASKLET EXECUTE: %d", timestamp));

        // Generate entity
        sampleService.generate(timestamp);

        // Finish step
        return RepeatStatus.FINISHED;
    }
}
