package com.ashu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ashu.binding.DashboardResponse;
import com.ashu.binding.EnquiryForm;
import com.ashu.entity.StudentEnqEntity;
import com.ashu.services.EnquiryServices;

@Controller
public class EnquiryController {

	@Autowired
	private HttpSession session;

	@Autowired
	private EnquiryServices enquiryServices;
	@Autowired
	private EnquiryServices enqService;

	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "index";
	}

	@GetMapping("/dashboard")
	public String showDashBoard(Model model) {

		Integer userId = (Integer) session.getAttribute("userId");
		DashboardResponse dashBoardData = enquiryServices.getdashboard(userId);

		model.addAttribute("dashBoardData", dashBoardData);

		return "dashboard";
	}

	@GetMapping("/enquiry")
	public String addEnquiryPage(Model model) {
		
		//TODO: load course Name 
		List<String> courses = enquiryServices.loadCourse();
		//TODO: load Enquiry Status 
		List<String> enquiry = enquiryServices.loadEnqStatus();
		
		EnquiryForm form = new EnquiryForm();
		
		//TODO send course name to UI by binding
		model.addAttribute("courseName", courses);
		
		//TODO send Enquiry Status to UI by Binding 
		model.addAttribute("enqStatus", enquiry);
		
		model.addAttribute("enqForm", form);
		
		return "add-enquiry";
	}

	
	@PostMapping("/enquiry")
	public String addEnquiry(@ModelAttribute("enqForm")  EnquiryForm form, Model model) {
		
		boolean status = enqService.upsertEnquires(form);
		
		if(status) {
			model.addAttribute("succMsg", "Student Enquiry Added to database");
		}
		else {
			model.addAttribute("errMsg", "Problem Occurs");
		}
		
		
		
		return "add-enquiry";
		
	}
	
	
	
	
	@GetMapping("/enquires")
	public String viewEnquiry(Model model) {
		
		List<StudentEnqEntity> enquries = enquiryServices.getEnquiry();
		model.addAttribute("enquries", enquries);
		return "view-enquiries";
	}

}
