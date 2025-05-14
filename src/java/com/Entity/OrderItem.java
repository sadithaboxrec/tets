package com.Entity;

public class OrderItem {
	
	private int itemId;
    private int orderId;
    private int productId;
    private int quantity;

    // Constructors
    public OrderItem() {}

    public OrderItem(int itemId, int orderId, int productId, int quantity) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getters and Setters
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
