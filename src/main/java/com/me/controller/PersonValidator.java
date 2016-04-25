package com.me.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.pojo.Person;


@Component
public class PersonValidator implements Validator{

	public boolean supports(Class aClass) {
		return aClass.equals(Person.class);
	}

	public void validate(Object obj, Errors errors) {
		Person person = (Person) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "validate.firstName", "First Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "validate.firstName", "Last Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "validate.firstName", "User Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "validate.firstName", "Password Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "validate.firstName", "Password Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "validate.email", "Email Required");
    }
}
