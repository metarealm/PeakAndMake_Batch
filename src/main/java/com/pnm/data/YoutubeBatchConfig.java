package com.pnm.data;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.pnm.batching.services.DataExtractorService;
import com.pnm.batching.services.impl.YTChannelInfoExtractorServiceImpl;


@Configuration
@EnableBatchProcessing
@ComponentScan(basePackages={"com.pnm.data","com.pnm.batching.services.impl","com.pnm.batching.services"})
public class YoutubeBatchConfig extends DefaultBatchConfigurer{

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DummyTasklet dummyTask;
	
//	@Bean DataExtractorService getYTChannelDtSvc(){
//		return new YTChannelInfoExtractorServiceImpl();
//	}
	
	
	@Bean
	public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
		return jobBuilderFactory.get("importUserJob").start(step1).build();
	}

	@Bean
	protected Step writeLines() {
		return stepBuilderFactory.get("writeLines").tasklet(dummyTask).build();
	}

	
}
