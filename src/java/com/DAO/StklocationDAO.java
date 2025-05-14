/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

/**
 *
 * @author dines
 */

import com.DB.DBConnect;
import java.sql.PreparedStatement;
import com.Entity.Category;
import com.Entity.stcklocation;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class StklocationDAO {
        	private Connection conn;
        
        public StklocationDAO(Connection conn) {
		super();
		this.conn = conn;
	      }
        
        public boolean addLocation(stcklocation loc ) {
        boolean f=false;
    
        try {
            
           String sql=" INSERT INTO stocklocation (loc_name, loc_status)  values (?,?)"; 
           
           PreparedStatement ps= conn.prepareStatement(sql);
           ps.setString(1, loc.getLocName());
           ps.setString(2, loc.getLocStatus());
           
           
                       
            int i= ps.executeUpdate();
            
            if(i==1) {
            	f=true;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return f;
    }

    
    
    
    
    	public List <stcklocation> getlockations() {
		
		List <stcklocation> list=new ArrayList<stcklocation>();
		stcklocation loc=null;
		
                try {

                        String sql="SELECT * FROM stocklocation";
                        PreparedStatement ps=conn.prepareStatement(sql);

                         ResultSet rs=ps.executeQuery();

                        while(rs.next()) 
                        {

                               loc = new stcklocation();
                               loc.setLocId(rs.getInt("loc_id"));
                               loc.setLocName(rs.getString("loc_name"));
                               loc.setLocStatus(rs.getString("loc_status"));
                               
                                list.add(loc);
                        }


              } catch (Exception e) {
                      e.printStackTrace();
              }

		return list;
	}




        

	public stcklocation getlockationById(int id) {
		
		stcklocation loc=null;
		
		try {
			
			String sql="SELECT * FROM stocklocation WHERE loc_id=? ";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);

			
			ResultSet rs=ps.executeQuery();	
			
			  while(rs.next()) 
			  {
				  
				 loc = new stcklocation();
                                 loc.setLocId(rs.getInt(1));
                                 loc.setLocName(rs.getString(2));
                                 loc.setLocStatus(rs.getString(3));
                                 
				  
			  }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return loc;
	}


	public boolean updateEditLocation(stcklocation loc) {
		
		boolean f=false;
		
		try {
			String sql="UPDATE stocklocation SET loc_name=?, loc_status=? WHERE loc_id=?";

			
			PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setString(1, loc.getLocName());
                        ps.setString(2, loc.getLocStatus());
			ps.setInt(3, loc.getLocId());
                        

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
