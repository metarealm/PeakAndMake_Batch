package com.pnm.batching.itemreader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.pnm.batching.dto.IYouTubeDTO;

public class YTVideoItemReader implements ItemReader<IYouTubeDTO> {

	@Override
	public IYouTubeDTO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// TODO Auto-generated method stub
		return null;
	}

}
