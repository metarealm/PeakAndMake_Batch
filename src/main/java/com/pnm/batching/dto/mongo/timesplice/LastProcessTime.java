package com.pnm.batching.dto.mongo.timesplice;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ProcessDateData")
public class LastProcessTime {

	@Id
	private String processingType;
	private LocalDateTime queryStartTime;

	public LastProcessTime(String processingType , LocalDateTime queryStartTime) {
		super();
		this.queryStartTime = queryStartTime;
		this.processingType = processingType;
	}

	/**
	 * @return the processingType
	 */
	public String getProcessingType() {
		return processingType;
	}

	/**
	 * @param processingType
	 *            the processingType to set
	 */
	public void setProcessingType(String processingType) {
		this.processingType = processingType;
	}

	/**
	 * @return the queryStartDate
	 */
	public LocalDateTime getQueryStartDate() {
		return queryStartTime;
	}

	/**
	 * @param queryStartDate
	 *            the queryStartDate to set
	 */
	public void setQueryStartDate(LocalDateTime queryStartDate) {
		this.queryStartTime = queryStartDate;
	}

}
