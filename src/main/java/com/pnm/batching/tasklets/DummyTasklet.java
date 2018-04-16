/**
 * 
 */
package com.pnm.batching.tasklets;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

/**
 * @author bhabanidas
 *
 */
@Component
public class DummyTasklet implements Tasklet {
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.out.println(" dummy tasklet executed");
		return null;
	}

}
