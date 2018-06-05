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
	private String videoDescription ="";
	private String videoImageDefaultUrl = "";
	private String videoImageHighUrl = "";
	private String videoImageMediumUrl = "";
	private String videoTags = "";
	private String video_channel_id = "";
	private String video_channel_title = "";
	private String video_creation_date = "";
	private int viewCount =0;
	private int likes = 0;
	private int dislikes=0;
	private String defaultAudioLanguage = "en";

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
	 * @param etag
	 *            the etag to set
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
	 * @param videoID
	 *            the videoID to set
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
	 * @param videoTitle
	 *            the videoTitle to set
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
	 * @param videoDescription
	 *            the videoDescription to set
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
	 * @param videoImageDefaultUrl
	 *            the videoImageDefaultUrl to set
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
	 * @param videoImageHighUrl
	 *            the videoImageHighUrl to set
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
	 * @param videoImageMediumUrl
	 *            the videoImageMediumUrl to set
	 */
	public void setVideoImageMediumUrl(String videoImageMediumUrl) {
		this.videoImageMediumUrl = videoImageMediumUrl;
	}

	/**
	 * @return the videoTags
	 */
	public String getVideoTags() {
		return videoTags;
	}

	/**
	 * @param videoTags
	 *            the videoTags to set
	 */
	public void setVideoTags(String videoTags) {
		this.videoTags = videoTags;
	}

	/**
	 * @return the video_channel_id
	 */
	public String getVideo_channel_id() {
		return video_channel_id;
	}

	/**
	 * @param video_channel_id
	 *            the video_channel_id to set
	 */
	public void setVideo_channel_id(String video_channel_id) {
		this.video_channel_id = video_channel_id;
	}

	/**
	 * @return the video_channel_title
	 */
	public String getVideo_channel_title() {
		return video_channel_title;
	}

	/**
	 * @param video_channel_title
	 *            the video_channel_title to set
	 */
	public void setVideo_channel_title(String video_channel_title) {
		this.video_channel_title = video_channel_title;
	}

	/**
	 * @return the video_creation_date
	 */
	public String getVideo_creation_date() {
		return video_creation_date;
	}

	/**
	 * @param video_creation_date the video_creation_date to set
	 */
	public void setVideo_creation_date(String video_creation_date) {
		this.video_creation_date = video_creation_date;
	}

	/**
	 * @return the viewCount
	 */
	public int getViewCount() {
		return viewCount;
	}

	/**
	 * @param viewCount the viewCount to set
	 */
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	/**
	 * @return the likes
	 */
	public int getLikes() {
		return likes;
	}

	/**
	 * @param likes the likes to set
	 */
	public void setLikes(int likes) {
		this.likes = likes;
	}

	/**
	 * @return the dislikes
	 */
	public int getDislikes() {
		return dislikes;
	}

	/**
	 * @param dislikes the dislikes to set
	 */
	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	/**
	 * @return the defaultAudioLanguage
	 */
	public String getDefaultAudioLanguage() {
		return defaultAudioLanguage;
	}

	/**
	 * @param defaultAudioLanguage the defaultAudioLanguage to set
	 */
	public void setDefaultAudioLanguage(String defaultAudioLanguage) {
		this.defaultAudioLanguage = defaultAudioLanguage;
	}

}
