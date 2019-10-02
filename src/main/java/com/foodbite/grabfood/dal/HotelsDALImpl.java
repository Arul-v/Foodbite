package com.foodbite.grabfood.dal;

import com.foodbite.grabfood.messages.Messages;
import com.foodbite.grabfood.model.Hotels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;

import java.nio.charset.MalformedInputException;
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
	public Hotels addHotel(Hotels hotel) throws Exception {
		Query query = new Query();
		query.addCriteria(Criteria.where("hotelId").is(hotel.getHotelId()));
		Hotels hotels = mongoTemplate.findOne(query, Hotels.class);
		if (hotels==null && hotel.getItems().size()>0) {
			mongoTemplate.save(hotel);
			return hotel;
		}
		else if(hotels!=null)
			throw new DataIntegrityViolationException(Messages.primaryKeyViolation);
		else if (hotel.getItems().size()==0)
			throw new Exception(Messages.itemsEmptyMessage);
		return hotel;
	}
}
