package com.pnm.batching.writer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

//@Profile("dev1")
@PropertySource("classpath:config.properties")
public class MongoWriterConfiguration extends AbstractMongoConfiguration {

	@Value("${bhabani.mongodb.name}")
	private String dbName;

	@Value("${bhabani.mongodb.host}")
	private String host;

	@Value("${bhabani.mongodb.port}")
	private Integer port;

	@Value("${bhabani.mongodb.username}")
	private String userName;

	@Value("${bhabani.mongodb.password}")
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
