package com.pnm.batching.services.impl;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.pnm.batching.dto.IYouTubeDTO;
import com.pnm.batching.dto.impl.YTChannelDto;
import com.pnm.batching.services.YTInfoExtractorService;

@Service
@Qualifier("YTChannelService")
public class YTChannelInfoExtractorServiceImpl<E> extends YTInfoExtractorService {


	private int maxQueryLoop = 5;

	@Override
	public HashSet<IYouTubeDTO> getYouTubeInfo() {
		return null;
	}

	public List<YTChannelDto> getJsonData(LocalDateTime startDate, LocalDateTime endQueryDate) {
		this.maxQueryLoop = Integer.valueOf(env.getProperty("YTChannel.repeatSameQuery"));
		
		List<YTChannelDto> channelData = new ArrayList<>();
		try {
			Optional<String> pageToken = Optional.empty();
			for (int i = 0; i < maxQueryLoop; i++) {
				SearchListResponse response = this.searchChannels(pageToken, startDate, endQueryDate);

				ObjectMapper mapper = new ObjectMapper();
				if (mapper.readTree(response.toString()).get("items") != null) {
					String itemString = mapper.readTree(response.toString()).get("items").toString();
					System.out.println(itemString);
					channelData.addAll(Arrays.asList(mapper.readValue(itemString, YTChannelDto[].class)));
					System.out.println("**************json*************");
				}

				if (mapper.readTree(response.toString()).get("nextPageToken") == null)
					break;
				pageToken = Optional.of(mapper.readTree(response.toString()).get("nextPageToken").asText());
				System.out.println("*****************" + pageToken + "******************");

			}

			channelData.forEach(data -> {
				try {
					System.out.println(new ObjectMapper().writeValueAsString(data));
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			});
			return channelData;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Got exception " + ex.getMessage());
			return null;
		}
	}
	
	public SearchListResponse searchChannels(Optional<String> pageToken, LocalDateTime startDate, LocalDateTime endQueryDate) throws IOException {
				
		YouTube.Search.List searchListByKeywordRequest = this.youtubeService.search().list("snippet").setMaxResults(Long.valueOf(env.getProperty("YTChannel.querySize")))
				.setQ("\"indian recipe\" cooking food").setType("channel")
				.setPublishedAfter(new DateTime(startDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()))
				.setPublishedBefore(new DateTime(endQueryDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));

		pageToken.ifPresent(val -> searchListByKeywordRequest.setPageToken(val));
		return searchListByKeywordRequest.execute();

	}

}