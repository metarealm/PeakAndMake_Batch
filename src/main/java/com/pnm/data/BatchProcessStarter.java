package com.pnm.data;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.pnm.batching.dto.mongo.timesplice.LastProcessTime;
import com.pnm.batching.dto.mongo.timesplice.ProcessTimeLine;
import com.pnm.batching.reactive.data.DateProcessRepository;


@Component
public class BatchProcessStarter implements CommandLineRunner {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private MongoClient mongoClient;

	@Autowired
	private DateProcessRepository dateRepository;

	@Autowired
	private Job job;

	@Override
	public void run(String... args) throws Exception {
		boolean DateDataExists = mongoClient.getDatabase("peeknmake").listCollectionNames().into(new ArrayList<String>()).contains("ProcessDateData");

		if ( !DateDataExists ) {
			LocalDateTime queryStartTime = LocalDateTime.parse("2005-01-01T00:00:00");
			LastProcessTime datedata = new LastProcessTime("YouTubeChannelData", queryStartTime);
			dateRepository.save(datedata);
			System.out.println("no of rows now are " + dateRepository.count());
		}
		System.out.println("Starting the batch job");
		JobExecution execution = jobLauncher.run(job, new JobParameters());
		System.out.println("Job Status : " + execution.getStatus());
		System.out.println("Job completed");
	}

	public void run_old(String... args) throws Exception {

		System.out.println(" date data loader");

		boolean DateDataExists = mongoClient.getDatabase("peeknmake").listCollectionNames().into(new ArrayList<String>()).contains("DateData");
		if (!DateDataExists) {
			LocalDateTime queryStartTime = LocalDateTime.parse("2018-01-01T00:00:00");
			for (int i = 0; i < 200; i++) {
				queryStartTime = queryStartTime.minusMonths(1L);
				ProcessTimeLine datedata = new ProcessTimeLine(queryStartTime);
				// dateRepository.save(datedata);
			}
		}

		System.out.println("Starting the batch job");
		JobExecution execution = jobLauncher.run(job, new JobParameters());
		System.out.println("Job Status : " + execution.getStatus());
		System.out.println("Job completed");
	}

}
