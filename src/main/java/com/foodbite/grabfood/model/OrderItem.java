package com.foodbite.grabfood.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class OrderItem {

    @Id
    private String orderItemId;
    private String itemId;
    private String itemName;
    private String qty;
    private String price;

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getQty() {
        return qty;
    }

    public String getPrice() {
        return price;
    }
}
