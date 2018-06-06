
package com.pnm.batching.tasklets;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.pnm.batching.dto.mongo.timesplice.LastProcessTime;
import com.pnm.batching.reactive.data.DateProcessRepository;
import com.pnm.batching.services.DataExtractorService;
import com.pnm.batching.services.DataLoaderService;
import com.pnm.batching.services.YTInfoExtractorService;

@Primary
@Component("YTChannelTasklet")
public class YTChannelDataTasklet implements Tasklet {


	protected Environment env;
	private YTInfoExtractorService extractorSvc;
	private DataLoaderService loaderSvc;
	private DateProcessRepository dateRepository;
	private int taskletLoop = 0;
	private int maxLoopCount = 5;

	@Autowired
	public YTChannelDataTasklet(@Qualifier("YTChannelService") DataExtractorService ytService, @Qualifier("MongoChannelService") DataLoaderService mongLoaderSvc,
			DateProcessRepository dateRepository,Environment env) {
		this.extractorSvc = (YTInfoExtractorService) ytService;
		this.dateRepository = dateRepository;
		this.loaderSvc = mongLoaderSvc;
		try {
			this.extractorSvc.setDataSrc();
		} catch (IOException e) {
			e.printStackTrace();
		}
		maxLoopCount = Integer.valueOf(env.getProperty("YTVideo.BatchSize"));
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		Optional<LastProcessTime> lastProcessedTime = dateRepository.findById("YouTubeChannelData");

		lastProcessedTime.ifPresent(value -> {
			LocalDateTime startTime = value.getQueryStartDate();
			System.out.println(startTime.toString());
			LocalDateTime endTime = value.getQueryStartDate().plusMonths(1L);
			if (Duration.between(endTime, LocalDateTime.now()).getSeconds() < 0)
				return;
			List<?> ytData = this.extractorSvc.getJsonData(startTime, endTime);
			loaderSvc.loadData(ytData);
			value.setQueryStartDate(endTime);
			System.out.println("going to save the new date" + endTime.toString());
			dateRepository.save(value);
			System.out.println(" dummy tasklet executed");
		});
		taskletLoop++;
		System.out.println("tasklet loop count is =" + taskletLoop);
		return (taskletLoop > this.maxLoopCount) ? RepeatStatus.FINISHED : RepeatStatus.CONTINUABLE;
	}

}
