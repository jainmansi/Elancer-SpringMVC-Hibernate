package com.me.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.me.pojo.Job;


@Component
public class JobValidator implements Validator{

	public boolean supports(Class aClass) {
		return aClass.equals(Job.class);
	}


	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}

}
