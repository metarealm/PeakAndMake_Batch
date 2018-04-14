package com.pnm.batching.writer;

import java.util.List;
import org.springframework.batch.item.ItemWriter;
import com.pnm.batching.dto.IYouTubeDTO;


/**
 * YouTubeBatchWriter Class is created to write items to MongoDB etc via service class
 *
 *@author onlinetechvision.com
 * @since 10 Dec 2012
 * @version 1.0.0
 */

public class YouTubeBatchWriter implements ItemWriter<IYouTubeDTO> {

	@Override
	public void write(List<? extends IYouTubeDTO> items) throws Exception {
		// TODO Auto-generated method stub

	}

}
