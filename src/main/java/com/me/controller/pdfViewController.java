package com.me.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.me.dao.ApplicantDAOImpl;
import com.me.dao.ApplicationDAOImpl;
import com.me.dao.PersonDAOImpl;
import com.me.exception.AdException;
import com.me.pojo.JobApplication;
import com.me.pojo.Person;
import com.me.springview.PdfReportView;

@Controller
public class pdfViewController {
	
	@Autowired
	@Qualifier("personDao")
	PersonDAOImpl personDao;
	
	@Autowired
	@Qualifier("applicantDao")
	ApplicantDAOImpl applicantDao;
	
	@Autowired
	@Qualifier("applicationDao")
	ApplicationDAOImpl applicationDao;
	
	@RequestMapping(value = "/report.htm", method = RequestMethod.GET)
    public ModelAndView createReport(HttpServletRequest request) throws AdException
    {
		Map<String,String> data = new HashMap();
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		Person person = personDao.findByUsername(username);
		long personId = person.getPersonID();
		
		ArrayList<JobApplication> applications = applicationDao.findByApplicantId(personId);
		System.out.println(applications.get(0).getApplicationId());
		session.setAttribute("reportData", applications);
		
        View view = new PdfReportView();
        return new ModelAndView(view);
    }
}
