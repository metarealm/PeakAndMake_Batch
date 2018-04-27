package com.pnm.batching.services;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface DataExtractorService {
	public void setDataSrc() throws IOException;

	public List<?> getJsonData();
}
