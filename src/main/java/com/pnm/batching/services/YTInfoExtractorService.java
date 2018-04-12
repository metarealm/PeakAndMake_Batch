package com.pnm.batching.services;

import java.util.HashSet;

import com.pnm.batching.dto.IYouTubeDTO;

public abstract class YTInfoExtractorService {

	protected String webserviceURL = null;

	public String getWebserviceURL() {
		return webserviceURL;
	}

	public void setWebserviceURL(String webserviceURL) {
		this.webserviceURL = webserviceURL;
	}

	public abstract HashSet<IYouTubeDTO> getYouTubeInfo();
}
