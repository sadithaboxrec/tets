package com.DAO;

import java.util.List;

import com.Entity.Products;

public interface ProductDAO {
	
	public boolean addProducts(Products p);
	
	public  List <Products> getAllProducts();
	
	public List<Products> getProductsBySupplier(int pro_supplier);
	
	

	
//	public Products getProductById(int id);
	
//	public boolean updateEditProducts(Products b);
	
	public void increaseStock(int productId, int qty);
	public List<Products> getProductsByCategory(int catId);
	
	

}
