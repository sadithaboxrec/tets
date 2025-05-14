package com.Entity;

public class Dispatch {
	
    private int dispatchId;
    private int productId;
    private int quantity;
    private String dispatchDate;
    private String destination;
    
    private String productName;
    
    private String status;
 


	public int getDispatchId() {
		return dispatchId;
	}
	public void setDispatchId(int dispatchId) {
		this.dispatchId = dispatchId;
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
	public String getDispatchDate() {
		return dispatchDate;
	}
	public void setDispatchDate(String dispatchDate) {
		this.dispatchDate = dispatchDate;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
    
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

  
}
