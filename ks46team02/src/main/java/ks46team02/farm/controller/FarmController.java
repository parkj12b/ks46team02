package ks46team02.farm.controller;

import ks46team02.farm.dto.*;
import ks46team02.farm.service.FarmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import ks46team02.farm.service.MentorMenteeService;

import java.util.List;

@Controller
@RequestMapping("/farm")
public class FarmController {
	
	MentorMenteeService mentorMenteeService;
	private final FarmService farmService;
	
	public FarmController(MentorMenteeService mentorMenteeService, FarmService farmService) {
		this.mentorMenteeService = mentorMenteeService;
		this.farmService = farmService;
	}
	@GetMapping("/feedList")
	public String getFeedList(Model model){
		List<Feed> feedList = farmService.getFeedList();
		model.addAttribute("title", "먹이 조회");
		model.addAttribute("feedList", feedList);
		return "farm/feedList";
	}

	@GetMapping("/productionList")
	public String getProductionList(Model model){
		List<Production> productionList = farmService.getProductionList();
		model.addAttribute("title", "생산량 목록");
		model.addAttribute("productionList", productionList);
		return "farm/productionList";
	}

	@GetMapping("/addFarm")
	public String addFarm(Model model){
		model.addAttribute("title", "사육장 등록");
		return "farm/addFarm";
	}

	@GetMapping("/addCage")
	public String addCage(Model model){
		model.addAttribute("title", "케이지 등록");
		return "farm/addCage";
	}

	@GetMapping("/addCycle")
	public String addCycle(Model model){
		model.addAttribute("title", "싸이클 등록");
		return "farm/addCycle";
	}

	@GetMapping("/cycleList")
	public String getCycleLIst(Model model){
		List<Cycle> cycleList = farmService.getCycleList();
		model.addAttribute("title", "싸이클 목록");
		model.addAttribute("cycleList", cycleList);
		return "farm/cycleList";
	}

	@GetMapping("/cageList")
	public String getCageList(Model model){
		List<Cage> cageList = farmService.getCageList();
		model.addAttribute("title", "케이지 목록");
		model.addAttribute("cageList", cageList);
		return "farm/cageList";
	}


	@GetMapping("/farmList")
	public String getFarmList(Model model){
		List<FarmInfo> farmList = farmService.getFarmList();
		model.addAttribute("title", "사육장 목록");
		model.addAttribute("farmList", farmList);
		return "farm/farmList";
	}

	@GetMapping("/farmStatusList")
	public String getFarmStatusList(Model model){
		List<FarmStatus> farmStatusListList = farmService.getFarmStatusList();
		model.addAttribute("title", "사육장 상태 정보");
		model.addAttribute("farmStatusListList", farmStatusListList);

		return "farm/farmStatusList";
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
