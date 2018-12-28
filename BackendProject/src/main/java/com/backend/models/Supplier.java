package com.backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="SupplierTable")
public class Supplier {

	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private int supplierId;
	     
	 @NotEmpty(message="enter Supplier Name")	     
	 private String supplierName;
	     
	 @NotEmpty(message="enter Supplier Address")
	 @Column(name="address")
	 private String supplierAddress;
	     
	 public int getSupplierId() {
	    return supplierId;
	    }
	    public void setSupplierId(int supplierId) {
	        this.supplierId = supplierId;
	    }
	    public String getSupplierName() {
	        return supplierName;
	    }
	    public void setSupplierName(String supplierName) {
	        this.supplierName = supplierName;
	    }
	    public String getSupplierAddress() {
	        return supplierAddress;
	    }
	    public void setSupplierAddress(String supplierAddress) {
	        this.supplierAddress = supplierAddress;
	    }
	
	
	
}
