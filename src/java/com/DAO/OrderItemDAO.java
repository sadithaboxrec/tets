package com.DAO;

import java.util.List;

import com.Entity.OrderItem;

public interface OrderItemDAO {
	
	public void addOrderItem(int orderId, int productId, int quantity);
	
	public List<OrderItem> getItemsByOrderId(int orderId);

}
