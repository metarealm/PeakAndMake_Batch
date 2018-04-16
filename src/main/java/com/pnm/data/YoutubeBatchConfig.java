package com.pnm.data;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.pnm.batching.tasklets.DummyTasklet;

@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ComponentScan(basePackages = {"com.pnm.data","com.pnm.batching.writer", "com.pnm.batching.tasklets"})
public class YoutubeBatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DummyTasklet dummyTask;

	@Bean
	public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
		return jobBuilderFactory.get("importUserJob").incrementer(new RunIdIncrementer()).listener(listener).flow(step1).end().build();
	}

	// @Bean
	// public Step step1(JdbcBatchItemWriter<String> writer) {
	// return stepBuilderFactory.get("step1")
	// .<String, String > chunk(10)
	// .reader(reader())
	// .processor(processor())
	// .writer(writer)
	// .build();
	// }

	   @Bean
	    protected Step writeLines() {
	        return stepBuilderFactory
	          .get("writeLines")
	          .tasklet(dummyTask)
	          .build();
	    }
}
