package com.backend.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.backend.models.Product;
import com.backend.models.User;
@Component
public class ProductValidation implements Validator {

	@Override
	public boolean supports(Class clazz) {
		// TODO Auto-generated method stub
		return Product.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Product pro=(Product)target;
		
		if(pro.getCategoryId()==0){
			errors.rejectValue("categoryId", "cat.Id");
		}
		if(pro.getSupplierId()==0){
			errors.rejectValue("supplierId", "supp.Id");
		}
	
		
			
	}



}
