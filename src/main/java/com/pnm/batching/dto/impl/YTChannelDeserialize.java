package com.pnm.batching.dto.impl;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class YTChannelDeserialize extends StdDeserializer<YTChannelReaderDtoImpl> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6938593913551716475L;

	public YTChannelDeserialize() {
		this(null);
	}

	protected YTChannelDeserialize(Class<YTChannelReaderDtoImpl> vc) {
		super(vc);
	}

	@Override
	public YTChannelReaderDtoImpl deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonNode productNode = jp.getCodec().readTree(jp);
		YTChannelReaderDtoImpl channelData = new YTChannelReaderDtoImpl();
		channelData.setChannelID(productNode.get("snippet").get("channelId").textValue());
		channelData.setEtag(productNode.get("etag").textValue());
		channelData.setChannelTitle(productNode.get("snippet").get("channelTitle").textValue());
		channelData.setChannelDescription(productNode.get("snippet").get("description").textValue());
		channelData.setChannelImageDefaultUrl(productNode.get("snippet").get("channelTitle").textValue());
		channelData.setChannelImageDefaultUrl(productNode.get("snippet").get("thumbnails").get("default").get("url").textValue());
		channelData.setChannelImageHighUrl(productNode.get("snippet").get("thumbnails").get("high").get("url").textValue());
		channelData.setChannelImageMediumUrl(productNode.get("snippet").get("thumbnails").get("medium").get("url").textValue());
		return channelData;
	}

}
