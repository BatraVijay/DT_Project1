package com.backend.daos;

import java.util.List;

import com.backend.models.Category;

public interface CategoryDaos {
	 public boolean addCategory(Category category);
	    public boolean deleteCategory(Category category);
	    public boolean updateCategory(Category category);
	    public List<Category> getAllCategories();
	    public Category getCategoryById(int categoryId);

}
