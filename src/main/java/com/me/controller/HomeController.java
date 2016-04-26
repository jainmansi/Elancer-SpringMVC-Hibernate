package com.me.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.me.dao.ClientDAOImpl;
import com.me.dao.PersonDAOImpl;
import com.me.email.ConfirmationEmail;
//import com.me.dao.UserDAOImpl;
import com.me.dao.ApplicantDAOImpl;
import com.me.exception.AdException;
import com.me.pojo.Client;
import com.me.pojo.Person;
import com.me.pojo.Applicant;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	@Qualifier("personValidator")
	PersonValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

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

	@RequestMapping(value = "/clientsignup.htm", method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("person") Person person, BindingResult result,
			HttpServletRequest request, ModelMap model) throws Exception {

		validator.validate(person, result);
		if (result.hasErrors()) {
			return "clientSignup";
		}
		
		String testName = request.getParameter("username");
		
		boolean unique = personDao.isUnique(testName);
		
		if(!unique){
			return "verifyAccount";
		}
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

			// Person person = new Person();
			// person.setFirstName(firstName);
			// person.setLastName(lastName);
			// personDao.save(person);

			System.out.println("Saved client and person");

			ConfirmationEmail confEmail = new ConfirmationEmail();
			String body = "Your account has bee successfully created. "
					+ "Login to our portal to access our services.";
			confEmail.setBody(body);
			confEmail.setRecipient(person.getEmail());
			confEmail.sendEmailToCLient();

			System.out.println("Saved applicant and person");
			return "confirm";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";
	}

	@RequestMapping(value = "/applicantsignup.htm", method = RequestMethod.POST)
	protected String doApplicantSubmitAction(@ModelAttribute("person") Person person, BindingResult result,
			HttpServletRequest request, ModelMap model) throws Exception {
		
		validator.validate(person, result);
		if (result.hasErrors()) {
			return "elancrrSignup";
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

			Random random = new Random();
			int length = 5 + (Math.abs(random.nextInt()) % 3);
			StringBuffer OTPStrBuffer = new StringBuffer();
			for (int i = 0; i < length; i++) {
				int baseCharacterNumber = Math.abs(random.nextInt()) % 62;
				int characterNumber = 0;
				if (baseCharacterNumber < 26) {
					characterNumber = 65 + baseCharacterNumber;
				} else if (baseCharacterNumber < 52) {
					characterNumber = 97 + (baseCharacterNumber - 26);
				} else {
					characterNumber = 48 + (baseCharacterNumber - 52);
				}
				OTPStrBuffer.append((char) characterNumber);
			}

			ConfirmationEmail confEmail = new ConfirmationEmail();
			String body = "Done";
			confEmail.setBody(body);
			confEmail.setRecipient(person.getEmail());
			confEmail.sendEmailToCLient();

			System.out.println("Saved applicant and person");
			return "confirm";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";
	}

	@RequestMapping(value = "/home.htm", method = RequestMethod.POST)
	protected String landingPage(@ModelAttribute("person") Person person, BindingResult result,
			HttpServletRequest request, ModelMap model) throws AdException {
		Person testPerson = new Person();
		testPerson = personDao.findByUsername(person.getUsername());

		if (testPerson.getUsername().equals(person.getUsername())
				&& testPerson.getPassword().equals(person.getPassword())) {

			HttpSession session = request.getSession();
			session.setAttribute("username", person.getUsername());
			session.setAttribute("usertype", testPerson.getUsertype());
			
			System.out.println(session.getAttribute("username"));
			System.out.println(session.getAttribute("usertype"));

			if (testPerson.getUsertype().equals("client")) {
				return "landingpage";
			} else if (testPerson.getUsertype().equals("applicant")) {
				return "homepage";
			} else if (testPerson.getUsertype().equals("admin")) {
				return "adminpage";
			}
		}
		return "index";
	}

	@RequestMapping(value = "/signin.htm", method = RequestMethod.GET)
	protected String signinForm(@ModelAttribute("person") Person person, BindingResult result,
			HttpServletRequest request, ModelMap model) {
		return "signin";
	}

	@RequestMapping(value = "/myhome.htm", method = RequestMethod.GET)
	protected String adminHome(@ModelAttribute("person") Person person, BindingResult result,
			HttpServletRequest request, ModelMap model) throws AdException {

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		System.out.println(username);

		if (username != null) {
			String usertype = (String) session.getAttribute("usertype");
			if (usertype.equals("admin"))
				return "adminpage";
			else if (usertype.equals("client")) {
				return "landingpage";
			} else if (usertype.equals("applicant")) {
				return "homepage";
			}
		}

		return "redirect:/";
	}

	@RequestMapping(value = "/adduser.htm", method = RequestMethod.GET)
	protected String initializeForm(@ModelAttribute("person") Person person, BindingResult result,
			HttpServletRequest request, ModelMap model) {
		String type = request.getParameter("type");
		if (type.equals("client"))
			return "clientSignup";
		else
			return "elancrrSignup";
	}

	@RequestMapping(value = "/resume.htm", method = RequestMethod.GET)
	protected String resume(@ModelAttribute("person") Person person, @RequestParam(value = "username") String username, BindingResult result, HttpServletRequest request,
			ModelMap model) {
		HttpSession session = request.getSession();
		session.getAttribute("resumeName");
		return "resumeName";
	}

	@RequestMapping(value = "/checkUsername", method = RequestMethod.GET)
	protected @ResponseBody String checkAvailability(@ModelAttribute("person") Person person, BindingResult result,
			HttpServletRequest request,HttpServletResponse response, ModelMap model) throws AdException, JSONException, Exception {
		
		boolean check = personDao.isUnique(request.getParameter("username"));
		
		JSONObject obj = new JSONObject();
		if(check){
			obj.put("message", "Username is unique");
			PrintWriter out = response.getWriter();
	        out.print(obj);
		}
		else{
			obj.put("message", "Username is not unique");
			PrintWriter out = response.getWriter();
	        out.print(obj);
		}		
		return null;
	}

}
