package com.pnm.batching.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.pnm.batching.dto.IYouTubeDTO;
import com.pnm.batching.dto.impl.YTChannelDto;
import com.pnm.batching.reactive.data.ChannelRepository;
import com.pnm.batching.services.DataLoaderService;

@Component
@Qualifier("MongoChannelService")
public class MongoChannelLoaderServiceImpl implements DataLoaderService {


	@Autowired private ChannelRepository mongoChannelRepository;
	
	@Override
	public void loadData(Iterable<IYouTubeDTO> data) {

	}

	@Override
	public void loadData(List<?> ytData) {
		List<YTChannelDto> ytRData = (List<YTChannelDto>) ytData;
		this.mongoChannelRepository.saveAll(ytRData);
	}

}
