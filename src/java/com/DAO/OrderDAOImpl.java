package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.Entity.Order;

public class OrderDAOImpl implements OrderDAO{
	
	private Connection conn;

	public OrderDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public int createOrder(int supplierId) {
		
		int orderId = 0;
		
		try {
			String sql = "INSERT INTO orders (supplier_id, order_date, status) VALUES (?, NOW(), 'pending')";
			
			PreparedStatement ps=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, supplierId);
			ps.executeUpdate();
			
			 ResultSet rs = ps.getGeneratedKeys();
		        if (rs.next()) {
		            orderId = rs.getInt(1);
		        }
		} catch (Exception e) {
			e.printStackTrace();
		}

		return orderId;
	}
	
	
	
	public void markAsReceived(int orderId) {
		
	    try {
		    String sql = "UPDATE orders SET status = 'received' WHERE order_id = ?";
		    PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, orderId);
	        ps.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public List<Order> getAllOrders() {
		
	    List<Order> list = new ArrayList<>();
	    


	    try {
		    String sql = "SELECT * FROM orders ORDER BY order_date DESC";
		    PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
	        	
	            Order o = new Order();
	            o.setOrderId(rs.getInt("order_id"));
	            o.setSupplierId(rs.getInt("supplier_id"));
	            o.setOrderDate(rs.getString("order_date"));
	            o.setStatus(rs.getString("status"));
	            list.add(o);
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}



}
