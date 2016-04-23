package com.me.controller;


import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.dao.ClientDAOImpl;
import com.me.dao.PersonDAOImpl;
//import com.me.dao.UserDAOImpl;
import com.me.dao.ApplicantDAOImpl;
import com.me.exception.AdException;
import com.me.pojo.Client;
import com.me.pojo.Person;
import com.me.pojo.Applicant;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
//	@Autowired
//	@Qualifier("userValidator")
//	UserValidator validator;
//	
//	@InitBinder
//	private void initBinder(WebDataBinder binder){
//		binder.setValidator(validator);
//	}
	
	@Autowired
	@Qualifier("personDao")
	PersonDAOImpl personDao;
	
	@Autowired
	@Qualifier("applicantDao")
	ApplicantDAOImpl applicantDao;
	
	@Autowired
	@Qualifier("clientDao")
	ClientDAOImpl clientDao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {		
		return "index";
	}
	
	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public String logoutFunction(HttpSession session, HttpServletResponse response) {
		 session.invalidate();
		 response.setHeader("Cache-Control", "no-cache");
		 response.setDateHeader("Expires", 0);
         return "redirect:/";
	}
	
	@RequestMapping(value="/clientsignup.htm", method=RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("person")Person person, BindingResult result, HttpServletRequest request, ModelMap model) throws Exception{
		//validator.validate(user, result);
//		if (result.hasErrors()) {
//			return null;
//		}
		
		System.out.println(person.getFirstName());
		
		try {
			System.out.print("test");
			
			Client client = new Client();
			
			client.setFirstName(person.getFirstName());
			client.setLastName(person.getLastName());
			client.setUsername(person.getUsername());
			client.setUsertype(person.getUsertype());
			client.setEmail(person.getEmail());
			client.setPassword(person.getPassword());
			client.setConfirmPassword(person.getConfirmPassword());
			clientDao.save(client);
			
//			Person person = new Person();
//			person.setFirstName(firstName);
//			person.setLastName(lastName);			
//			personDao.save(person);
			
			System.out.println("Saved client and person");
			return "confirm";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";
	}
	
	@RequestMapping(value="/applicantsignup.htm", method=RequestMethod.POST)
	protected String doApplicantSubmitAction(@ModelAttribute("person")Person person, BindingResult result, HttpServletRequest request, ModelMap model) throws Exception{
		//validator.validate(user, result);
		if (result.hasErrors()) {
			return null;
		}
		
		try {
			System.out.print("test");
			
			Applicant applicant = new Applicant();
			
			applicant.setFirstName(person.getFirstName());
			applicant.setLastName(person.getLastName());
			applicant.setUsername(person.getUsername());
			applicant.setUsertype(person.getUsertype());
			applicant.setEmail(person.getEmail());
			applicant.setPassword(person.getPassword());
			applicant.setConfirmPassword(person.getConfirmPassword());
			applicantDao.save(applicant);
			
			System.out.println("Saved applicant and person");
			return "confirm";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";
	}
	
	@RequestMapping(value="/home.htm", method=RequestMethod.POST)
	protected String landingPage(@ModelAttribute("person") Person person, BindingResult result, HttpServletRequest request, ModelMap model) throws AdException{		
		Person testPerson = new Person();
		testPerson = personDao.findByUsername(person.getUsername());
		
		if(testPerson.getUsername().equals(person.getUsername()) && testPerson.getPassword().equals(person.getPassword())){
			
			HttpSession session = request.getSession();
			session.setAttribute("username", person.getUsername());
			session.setAttribute("usertype", person.getUsertype());
			
			if(testPerson.getUsertype().equals("client")){
				return "landingpage";
			}
			else if(testPerson.getUsertype().equals("applicant")){
				return "homepage";
			}
			else if(testPerson.getUsertype().equals("admin")){
				return "adminpage";
			}
		}
		return "index";
	}
	
	@RequestMapping(value="/signin.htm", method = RequestMethod.GET)
	protected String signinForm(@ModelAttribute("person") Person person, BindingResult result, HttpServletRequest request, ModelMap model){
		return "signin";
	}
	
	@RequestMapping(value="/myhome.htm", method = RequestMethod.GET)
	protected String adminHome(@ModelAttribute("person") Person person, BindingResult result, HttpServletRequest request, ModelMap model) throws AdException{
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		System.out.println(username);
		
		if(username!=null){
			if(personDao.findByUsername(username).getUsertype().equals("admin"))
			return "adminpage";
		}
		
		return "redirect:/";
	}

	@RequestMapping(value="/adduser.htm", method = RequestMethod.GET)
	protected String initializeForm(@ModelAttribute("person") Person person, BindingResult result, HttpServletRequest request, ModelMap model) {
		String type = request.getParameter("type");
		if(type.equals("client"))
			return "clientSignup";
		else
			return "elancrrSignup";
	}
	
}
