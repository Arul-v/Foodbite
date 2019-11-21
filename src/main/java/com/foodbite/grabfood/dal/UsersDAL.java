package com.foodbite.grabfood.dal;

import com.foodbite.grabfood.model.Users;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

public interface UsersDAL {

	Users addUser(Users user, MultipartFile image) throws Exception;
	HashMap<String, String> login(String username, String password) throws Exception;

}