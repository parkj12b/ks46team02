package ks46team02.farm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import ks46team02.common.dto.AllContractInfo;
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

	/**
	 * 전체 사육장 케이지 검색 조회
	 * @param model
	 * @return
	 */
	@GetMapping("/cageList")
	public String getSearchCageList(Model model
			,@RequestParam(name="searchKey", required = false) String searchKey
			,@RequestParam(name="searchValue", required = false) String searchValue
			,HttpSession session){
		String companyCode =(String) session.getAttribute("sessionCompanyCode");
		List<Cage> cageList = farmService.getSearchCageList(companyCode,searchKey,searchValue);
		model.addAttribute("title", "케이지 목록");
		model.addAttribute("cageList", cageList);
		return "farm/cage_list";
	}



	/**
	 * 사육 장 등록
	 * @param model
	 * @return
	 */
	@GetMapping("/addFarm")
	public String addFarm(Model model){
		model.addAttribute("title", "사육장 등록");

		return "farm/add_farm";
	}



	/**
	 * 한 사육장 상태 조회
	 * @param model
	 * @return
	 */
	@GetMapping("/farmStatusList")
	public String getFarmStatusList(Model model
			,@RequestParam(name="farmCode") String farmCode){
		List<FarmStatus> farmStatusListList = farmService.getFarmStatusList(farmCode);
		model.addAttribute("title", "사육장 상태 정보");
		model.addAttribute("farmStatusListList", farmStatusListList);

		return "farm/farm_status_list";
	}


	/**
	 * 하나의 싸이클 먹이 조회
	 * @param model
	 * @param cycleCode
	 * @return
	 */

	@GetMapping("/feedList")
	public String getFeedListByCycleCode(Model model
			,@RequestParam(name="cycleCode") String cycleCode){
		List<Feed> feedList = farmService.getFeedListByCycleCode(cycleCode);
		model.addAttribute("title", "먹이 조회");
		model.addAttribute("feedList", feedList);
		return "farm/feed_list";
	}

	/**
	 * 전체 사육장 생산량 조회
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/productionList")
	public String getSearchProduction(Model model
			,HttpSession session
			,@RequestParam(name="searchKey", required = false) String searchKey
			,@RequestParam(name="searchValue", required = false) String searchValue
			,@RequestParam(name="fromDate", required = false) String fromDate
			,@RequestParam(name="toDate", required = false) String toDate){
		String companyCode =(String) session.getAttribute("sessionCompanyCode");

		List<Production> productionList = farmService.getSearchProduction(companyCode,searchKey,searchValue,fromDate,toDate);
		model.addAttribute("title", "생산량 목록");
		model.addAttribute("productionList", productionList);
		return "farm/production_list";
	}

	/**
	 * 한 사육장 세부 정보 (사육장 정보, 생산량, 싸이클)
	 * @return "farm/farm_detail"
	 */
	@GetMapping("/farmDetail")
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
		List<Production> productionList = farmService.getProductionByCode(farmCode);
		List<Cycle> cycleList = farmService.getSearchCycle(farmCode,companyCode,searchKey,searchValue,fromDate,toDate);

		model.addAttribute("title","사육장 정보");
		model.addAttribute("farmInfo", farmInfo);
		model.addAttribute("cycleList",cycleList);
		model.addAttribute("productionList",productionList);
		model.addAttribute("farmCode", farmCode);
		model.addAttribute("tapName", tapName);
		log.info(farmCode);
		return "farm/farm_detail";
	}


	/**
	 * 전체 사육장 리스트
	 * @param model
	 * @param session
	 * @return "farm/farm_list"
	 */
	@GetMapping("/farmList")
	public String getFarmList(Model model,
							  HttpSession session){
		String companyCode =(String) session.getAttribute("sessionCompanyCode");
		List<FarmInfo> farmList = farmService.getFarmList(companyCode);
		model.addAttribute("title", "사육장 목록");
		model.addAttribute("farmList", farmList);
		return "farm/farm_list";
	}




	// ========================= 멘토 멘티 ================================= //

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
		Map<String, String> keyValue = new HashMap<String,String>();
		keyValue.put("key1", "contractor_company_code");
		keyValue.put("value1", companyCode);
		keyValue.put("key2", "contract_type");
		keyValue.put("value2", "mentormentee");
		keyValue.put("key3", "contract_approval");
		keyValue.put("value3", "under review");

		List<AllContractInfo> contractList = mentorMenteeService.getMMContractListByKeyValue(keyValue);
		model.addAttribute("contractList",contractList);
		log.info("{}",contractList);
		return "farm/my_mm_contract_approve_list";
	}


}
