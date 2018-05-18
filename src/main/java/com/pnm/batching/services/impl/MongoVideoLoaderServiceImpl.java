package com.pnm.batching.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.pnm.batching.dto.IYouTubeDTO;
import com.pnm.batching.dto.impl.YTVideoDtoImpl;
import com.pnm.batching.reactive.data.ChannelRepository;
import com.pnm.batching.reactive.data.VideoRepository;
import com.pnm.batching.services.DataLoaderService;

@Component
@Qualifier("MongoVideoService")
public class MongoVideoLoaderServiceImpl implements DataLoaderService{

	@Autowired private VideoRepository mongoVideoRepository;

	@Override
	public void loadData(Iterable<IYouTubeDTO> data) {
		
	}

	@Override
	public void loadData(List<?> ytData) {
		List<YTVideoDtoImpl> ytRData = (List<YTVideoDtoImpl>) ytData;
		this.mongoVideoRepository.saveAll(ytRData);
	}

}
