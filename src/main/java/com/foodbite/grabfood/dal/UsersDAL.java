package com.foodbite.grabfood.dal;


import com.foodbite.grabfood.model.Users;

import java.util.List;

public interface UsersDAL {

	Users addUser(Users user) throws Exception;
	String login(String username, String password) throws Exception;

}