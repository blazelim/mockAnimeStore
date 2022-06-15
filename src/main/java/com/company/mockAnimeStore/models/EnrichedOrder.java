//package com.company.mockAnimeStore.models;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import java.util.Objects;
//
//@Document
//public class EnrichedOrder {
//
//    @Id
//    private String id;
//    private Integer orderId;
//    private String productName;
//    private Double totalPrice;
//    private int quantity;
//    private Product product;
//
//    public EnrichedOrder() {
//    }
//
//
//    public EnrichedOrder(String id, Integer orderId, String productName, Double totalPrice, int quantity, Product product) {
//        this.id = id;
//        this.orderId = orderId;
//        this.productName = productName;
//        this.totalPrice = totalPrice;
//        this.quantity = quantity;
//        this.product = product;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//
//    public Integer getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(Integer orderId) {
//        this.orderId = orderId;
//    }
//
//    public String getProductName() {
//        return productName;
//    }
//
//    public void setProductName(String productName) {
//        this.productName = productName;
//    }
//
//    public Double getTotalPrice() {
//        return totalPrice;
//    }
//
//    public void setTotalPrice(Double totalPrice) {
//        this.totalPrice = totalPrice;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        EnrichedOrder that = (EnrichedOrder) o;
//        return quantity == that.quantity && Objects.equals(id, that.id) && Objects.equals(orderId, that.orderId) && Objects.equals(productName, that.productName) && Objects.equals(totalPrice, that.totalPrice) && Objects.equals(product, that.product);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, orderId, productName, totalPrice, quantity, product);
//    }
//
//    @Override
//    public String toString() {
//        return "EnrichedOrder{" +
//                "id='" + id + '\'' +
//                ", orderId=" + orderId +
//                ", productName='" + productName + '\'' +
//                ", totalPrice=" + totalPrice +
//                ", quantity=" + quantity +
//                ", product=" + product +
//                '}';
//    }
//}
