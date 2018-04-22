package com.pnm.batching.services;

import java.io.IOException;

public interface DataExtractorService {
	public void setDataSrc() throws IOException;

	public void getJsonData();
}
