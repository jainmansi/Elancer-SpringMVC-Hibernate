package com.me.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.pojo.JobCategory;

@Component
public class CategoryValidator implements Validator{

	@Override
	public boolean supports(Class aClass) {
		return aClass.equals(JobCategory.class);
	}


	@Override
	public void validate(Object target, Errors errors) {
		JobCategory category = (JobCategory) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoryName", "validate.categoryName", "Category name cannot be empty!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "validate.description", "Category description cannot be empty!");
	}

}
