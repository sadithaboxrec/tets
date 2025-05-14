package com.DAO;

import java.util.List;

import com.Entity.Products;
import com.Entity.Order;

public interface OrderDAO {
	
	public int createOrder(int supplierId);
	
	public void markAsReceived(int orderId);
	
	public List<Order> getAllOrders();

}
