package com.pnm.batching.services;

import java.util.List;

import com.pnm.batching.dto.IYouTubeDTO;
import com.pnm.batching.dto.impl.YTChannelDto;

public interface DataLoaderService {


	void loadData(Iterable<IYouTubeDTO> data);

	void loadData(List<?> ytData);
}
