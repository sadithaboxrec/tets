package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Entity.Dispatch;

public class DispatchDAOImpl implements DispatchDAO {
	
	private Connection conn;
	
	public DispatchDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public boolean sendProduct(int productId, int qty, String destination) {
		
		try {
			
         	String sql = "INSERT INTO dispatches (product_id, quantity, destination, dispatch_date, status) VALUES (?, ?, ?, NOW(), 'preparing')";
		    String updateStock = "UPDATE products SET pro_stock = pro_stock - ? WHERE pro_id = ? AND pro_stock >= ?";
		    
	        conn.setAutoCommit(false); 

	        try {
	        	PreparedStatement ps1 = conn.prepareStatement(updateStock);
	            ps1.setInt(1, qty);
	            ps1.setInt(2, productId);
	            ps1.setInt(3, qty);
	            int updated = ps1.executeUpdate();

	            if (updated == 0) {
	                conn.rollback();
	                return false; 
	            }
	        } catch (Exception e) {
				e.printStackTrace();
			}
			

	        try  {
	        	PreparedStatement ps2 = conn.prepareStatement(sql);
	            ps2.setInt(1, productId);
	            ps2.setInt(2, qty);
	            ps2.setString(3, destination);
	            ps2.executeUpdate();
	        } catch (Exception e) {
				e.printStackTrace();
			}
			

	        conn.commit();
	        return true;
		
	        
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		return false;
	}
	
	
	public boolean createDispatch(int productId, int quantity, String destination) {
	    try {
	        String sql = "INSERT INTO dispatches (product_id, quantity, destination, status, dispatch_date) VALUES (?, ?, ?, 'preparing', NOW())";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, productId);
	        ps.setInt(2, quantity);
	        ps.setString(3, destination);
	        int rows = ps.executeUpdate();
	        return rows == 1;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	
	
	
	public List<Dispatch> getAllDispatches() {
	    List<Dispatch> list = new ArrayList<>();

	    String sql = "SELECT d.dispatch_id, d.product_id, d.quantity, d.destination, d.dispatch_date, d.status, p.pro_name " +
	                 "FROM dispatches d " +
	                 "JOIN products p ON d.product_id = p.pro_id";

	    try {
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Dispatch d = new Dispatch();

	            d.setDispatchId(rs.getInt("dispatch_id"));
	            d.setProductId(rs.getInt("product_id"));
	            d.setQuantity(rs.getInt("quantity"));
	            d.setDestination(rs.getString("destination"));
	            d.setDispatchDate(rs.getString("dispatch_date")); 
	            d.setStatus(rs.getString("status"));
	            d.setProductName(rs.getString("pro_name"));

	            list.add(d);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}


	
	public boolean markDispatchAsCompleted(int dispatchId) {
	    PreparedStatement psStatus = null;
	    PreparedStatement psCheckStock = null;
	    PreparedStatement psUpdateStock = null;

	    try {
	       
	        String checkSql = "SELECT product_id, quantity FROM dispatches WHERE dispatch_id = ?";
	        psCheckStock = conn.prepareStatement(checkSql);
	        psCheckStock.setInt(1, dispatchId);
	        ResultSet rs = psCheckStock.executeQuery();

	        if (rs.next()) {
	            int productId = rs.getInt("product_id");
	            int qtyToDispatch = rs.getInt("quantity");

	           
	            String stockSql = "SELECT pro_stock FROM products WHERE pro_id = ?";
	            PreparedStatement psStock = conn.prepareStatement(stockSql);
	            psStock.setInt(1, productId);
	            ResultSet stockRs = psStock.executeQuery();

	            if (stockRs.next()) {
	                int currentStock = stockRs.getInt("pro_stock");

	                if (currentStock < qtyToDispatch) {
	                   
	                    return false;
	                }

	              
	                String statusSql = "UPDATE dispatches SET status = 'completed' WHERE dispatch_id = ?";
	                psStatus = conn.prepareStatement(statusSql);
	                psStatus.setInt(1, dispatchId);
	                int updated = psStatus.executeUpdate();

	                if (updated == 1) {
	                 
	                    String updateStockSql = "UPDATE products SET pro_stock = pro_stock - ? WHERE pro_id = ?";
	                    psUpdateStock = conn.prepareStatement(updateStockSql);
	                    psUpdateStock.setInt(1, qtyToDispatch);
	                    psUpdateStock.setInt(2, productId);
	                    psUpdateStock.executeUpdate();

	                    return true;
	                }
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try { if (psStatus != null) psStatus.close(); } catch (Exception e) {}
	        try { if (psCheckStock != null) psCheckStock.close(); } catch (Exception e) {}
	        try { if (psUpdateStock != null) psUpdateStock.close(); } catch (Exception e) {}
	    }

	    return false;
	}


	

}
