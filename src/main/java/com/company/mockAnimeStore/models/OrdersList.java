package com.company.mockAnimeStore.models;

import java.util.List;
import java.util.Objects;

public class OrdersList {
    private List<Order> orders;

    public OrdersList() {
    }

    public OrdersList(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersList that = (OrdersList) o;
        return Objects.equals(orders, that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orders);
    }

    @Override
    public String toString() {
        return "OrdersList{" +
                "orders=" + orders +
                '}';
    }
}
