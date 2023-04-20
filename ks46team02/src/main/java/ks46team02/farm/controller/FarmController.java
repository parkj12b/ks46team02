package ks46team02.farm.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import ks46team02.farm.dto.Cage;
import ks46team02.farm.dto.Cycle;
import ks46team02.farm.dto.FarmInfo;
import ks46team02.farm.dto.FarmStatus;
import ks46team02.farm.dto.Feed;
import ks46team02.farm.dto.MMContractInfo;
import ks46team02.farm.dto.MMRegInfoMentee;
import ks46team02.farm.dto.MMRegInfoMentor;
import ks46team02.farm.dto.Production;
import ks46team02.farm.service.FarmService;
import ks46team02.farm.service.MentorMenteeService;

@Controller
@RequestMapping("/farm")
public class FarmController {
	
	MentorMenteeService mentorMenteeService;
	private final FarmService farmService;
	
	
	private static final Logger log = LoggerFactory.getLogger(FarmController.class);

	
	public FarmController(MentorMenteeService mentorMenteeService, FarmService farmService) {
		this.mentorMenteeService = mentorMenteeService;
		this.farmService = farmService;
	}

	@GetMapping("/farmDetail")
	public  String getFarmDetail(Model model
								 ,@RequestParam(name="tapName", required = false) String tapName
								 ,@RequestParam(name="farmCode") String farmCode
								 ,@RequestParam(name="searchKey", required = false) String searchKey
								 ,@RequestParam(name="searchValue", required = false) String searchValue
								 ,@RequestParam(name="fromDate", required = false) String fromDate
								 ,@RequestParam(name="toDate", required = false) String toDate){
		FarmInfo farmInfo = farmService.getFarmInfoByCode(farmCode);
		List<Production> productionList = farmService.getProductionList(farmCode,searchKey,searchValue,fromDate,toDate);
		model.addAttribute("title","사육장 정보");
		model.addAttribute("farmInfo", farmInfo);
		model.addAttribute("productionList",productionList);
		model.addAttribute("farmCode", farmCode);
		model.addAttribute("tapName", tapName);
		log.info(farmCode);
		return "farm/farmDetail";
	}

	@GetMapping("/feedList")
	public String getFeedList(Model model){
		List<Feed> feedList = farmService.getFeedList();
		model.addAttribute("title", "먹이 조회");
		model.addAttribute("feedList", feedList);
		return "farm/feedList";
	}

	@GetMapping("/productionList")
	public String getAllProductionList(Model model,
									   HttpSession session){
		String companyCode =(String) session.getAttribute("sessionCompanyCode");
		List<Production> allProductionList = farmService.getAllProductionList(companyCode);
		model.addAttribute("title", "생산량 목록");
		model.addAttribute("allProductionList", allProductionList);
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
	public String getAllCycleList(Model model){
		List<Cycle> cycleList = farmService.getAllCycleList();
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
	public String getFarmList(Model model,
							  HttpSession session){
		String companyCode =(String) session.getAttribute("sessionCompanyCode");
		List<FarmInfo> farmList = farmService.getFarmList(companyCode);
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
	public String getMentorMenteeView(HttpSession session, Model model){
		String companyCode = (String) session.getAttribute("sessionCompanyCode");
		int mmRegType = mentorMenteeService.getMMRegType(companyCode);
		model.addAttribute(mmRegType);
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
		Map<String,Object> mmRegInfoMap = mentorMenteeService.getMentorMenteeRegisterStatus(companyCode);
		int mmRegType = mentorMenteeService.getMMRegType(companyCode);
		if(mmRegType == 1) {
			MMRegInfoMentor mmRegInfo = (MMRegInfoMentor) mmRegInfoMap.get("mmRegInfo");
			model.addAttribute("mmRegInfo", mmRegInfo);
			log.info("{}", mmRegInfo);
		} else if(mmRegType == 2) {
			MMRegInfoMentee mmRegInfo = (MMRegInfoMentee) mmRegInfoMap.get("mmRegInfo");
			model.addAttribute("mmRegInfo", mmRegInfo);
			log.info("{}", mmRegInfo);
		}
		model.addAttribute("mmRegType", mmRegType);
		
		return "farm/mentorMenteeRegisterStatus";
	}
	
	@GetMapping("/mentorMenteeContract")
	public String getMMContractList(Model model) {
		
		List<MMContractInfo> mmContractInfo = mentorMenteeService.getMMContractList("");
		model.addAttribute("mmContractInfo", mmContractInfo);
		return "farm/mmContractList";
	}
	
	@GetMapping("/mentorMenteeContractDetail")
	public String getMMContractDetail(Model model, @RequestParam(name="companyCode") String companyCode) {
		
		MMContractInfo mmContractInfo = mentorMenteeService.getMMContractList(companyCode).get(0);
		
		
		return "farm/mmContractDetail";
	}
	
	
}
