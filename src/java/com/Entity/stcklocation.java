/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Entity;

/**
 *
 * @author dines
 */
public class stcklocation {
    private int loc_id;
    private String loc_name;
    private String loc_status;
    
public stcklocation(){
            super();
        }
        
        public stcklocation(String loc_name,String loc_status){
            super();
            
            this.loc_name=loc_name;
            this.loc_status=loc_status;
        }

        
        
        
    public void setLocId(int loc_id) {
        this.loc_id = loc_id;
    }


    public void setLocName(String loc_name) {
        this.loc_name = loc_name;
    }

    public void setLocStatus(String loc_status) {
        this.loc_status = loc_status;
    }

    public int getLocId() {
        return this.loc_id;
    }
    
    public String getLocName() {
        return loc_name;
    }

    public String getLocStatus() {
        return loc_status;
    }
            
            }
