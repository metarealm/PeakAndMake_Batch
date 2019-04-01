package com.pnm.data;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.Scheduled;

import com.pnm.batching.reactive.data.ChannelRepository;

@Configuration
@EnableBatchProcessing
@ComponentScan(basePackages = {"com.pnm.batching.tasklets", "com.pnm.data",
		"com.pnm.batching.services.impl", "com.pnm.batching.services",
		"com.pnm.batching.*"})
@EnableMongoRepositories(basePackageClasses = ChannelRepository.class)
public class YoutubeBatchConfig extends DefaultBatchConfigurer {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public JobLauncher jobLauncher(JobRepository jobRepository)
			throws Exception {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository);
		jobLauncher.afterPropertiesSet();

		return jobLauncher;
	}

	@Bean("importChannelDataJob")
	public Job importChannelDataJob(Step channelDataProcessor,
			Step videoDataProcessor) {
		return jobBuilderFactory.get("importYTdataJob")
				.start(channelDataProcessor).next(videoDataProcessor).build();
	}

	@Bean("populateSubTitles")
	public Job populateSubTitles(Step dummyTask) {
		return jobBuilderFactory.get("populateYTCC").start(dummyTask).build();
	}

	@Bean("pushToSolr")
	public Job pushToSolr(Step dummyTask) {
		return jobBuilderFactory.get("pushToSolr").start(dummyTask).build();
	}

	@Bean
	protected Step channelDataProcessor(
			JobCompletionNotificationListener listener,
			@Qualifier("YTChannelTasklet") Tasklet importChannelDataTask) {
		return stepBuilderFactory.get("process channel data")
				.tasklet(importChannelDataTask).listener(listener).build();
	}

	@Bean
	protected Step videoDataProcessor(
			JobCompletionNotificationListener listener,
			@Qualifier("YTVideoTasklet") Tasklet importVideoDataTask) {
		return stepBuilderFactory.get("process Video data")
				.tasklet(importVideoDataTask).listener(listener).build();
	}

	@Bean
	protected Step dummyTask(@Qualifier("DummyTasklet") Tasklet dummyTask) {
		return stepBuilderFactory.get("processdummy").tasklet(dummyTask)
				.build();
	}


}
