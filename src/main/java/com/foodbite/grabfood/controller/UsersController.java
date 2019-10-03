package com.foodbite.grabfood.controller;

import com.foodbite.grabfood.dal.UsersDAL;
import com.foodbite.grabfood.dal.UsersDALImpl;
import com.foodbite.grabfood.dal.UsersRepository;
import com.foodbite.grabfood.messages.Messages;
import com.foodbite.grabfood.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/")
public class UsersController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final UsersRepository usersRepository;

	private final UsersDAL usersDAL;
	private final UsersDALImpl usersDALImpl;

	public UsersController(UsersRepository usersRepository, UsersDAL usersDAL, UsersDALImpl usersDALImpl) {
		this.usersRepository = usersRepository;
		this.usersDAL = usersDAL;
		this.usersDALImpl = usersDALImpl;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public String adduser(@RequestBody Users user) {
		try
		{
			usersDALImpl.addUser(user);
			return Messages.successfullyAddedInDBMessage;
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
	public String login(@RequestBody String creds) {
		try {
			String[] credentials = creds.split(":");
			return usersDALImpl.login(credentials[0], credentials[1]);
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}

}