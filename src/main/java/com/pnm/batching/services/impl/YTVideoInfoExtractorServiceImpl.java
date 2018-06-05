package com.pnm.batching.services.impl;

import java.io.UncheckedIOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.Joiner;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.VideoListResponse;
import com.pnm.batching.dto.IYouTubeDTO;
import com.pnm.batching.dto.impl.YTChannelDto;
import com.pnm.batching.dto.impl.YTVideoDtoImpl;
import com.pnm.batching.dto.mongo.timesplice.LastProcessTime;
import com.pnm.batching.reactive.data.DateProcessRepository;
import com.pnm.batching.services.YTInfoExtractorService;

@Service
@Qualifier("YTVideoService")
public class YTVideoInfoExtractorServiceImpl extends YTInfoExtractorService {

	private int maxQueryLoop = 10;

	@Override
	public HashSet<IYouTubeDTO> getYouTubeInfo() {
		return null;
	}

	public List<?> getJsonData(LocalDateTime startDate, LocalDateTime endQueryDate) {
		List<YTVideoDtoImpl> videoData = new ArrayList<>();
		try {
			Optional<String> pageToken = Optional.empty();

			YouTube.Search.List searchListByKeywordRequest = this.youtubeService.search().list("snippet");
			searchListByKeywordRequest.setMaxResults(5L);
			searchListByKeywordRequest.setQ("indian recipe cooking food");
			searchListByKeywordRequest.setType("video");
			searchListByKeywordRequest.setVideoEmbeddable("true");
			searchListByKeywordRequest.setFields("items(id/videoId)");
			searchListByKeywordRequest.setPublishedAfter(new DateTime(startDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));
			searchListByKeywordRequest.setPublishedBefore(new DateTime(endQueryDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));

			for (int i = 0; i < maxQueryLoop; i++) {

				pageToken.ifPresent(val -> searchListByKeywordRequest.setPageToken(val));
				SearchListResponse searchResponse = searchListByKeywordRequest.execute();
				List<SearchResult> searchResultList = searchResponse.getItems();
				List<String> videoIds = new ArrayList<String>();

				if (searchResultList != null) {
					for (SearchResult searchResult : searchResultList) {
						videoIds.add(searchResult.getId().getVideoId());
					}
					Joiner stringJoiner = Joiner.on(',');
					String videoId = stringJoiner.join(videoIds);

					// Call the YouTube Data API's youtube.videos.list method to
					// retrieve the resources that represent the specified
					// videos.
					YouTube.Videos.List listVideosRequest = this.youtubeService.videos().list("snippet, statistics").setId(videoId);
					VideoListResponse listResponse = listVideosRequest.execute();
					// List<Video> videoList = listResponse.getItems();
					ObjectMapper mapper = new ObjectMapper();
					System.out.println(listResponse);
					if (mapper.readTree(listResponse.toString()).get("items") != null) {
						String itemString = mapper.readTree(listResponse.toString()).get("items").toString();
						System.out.println(itemString);
						videoData.addAll(Arrays.asList(mapper.readValue(itemString, YTVideoDtoImpl[].class)));
						System.out.println("**************json*************");
					}
				}
				System.out.println("*****************" + pageToken + "******************");
			}

			videoData.forEach(data -> {
				try {
					System.out.println(new ObjectMapper().writeValueAsString(data));
				} catch (JsonProcessingException e) {
					e.printStackTrace();
					throw new UncheckedIOException(e);
				}
			});
			return videoData;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Got exception " + ex.getMessage());
			return null;
		}

	}

}
