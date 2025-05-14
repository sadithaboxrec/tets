package com.Entity;

public class Products {
	
	private int pro_id;
	private String pro_name;
	private int pro_category;
	private int pro_supplier;
	private int pro_stock;
	private String pro_photo;
	private String user;
	
	
    // New fields for category and supplier names
    private String categoryName;
    private String suppName;
	
	public Products() {
		super();
		
	}


	public Products( String pro_name, int pro_category, int pro_supplier, int pro_stock,  String pro_photo, String user) {
		super();
		this.pro_name = pro_name;
		this.pro_category = pro_category;
		this.pro_supplier = pro_supplier;
		this.pro_stock = pro_stock;
		this.pro_photo = pro_photo;
		this.user=user;
	}


	public int getPro_id() {
		return pro_id;
	}


	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}


	public String getPro_name() {
		return pro_name;
	}


	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}


	public int getPro_category() {
		return pro_category;
	}


	public void setPro_category(int pro_category) {
		this.pro_category = pro_category;
	}


	public int getPro_supplier() {
		return pro_supplier;
	}


	public void setPro_supplier(int pro_supplier) {
		this.pro_supplier = pro_supplier;
	}


	public int getPro_stock() {
		return pro_stock;
	}


	public void setPro_stock(int pro_stock) {
		this.pro_stock = pro_stock;
	}


	public String getPro_photo() {
		return pro_photo;
	}


	public void setPro_photo(String pro_photo) {
		this.pro_photo = pro_photo;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}
	
	
	
	//for view methods
	
	
	
	

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSuppName() {
        return suppName;
    }

    public void setSuppName(String suppName) {
        this.suppName = suppName;
    }
	
	


}
