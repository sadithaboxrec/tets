
package com.DAO;

import com.Entity.Supplier;
import java.util.List;

public interface SupplierDAO {
    
    public boolean addSupplier(Supplier s);
    
    public  List <Supplier> getSupplier();
    
    public Supplier getSupplierById(int id);

    public boolean updateEditSupplier(Supplier s);
}
