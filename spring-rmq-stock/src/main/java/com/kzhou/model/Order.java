package com.kzhou.model;

public class Order {
    private String orderNumber;
    private String orderName;
    private float orderMoney;

    public Order(String orderNumber, String orderName, float orderMoney) {
        this.orderNumber = orderNumber;
        this.orderName = orderName;
        this.orderMoney = orderMoney;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public float getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(float orderMoney) {
        this.orderMoney = orderMoney;
    }
}
