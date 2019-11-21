package com.foodbite.grabfood.dal;

import com.foodbite.grabfood.messages.Messages;
import com.foodbite.grabfood.model.Users;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.HashMap;


@Repository
public class UsersDALImpl implements UsersDAL {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Users addUser(Users user) throws Exception {
		if (validateEmailExists(user)) {
			mongoTemplate.save(user);
			uploadImage(user.getEmail());
			return user;
		}
		else
			throw new DataIntegrityViolationException(Messages.userEmailOrUserNameViolation);
	}

	@Override
	public HashMap<String, String> login(String username, String password) throws Exception {
		if(validateLogin(username,password)) {
			updateToken(username);
			return getTokenOfUser(username);
		}
		else
			throw new Exception(Messages.loginFailedMessage);
	}

	public boolean validateLogin(String username, String password)
	{
		Query login = new Query();
		login.addCriteria(Criteria.where("email").is(username));
		Users user1 = mongoTemplate.findOne(login, Users.class);
		if (user1==null)
			return false;
		else
			if(user1.getPassword().equals(password))
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
		userNameQuery.addCriteria(Criteria.where("email").is(username));
		userNameQuery.fields().include("email");
		Users userTest3 = mongoTemplate.findOne(userNameQuery, Users.class);
		Update update = new Update();
		update.set("token", getToken());

		mongoTemplate.updateFirst(userNameQuery, update, Users.class);
	}

	public HashMap<String, String> getTokenOfUser(String username) throws JSONException {
		Query emailQuery = new Query();
		emailQuery.addCriteria(Criteria.where("email").is(username));
		Users user1 = mongoTemplate.findOne(emailQuery, Users.class);
		HashMap<String, String> map = new HashMap<>();
		map.put("token", user1.getToken());
		map.put("username", user1.getFirstname());
		return map;
	}

	public Users getUserWithToken(String token) throws Exception {
		Query emailQuery = new Query();
		emailQuery.addCriteria(Criteria.where("token").is(token));
		Users user = mongoTemplate.findOne(emailQuery, Users.class);
		if (user == null)
			throw new Exception("Cant find user with the token!");
		return user;
	}
	public void uploadImage(String email) {

		try {
			Mongo mongo = new Mongo("localhost", 27017);
			DB db = mongo.getDB("foodbite");
			DBCollection collection = db.getCollection("users");


			String newFileName = "testImage2";

			File imageFile = new File("/Users/Vijayaragavan/test/Foodbite/src/main/resources/Image1.png");

			// create a "photo" namespace
			GridFS gfsPhoto = new GridFS(db, "photo");

			// get image file from local drive
			GridFSInputFile gfsFile = gfsPhoto.createFile(imageFile);

			// set a new filename for identify purpose

			gfsFile.setId(email);
			gfsFile.setFilename(email);

			// save the image file into mongoDB
			gfsFile.save();


		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void getImage(String email) {
		Mongo mongo = new Mongo("localhost", 27017);
		DB db = mongo.getDB("foodbite");

		GridFS gfsPhoto = new GridFS(db, "photo");
		GridFSDBFile imageForOutput = gfsPhoto.findOne(email);
		System.out.println(email);
		System.out.println(imageForOutput);
	}
}
