package com.pnm.batching.dto.impl;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.pnm.batching.dto.IYouTubeDTO;

@Document(collection = "ChannelData")
@JsonDeserialize(using = YTChannelDeserialize.class)
public class YTChannelDto implements IYouTubeDTO {
	private String etag;
	@Id
	private String channelID;
	private String channelTitle;
	private String channelDescription;
	private String channelImageDefaultUrl;
	private String channelImageHighUrl;
	private String channelImageMediumUrl;
	
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
	 * @return the channelID
	 */
	public String getChannelID() {
		return channelID;
	}
	/**
	 * @param channelID the channelID to set
	 */
	public void setChannelID(String channelID) {
		this.channelID = channelID;
	}
	/**
	 * @return the channelTitle
	 */
	public String getChannelTitle() {
		return channelTitle;
	}
	/**
	 * @param channelTitle the channelTitle to set
	 */
	public void setChannelTitle(String channelTitle) {
		this.channelTitle = channelTitle;
	}
	/**
	 * @return the channelDescription
	 */
	public String getChannelDescription() {
		return channelDescription;
	}
	/**
	 * @param channelDescription the channelDescription to set
	 */
	public void setChannelDescription(String channelDescription) {
		this.channelDescription = channelDescription;
	}
	/**
	 * @return the channelImageDefaultUrl
	 */
	public String getChannelImageDefaultUrl() {
		return channelImageDefaultUrl;
	}
	/**
	 * @param channelImageDefaultUrl the channelImageDefaultUrl to set
	 */
	public void setChannelImageDefaultUrl(String channelImageDefaultUrl) {
		this.channelImageDefaultUrl = channelImageDefaultUrl;
	}
	/**
	 * @return the channelImageHighUrl
	 */
	public String getChannelImageHighUrl() {
		return channelImageHighUrl;
	}
	/**
	 * @param channelImageHighUrl the channelImageHighUrl to set
	 */
	public void setChannelImageHighUrl(String channelImageHighUrl) {
		this.channelImageHighUrl = channelImageHighUrl;
	}
	/**
	 * @return the channelImageMediumUrlÏ
	 */
	public String getChannelImageMediumUrl() {
		return channelImageMediumUrl;
	}
	/**
	 * @param channelImageMediumUrl the channelImageMediumUrl to set
	 */
	public void setChannelImageMediumUrl(String channelImageMediumUrl) {
		this.channelImageMediumUrl = channelImageMediumUrl;
	}

}
