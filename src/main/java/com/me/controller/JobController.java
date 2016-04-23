package com.me.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.dao.ApplicantDAOImpl;
import com.me.dao.ClientDAOImpl;
import com.me.dao.JobCategoryDAOImpl;
import com.me.dao.JobDAOImpl;
import com.me.dao.PersonDAOImpl;
import com.me.pojo.Client;
import com.me.pojo.Job;
import com.me.pojo.JobCategory;
import com.me.pojo.Person;

@Controller
public class JobController {
	
	@Autowired
	@Qualifier("personDao")
	PersonDAOImpl personDao;
	
	@Autowired
	@Qualifier("applicantDao")
	ApplicantDAOImpl applicantDao;
	
	@Autowired
	@Qualifier("clientDao")
	ClientDAOImpl clientDao;
	
	@Autowired
	@Qualifier("jobDao")
	JobDAOImpl jobDao;
	
	@Autowired
	@Qualifier("categoryDao")
	JobCategoryDAOImpl categoryDao;
	
	@RequestMapping(value="/addJob.htm", method = RequestMethod.GET)
	protected String addJobPage(@ModelAttribute("job") Job job, BindingResult result, HttpServletRequest request, Model model) throws Exception{
		ArrayList<JobCategory> categoryList = new ArrayList<JobCategory>();
		categoryList = categoryDao.findAll();
		
		ArrayList<String> categoryName = new ArrayList<String>();
		
		for(JobCategory jc : categoryList){
			categoryName.add(jc.getCategoryName());
		}
		
		//System.out.println(categoryList.get(0).getCategoryName());
		
		model.addAttribute("list", categoryList);
		model.addAttribute("namelist", categoryName);
		return "postJob";
	}
	
	@RequestMapping(value="/addJob.htm", method = RequestMethod.POST)
	protected String addJobForm(@ModelAttribute("job") Job job, BindingResult result, HttpServletRequest request, ModelMap model) throws Exception{
		try {
			
			//System.out.println(job.getJobCategory().getCategoryId());
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			
			Client client = (Client) clientDao.findByUsername(username);	
			job.setPostedBy(client);
			
			int id = Integer.parseInt(request.getParameter("catId"));
			JobCategory selectedCategory = new JobCategory();
			selectedCategory = categoryDao.findById(id);
			
			job.setJobCategory(selectedCategory);
			
			System.out.print("test");
			jobDao.save(job);		
			System.out.println("Saved new job");
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";
	}
	}
	
	