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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.me.dao.JobCategoryDAOImpl;
import com.me.pojo.JobCategory;

@Controller
public class CategoryController {
	
	@Autowired
	@Qualifier("categoryValidator")
	CategoryValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(validator);
	}
	
	@Autowired
	@Qualifier("categoryDao")
	JobCategoryDAOImpl categoryDao;

	@RequestMapping(value="/newCategory.htm", method = RequestMethod.GET)
	protected String NewCategoryForm(@ModelAttribute("jobCategory") JobCategory jobCategory, BindingResult result, HttpServletRequest request, ModelMap model){
		
		HttpSession session = request.getSession();
        if(session.getAttribute("username")==null){
            return "redirect:/signin.htm";
        }		
        
			return "addCategoryForm";
	}
	
	@RequestMapping(value="/viewCategory.htm", method = RequestMethod.GET)
	protected String viewCategory(@ModelAttribute("jobCategory") JobCategory jobCategory, BindingResult result, HttpServletRequest request, Model model) throws Exception{
		
		HttpSession session = request.getSession();
        if(session.getAttribute("username")==null){
            return "redirect:/signin.htm";
        }	
		
		ArrayList<JobCategory> categoryList = new ArrayList<JobCategory>();
		categoryList = categoryDao.findAll();
		
		System.out.println(categoryList.get(0).getCategoryName());
		
		model.addAttribute("list", categoryList);
		return "viewCategory";
	}
	
	@RequestMapping(value="/addCategory.htm", method=RequestMethod.POST)
	protected String addNewCategory(@ModelAttribute("jobCategory") JobCategory jobCategory, BindingResult result, HttpServletRequest request, ModelMap model){
		
		HttpSession session = request.getSession();
        if(session.getAttribute("username")==null){
            return "redirect:/signin.htm";
        }	
		
		validator.validate(jobCategory, result);
		if (result.hasErrors()) {
			return "addCategoryForm";
		}
		
		try {
			System.out.print("test");
			categoryDao.save(jobCategory);		
			System.out.println("Saved job category");
			return "categoryAdded";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";
	}
	
	
	
}
