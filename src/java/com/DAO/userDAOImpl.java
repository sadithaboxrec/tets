package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Entity.User;



public class userDAOImpl implements  userDAO {
	
	private Connection conn;

	
	
	
	public userDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}




	public boolean userRegister(User us) {
		
		boolean f=false;
		
		try {
			
			String sql = "INSERT INTO user (name, email, telephone, status, username, password, photoName, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            
			PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1, us.getName());
            ps.setString(2, us.getEmail());
            ps.setString(3, us.getTelephone());
            
            ps.setString(4, us.getStatus());
            ps.setString(5, us.getUsername()); 
            ps.setString(6, us.getPassword());
            ps.setString(7, us.getPhotoName());
            ps.setString(8, us.getRole());
           // ps.setInt(8, us.getUserroleId());
            
            int i= ps.executeUpdate();
            
            if(i==1) {
            	f=true;
            }
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		return f;
	
	}
	
	




@Override
public User login(String username, String password) {
    
    User us = null;

    try {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username); 
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            us = new User();
            us.setUserId(rs.getInt("user_id"));
            us.setName(rs.getString("name"));
            us.setEmail(rs.getString("email"));
            us.setTelephone(rs.getString("telephone"));
            us.setUsername(rs.getString("username"));    
            us.setPassword(rs.getString("password"));
            us.setStatus(rs.getString("status"));
            us.setPhotoName(rs.getString("photoName"));
            us.setRole(rs.getString("role"));  // Directly from ENUM column
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return us; 
}




@Override
public List<User> getAllAdmins() {
    List<User> list = new ArrayList<>();
    User us = null;

    try {
        String sql = "SELECT * FROM user";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            us = new User();
            us.setUserId(rs.getInt("user_id"));
            us.setName(rs.getString("name"));
            us.setEmail(rs.getString("email"));
            us.setTelephone(rs.getString("telephone"));
            us.setUsername(rs.getString("username"));
            us.setPassword(rs.getString("password"));
            us.setStatus(rs.getString("status"));
            us.setPhotoName(rs.getString("photoName"));
            us.setRole(rs.getString("role")); // use ENUM field

            list.add(us);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}




@Override
public User getUserById(int user_id) {
    User us = null;

    try {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, user_id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            us = new User();
            us.setUserId(rs.getInt("user_id"));
            us.setName(rs.getString("name"));
            us.setEmail(rs.getString("email"));
            us.setTelephone(rs.getString("telephone"));
            us.setUsername(rs.getString("username"));
            us.setPassword(rs.getString("password"));
            us.setStatus(rs.getString("status"));
            us.setPhotoName(rs.getString("photoName"));
            us.setRole(rs.getString("role")); // from ENUM
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return us;
}



@Override
public boolean updateUsers(User us) {
    boolean f = false;

    try {
        String sql = "UPDATE user SET name=?, email=?, telephone=?, username=?, password=?, status=?, role=? WHERE user_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, us.getName());
        ps.setString(2, us.getEmail());
        ps.setString(3, us.getTelephone());
        ps.setString(4, us.getUsername());
        ps.setString(5, us.getPassword());
        ps.setString(6, us.getStatus());
        ps.setString(7, us.getRole()); // direct enum
        ps.setInt(8, us.getUserId());

        int i = ps.executeUpdate();
        if (i == 1) {
            f = true;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return f;
}

	
	
	
	
	

}
