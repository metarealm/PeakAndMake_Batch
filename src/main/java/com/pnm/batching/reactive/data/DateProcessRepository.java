package com.pnm.batching.reactive.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pnm.data.mongo.timesplice.ProcessTimeLine;

public interface DateProcessRepository extends MongoRepository<ProcessTimeLine, String>{

}
