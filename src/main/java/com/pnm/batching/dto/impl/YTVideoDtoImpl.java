package com.pnm.batching.dto.impl;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.pnm.batching.dto.IYouTubeDTO;

@Document(collection = "VideoData")
@JsonDeserialize(using = YTVideoDeserialize.class)
public class YTVideoDtoImpl implements IYouTubeDTO {
	
	private String etag;
	@Id
	private String videoID;
	private String videoTitle;
	private String videoDescription;
	private String videoImageDefaultUrl;
	private String videoImageHighUrl;
	private String videoImageMediumUrl;
	

    public YTVideoDtoImpl() {
    }

    public YTVideoDtoImpl(String id, String name) {
        this.videoID = id;
        this.videoTitle = name;
    }

	/**
	 * @return the etag
	 */
	public String getEtag() {
		return etag;
	}

	/**
	 * @param etag the etag to set
	 */
	public void setEtag(String etag) {
		this.etag = etag;
	}

	/**
	 * @return the videoID
	 */
	public String getVideoID() {
		return videoID;
	}

	/**
	 * @param videoID the videoID to set
	 */
	public void setVideoID(String videoID) {
		this.videoID = videoID;
	}

	/**
	 * @return the videoTitle
	 */
	public String getVideoTitle() {
		return videoTitle;
	}

	/**
	 * @param videoTitle the videoTitle to set
	 */
	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}

	/**
	 * @return the videoDescription
	 */
	public String getVideoDescription() {
		return videoDescription;
	}

	/**
	 * @param videoDescription the videoDescription to set
	 */
	public void setVideoDescription(String videoDescription) {
		this.videoDescription = videoDescription;
	}

	/**
	 * @return the videoImageDefaultUrl
	 */
	public String getVideoImageDefaultUrl() {
		return videoImageDefaultUrl;
	}

	/**
	 * @param videoImageDefaultUrl the videoImageDefaultUrl to set
	 */
	public void setVideoImageDefaultUrl(String videoImageDefaultUrl) {
		this.videoImageDefaultUrl = videoImageDefaultUrl;
	}

	/**
	 * @return the videoImageHighUrl
	 */
	public String getVideoImageHighUrl() {
		return videoImageHighUrl;
	}

	/**
	 * @param videoImageHighUrl the videoImageHighUrl to set
	 */
	public void setVideoImageHighUrl(String videoImageHighUrl) {
		this.videoImageHighUrl = videoImageHighUrl;
	}

	/**
	 * @return the videoImageMediumUrl
	 */
	public String getVideoImageMediumUrl() {
		return videoImageMediumUrl;
	}

	/**
	 * @param videoImageMediumUrl the videoImageMediumUrl to set
	 */
	public void setVideoImageMediumUrl(String videoImageMediumUrl) {
		this.videoImageMediumUrl = videoImageMediumUrl;
	}

	
}
