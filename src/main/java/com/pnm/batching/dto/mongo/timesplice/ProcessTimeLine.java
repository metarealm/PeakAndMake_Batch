package com.pnm.batching.dto.mongo.timesplice;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "DateData")
public class ProcessTimeLine {
	
	private ZonedDateTime queryStartDate;
	private boolean isChannelProcessed = false;
	private boolean isVideoProcessed = false;
	
	public ProcessTimeLine(LocalDateTime queryStartDate) {
		super();
		this.queryStartDate = ZonedDateTime.of(queryStartDate,  ZoneId.of("UTC"));
	}
	
	public ProcessTimeLine(ZonedDateTime queryStartDate, boolean isChannelProcessed , boolean isVideoProcessed) {
		super();
		this.queryStartDate = queryStartDate;
		this.isChannelProcessed = isChannelProcessed;
		this.isVideoProcessed = isVideoProcessed;
	}
	/**
	 * @return the queryStartDate
	 */
	public ZonedDateTime getQueryStartDate() {
		return queryStartDate;
	}
	/**
	 * @param queryStartDate the queryStartDate to set
	 */
	public void setQueryStartDate(ZonedDateTime queryStartDate) {
		this.queryStartDate = queryStartDate;
	}

	/**
	 * @return the isChannelProcessed
	 */
	public boolean isChannelProcessed() {
		return isChannelProcessed;
	}

	/**
	 * @param isChannelProcessed the isChannelProcessed to set
	 */
	public void setChannelProcessed(boolean isChannelProcessed) {
		this.isChannelProcessed = isChannelProcessed;
	}

	/**
	 * @return the isVideoProcessed
	 */
	public boolean isVideoProcessed() {
		return isVideoProcessed;
	}

	/**
	 * @param isVideoProcessed the isVideoProcessed to set
	 */
	public void setVideoProcessed(boolean isVideoProcessed) {
		this.isVideoProcessed = isVideoProcessed;
	}

}
