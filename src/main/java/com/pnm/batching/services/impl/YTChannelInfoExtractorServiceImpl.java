package com.pnm.batching.services.impl;

import java.util.HashMap;
import java.util.HashSet;
import org.springframework.stereotype.Service;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.pnm.batching.dto.IYouTubeDTO;
import com.pnm.batching.services.YTInfoExtractorService;

@Service
public class YTChannelInfoExtractorServiceImpl extends YTInfoExtractorService {

	@Override
	public HashSet<IYouTubeDTO> getYouTubeInfo() {
		return null;
	}

	public void getJsonData() {
		try {
			HashMap<String, String> parameters = new HashMap<>();
			parameters.put("part", "snippet");
			parameters.put("maxResults", "5");
			parameters.put("q", "indian recipe");
			parameters.put("type", "channel");

			YouTube.Search.List searchListByKeywordRequest = this.youtubeService.search().list(parameters.get("part"));
			if (parameters.containsKey("maxResults")) {
				searchListByKeywordRequest.setMaxResults(Long.parseLong(parameters.get("maxResults").toString()));
			}

			if (parameters.containsKey("q") && parameters.get("q") != "") {
				searchListByKeywordRequest.setQ(parameters.get("q").toString());
			}

			if (parameters.containsKey("type") && parameters.get("type") != "") {
				searchListByKeywordRequest.setType(parameters.get("type").toString());
			}

			SearchListResponse response = searchListByKeywordRequest.execute();
			System.out.println(response);
		} catch (Exception ex) {
			System.out.println("Got exception " + ex.getMessage());
		}
	}
}