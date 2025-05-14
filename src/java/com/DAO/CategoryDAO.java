
package com.DAO;

import com.Entity.Category;
import java.util.List;

public interface CategoryDAO {
    
    public boolean addCategory(Category c);
    
    public  List <Category> getCategory();
    
    public Category getCategoryById(int id);

    public boolean updateEditCategory(Category c);


    
}
