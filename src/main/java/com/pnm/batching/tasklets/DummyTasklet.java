/**
 * 
 */
package com.pnm.batching.tasklets;

import java.io.IOException;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.pnm.batching.services.DataExtractorService;
import com.pnm.batching.services.YTInfoExtractorService;

/**
 * @author bhabanidas
 *
 */
@Component
public class DummyTasklet implements Tasklet {

	@Autowired
	DummyTasklet(@Qualifier("YTChannelService")DataExtractorService dataService) {
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.out.println(" dummy tasklet executed");
		return null;
	}

}
