package com.foodbite.grabfood.dal;

import com.foodbite.grabfood.model.Order;
import com.foodbite.grabfood.model.Users;
import java.util.List;

public interface OrderDAL {
    public Order addOrder(Order order) throws Exception;
    public List<Order> getOrders(Users user) throws Exception;
}
