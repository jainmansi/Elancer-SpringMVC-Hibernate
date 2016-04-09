package com.me.controller;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.dao.PersonDAOImpl;
import com.me.dao.UserDAOImpl;
import com.me.pojo.Person;
import com.me.pojo.User;

@Controller
@RequestMapping("/adduser.htm")
public class AddUserController {
	
	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(validator);
	}
	
	private String type = "client";
	
	@RequestMapping(method=RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("user")User user, BindingResult result, HttpServletRequest request, ModelMap model) throws Exception{
		validator.validate(user, result);
		if (result.hasErrors()) {
			return null;
		}
		
		try {
			System.out.print("test");
			PersonDAOImpl personDao = new PersonDAOImpl();
			UserDAOImpl userDao = new UserDAOImpl();
			
			userDao.save(user);
			
			String firstName = user.getFirstName();
			String lastName = user.getLastName();
			String username = user.getUsername();
			String email = user.getEmail();
			String userType = type;
			String password = user.getPassword();
			String confirmPassword = user.getConfirmPassword();
			
			Person person = new Person();
			
			person.setFirstName(firstName);
			person.setLastName(lastName);		
			
			personDao.save(person);
			
			System.out.println("Saved user and person");
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("user") User user, BindingResult result, HttpServletRequest request, ModelMap model) {
		type = request.getParameter("type");
		return "signup";
	}
	
}
