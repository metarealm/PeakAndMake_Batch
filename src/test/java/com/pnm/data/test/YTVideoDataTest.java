package com.pnm.data.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.services.youtube.model.SearchListResponse;
import com.pnm.batching.dto.impl.YTVideoDtoImpl;
import com.pnm.batching.services.impl.YTVideoInfoExtractorServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class YTVideoDataTest {

	// private VideoListResponse videoResponse;
	private YTVideoInfoExtractorServiceImpl ytService;

	@Before
	public void setup() throws Exception {
		this.ytService = new YTVideoInfoExtractorServiceImpl();
		this.ytService.setDataSrc();

	}

	@Test
	public void test_YoutubeVideoSearch() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		SearchListResponse searchResponse = this.ytService.searchVideos(Optional.empty(), LocalDateTime.parse("2010-01-01T00:00:00"), LocalDateTime.parse("2011-01-01T00:00:00"));
		System.out.println("searchResponse" + searchResponse);
		assertFalse(mapper.readTree(searchResponse.toString()).get("nextPageToken") == null);
	}

	@Test
	public void test_YoutubeVideoDetail() throws IOException {
		
		List<YTVideoDtoImpl> videoData = this.ytService.getVideoDetail("t_CLAfaDcuk");

		assertEquals(videoData.get(0).getVideoID(), "t_CLAfaDcuk");
		assertEquals(videoData.get(0).getVideo_channel_title(), "Gayatri Vantillu");
		assertEquals(videoData.get(0).getVideo_channel_id(), "UCaGpgaj_OHeROcx1IpLYlHQ");
		assertEquals(videoData.get(0).getVideoID(), "t_CLAfaDcuk");
		assertEquals(videoData.get(0).getVideo_channel_title(), "Gayatri Vantillu");
		assertEquals(videoData.get(0).getVideo_channel_id(), "UCaGpgaj_OHeROcx1IpLYlHQ");

	}

}
