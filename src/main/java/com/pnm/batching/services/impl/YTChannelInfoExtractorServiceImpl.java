package com.pnm.batching.services.impl;

import java.util.HashSet;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pnm.batching.dto.IYouTubeDTO;
import com.pnm.batching.services.YTInfoExtractorService;

@Service
public class YTChannelInfoExtractorServiceImpl extends YTInfoExtractorService {

	@Override
	public HashSet<IYouTubeDTO> getYouTubeInfo() {
		RestTemplate restTemplate = new RestTemplate();
		// Responses ch =
		// restTemplate.getForObject("http://services.groupkt.com/country/get/all",
		// Responses.class);
		return null;
	}

}
