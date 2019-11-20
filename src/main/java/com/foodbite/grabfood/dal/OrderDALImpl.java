package com.foodbite.grabfood.dal;

import com.foodbite.grabfood.model.Order;
import com.foodbite.grabfood.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDALImpl implements OrderDAL {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Order addOrder(Order order) throws Exception {
        mongoTemplate.save(order);
        return order;
    }

    @Override
    public List<Order> getOrders(Users user) throws Exception {
        Query userIDQuery = new Query();
        userIDQuery.addCriteria(Criteria.where("userId").is(user.getUserId()));
        return mongoTemplate.find(userIDQuery, Order.class);
    }
}
