package com.me.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.me.dao.ApplicantDAOImpl;
import com.me.dao.ApplicationDAOImpl;
import com.me.dao.ClientDAOImpl;
import com.me.dao.JobCategoryDAOImpl;
import com.me.dao.JobDAOImpl;
import com.me.dao.PersonDAOImpl;
import com.me.exception.AdException;
import com.me.pojo.Applicant;
import com.me.pojo.Client;
import com.me.pojo.Job;
import com.me.pojo.JobApplication;
import com.me.pojo.JobCategory;
import com.me.pojo.Person;

@Controller
@SessionAttributes("username")
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
	@Qualifier("applicationDao")
	ApplicationDAOImpl applicationDao;
	
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
			
			System.out.println(username);
			
			Person person = (Person) personDao.findByUsername(username);
			//System.out.println(person.getFirstName());
			long id = person.getPersonID();
			Client client = (Client) clientDao.findById(id);
			job.setPostedBy(client);
			
			int catid = Integer.parseInt(request.getParameter("catId"));
			JobCategory selectedCategory = new JobCategory();
			selectedCategory = categoryDao.findById(catid);
			
			job.setJobCategory(selectedCategory);
			
			System.out.print("test");
			jobDao.save(job);		
			System.out.println("Saved new job");
			return "jobAdded";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";
	}
	
	@RequestMapping(value="/viewMyJobs.htm", method = RequestMethod.GET)
	protected String viewCategory(@ModelAttribute("jobCategory") JobCategory jobCategory, BindingResult result, HttpServletRequest request, Model model) throws Exception{
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		ArrayList<Job> jobList = new ArrayList<Job>();
		jobList = jobDao.findByUserId(personDao.findByUsername(username).getPersonID());
		model.addAttribute("myJobList", jobList);
		return "viewJobs";
	}
	
	@RequestMapping(value="/searchJobs.htm", method = RequestMethod.GET)
	protected String signinForm(@ModelAttribute("job") Job job, BindingResult result, HttpServletRequest request, ModelMap model) throws Exception{
		ArrayList<JobCategory> categoryList = new ArrayList<JobCategory>();
		categoryList = categoryDao.findAll();
		
		ArrayList<String> categoryName = new ArrayList<String>();
		
		for(JobCategory jc : categoryList){
			categoryName.add(jc.getCategoryName());
		}
		
		//System.out.println(categoryList.get(0).getCategoryName());
		
		model.addAttribute("list", categoryList);
		model.addAttribute("namelist", categoryName);
		return "searchJobs";
	}
	
	@RequestMapping(value="/applyNow.htm", method = RequestMethod.GET)
	protected String jobApplication(@ModelAttribute("jobApplication") JobApplication jobApplication, BindingResult result, HttpServletRequest request, ModelMap model){
		int i = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		session.setAttribute("jobid", i);
		System.out.println(i);
		return "applicationForm";
	}
	
	@RequestMapping(value="/application.htm", method = RequestMethod.POST)
	protected String applicationProcessing(@ModelAttribute("jobApplication") JobApplication jobApplication, BindingResult result, HttpServletRequest request, ModelMap model) throws Exception{
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		Applicant applicant = applicantDao.findByUsername(username);
		System.out.println("Applicant"+applicant.getFirstName());
		jobApplication.setAppliedBy(applicant);
		
		int id = (Integer) session.getAttribute("jobid");
		System.out.println("jobid"+id);
		Job job = jobDao.findById(id);
		jobApplication.setJob(job);
		
		jobApplication.setStatus("Applied");
		applicationDao.save(jobApplication);
		
		return "success";
	}
	
	@RequestMapping("/search.htm")
	public String searchPost(@ModelAttribute("job") Job job, BindingResult result, HttpServletRequest request, Model model) throws AdException {
		String keyword = request.getParameter("keyword");
		int categoryId = Integer.parseInt(request.getParameter("catId"));
		
		ArrayList<Job> searchList = (ArrayList<Job>) jobDao.findByKeyword(keyword, categoryId);
		model.addAttribute("searchList", searchList);
		return "searchResult";
	}
	
}
	
	