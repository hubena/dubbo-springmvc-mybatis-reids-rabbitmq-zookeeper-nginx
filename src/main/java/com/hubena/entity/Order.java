package com.hubena.entity;

public class Order {
    private Integer id;

    private Integer orderYear;

    private Integer orderPrice;

    private String customer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderYear() {
        return orderYear;
    }

    public void setOrderYear(Integer orderYear) {
        this.orderYear = orderYear;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer == null ? null : customer.trim();
    }
}