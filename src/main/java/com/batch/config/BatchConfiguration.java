package com.batch.config;


import com.batch.steps.ItemDescompressStep;
import com.batch.steps.ItemProcessorStep;
import com.batch.steps.ItemReaderStep;
import com.batch.steps.ItemWriterStep;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;


    @JobScope
    @Bean
    public ItemDescompressStep itemDescompressStep(){
        return new ItemDescompressStep();
    }

    @JobScope
    @Bean
    public ItemReaderStep itemReaderStep(){
        return new ItemReaderStep();
    }

    @JobScope
    @Bean
    public ItemProcessorStep itemProcessorStep(){
        return new ItemProcessorStep();
    }

    @JobScope
    @Bean
    public ItemWriterStep itemWriterStep(){
        return new ItemWriterStep();
    }

    @Bean
    public Step descompressFileStep(){
        return stepBuilderFactory.get("itemDescompressStep")
                .tasklet(itemDescompressStep())
                .build();
    }

    @Bean
    public Step readFileStep(){
        return stepBuilderFactory.get("itemReaderStep")
                .tasklet(itemReaderStep())
                .build();
    }

    @Bean
    public Step processDataStep(){
        return stepBuilderFactory.get("itemProcessStep")
                .tasklet(itemProcessorStep())
                .build();
    }

    @Bean
    public Step writerDataStep(){
        return stepBuilderFactory.get("itemWriterStep")
                .tasklet(itemWriterStep())
                .build();
    }

    @Bean
    public Job readCSVJob(){
        return jobBuilderFactory.get("readCSVJob")
                .start(descompressFileStep())
                .next(readFileStep())
                .next(processDataStep())
                .next(writerDataStep())
                .build();
    }

}
