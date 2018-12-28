package com.backend.daos;

import java.util.List;

import com.backend.models.Product;

public interface ProductDao {
	
	public boolean addProduct(Product productObj);
	public boolean deleteProduct(Product productObj);
	public boolean updateProduct(Product productObj);
	 public Product getProduct(int pId);
	    public List<Product> listProducts();
	    public List<Product> getAllProductsByCategory(int categoryId);

}
