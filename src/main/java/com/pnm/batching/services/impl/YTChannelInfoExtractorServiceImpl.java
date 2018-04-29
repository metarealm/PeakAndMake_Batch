package com.pnm.batching.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.pnm.batching.dto.IYouTubeDTO;
import com.pnm.batching.dto.impl.YTChannelDto;
import com.pnm.batching.services.YTInfoExtractorService;

@Service
public class YTChannelInfoExtractorServiceImpl<E> extends YTInfoExtractorService {

	private int maxQueryLoop = 10;

	@Override
	public HashSet<IYouTubeDTO> getYouTubeInfo() {
		return null;
	}

	public List<YTChannelDto> getJsonData() {
		List<YTChannelDto> channelData = new ArrayList<>();
		try {
			Optional<String> pageToken = Optional.empty();

			YouTube.Search.List searchListByKeywordRequest = this.youtubeService.search().list("snippet");
			searchListByKeywordRequest.setMaxResults(10L);
			searchListByKeywordRequest.setQ("indian recipe");
			searchListByKeywordRequest.setType("channel");

			for (int i = 0; i < maxQueryLoop; i++) {
				if (pageToken.isPresent()){
					searchListByKeywordRequest.setPageToken(pageToken.get());
					System.out.println("current page token is "+ pageToken.get());
				}
				SearchListResponse response = searchListByKeywordRequest.execute();
				System.out.println("**************response*************");
				// System.out.println(response.toPrettyString());
				ObjectMapper mapper = new ObjectMapper();
				pageToken =Optional.of(mapper.readTree(response.toString()).get("nextPageToken").asText());
				String itemString = mapper.readTree(response.toString()).get("items").toString();
				System.out.println("*****************" + pageToken + "******************");
				System.out.println(itemString);
				channelData.addAll(Arrays.asList(mapper.readValue(itemString, YTChannelDto[].class)));
				System.out.println("**************json*************");
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
}