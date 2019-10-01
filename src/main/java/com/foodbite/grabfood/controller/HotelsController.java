package com.foodbite.grabfood.controller;

import com.foodbite.grabfood.dal.HotelsRepository;
import com.foodbite.grabfood.dal.HotelsDAL;
import com.foodbite.grabfood.model.Hotels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class HotelsController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final HotelsRepository hotelsRepository;

	private final HotelsDAL hotelsDAL;

	public HotelsController(HotelsRepository hotelsRepository, HotelsDAL hotelsDAL) {
		this.hotelsRepository = hotelsRepository;
		this.hotelsDAL = hotelsDAL;
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/getHotels", method = RequestMethod.GET)
	public List<Hotels> getAllHotels() {
		LOG.info("Getting all hotels.");
		return hotelsRepository.findAll();
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/createHotel", method = RequestMethod.POST)
	public Hotels addHotel(@RequestBody Hotels hotel) {
		LOG.info("Saving user.");
		return hotelsRepository.save(hotel);
	}
}