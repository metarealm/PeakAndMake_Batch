package com.pnm.batching.reactive.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pnm.batching.dto.impl.YTChannelDto;

@Repository
public interface ChannelRepository extends MongoRepository<YTChannelDto, String> {

}
