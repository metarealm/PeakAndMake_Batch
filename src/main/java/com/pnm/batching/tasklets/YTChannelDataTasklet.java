package com.pnm.batching.tasklets;

import java.io.IOException;
import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.pnm.batching.services.DataExtractorService;
import com.pnm.batching.services.DataLoaderService;
import com.pnm.batching.services.YTInfoExtractorService;

@Primary
@Component
public class YTChannelDataTasklet implements Tasklet {

	private YTInfoExtractorService extractorSvc;
	private DataLoaderService loaderSvc;
	private MongoClient mongo;
	
	@Autowired
	public YTChannelDataTasklet(DataExtractorService ytService ,DataLoaderService mongLoaderSvc) {
		this.extractorSvc = (YTInfoExtractorService) ytService;
		this.loaderSvc = mongLoaderSvc;
		try {
			this.extractorSvc.setDataSrc();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		 List<?> ytData = this.extractorSvc.getJsonData();
		loaderSvc.loadData(ytData);
		System.out.println(" dummy tasklet executed");
		return RepeatStatus.FINISHED;
	}

}
