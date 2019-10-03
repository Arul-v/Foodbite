package com.foodbite.grabfood.dal;

import com.foodbite.grabfood.model.Users;

import java.util.HashMap;

public interface UsersDAL {

	Users addUser(Users user) throws Exception;
	HashMap<String, String> login(String username, String password) throws Exception;

}