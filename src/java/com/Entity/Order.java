package com.Entity;

public class Order {
    private int orderId;
    private int supplierId;
    private String orderDate;
    private String status;

    
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
