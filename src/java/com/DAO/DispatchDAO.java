package com.DAO;

import java.util.List;

import com.Entity.Dispatch;

public interface DispatchDAO {
	
	public boolean sendProduct(int productId, int qty, String destination);

	public List<Dispatch> getAllDispatches();
	
	public boolean markDispatchAsCompleted(int dispatchId);
	
	public boolean createDispatch(int productId, int quantity, String destination);
}
