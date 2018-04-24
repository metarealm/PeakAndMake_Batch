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
import org.springframework.stereotype.Component;

import com.pnm.batching.services.DataExtractorService;
import com.pnm.batching.services.YTInfoExtractorService;

/**
 * @author bhabanidas
 *
 */
@Component
public class DummyTasklet implements Tasklet {
	
	private YTInfoExtractorService ytsvc;
	@Autowired
	DummyTasklet(DataExtractorService ytService){
		this.ytsvc = (YTInfoExtractorService) ytService;
		try {
			this.ytsvc.setDataSrc();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		this.ytsvc.getJsonData();
		System.out.println(" dummy tasklet executed");
		return null;
	}

}
