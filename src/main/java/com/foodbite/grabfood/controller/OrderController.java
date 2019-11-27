package com.foodbite.grabfood.controller;

import com.foodbite.grabfood.dal.OrderDALImpl;
import com.foodbite.grabfood.dal.UsersDALImpl;
import com.foodbite.grabfood.model.Order;
import com.foodbite.grabfood.model.Users;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/")
public class OrderController {

    private final OrderDALImpl orderDALImpl;

    private final UsersDALImpl usersDALImpl;

    public OrderController(OrderDALImpl orderDALImpl, UsersDALImpl usersDALImpl){
        this.orderDALImpl = orderDALImpl;
        this.usersDALImpl = usersDALImpl;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
    public Order placeOrder(@RequestHeader Map<String, String> headers, @RequestBody Order order) {

        try
        {
            Users user = usersDALImpl.getUserWithToken(headers.get("token"));
            order.setUserId(user.getUserId());
            Date myDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            order.setOrderTime(sdf.format(myDate));
            orderDALImpl.addOrder(order);
            return order;
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @RequestMapping(value = "/orderHistory", method = RequestMethod.GET)
    public List<Order> orderHistory(@RequestHeader Map<String, String> headers) {
        try
        {
            Users user = usersDALImpl.getUserWithToken(headers.get("token"));
            return orderDALImpl.getOrders(user);
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
}
