package com.pnm.batching.dto.impl;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class YTVideoDeserialize extends StdDeserializer<YTVideoDtoImpl> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6938593913551716475L;

	public YTVideoDeserialize() {
		this(null);
	}

	protected YTVideoDeserialize(Class<YTVideoDtoImpl> vc) {
		super(vc);
	}

	@Override
	public YTVideoDtoImpl deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonNode productNode = jp.getCodec().readTree(jp);
		YTVideoDtoImpl videoData = new YTVideoDtoImpl();
		videoData.setVideoID(productNode.get("id").textValue());
		videoData.setEtag(productNode.get("etag").textValue());
		videoData.setVideoTitle(productNode.get("snippet").get("title").textValue());
		if (productNode.get("snippet").get("description") != null)
			videoData.setVideoDescription(productNode.get("snippet").get("description").textValue());
		if (productNode.get("snippet").get("thumbnails").get("default").get("url") != null)
			videoData.setVideoImageDefaultUrl(productNode.get("snippet").get("thumbnails").get("default").get("url").textValue());
		if (productNode.get("snippet").get("thumbnails").get("high").get("url") != null)
			videoData.setVideoImageHighUrl(productNode.get("snippet").get("thumbnails").get("high").get("url").textValue());
		if (productNode.get("snippet").get("thumbnails").get("medium").get("url") != null)
			videoData.setVideoImageMediumUrl(productNode.get("snippet").get("thumbnails").get("medium").get("url").textValue());
		if (productNode.get("snippet").get("channelId") != null)
			videoData.setVideo_channel_id(productNode.get("snippet").get("channelId").textValue());
		if (productNode.get("snippet").get("channelTitle") != null)
			videoData.setVideo_channel_title(productNode.get("snippet").get("channelTitle").textValue());
		if (productNode.get("snippet").get("publishedAt") != null)
			videoData.setVideo_creation_date(productNode.get("snippet").get("publishedAt").textValue());

		if (productNode.get("snippet").get("tags") != null)
			videoData.setVideoTags(productNode.get("snippet").get("tags").toString());
		if (productNode.get("snippet").get("defaultAudioLanguage") != null)
			videoData.setDefaultAudioLanguage(productNode.get("snippet").get("defaultAudioLanguage").textValue());

		if (productNode.get("statistics").get("dislikeCount") != null)
			videoData.setDislikes(Integer.valueOf(productNode.get("statistics").get("dislikeCount").textValue()));
		if (productNode.get("statistics").get("dislikeCount") != null)
			videoData.setLikes(Integer.valueOf(productNode.get("statistics").get("likeCount").textValue()));
		if (productNode.get("statistics").get("dislikeCount") != null)
			videoData.setViewCount(Integer.valueOf(productNode.get("statistics").get("viewCount").textValue()));
		return videoData;
	}

}
