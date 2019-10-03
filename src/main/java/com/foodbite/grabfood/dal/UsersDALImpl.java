package com.foodbite.grabfood.dal;

import com.foodbite.grabfood.messages.Messages;
import com.foodbite.grabfood.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.security.SecureRandom;


@Repository
public class UsersDALImpl implements UsersDAL {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Users addUser(Users user) throws Exception {
		if (validateUsernameExists(user) && validateEmailExists(user)) {
			mongoTemplate.save(user);
			return user;
		}
		else
			throw new DataIntegrityViolationException(Messages.userEmailOrUserNameViolation);
	}

	@Override
	public String login(String username, String password) throws Exception {
		if(validateLogin(username,password)) {
			updateToken(username);
			return "success";
		}
		else
			throw new Exception(Messages.loginFailedMessage);
	}

	public boolean validateLogin(String username, String password)
	{
		Query login = new Query();
		login.addCriteria(Criteria.where("username").is(username));
		Users user1 = mongoTemplate.findOne(login, Users.class);
		if (user1==null)
			return false;
		else
			if(user1.getPassword().equals(password))
				return true;
			else
				return false;
	}

	public boolean validateUsernameExists(Users user)
	{
		Query userNameQuery = new Query();
		userNameQuery.addCriteria(Criteria.where("username").is(user.getUsername()));
		Users user1 = mongoTemplate.findOne(userNameQuery, Users.class);
		if (user1==null)
			return true;
		else
			return false;
	}

	public boolean validateEmailExists(Users user)
	{
		Query emailQuery = new Query();
		emailQuery.addCriteria(Criteria.where("email").is(user.getEmail()));
		Users user1 = mongoTemplate.findOne(emailQuery, Users.class);
		if (user1==null)
			return true;
		else
			return false;
	}

	public String getToken()
	{
		final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		SecureRandom rnd = new SecureRandom();

		StringBuilder token = new StringBuilder( 15 );
		for( int i = 0; i < 15; i++ )
			token.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		return  token.toString();
	}

	public void updateToken(String username)
	{
		Query userNameQuery = new Query();
		userNameQuery.addCriteria(Criteria.where("username").is(username));
		userNameQuery.fields().include("username");
		Users userTest3 = mongoTemplate.findOne(userNameQuery, Users.class);
		Update update = new Update();
		update.set("token", getToken());

		mongoTemplate.updateFirst(userNameQuery, update, Users.class);
	}
}
