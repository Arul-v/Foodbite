package com.foodbite.grabfood.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodbite.grabfood.dal.UsersDAL;
import com.foodbite.grabfood.dal.UsersDALImpl;
import com.foodbite.grabfood.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;
import java.util.Map;
import java.io.InputStream;


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
	public Users adduser(@RequestPart("payload") String userpayload, @RequestPart("image") MultipartFile image) {
		try
		{
			Users user = new ObjectMapper().readValue(userpayload, Users.class);
			usersDALImpl.addUser(user, image);
			// InputStream in = image.getInputStream();
			System.out.println(image.getOriginalFilename());
			

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
			public ResponseEntity<InputStreamResource> getImage(@RequestParam("username")  String username) {
		try {
			return usersDALImpl.getImage(username);

		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}

}
