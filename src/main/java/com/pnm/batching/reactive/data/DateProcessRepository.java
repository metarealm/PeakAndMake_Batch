package com.pnm.batching.reactive.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pnm.batching.dto.mongo.timesplice.LastProcessTime;


public interface DateProcessRepository extends MongoRepository<LastProcessTime, String>{

}
