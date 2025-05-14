package com.DAO;

import java.util.List;

import com.Entity.User;


public interface userDAO {
	
	public boolean userRegister(User us);
	
	public User login(String username,String password);
	
	public List <User> getAllAdmins();
	
	public User getUserById(int user_id);
	
	public boolean updateUsers(User us);

}
