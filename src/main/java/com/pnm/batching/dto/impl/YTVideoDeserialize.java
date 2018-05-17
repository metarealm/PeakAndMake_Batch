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
		videoData.setVideoDescription(productNode.get("snippet").get("description").textValue());
		videoData.setVideoImageDefaultUrl(productNode.get("snippet").get("thumbnails").get("default").get("url").textValue());
		videoData.setVideoImageHighUrl(productNode.get("snippet").get("thumbnails").get("high").get("url").textValue());
		videoData.setVideoImageMediumUrl(productNode.get("snippet").get("thumbnails").get("medium").get("url").textValue());
		return videoData;
	}

}
