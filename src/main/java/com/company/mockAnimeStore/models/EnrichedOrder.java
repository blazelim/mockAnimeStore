package com.company.mockAnimeStore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
@JsonIgnoreProperties({"hibernateLazyIntializer", "handler"})
public class EnrichedOrder {


    @Id
    private String id;

    private String productName;
    private int quantity;
    private Double totalPrice;
    private Product product;

    public EnrichedOrder() {
    }

    public EnrichedOrder(Order order){
        this.id = order.getId();
        this.productName = order.getProductName();
        this.quantity = order.getQuantity();
    }

    public EnrichedOrder(String id, String productName, int quantity, Double totalPrice, Product product) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.product = product;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrichedOrder that = (EnrichedOrder) o;
        return quantity == that.quantity && Objects.equals(id, that.id) && Objects.equals(productName, that.productName) && Objects.equals(totalPrice, that.totalPrice) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, quantity, totalPrice, product);
    }

    @Override
    public String toString() {
        return "EnrichedOrder{" +
                "id='" + id + '\'' +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", product=" + product +
                '}';
    }
}
