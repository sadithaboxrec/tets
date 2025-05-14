package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.Entity.OrderItem;

public class OrderItemDAOImpl implements OrderItemDAO {
	
	private Connection conn;

	public OrderItemDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	
	public void addOrderItem(int orderId, int productId, int quantity) {
		
		try {
			
			String sql = "INSERT INTO order_items (order_id, product_id, quantity) VALUES (?, ?, ?)";
			
			PreparedStatement ps=conn.prepareStatement(sql);
			
			ps.setInt(1, orderId);
	        ps.setInt(2, productId);
	        ps.setInt(3, quantity);
	        ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public List<OrderItem> getItemsByOrderId(int orderId) {
		
	    List<OrderItem> list = new ArrayList<>();
	    
	    
	    
	    try  {
	    	
	    	String sql = "SELECT * FROM order_items WHERE order_id = ?";
	    	
	    	PreparedStatement ps=conn.prepareStatement(sql);
	    	
	        ps.setInt(1, orderId);
	        
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
	        	
	            OrderItem item = new OrderItem();
	            item.setProductId(rs.getInt("product_id"));
	            item.setQuantity(rs.getInt("quantity"));
	            list.add(item);
	            
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return list;
	}

	

}
