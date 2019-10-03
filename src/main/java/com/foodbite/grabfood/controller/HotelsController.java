package com.foodbite.grabfood.controller;

import com.foodbite.grabfood.dal.HotelsDALImpl;
import com.foodbite.grabfood.dal.HotelsRepository;
import com.foodbite.grabfood.dal.HotelsDAL;
import com.foodbite.grabfood.messages.Messages;
import com.foodbite.grabfood.model.Hotels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/")
public class HotelsController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final HotelsRepository hotelsRepository;

	private final HotelsDAL hotelsDAL;
	private final HotelsDALImpl hotelsDALImpl;

	public HotelsController(HotelsRepository hotelsRepository, HotelsDAL hotelsDAL, HotelsDALImpl hotelsDALImpl) {
		this.hotelsRepository = hotelsRepository;
		this.hotelsDAL = hotelsDAL;
		this.hotelsDALImpl = hotelsDALImpl;
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/getHotels", method = RequestMethod.GET)
	public List<Hotels> getAllHotels() {
		return hotelsRepository.findAll();
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/createHotel", method = RequestMethod.POST)
	public String addHotel(@RequestBody Hotels hotel) {
		try
		{
			hotelsDALImpl.addHotel(hotel);
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
}