package com.me.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
	@Qualifier("categoryDao")
	JobCategoryDAOImpl categoryDao;

	@RequestMapping(value="/newCategory.htm", method = RequestMethod.GET)
	protected String NewCategoryForm(@ModelAttribute("jobCategory") JobCategory jobCategory, BindingResult result, HttpServletRequest request, ModelMap model){
		return "addCategoryForm";
	}	
	
	@RequestMapping(value="/addCategory.htm", method=RequestMethod.POST)
	protected String addNewCategory(@ModelAttribute("jobCategory") JobCategory jobCategory, BindingResult result, HttpServletRequest request, ModelMap model){
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
