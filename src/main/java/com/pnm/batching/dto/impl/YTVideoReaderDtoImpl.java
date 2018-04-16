package com.pnm.batching.dto.impl;

import com.pnm.batching.dto.IYouTubeDTO;

public class YTVideoReaderDtoImpl implements IYouTubeDTO {
	private String videoID;
    private String videoName;

    public YTVideoReaderDtoImpl() {
    }

    public YTVideoReaderDtoImpl(String id, String name) {
        this.videoID = id;
        this.videoName = name;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "YTVideoReaderDtoImpl [videoID=" + videoID + ", videoName=" + videoName + "]";
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
	 * @return the videoName
	 */
	public String getVideoName() {
		return videoName;
	}

	/**
	 * @param videoName the videoName to set
	 */
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}


}
