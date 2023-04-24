package ks46team02.farm.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import ks46team02.company.dto.AllContractInfo;
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
	


	@RequestMapping(value = "/farmDetail", method = {RequestMethod.GET, RequestMethod.POST})
	public String getFarmDetail(Model model
								 ,@RequestParam(name="tapName", required = false) String tapName
								 ,@RequestParam(name="farmCode") String farmCode
								 ,@RequestParam(name="searchKey", required = false) String searchKey
								 ,@RequestParam(name="searchValue", required = false) String searchValue
								 ,@RequestParam(name="fromDate", required = false) String fromDate
								 ,@RequestParam(name="toDate", required = false) String toDate
								 ,HttpSession session){
		String companyCode =(String) session.getAttribute("sessionCompanyCode");
		FarmInfo farmInfo = farmService.getFarmInfoByCode(farmCode);
		List<Cycle> cycleList = farmService.getCycleList(farmCode,companyCode);
		List<Production> productionList = farmService.getProductionList(farmCode,searchKey,searchValue,fromDate,toDate);
		model.addAttribute("title","사육장 정보");
		model.addAttribute("farmInfo", farmInfo);
		model.addAttribute("cycleList",cycleList);
		model.addAttribute("productionList",productionList);
		model.addAttribute("farmCode", farmCode);
		model.addAttribute("tapName", tapName);
		log.info(farmCode);
		return "farm/farm_detail";
	}

	@GetMapping("/feedList")
	public String getFeedList(Model model
							,@RequestParam(name="cycleCode") String cycleCode){
		List<Feed> feedList = farmService.getFeedList(cycleCode);
		model.addAttribute("title", "먹이 조회");
		model.addAttribute("feedList", feedList);
		return "farm/feed_list";
	}

	@GetMapping("/productionList")
	public String getAllProductionList(Model model,
									   HttpSession session){
		String companyCode =(String) session.getAttribute("sessionCompanyCode");
		List<Production> allProductionList = farmService.getAllProductionList(companyCode);
		model.addAttribute("title", "생산량 목록");
		model.addAttribute("allProductionList", allProductionList);
		return "farm/production_list";
	}

	@GetMapping("/addFarm")
	public String addFarm(Model model){
		model.addAttribute("title", "사육장 등록");
		return "farm/add_farm";
	}

	@GetMapping("/addCage")
	public String addCage(Model model){
		model.addAttribute("title", "케이지 등록");
		return "farm/add_cage";
	}

	@GetMapping("/addCycle")
	public String addCycle(Model model){
		model.addAttribute("title", "싸이클 등록");
		return "farm/add_cycle";
	}

	@GetMapping("/cycleList")
	public String getAllCycleList(Model model){
		List<Cycle> cycleList = farmService.getAllCycleList();
		model.addAttribute("title", "싸이클 목록");
		model.addAttribute("cycleList", cycleList);
		return "farm/cycle_list";
	}

	@GetMapping("/cageList")
	public String getCageList(Model model){
		List<Cage> cageList = farmService.getCageList();
		model.addAttribute("title", "케이지 목록");
		model.addAttribute("cageList", cageList);
		return "farm/cage_list";
	}


	@GetMapping("/farmList")
	public String getFarmList(Model model,
							  HttpSession session){
		String companyCode =(String) session.getAttribute("sessionCompanyCode");
		List<FarmInfo> farmList = farmService.getFarmList(companyCode);
		model.addAttribute("title", "사육장 목록");
		model.addAttribute("farmList", farmList);
		return "farm/farm_list";
	}

	@GetMapping("/farmStatusList")
	public String getFarmStatusList(Model model){
		List<FarmStatus> farmStatusListList = farmService.getFarmStatusList();
		model.addAttribute("title", "사육장 상태 정보");
		model.addAttribute("farmStatusListList", farmStatusListList);

		return "farm/farm_status_list";
	}
	
	@GetMapping("/mentorMentee")
	public String getMentorMenteeView(HttpSession session, Model model){
		String companyCode = (String) session.getAttribute("sessionCompanyCode");
		int mmRegType = mentorMenteeService.getMMRegType(companyCode);
		model.addAttribute("mmRegType",mmRegType);
		log.info("{}",mmRegType);
		return "farm/mentor_mentee_intro";
	}
	
	@GetMapping("/mentorSignUp")
	public String getMentorSignUpForm(){
		return "farm/mentor_sign_up";
	}
	
	@GetMapping("/menteeSignUp")
	public String getMenteeSignUpForm(){
		return "farm/mentee_sign_up";
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
		
		return "farm/mentor_mentee_register_status";
	}
	
	@GetMapping("/mentorMenteeContract")
	public String getMMContractList(Model model) {
		String searchKey = "company_code";
		List<MMContractInfo> mmContractInfo = mentorMenteeService.getMMContractList(searchKey,"");
		model.addAttribute("mmContractInfo", mmContractInfo);
		return "farm/mm_contract_list";
	}
	
	@GetMapping("/mentorMenteeContractDetail")
	public String getMMContractDetail(Model model, @RequestParam(name="mentorContractRegCode") String mentorContractRegCode) {
		String searchKey = "mentor_contract_reg_code";
		MMContractInfo mmContractInfo = mentorMenteeService.getMMContractList(searchKey, mentorContractRegCode).get(0);
		log.info("{}", mmContractInfo);
		model.addAttribute("mmContractInfo",mmContractInfo);
		
		return "farm/mm_contract_detail";
	}
	
	@GetMapping("/myMentorMenteeContract")
	public String getMMContractListMentor(Model model, HttpSession session) {
		String companyCode = (String)session.getAttribute("sessionCompanyCode");
		List<MMContractInfo> mmContractInfo = mentorMenteeService.getMMContractList("company_code", companyCode);
		model.addAttribute("mmContractInfo",mmContractInfo);
		return "farm/my_mm_contract_list";
	}
	
	@GetMapping("/mentorMenteeContractModify")
	public String getMMContractModify(Model model, @RequestParam(name="mentorContractRegCode") String mentorContractRegCode) {
		
		MMContractInfo mmContractInfo = mentorMenteeService.getMMContractList("mentor_contract_reg_code", mentorContractRegCode).get(0);
		log.info("{}", mmContractInfo);
		model.addAttribute("mmContractInfo",mmContractInfo);
		
		return "farm/mm_contract_modify";
	}
	//미구현
	@PostMapping("/mmContractModifyAction")
	public String setMMContractModify(MMContractInfo contractInfo) {
		
		log.info("{}", contractInfo);
		return "redirect:/farm/myMentorMenteeContract";
	}
	
	@GetMapping("/registerMentorMenteeContract")
	public String addMMContractRegister() {
		
		
		return "farm/mm_contract_register";
	}
	
	@GetMapping("/mentorMenteeContractApprove")
	public String mentorMenteeContractApprove(Model model, HttpSession session) {
		
		String companyCode = (String) session.getAttribute("sessionCompanyCode");
		List<AllContractInfo> contractList = mentorMenteeService.getMMContractApproveList("contractor_company_code", companyCode);
		model.addAttribute("contractList",contractList);
		log.info("{}",contractList);
		return "farm/my_mm_contract_approve_list";
	}
	
}
