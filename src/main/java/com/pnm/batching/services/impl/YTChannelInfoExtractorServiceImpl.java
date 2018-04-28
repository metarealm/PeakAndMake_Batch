package com.pnm.batching.services.impl;

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

	@Override
	public HashSet<IYouTubeDTO> getYouTubeInfo() {
		return null;
	}

	public List<YTChannelDto> getJsonData() {
		try {
			Optional<String> pageToken = Optional.empty();
			String sPageToken = null;
			
			YouTube.Search.List searchListByKeywordRequest = this.youtubeService.search().list("snippet");
			searchListByKeywordRequest.setMaxResults(10L);
			searchListByKeywordRequest.setQ("indian recipe");
			searchListByKeywordRequest.setType("channel");
			searchListByKeywordRequest.setPageToken(sPageToken);
			SearchListResponse response = searchListByKeywordRequest.execute();
			
			System.out.println("**************response*************");
			// System.out.println(response.toPrettyString());
			ObjectMapper mapper = new ObjectMapper();
			String itemString = mapper.readTree(response.toString()).get("items").toString();
			YTChannelDto[] channelData = mapper.readValue(itemString, YTChannelDto[].class);
			System.out.println("**************json*************");
			Stream.of(channelData).forEach(data -> {
				try {
					System.out.println(new ObjectMapper().writeValueAsString(data));
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			});
			return Arrays.asList(channelData);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Got exception " + ex.getMessage());
			return null;
		}
	}
}