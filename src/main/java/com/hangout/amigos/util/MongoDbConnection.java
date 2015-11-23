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
		MongoClientURI uri=null;
		MongoClient mongoclient=null;
		MongoTemplate mongoConnection=null;
		try {
			uri = new MongoClientURI("mongodb://cmpe280:hangoutamigos@ds057224.mongolab.com:57224/hangoutamigos");
			mongoclient = new MongoClient(uri);
			mongoConnection = new MongoTemplate(mongoclient,"bikeshare");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		return mongoConnection;
	}
	
}
