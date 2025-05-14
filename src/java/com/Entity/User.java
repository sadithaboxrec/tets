package com.Entity;

public class User {
	
	private int userId;
	private String name;
	private String email;
	private String telephone;
	private String role;
	private String status;
	private String username;
	private String password;
	private String photoName;
	private int userroleId;
	
	//private String roleName; 

	
	
	
	public User() {
		super();
		
	}
	
	
	public User(String name, String email, String telephone, String status, String username,
			String password, String photoName,String role) {
		super();
		this.name = name;
		this.email = email;
		this.telephone = telephone;
		
		this.status = status;
		this.username = username;
		this.password = password;
		this.photoName = photoName;
                this.role = role;
		// this.userroleId = userroleId;
	}

	

	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}


	public int getUserroleId() {
		return userroleId;
	}


	public void setUserroleId(int userroleId) {
		this.userroleId = userroleId;
	}


	
	public String getRole() {
        return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

	public String uvalidate (String u_roll){
            if(this.role.equals(u_roll)){
                return "true";
            }
            return null;
        }

}
