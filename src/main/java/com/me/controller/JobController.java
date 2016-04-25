package com.me.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

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
import com.me.springview.PdfReportView;

@Controller
@SessionAttributes("username")
public class JobController {
	
	private static final int JobApplication = 0;

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
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value="/addJobClient.htm", method = RequestMethod.GET)
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
		
		//System.out.println("11111111111111111111");
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		ArrayList<Job> jobList = new ArrayList<Job>();
		jobList = jobDao.findByUserId(personDao.findByUsername(username).getPersonID());
		model.addAttribute("myJobList", jobList);
		return "showJobs";
	}
	
	@RequestMapping(value="/searchJobs.htm", method = RequestMethod.POST)
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
		//System.out.println(i);
		return "applicationForm";
	}
	
	@RequestMapping(value="/jobStatus.htm", method = RequestMethod.GET)
	protected String jobStatus(@ModelAttribute("jobApplication") JobApplication jobApplication, BindingResult result, HttpServletRequest request, Model model) throws AdException{
		int i = Integer.parseInt(request.getParameter("id"));
		Job job = jobDao.findById(i);
		String title = job.getJobTitle();
		ArrayList<JobApplication> applicationList = applicationDao.findById(i);
		model.addAttribute("applications", applicationList);
		model.addAttribute("title", title);
		//System.out.println(i);
		return "jobStatus";
	}
	
	@RequestMapping(value="/application.htm", method = RequestMethod.POST)
	protected String applicationProcessing(@ModelAttribute("jobApplication") JobApplication jobApplication, BindingResult result,@RequestParam("photo") MultipartFile photoFile, HttpServletRequest request, ModelMap model) throws Exception{
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		Applicant applicant = applicantDao.findByUsername(username);
		System.out.println("Applicant"+applicant.getFirstName());
		jobApplication.setAppliedBy(applicant);
		
		int id = (Integer) session.getAttribute("jobid");
		//System.out.println("jobid"+id);
		Job job = jobDao.findById(id);
		session.removeAttribute("jobid");
		
		File file;
        String check = File.separator; //Checking if system is linux based or windows based by checking seprator used.
        String path = null;
        if(check.equalsIgnoreCase("\\")) {
         path = servletContext.getRealPath("").replace("build\\","resources\\uploads\\"); //Netbeans projects gives real path as Lab6/build/web/ so we need to replace build in the path.
     }
     
         if(check.equalsIgnoreCase("/")) {
        path = servletContext.getRealPath("").replace("build/","resources/uploads/");
        path += "/"; //Adding trailing slash for Mac systems.

     }
         if(jobApplication.getPhoto() != null){
            String fileNameWithExt = System.currentTimeMillis() + jobApplication.getPhoto().getOriginalFilename();
             file = new File(path+fileNameWithExt);
             String context = servletContext.getContextPath();
             jobApplication.getPhoto().transferTo(file);
             jobApplication.setPhotoName(path+fileNameWithExt);
//                  File file1 = File.createTempFile(fileNameWithoutExt, ".jpg", new File("D:\\sem2\\WebTools\\Project"));
//                  advertApt.getPhoto().transferTo(file1);
         }
        
        
        //AdvertisementAparjobApplicationtment advApt = advertApts.create(title,advertApt.getPhotoName(), message, host,category.getId(),category.getTitle(),price, street, city, state, zip, rooms, numOfBeds,bathrooms,maxOccupants,priceExtraOccupant,furnished,checkIn,checkOut);
		jobApplication.setJob(job);
		
		jobApplication.setStatus("Applied");
		applicationDao.save(jobApplication);
		
		return "appliedSuccessfully";
	}
	
	@RequestMapping("/search.htm")
	public String searchPost(@ModelAttribute("job") Job job, BindingResult result, HttpServletRequest request, Model model) throws AdException {
		String keyword = request.getParameter("keyword");
		int categoryId = Integer.parseInt(request.getParameter("catId"));
		
		ArrayList<Job> searchList = (ArrayList<Job>) jobDao.findByKeyword(keyword, categoryId);
		model.addAttribute("searchList", searchList);
		return "searchResult";
	}
	
	@RequestMapping(value="/details.htm", method = RequestMethod.GET)
	protected String moreDetails(@ModelAttribute("jobApplication") JobApplication jobApplication, BindingResult result, HttpServletRequest request, Model model) throws AdException{
		int id = Integer.parseInt(request.getParameter("id"));
		JobApplication ja = applicationDao.findByApplicationId(id);
		model.addAttribute("application", ja);
		return "applicantDetails";
	}
	
	@RequestMapping(value="/result.htm", method = RequestMethod.GET)
	protected String applicationResult(@ModelAttribute("jobApplication") JobApplication jobApplication, BindingResult result, HttpServletRequest request, Model model) throws AdException{
		String res = request.getParameter("val");
		int id = Integer.parseInt(request.getParameter("app"));
		
		JobApplication application = applicationDao.findByApplicationId(id);
		
		if(res.equals("approved")){
			applicationDao.updateStatus(id, "Approved");
		}
		else if(res.equals("rejected")){
			applicationDao.updateStatus(id, "Rejected");
		}
		else{
			applicationDao.updateStatus(id, "Pending");
		}
		return "redirect:/viewMyJobs.htm";
	}
	
	@RequestMapping(value="/viewAppliedJobs.htm", method = RequestMethod.GET)
	protected String appliedJobs(@ModelAttribute("jobApplication") JobApplication jobApplication, BindingResult result, HttpServletRequest request, Model model) throws AdException{
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		Person person = personDao.findByUsername(username);
		long personId = person.getPersonID();
		
		ArrayList<JobApplication> applications = applicationDao.findByApplicantId(personId);
		model.addAttribute("applications", applications);
		return "showAppliedJobs";
	}
	
	
}
	
	