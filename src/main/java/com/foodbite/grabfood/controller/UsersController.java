package com.foodbite.grabfood.controller;

import com.foodbite.grabfood.dal.UsersDAL;
import com.foodbite.grabfood.dal.UsersDALImpl;
import com.foodbite.grabfood.dal.UsersRepository;
import com.foodbite.grabfood.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/")
public class UsersController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final UsersDALImpl usersDALImpl;

	public UsersController(UsersDALImpl usersDALImpl) {
		this.usersDALImpl = usersDALImpl;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public Users adduser(@RequestBody Users user) {
		try
		{
			usersDALImpl.addUser(user);

			return user;
		}
		catch (DataIntegrityViolationException e)
		{
			throw new ResponseStatusException(
				HttpStatus.CONFLICT, e.getMessage());
		}
		catch (Exception e)
		{
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public HashMap<String, String> login(@RequestBody Map<String, String> credentials) {
		try {
			return usersDALImpl.login(credentials.get("username"), credentials.get("password"));
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}


	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/getImage", method = RequestMethod.GET)
			public void getImage(@RequestBody Map<String, String> credentials) {
		try {
			usersDALImpl.getImage(credentials.get("username"));
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}

}
