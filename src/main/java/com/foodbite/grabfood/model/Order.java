package com.foodbite.grabfood.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Order {
    @Id
    private String orderId;
    private String restaurantId;
    private String restaurantName;
    private String tax;
    private String deliveryFee;
    private String totalValue;
    private OrderItem[] orderItems;
    private String userId;
    private String orderTime;

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public void setTotalValue(String totalValue) {
        this.totalValue = totalValue;
    }

    public void setOrderItems(OrderItem[] orderItems) {
        this.orderItems = orderItems;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getTax() {
        return tax;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public String getTotalValue() {
        return totalValue;
    }

    public OrderItem[] getOrderItems() {
        return orderItems;
    }

    public String getUserId() {
        return userId;
    }

    public String getOrderTime() {
        return orderTime;
    }
}
