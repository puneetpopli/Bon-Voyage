package com.hangout.amigos.dao;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.hangout.amigos.dto.Login;
import com.hangout.amigos.util.HangoutAmigosUtil;
import com.hangout.amigos.util.MongoDbConnection;

public class LoginDao {

	private MongoTemplate mongoTemplate  = MongoDbConnection.getConnection();
	
	public void saveObject(Login login) {
		mongoTemplate.insert(login);
	}
	
	public Login getObject(String id) {
		return mongoTemplate.findOne(new Query((Criteria.where("_id").is(Integer.parseInt(id)))), Login.class);
	}
	
	public void updateObject(Login login) {
		Query query = new Query((Criteria.where("_id").is(login.getUserId())));
		mongoTemplate.updateFirst(query, Update.update("sessionId", login.getSessionId()),"login");
	}
	
	public Login getObject(String email, String password) {
		Criteria usernameQuery = new Criteria().where("email").is(email);
		Criteria passwordQuery = new Criteria().where("password").is(HangoutAmigosUtil.passwordEncryption(password));
		Criteria loginSuccess = new Criteria().andOperator(usernameQuery,passwordQuery);
		return mongoTemplate.findOne(new Query(loginSuccess), Login.class);
	}

	
}
