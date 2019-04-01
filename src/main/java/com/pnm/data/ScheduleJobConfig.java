package com.pnm.data;

import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class ScheduleJobConfig {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	@Qualifier("importChannelDataJob")
	private Job importChannelDataJob;

	@Autowired
	@Qualifier("populateSubTitles")
	private Job populateSubTitles;

	@Autowired
	@Qualifier("pushToSolr")
	private Job pushToSolr;

	private AtomicInteger batchRunCounter = new AtomicInteger(0);
	private AtomicBoolean enabled = new AtomicBoolean(true);

	@Scheduled(cron = "0 40 */1 * * * ")
	public void scheduleFixedDelayTask()
			throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		if (enabled.get()) {
			System.out.println(
					"chron task time- " + System.currentTimeMillis() / 1000);
			Date date = new Date();
			JobExecution execution ;
			execution = jobLauncher.run(importChannelDataJob,
					new JobParametersBuilder().addDate("launchDate", date)
							.toJobParameters());
			System.out.println(
					"importChannelData Job Status : " + execution.getStatus());
			execution = jobLauncher.run(populateSubTitles,
					new JobParametersBuilder().addDate("launchDate", date)
							.toJobParameters());
			System.out.println(
					"populateSubTitles Job Status : " + execution.getStatus());
		}
	}
}
