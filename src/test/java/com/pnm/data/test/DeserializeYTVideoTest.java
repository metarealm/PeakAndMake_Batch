package com.pnm.data.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.VideoListResponse;
import com.pnm.batching.dto.impl.YTVideoDtoImpl;
import com.pnm.batching.services.YTInfoExtractorService;
import com.pnm.batching.services.impl.YTVideoInfoExtractorServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class DeserializeYTVideoTest {

	private VideoListResponse videoResponse;
	private YTInfoExtractorService ytService;

	@Before
	public void setup() throws Exception {
		this.ytService = new YTVideoInfoExtractorServiceImpl();
		this.ytService.setDataSrc();
		YouTube.Videos.List listVideosRequest = this.ytService.getDataSrc().videos().list("snippet, statistics").setId("t_CLAfaDcuk");
		videoResponse = listVideosRequest.execute();
		System.out.println(" The list response is =" + videoResponse);
	}

	@Test
	public void test_YoutubeVideoDetail () throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		assertFalse(mapper.readTree(this.videoResponse.toString()).get("items") == null);
		String itemString = mapper.readTree(this.videoResponse.toString()).get("items").toString();
		System.out.println(itemString);
		YTVideoDtoImpl[] videoData = mapper.readValue(itemString, YTVideoDtoImpl[].class);
		
		assertEquals(videoData[0].getVideoID(), "t_CLAfaDcuk");
		assertEquals(videoData[0].getVideo_channel_title(), "Gayatri Vantillu");
		assertEquals(videoData[0].getVideo_channel_id(), "UCaGpgaj_OHeROcx1IpLYlHQ");
		assertEquals(videoData[0].getVideoID(), "t_CLAfaDcuk");
		assertEquals(videoData[0].getVideo_channel_title(), "Gayatri Vantillu");
		assertEquals(videoData[0].getVideo_channel_id(), "UCaGpgaj_OHeROcx1IpLYlHQ");

	}

}
