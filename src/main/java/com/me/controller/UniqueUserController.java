package com.me.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.dao.PersonDAOImpl;
import com.me.pojo.Person;

@Controller
@RequestMapping("/ajaxControllerVerifyUser.htm")
public class UniqueUserController {
	
	@Autowired
	@Qualifier("personDao")
	PersonDAOImpl personDao;

	@RequestMapping(method = RequestMethod.POST)
    public String doSubmitAction(HttpServletRequest hsr, HttpServletResponse response) throws Exception {

        /*    validator.validate(company, result);
            if (result.hasErrors()) {
                return "addUserForm";
            }  */
            HttpSession session = hsr.getSession();
            try {
                System.out.println("I am in JSON START");
                String name = hsr.getParameter("username");
                System.out.println(name);
                Person p = personDao.findByUsername(name);
                if(p == null){
                    JSONObject obj = new JSONObject();
                     obj.put("successmsg","Username Valid");
                     PrintWriter out = response.getWriter();
                     out.print(obj);
                    return null;
                } else {
                    JSONObject obj = new JSONObject();
                     obj.put("successmsg","Select Other User Name");
                     PrintWriter out = response.getWriter();
                     out.print(obj);
                    return null;
                }
                // DAO.close();
            } catch (Exception e) {
                JSONObject obj = new JSONObject();
                 obj.put("successmsg","Username Valid");
                 PrintWriter out = response.getWriter();
                 out.print(obj);
                System.out.println("Exception: " + e.getMessage());
                return null;
            }
            
        }

}

