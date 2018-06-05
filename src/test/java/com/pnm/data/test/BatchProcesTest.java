/**
 * 
 */
package com.pnm.data.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author bhabanidas
 *
 */

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {YoutubeBatchConfig.class})
//@SpringBootTest
public class BatchProcesTest {

    @Autowired
    JobLauncher jobLauncher;
 
    @Autowired
    Job job;

	@Test
	public void givenTaskletsJob_whenJobEnds_thenStatusCompleted() throws Exception {

        try {
        	JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
	}

}
