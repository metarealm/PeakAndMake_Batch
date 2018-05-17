package com.pnm.batching.reactive.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pnm.batching.dto.impl.YTVideoDtoImpl;

public interface VideoRepository extends MongoRepository<YTVideoDtoImpl, String> {

}
