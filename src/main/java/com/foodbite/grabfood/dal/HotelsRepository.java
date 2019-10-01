package com.foodbite.grabfood.dal;

import com.foodbite.grabfood.model.Hotels;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelsRepository extends MongoRepository<Hotels, String> {
}
