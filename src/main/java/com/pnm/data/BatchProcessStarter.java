package com.pnm.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.pnm.batching.dto.mongo.timesplice.LastProcessTime;
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

		if (DateDataExists) {
			Optional<LastProcessTime> channelLastProcessedTime = dateRepository.findById("YouTubeChannelData");
			if (!channelLastProcessedTime.isPresent()) {
				LocalDateTime queryStartTime = LocalDateTime.parse("2005-01-01T00:00:00");
				LastProcessTime datedata = new LastProcessTime("YouTubeChannelData", queryStartTime);
				dateRepository.save(datedata);
				System.out.println("no of rows now are " + dateRepository.count());
			}
			Optional<LastProcessTime> videoLastProcessedTime = dateRepository.findById("YouTubeVideoData");
			if (!videoLastProcessedTime.isPresent()) {
				LocalDateTime queryStartTime = LocalDateTime.parse("2005-01-01T00:00:00");
				LastProcessTime datedata = new LastProcessTime("YouTubeVideoData", queryStartTime);
				dateRepository.save(datedata);
				System.out.println("no of rows now are " + dateRepository.count());
			}
		}else{
			LocalDateTime queryStartTime = LocalDateTime.parse("2005-01-01T00:00:00");
			LastProcessTime datedata = new LastProcessTime("YouTubeChannelData", queryStartTime);
			dateRepository.save(datedata);
			queryStartTime = LocalDateTime.parse("2005-01-01T00:00:00");
			datedata = new LastProcessTime("YouTubeVideoData", queryStartTime);
			dateRepository.save(datedata);
		}
		System.out.println("Starting the batch job");
		JobExecution execution = jobLauncher.run(job, new JobParameters());
		System.out.println("Job Status : " + execution.getStatus());
		System.out.println("Job completed");
	}

}
