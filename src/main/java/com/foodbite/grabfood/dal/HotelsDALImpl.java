package com.foodbite.grabfood.dal;

import com.foodbite.grabfood.model.Hotels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HotelsDALImpl implements HotelsDAL {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Hotels> getAllHotels() {
		return mongoTemplate.findAll(Hotels.class);
	}

	@Override
	public Hotels addHotel(Hotels hotel) {
		mongoTemplate.save(hotel);
		// Now, user object will contain the ID as well
		return hotel;
	}
}
