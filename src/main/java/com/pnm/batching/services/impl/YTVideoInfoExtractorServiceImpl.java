package com.pnm.batching.services.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.VideoListResponse;
import com.pnm.batching.dto.IYouTubeDTO;
import com.pnm.batching.dto.impl.YTVideoDtoImpl;
import com.pnm.batching.services.YTInfoExtractorService;

@Service
@Qualifier("YTVideoService")
public class YTVideoInfoExtractorServiceImpl extends YTInfoExtractorService {

	private int maxQueryLoop = 10;
	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public HashSet<IYouTubeDTO> getYouTubeInfo() {
		return null;
	}

	public List<?> getJsonData(LocalDateTime startDate, LocalDateTime endQueryDate) {
		List<YTVideoDtoImpl> videoData = new ArrayList<>();
		try {
			Optional<String> pageToken = Optional.empty();

			for (int i = 0; i < maxQueryLoop; i++) {
				SearchListResponse searchResponse = this.searchVideos(pageToken, startDate, endQueryDate);
				if (mapper.readTree(searchResponse.toString()).get("nextPageToken") == null)
					break;
				pageToken = Optional.of(mapper.readTree(searchResponse.toString()).get("nextPageToken").asText());
				List<SearchResult> searchResultList = searchResponse.getItems();

				if (searchResultList != null) {
					List<String> videoIds = new ArrayList<String>();
					for (SearchResult searchResult : searchResultList) {
						videoIds.add(searchResult.getId().getVideoId());
					}
					String videoId = String.join(",", videoIds);

					// Call the YouTube Data API's youtube.videos.list method
					// for the specified videos.
					videoData.addAll(this.getVideoDetail(videoId));
				}
				System.out.println("*****************" + pageToken + "******************");
			}

//			printVideoData(videoData);
			return videoData;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Got exception " + ex.getMessage());
			return null;
		}

	}

	public SearchListResponse searchVideos(Optional<String> pageToken, LocalDateTime startDate, LocalDateTime endQueryDate) throws IOException {
		YouTube.Search.List searchListByKeywordRequest = this.youtubeService.search().list("snippet").setMaxResults(Long.valueOf(env.getProperty("YTVideo.querySize")))
				.setQ("indian recipe cooking food").setType("video").setVideoEmbeddable("true").setFields("items(id/videoId),nextPageToken")
				.setPublishedAfter(new DateTime(startDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()))
				.setPublishedBefore(new DateTime(endQueryDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));

		pageToken.ifPresent(val -> searchListByKeywordRequest.setPageToken(val));
		return searchListByKeywordRequest.execute();

	}

	public List<YTVideoDtoImpl> getVideoDetail(String videoId) throws IOException {
		YouTube.Videos.List listVideosRequest = this.youtubeService.videos().list("snippet, statistics").setId(videoId);
		VideoListResponse listResponse = listVideosRequest.execute();

		System.out.println(listResponse);
		if (mapper.readTree(listResponse.toString()).get("items") != null) {
			String itemString = mapper.readTree(listResponse.toString()).get("items").toString();
			return Arrays.asList(mapper.readValue(itemString, YTVideoDtoImpl[].class));
		} else {
			return null;
		}

	}

	public void printVideoData(List<YTVideoDtoImpl> videoData) {
		videoData.forEach(data -> {
			try {
				System.out.println(new ObjectMapper().writeValueAsString(data));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		});
	}

}
