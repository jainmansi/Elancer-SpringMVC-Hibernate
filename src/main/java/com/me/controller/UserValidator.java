package com.me.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

//import com.me.pojo.User;

@Component
public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		
	}

//	public boolean supports(Class aClass) {
//		return aClass.equals(User.class);
//	}
//
//	public void validate(Object obj, Errors errors) {
//		User user = (User) obj;
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.user", "First Name Required");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.user", "Last Name Required");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.invalid.user", "User Name Required");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "error.invalid.confirmPassword", "Password Required");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.invalid.email.emailId", "Email Required");
//    }
}
