package com.pnm.batching.writer;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

@Configuration
@EnableBatchProcessing
@Profile("dev1")
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
		final UserCredentials userCredentials = new UserCredentials(this.userName, this.password);

		final MongoTemplate mongoTemplate = new MongoTemplate(mongoClient(), getDatabaseName());
		mongoTemplate.setWriteConcern(WriteConcern.SAFE);

		return mongoTemplate;
	}

}
