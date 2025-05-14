package com.Entity;


public class Supplier {
    
    private int supp_id;
    private String supp_name;
    private String supp_email;
    private String supp_cno;

    // No-argument constructor
    public Supplier() {
        super();
    }

    // Parameterized constructor
    public Supplier( String supp_name, String supp_email, String supp_cno) {
        super();
        
        this.supp_name = supp_name;
        this.supp_email = supp_email;
        this.supp_cno = supp_cno;
    }

    // Getters and Setters
    public int getSupp_id() {
        return supp_id;
    }

    public void setSupp_id(int supp_id) {
        this.supp_id = supp_id;
    }

    public String getSupp_name() {
        return supp_name;
    }

    public void setSupp_name(String supp_name) {
        this.supp_name = supp_name;
    }

    public String getSupp_email() {
        return supp_email;
    }

    public void setSupp_email(String supp_email) {
        this.supp_email = supp_email;
    }

    public String getSupp_cno() {
        return supp_cno;
    }

    public void setSupp_cno(String supp_cno) {
        this.supp_cno = supp_cno;
    }
    
}
