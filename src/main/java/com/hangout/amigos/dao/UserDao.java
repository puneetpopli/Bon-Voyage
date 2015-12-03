package com.hangout.amigos.dao;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.hangout.amigos.dto.User;
import com.hangout.amigos.util.MongoDbConnection;

public class UserDao {
	
	private MongoTemplate mongoTemplate = MongoDbConnection.getConnection();
	
	public void saveUser(User user) {
		System.out.println("Save user called");
		mongoTemplate.save(user, "user");
	}
	
	public User getUser(String id) {
		return mongoTemplate.findOne(new Query((Criteria.where("_id").is(Integer.parseInt(id)))), User.class);
	}
	
	public User getUserBasedOnEmail(String email) {
		return mongoTemplate.findOne(new Query((Criteria.where("email").is(email.toString()))), User.class);
	}

	public List<User> getAllUsers() {
		 return mongoTemplate.findAll(User.class);
	}

	public void removeUser(String id) {
		mongoTemplate.remove(new Query((Criteria.where("_id").is(id))), User.class);
		
	}

	public void updateUser(User user) {
		Query query = new Query((Criteria.where("_id").is(user.getUserId())));  
		mongoTemplate.updateFirst(query, Update.update("firstName", user.getFirstName()), "User");
		mongoTemplate.updateFirst(query, Update.update("lastName", user.getLastName()), "User");
		mongoTemplate.updateFirst(query, Update.update("email", user.getEmail()),"User");
	}
	
}
