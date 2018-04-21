package com.pnm.batching.writer;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

@Configuration
@EnableBatchProcessing
//@Profile("dev1")
@PropertySource("classpath:config.properties")
public class MongoWriterConfiguration extends AbstractMongoConfiguration {

	@Value("${mongodb.name}")
	private String dbName;

	@Value("${mongodb.host}")
	private String host;

	@Value("${mongodb.port}")
	private Integer port;

	@Value("${mongodb.username}")
	private String userName;

	@Value("${mongodb.password}")
	private String password;

	@Override
	protected String getDatabaseName() {
		return this.dbName;
	}

	@Override
	public MongoClient mongoClient() {
		return new MongoClient(this.host, this.port);
	}

	@Override
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		final MongoTemplate mongoTemplate = new MongoTemplate(mongoClient(), getDatabaseName());
		return mongoTemplate;
	}

}
