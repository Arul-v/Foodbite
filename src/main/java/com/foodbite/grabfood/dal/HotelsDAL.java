package com.foodbite.grabfood.dal;

import com.foodbite.grabfood.model.Hotels;

import java.util.List;

public interface HotelsDAL {

	List<Hotels> getAllHotels();
	Hotels addHotel(Hotels hotel);

}