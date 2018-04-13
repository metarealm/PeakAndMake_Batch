package com.pnm.batching.itemreader;

import java.util.HashSet;
import java.util.Iterator;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pnm.batching.dto.IYouTubeDTO;
import com.pnm.batching.services.YTInfoExtractorService;

@Component
public class YTChannelItemReader implements ItemReader<IYouTubeDTO> {

	private int nextIndex;
	private int maxIndex;

	private HashSet<IYouTubeDTO> youTubeData;
	
	@Autowired
	private YTInfoExtractorService infoExtractorService;
	private Iterator<IYouTubeDTO> ytItr;

	public int getNextIndex() {
		return nextIndex;
	}

	public void setNextIndex(int nextIndex) {
		this.nextIndex = nextIndex;
	}

	public int getMaxIndex() {
		return maxIndex;
	}

	public void setMaxIndex(int maxIndex) {
		this.maxIndex = maxIndex;
	}

	public HashSet<IYouTubeDTO> getYouTubeData() {
		return youTubeData;
	}

	public void setYouTubeData(HashSet<IYouTubeDTO> youTubeData) {
		this.youTubeData = youTubeData;
	}

	public YTInfoExtractorService getInfoExtractorService() {
		return infoExtractorService;
	}

	public void setInfoExtractorService(YTInfoExtractorService infoExtractorService) {
		this.infoExtractorService = infoExtractorService;
	}

	YTChannelItemReader() {
		initialize();
	}

	private void initialize() {
		youTubeData.addAll(infoExtractorService.getYouTubeInfo());
		ytItr = youTubeData.iterator();
	}

	@Override
	public IYouTubeDTO read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		if (!ytItr.hasNext()) // If all data has exhausted, fetch data from webservice
			initialize();

		if (!ytItr.hasNext()) // if even after calling webservice no data found , that means no more data to read , return null
			return null;

		IYouTubeDTO youTubeDto = null;
		while (ytItr.hasNext())
			youTubeDto = ytItr.next();

		return youTubeDto;
	}

}
