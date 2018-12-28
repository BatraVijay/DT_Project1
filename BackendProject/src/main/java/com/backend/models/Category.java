package com.backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table (name="CategoryTable_12")
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cat_Id")
	private int categoryId;
	
	@NotEmpty(message="Enter category name")
	private String categoryName;
	
	@NotEmpty(message="Enter category Description")
	private String categoryDesc;
	
	public int getCategoryId()
	{
		return categoryId;
	}
	public void setCategoryId(int categoryId)
	{
	this.categoryId = categoryId;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getCategoryDesc() {
        return categoryDesc;
    }
    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

}
