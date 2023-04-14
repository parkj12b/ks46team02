package ks46team02.farm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import ks46team02.farm.dto.MMRegInfo;
import ks46team02.farm.service.MentorMenteeService;

@Controller
@RequestMapping("/farm")
public class FarmController {
	
	MentorMenteeService mentorMenteeService;
	
	public FarmController(MentorMenteeService mentorMenteeService) {
		this.mentorMenteeService = mentorMenteeService;
	}
	
	@GetMapping("/mentorMentee")
	public String getMentorMenteeView(){
		return "farm/mentorMenteeIntro";
	}
	
	@GetMapping("/mentorSignUp")
	public String getMentorSignUpForm(){
		return "farm/mentorSignUp";
	}
	
	@GetMapping("/menteeSignUp")
	public String getMenteeSignUpForm(){
		return "farm/menteeSignUp";
	}
	
	@GetMapping("/mentorMenteeRegisterStatus")
	public String getMentorMenteeRegisterStatus(HttpSession session, Model model) {
		String companyCode = (String) session.getAttribute("sessionCompanyCode");
		MMRegInfo mmRegInfo = mentorMenteeService.getMentorMenteeRegisterStatus(companyCode);
		model.addAttribute("mmRegInfo", mmRegInfo);
		return "farm/mentorMenteeRegisterStatus";
	}
	
}
