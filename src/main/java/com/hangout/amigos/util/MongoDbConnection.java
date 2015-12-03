package com.hangout.amigos.util;

import java.net.UnknownHostException;
import java.util.Arrays;

import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class MongoDbConnection {

	public static MongoTemplate getConnection() {


		try {
			return new MongoTemplate(mongoDbFactory());
			} catch (Exception e) {
			throw new AssertionError("Page cannot be created");
			}
	}
	
	
	public static MongoDbFactory mongoDbFactory() throws Exception {

	

		MongoCredential credential = MongoCredential.createCredential("hangoutamigos", "cmpe280", "hangoutamigos".toCharArray());
		
		String mongoHost = "ds063892.mongolab.com";
		int mongoPort = 63892;
		
		ServerAddress serverAddress = new ServerAddress(mongoHost, mongoPort);
		MongoClient mongoClient = new MongoClient(serverAddress, Arrays.asList(credential));
	
		// Mongo DB Factory
		SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(
				mongoClient, "cmpe280");

		return simpleMongoDbFactory;
	}

}
