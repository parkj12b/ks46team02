package ks46team02.farm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/farm")
public class FarmController {
	
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
	
}
