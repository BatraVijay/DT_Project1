package com.backend.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.backend.models.User;
@Component
public class MyPasswordValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		// TODO Auto-generated method stub
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","required.password");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password2","required.passwordconfirm");
		
		User user=(User) target;
		if(!(user.getPassword().equals(user.getPassword2()))){
			errors.rejectValue("password","notmatch.password");
		}
			
	}



}
