package com.backend.daos;

import java.util.List;

import com.backend.models.Supplier;

public interface SupplierDao {

	 public boolean addSupplier(Supplier supplierObj);
	    public boolean deleteSupplier(Supplier supplierObj);
	    public boolean updateSupplier(Supplier supplierObj);
	    public Supplier getSupplierById(int id);
	    public List<Supplier> getAllSuppliers();
}
