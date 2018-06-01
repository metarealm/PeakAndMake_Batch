package com.pnm.data;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.pnm.batching.reactive.data.ChannelRepository;

@Configuration
@EnableBatchProcessing
@ComponentScan(basePackages = { "com.pnm.batching.tasklets", "com.pnm.data", "com.pnm.batching.services.impl", "com.pnm.batching.services", "com.pnm.batching.*" })
@EnableMongoRepositories(basePackageClasses = ChannelRepository.class)
public class YoutubeBatchConfig extends DefaultBatchConfigurer {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job importChannelDataJob(Step channelDataProcessor,Step videoDataProcessor ) {
		return jobBuilderFactory.get("importYTdataJob").start(channelDataProcessor).next(videoDataProcessor).build();
	}

	@Bean
	protected Step channelDataProcessor(JobCompletionNotificationListener listener , 
			@Qualifier("YTChannelTasklet")Tasklet importChannelDataTask) {
		return stepBuilderFactory.get("process channel data").tasklet(importChannelDataTask).listener(listener).build();
	}
	
	@Bean
	protected Step videoDataProcessor(JobCompletionNotificationListener listener,
			@Qualifier("YTVideoTasklet")Tasklet importVideoDataTask) {
		return stepBuilderFactory.get("process Video data").tasklet(importVideoDataTask).listener(listener).build();
	}

}
