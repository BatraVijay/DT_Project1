package com.backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;


@Entity
@Table(name="productTable_04")
public class Product{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer productId;
	
	@NotEmpty(message="Enter productname")
	private String productName;
	
	@NotEmpty(message="enter product desc")
	private String productDesc;
	
	
	private Integer supplierId;
	
	private Integer categoryId;
	
	@NotNull(message="Enter price")
	private Double price;
	
	@NotNull(message="Enter stock")
	private Integer stock;
	
	
	
	private String img;
	
	@Transient
	@NotNull(message="Upload Product Image")
	private MultipartFile vimage;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public MultipartFile getVimage() {
		return vimage;
	}

	public void setVimage(MultipartFile vimage) {
		this.vimage = vimage;
	}

	
	
}