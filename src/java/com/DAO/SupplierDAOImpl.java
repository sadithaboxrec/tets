
package com.DAO;

import com.Entity.Supplier;
import com.DB.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class SupplierDAOImpl implements SupplierDAO{
    
    
    private Connection conn;
        
    public SupplierDAOImpl(Connection conn) {
		super();
		this.conn = conn;
       }

    @Override
    public boolean addSupplier(Supplier s) {
        
        boolean f=false;
        
        
        try {
            
           String sql=" INSERT INTO supplier (supp_name, supp_email,supp_cno)  values (?,?,?)"; 
           
           PreparedStatement ps= conn.prepareStatement(sql);
           ps.setString(1, s.getSupp_name());
           ps.setString(2, s.getSupp_email());
           ps.setString(3, s.getSupp_cno());
               
            int i= ps.executeUpdate();
            
            if(i==1) {
            	f=true;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        
        
        return f;
    }
    
    
    
    
       	public List <Supplier> getSupplier() {
		
		List <Supplier> list=new ArrayList<Supplier>();
		Supplier s=null;
		
                try {

                        String sql="SELECT * FROM supplier";
                        PreparedStatement ps=conn.prepareStatement(sql);

                         ResultSet rs=ps.executeQuery();

                        while(rs.next()) 
                        {

                               s = new Supplier();
                               s.setSupp_id(rs.getInt(1));
                               s.setSupp_name(rs.getString(2));
                               s.setSupp_email(rs.getString(3));
                               s.setSupp_cno(rs.getString(4));
                                list.add(s);
                        }


              } catch (Exception e) {
                      e.printStackTrace();
              }

		return list;
	}
        
        
        
        
       public Supplier getSupplierById(int id) {
		
		Supplier s=null;
		
		try {
			
			String sql="SELECT * FROM supplier WHERE supp_id=? ";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);

			
			ResultSet rs=ps.executeQuery();	
			
			  while(rs.next()) 
			  {
				  
				 s = new Supplier();
                                 s.setSupp_id(rs.getInt(1));
                                 s.setSupp_name(rs.getString(2));
                                 s.setSupp_email(rs.getString(3));
                                 s.setSupp_cno(rs.getString(4));

				  
			  }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return s;
	}

       
       
       
              @Override
	public boolean updateEditSupplier(Supplier s) {
		
		boolean f=false;
		
		try {
			String sql="UPDATE supplier SET supp_name=?, supp_email=?, supp_cno=? WHERE supp_id=?";

			
			PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setString(1, s.getSupp_name());
                        ps.setString(2, s.getSupp_email());
                        ps.setString(3, s.getSupp_cno());
                        ps.setInt(4, s.getSupp_id());

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
