package com.pnm.batching.services.impl;

import java.util.HashSet;

import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.pnm.batching.dto.IYouTubeDTO;
import com.pnm.batching.services.YTInfoExtractorService;

@Service
public class YTChannelInfoExtractorServiceImpl extends YTInfoExtractorService {

	@Override
	public HashSet<IYouTubeDTO> getYouTubeInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public void getJsonData() {
		try {
			YouTube.Channels.List channelsListByUsernameRequest = this.youtubeService.channels().list("snippet,contentDetails,statistics");
			channelsListByUsernameRequest.setForUsername("GoogleDevelopers");

			ChannelListResponse response = channelsListByUsernameRequest.execute();
			Channel channel = response.getItems().get(0);
			System.out.printf("This channel's ID is %s. Its title is '%s', and it has %s views.\n", channel.getId(), channel.getSnippet().getTitle(), channel.getStatistics().getViewCount());
		} catch (GoogleJsonResponseException e) {
			e.printStackTrace();
			System.err.println("There was a service error: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}