package ks46team02.farm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import ks46team02.admin.service.MemberService;
import ks46team02.common.dto.AllContractInfo;
import ks46team02.common.dto.Member;
import ks46team02.common.service.FileService;
import ks46team02.farm.dto.Cage;
import ks46team02.farm.dto.Cycle;
import ks46team02.farm.dto.EvaluationLargeCategory;
import ks46team02.farm.dto.EvaluationStandard;
import ks46team02.farm.dto.FarmInfo;
import ks46team02.farm.dto.FarmStatus;
import ks46team02.farm.dto.Feed;
import ks46team02.farm.dto.GoogleFormResponse;
import ks46team02.farm.dto.GoogleFormResult;
import ks46team02.farm.dto.MMContractInfo;
import ks46team02.farm.dto.MMRegInfoMentee;
import ks46team02.farm.dto.MMRegInfoMentor;
import ks46team02.farm.dto.MentorFeedbackToken;
import ks46team02.farm.dto.Production;
import ks46team02.farm.dto.ResultHistory;
import ks46team02.farm.dto.VisitHistory;
import ks46team02.farm.mapper.FarmMapper;
import ks46team02.farm.service.FarmService;
import ks46team02.farm.service.MentorMenteeService;

@Controller
@RequestMapping("/farm")
public class FarmController {

	@Value("${files.path}")
	private String filePath;
	
	MentorMenteeService mentorMenteeService;
	private final FarmService farmService;
	private final MemberService memberService;
	private final FarmMapper farmMapper;
	private FileService fileService;
	
	private static final Logger log = LoggerFactory.getLogger(FarmController.class);


	public FarmController(MentorMenteeService mentorMenteeService
						,FarmService farmService
						,MemberService memberService
						,FarmMapper farmMapper
						,FileService fileService){
		this.mentorMenteeService = mentorMenteeService;
		this.farmService = farmService;
		this.memberService = memberService;
		this.farmMapper = farmMapper;
		this.fileService = fileService;
	}

	/**
	 * 먹이 급여량 등록
	 */
	@PostMapping("/addFeed")
	@ResponseBody
	public Feed addFeed(Feed feed
						,HttpSession session) {
		String memberId = (String) session.getAttribute("sessionId");
		String companyCode = (String) session.getAttribute("sessionCompanyCode");

		feed.setCompanyCode(companyCode);
		feed.setMemberId(memberId);
		log.info("화면에서 전달받은 데이터 : {}", feed);
		return farmService.addFeed(feed);
	}
	/**
	 * 생산량 그래프
	 */
	@GetMapping("/productionGraph")
	@ResponseBody
	public List<Production> getProductionGraph(String farmCode){
		List<Production> Production = farmService.getProductionGraph(farmCode);
		return Production;
	}
	/**
	 * 먹이 급여 그래프
	 */
	@GetMapping("/feedGraph")
	@ResponseBody
	public List<Feed> getFeedGraph(String cycleCode){
		List<Feed> feed = farmService.getFeedGraph(cycleCode);
		return feed;
	}

	/**
	 * 케이지 수정
	 */
	@PostMapping("/modifyCage")
	public String modifyCage(Cage cage
							,HttpSession session){
		String memberId = (String) session.getAttribute("sessionId");
		String companyCode = (String) session.getAttribute("sessionCompanyCode");
		cage.setCompanyCode(companyCode);
		cage.setMemberId(memberId);
		log.info("화면에서 전달받은 데이터 : {}", cage);
		farmService.modifyCage(cage);
		return "redirect:/farm/cageList";
	}
	@GetMapping("/modifyCage")
	public String modifyCage(Model model
							,@RequestParam(name="cageCode")String cageCode){
		Cage cage = farmMapper.getCageByCode(cageCode);
		model.addAttribute("title","케이지 수정");
		model.addAttribute("cage",cage);

		return "farm/modify_cage";
	}
	/**
	 * 싸이클 수정
	 */
	@PostMapping("/modifyCycle")
	public String modifyCycle(Cycle cycle
							,RedirectAttributes reAttr
							,HttpSession session){
		String memberId = (String) session.getAttribute("sessionId");
		String companyCode = (String) session.getAttribute("sessionCompanyCode");
		cycle.setCompanyCode(companyCode);
		cycle.setMemberId(memberId);
		String farmCode = cycle.getFarmCode();
		String tapName = "cycle";
		reAttr.addAttribute("farmCode", farmCode);
		reAttr.addAttribute("tapName", tapName);
		farmService.modifyCycle(cycle);

		return "redirect:/farm/farmDetail";
	}
	@GetMapping("/modifyCycle")
	public String modifyCycle(Model model
							,@RequestParam(name="cycleCode") String cycleCode){

		Cycle cycle = farmMapper.getCycleByCode(cycleCode);
		String cageCode = cycle.getCageCode();
		Cage cage = farmMapper.getCageByCode(cageCode);
		model.addAttribute("title","싸이클 수정");
		model.addAttribute("cycle",cycle);
		model.addAttribute("cage",cage);
		return "farm/modify_cycle";
	}


	/**
	 * 생산량 수정
	 */
	@PostMapping("/modifyProduction")
	public String modifyProduction(Production production
									,HttpSession session){
		String memberId = (String) session.getAttribute("sessionId");
		String companyCode = (String) session.getAttribute("sessionCompanyCode");
		production.setCompanyCode(companyCode);
		production.setMemberId(memberId);
		farmService.modifyProduction(production);

		return "redirect:/farm/productionList";
	}
	@GetMapping("/modifyProduction")
	public String modifyProduction(Model model
									,@RequestParam(name="productionCode")String productionCode){
		log.info("productionCode : {}", productionCode);
		Production production = farmMapper.getProductionByPCode(productionCode);
		model.addAttribute("title","생산량 수정");
		model.addAttribute("production",production);
		return "farm/modify_production";

	}


	/**
	 * 사육장 수정
	 */
	@PostMapping("/modifyFarm")
	public String modifyFarm(FarmInfo farmInfo
							,HttpSession session){
		String memberId = (String) session.getAttribute("sessionId");
		String companyCode = (String) session.getAttribute("sessionCompanyCode");
		farmInfo.setMemberId(memberId);
		farmInfo.setCompanyCode(companyCode);
		farmService.modifyFarm(farmInfo);
		log.info("화면에서 전달받은 데이터 : {}", farmInfo);
		return "redirect:/farm/farmList";
	}
	@GetMapping("/modifyFarm")
	public String modifyFarm(Model model
							,@RequestParam(name="farmCode")String farmCode){
		FarmInfo farmInfo = farmService.getFarmInfoByCode(farmCode);
		model.addAttribute("title","사육장 수정");
		model.addAttribute("farmInfo",farmInfo);
		return "farm/modify_farm";
	}
	/**
	 * 모달창 싸이클 정보 가져오기
	 */
	@GetMapping("/getCycleInfo")
	@ResponseBody
	public Cycle getCycleInfo(@RequestParam("cycleCode") String cycleCode) {
		Cycle cycle = farmMapper.getCycleByCode(cycleCode);
		return cycle;
	}



	/**
	 * 생산량 등록
	 */
	@PostMapping("/addProduction")
	public String addProduction(@RequestParam(name="cycleCode")String cycleCode
								,@RequestParam(name="realProduction")double realProduction
								,@RequestParam(name="realHarvestDay")String realHarvestDay
								,HttpSession session){
		String memberId = (String) session.getAttribute("sessionId");
		String companyCode = (String) session.getAttribute("sessionCompanyCode");

		Production production = new Production();
		production.setCompanyCode(companyCode);
		production.setMemberId(memberId);
		production.setRealProduction(realProduction);
		production.setExpectedCageProductionCode(cycleCode);
		production.setRealHarvestDay(realHarvestDay);
		farmService.addProduction(production);
		return "redirect:/farm/productionList";
	}
	/**
	 * 케이지 등록
	 */
	@PostMapping("/addCage")
	public String addCage(Cage cage
						,HttpSession session){
		log.info("화면에서 전달받은 데이터 : {}", cage);
		String companyCode = (String) session.getAttribute("sessionCompanyCode");
		String memberId = (String) session.getAttribute("sessionId");
		cage.setCompanyCode(companyCode);
		cage.setMemberId(memberId);
		farmService.addCage(cage);
		return "redirect:/farm/cageList";
	}
	@GetMapping("/addCage")
	public String addCage(Model model
						,HttpSession session){
		String companyCode = (String) session.getAttribute("sessionCompanyCode");
		List<FarmInfo> farmList = farmService.getFarmList(companyCode);
		model.addAttribute("title","케이지 등록");
		model.addAttribute("farmList", farmList);

		return "farm/add_cage";
	}


	/**
	 * 모달 창 케이지 조회
	 */
	@GetMapping("/cages")
	@ResponseBody
	public Cage getCageByCode(@RequestParam(name = "cageCode") String cageCode) {
	    log.info("getCageByCode() method called with cageCode: {}", cageCode);
	    Cage cage = farmService.getCageByCode(cageCode);
	    log.info("cage found: {}", cage);
	    return cage;
	}

	/**
	 * 하나의 사육장 싸이클 등록
	 */
	@PostMapping("/addCycle")
	public String addCycle(Cycle cycle
						,RedirectAttributes reAttr
						,HttpSession session){
		String companyCode = (String) session.getAttribute("sessionCompanyCode");
		String memberId = (String) session.getAttribute("sessionId");
		cycle.setCompanyCode(companyCode);
		cycle.setMemberId(memberId);
		String farmCode = cycle.getFarmCode();
		String tapName = "cycle";
		reAttr.addAttribute("farmCode", farmCode);
		reAttr.addAttribute("tapName", tapName);
		log.info("화면에서 전달받은 데이터 : {}", cycle);
		farmService.addCycle(cycle);
		return "redirect:/farm/farmDetail";
	}
	@GetMapping("/addCycle")
	public String addCycle(Model model
							,@RequestParam(name="farmCode") String farmCode) {
		List<Cage> cageList = farmService.getCageListByCode(farmCode);
		model.addAttribute("title", "싸이클 등록");
		model.addAttribute("cageList", cageList);
		model.addAttribute("farmCode",farmCode);
		return "farm/add_cycle";
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
	 * 등록 화면 이랑 등록
	 */
	@PostMapping("/addFarm")
	public String addFarm(FarmInfo farmInfo
						,Model model
						,HttpSession session
						,@RequestParam(name="password")String password) {

		String companyCode = (String) session.getAttribute("sessionCompanyCode");
		String memberId = (String) session.getAttribute("sessionId");
		Member memberInfo = memberService.getMemberInfoById(memberId);
		String memberPw = memberInfo.getMemberPw();
		if(password.equals(memberPw)){
			farmInfo.setMemberId(memberId);
			farmInfo.setCompanyCode(companyCode);
			farmService.addFarm(farmInfo);
			log.info("화면에서 전달받은 데이터 : {}", farmInfo);
			return "redirect:/farm/farmList";
		}else{
			model.addAttribute("errorMsg", "비밀번호가 일치하지 않습니다.");
			return "farm/add_farm";
		}

	}

	@GetMapping("/addFarm")
	public String addFarm(Model model){
		model.addAttribute("title", "사육장 등록");
		return "farm/add_farm";
	}

	/**
	 * 한 사육장 상태 조회
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
	 */

	@GetMapping("/feedList")
	public String getFeedListByCycleCode(Model model
			,@RequestParam(name="cycleCode") String cycleCode){
		List<Feed> feedList = farmService.getFeedListByCycleCode(cycleCode);
		model.addAttribute("title", "먹이 조회");
		model.addAttribute("feedList", feedList);
		model.addAttribute("cycleCode", cycleCode);
		return "farm/feed_list";
	}

	/**
	 * 전체 사육장 생산량 조회
	 */
	@GetMapping("/productionList")
	public String getSearchProduction(Model model
			,HttpSession session
			,@RequestParam(name="searchKey", required = false) String searchKey
			,@RequestParam(name="searchValue", required = false) String searchValue
			,@RequestParam(name="fromDate", required = false) String fromDate
			,@RequestParam(name="toDate", required = false) String toDate){
		String companyCode =(String) session.getAttribute("sessionCompanyCode");

		List<Cycle> cycleList = farmMapper.getCycleListByCompanyCode(companyCode);
		List<Production> productionList = farmService.getSearchProduction(companyCode,searchKey,searchValue,fromDate,toDate);
		model.addAttribute("title", "생산량 목록");
		model.addAttribute("productionList", productionList);
		model.addAttribute("cycleList", cycleList);
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

	//멘토멘티 신청
	@GetMapping("/mentorMentee")
	public String getMentorMenteeView(HttpSession session, Model model){
		String companyCode = (String) session.getAttribute("sessionCompanyCode");
		int mmRegType = mentorMenteeService.getMMRegType(companyCode);
		boolean isApply = mentorMenteeService.mentorMenteeIsApply(companyCode);
		model.addAttribute("mmRegType",mmRegType);
		model.addAttribute("isApply", isApply);
		model.addAttribute("title", "멘토멘티 신청");
		log.info("{}",mmRegType);
		return "farm/mentor_mentee_intro";
	}

	//멘토 신청
	@GetMapping("/mentorSignUp")
	public String getMentorSignUpForm(Model model){
		model.addAttribute("title","멘토멘티 멘토 신청");
		return "farm/mentor_sign_up";
	}

	//멘티 신청
	@GetMapping("/menteeSignUp")
	public String getMenteeSignUpForm(Model model){
		model.addAttribute("title", "멘토멘티 멘티 신청");
		return "farm/mentee_sign_up";
	}

	//멘토멘티 승인여부 조회
	@GetMapping("/mentorMenteeRegisterStatus")
	public String getMentorMenteeRegisterStatus(HttpSession session, Model model) {
		String companyCode = (String) session.getAttribute("sessionCompanyCode");
		Map<String,Object> mmRegInfoMap = mentorMenteeService.getMentorMenteeRegisterStatus(companyCode);
		int mmRegType = (int) mmRegInfoMap.get("mmRegType");
		
		if(mmRegType == 1) {
			MMRegInfoMentor mmRegInfo = (MMRegInfoMentor) mmRegInfoMap.get("mmRegInfo");			
			model.addAttribute("mmRegInfo", mmRegInfo);
		} else if(mmRegType == 2) {
			MMRegInfoMentee mmRegInfo = (MMRegInfoMentee) mmRegInfoMap.get("mmRegInfo");			
			model.addAttribute("mmRegInfo", mmRegInfo);
		}
		
		model.addAttribute("mmRegType", mmRegType);
		model.addAttribute("title", "멘토멘티 승인여부 조회");
		
		return "farm/mentor_mentee_register_status";
	}

	//멘토멘티 공고 조회
	@GetMapping("/mentorMenteeContract")
	public String getMMContractList(Model model) {
		String searchKey = "company_code";
		List<MMContractInfo> mmContractInfo = mentorMenteeService.getMMContractList(searchKey,"");
		model.addAttribute("mmContractInfo", mmContractInfo);
		model.addAttribute("title", "멘토멘티 공고 조회");
		
		return "farm/mm_contract_list";
	}

	//멘토멘티 공고 상세정보 조회
	@GetMapping("/mentorMenteeContractDetail")
	public String getMMContractDetail(Model model, @RequestParam(name="mentorContractRegCode") String mentorContractRegCode) {
		String searchKey = "mentor_contract_reg_code";
		MMContractInfo mmContractInfo = mentorMenteeService.getMMContractList(searchKey, mentorContractRegCode).get(0);
		log.info("{}", mmContractInfo);
		model.addAttribute("mmContractInfo",mmContractInfo);
		model.addAttribute("title", "멘토멘티 공고 상세");

		return "farm/mm_contract_detail";
	}

	//나의 멘토멘티 공고 조회
	@GetMapping("/myMentorMenteeContractMentor")
	public String getMMContractListMentor(Model model, HttpSession session) {
		String companyCode = (String)session.getAttribute("sessionCompanyCode");
		List<MMContractInfo> mmContractInfo = mentorMenteeService.getMMContractList("company_code", companyCode);
		model.addAttribute("mmContractInfo",mmContractInfo);
		model.addAttribute("title", "나의 멘토멘티 공고 조회");
		
		log.info("{}", mmContractInfo);
		return "farm/my_mm_contract_list_mentor";
	}

	//나의 멘티 계약 조회 멘티
	@GetMapping("/myMentorMenteeContractMentee")
	public String getMMContractListMentee(Model model, HttpSession session) {
		String companyCode = (String)session.getAttribute("sessionCompanyCode");

		Map<String, String> paramMap = new HashMap<String,String>();
		paramMap.put("contractee_company_code", companyCode);
		paramMap.put("contract_type", "mentormentee");
		paramMap.put("contract_approval", "approve");

		Set<String> keySet = paramMap.keySet();
		List<Map<String, Object>> searchList = new ArrayList<>();

		for(String key : keySet) {
			Map<String, Object> map = new HashMap<>();
			map.put("key", key);
			map.put("value", paramMap.get(key));
			searchList.add(map);
		}

		AllContractInfo mmContractInfo = mentorMenteeService.getMMContractByKeyValue(searchList);


		if(mmContractInfo == null) {
			return "farm/my_mm_contract_mentee";
		}
		Map<String,Object> visitHistoryInfo = mentorMenteeService.getVisitHistoryInfo(mmContractInfo.getContractCode());
		List<VisitHistory> visitHistoryList = (List<VisitHistory>) visitHistoryInfo.get("visitHistoryList");
		int numComplete = (int)visitHistoryInfo.get("numComplete");
		int totalVisit = (int)visitHistoryList.size();

		int contractDays = mmContractInfo.getContractDays();
		int daysLeft = mmContractInfo.getDaysLeft();

		if(daysLeft <= 0) {
			daysLeft = 0;
		}

		double contractPercentDone = ((double) (contractDays-daysLeft)*100)/contractDays;
		double widthVisitBar = (double) 1/totalVisit*100;

		log.info("num={}",contractPercentDone);
		log.info("num={}",contractDays);
		log.info("num={}",daysLeft);
		log.info("numComplete={}",numComplete);
		log.info("totalVisit={}",totalVisit);
		log.info("widthVisitBar={}",widthVisitBar);
		model.addAttribute("mmContractInfo",mmContractInfo);
		model.addAttribute("contractPercentDone", contractPercentDone);
		model.addAttribute("visitHistoryList", visitHistoryList);
		model.addAttribute("numComplete", numComplete);
		model.addAttribute("totalVisit", totalVisit);
		model.addAttribute("widthVisitBar", widthVisitBar);
		model.addAttribute("title", "나의 멘토 계약 조회");
		
		return "farm/my_mm_contract_mentee";
	}

	//나의 등록 계약 수정
	@GetMapping("/mentorMenteeContractModify")
	public String getMMContractModify(Model model, @RequestParam(name="mentorContractRegCode") String mentorContractRegCode) {

		MMContractInfo mmContractInfo = mentorMenteeService.getMMContractList("mentor_contract_reg_code", mentorContractRegCode).get(0);
		log.info("{}", mmContractInfo);
		model.addAttribute("mmContractInfo",mmContractInfo);
		model.addAttribute("title", "나의 등록 계약 수정");
		
		return "farm/mm_contract_modify";
	}
	//미구현
	@PostMapping("/mmContractModifyAction")
	public String setMMContractModify(MMContractInfo contractInfo) {

		log.info("{}", contractInfo);
		
		return "redirect:/farm/myMentorMenteeContract";
	}

	//멘토멘티 공고 등록
	@GetMapping("/registerMentorMenteeContract")
	public String addMMContractRegister(Model model) {

		model.addAttribute("title", "멘토멘티 공고 등록");
		return "farm/mm_contract_register";
	}

	//승인대기 멘토멘티 계약 조회
	@GetMapping("/mentorMenteeContractApprove")
	public String mentorMenteeContractApprove(Model model, HttpSession session) {

		String companyCode = (String) session.getAttribute("sessionCompanyCode");

		Map<String, String> paramMap = new HashMap<String,String>();
		paramMap.put("contractor_company_code", companyCode);
		paramMap.put("contract_type", "mentormentee");
		paramMap.put("contract_approval", "under_review");

		Set<String> keySet = paramMap.keySet();
		List<Map<String, Object>> searchList = new ArrayList<>();

		for(String key : keySet) {
			Map<String, Object> map = new HashMap<>();
			map.put("key", key);
			map.put("value", paramMap.get(key));
			searchList.add(map);
		}


		List<AllContractInfo> contractList = mentorMenteeService.getMMContractListByKeyValue(searchList);

		model.addAttribute("contractList",contractList);
		model.addAttribute("title", "승인대기 멘토멘티 계약조회"); 
		
		log.info("{}",contractList);
		return "farm/my_mm_contract_approve_list";
	}

	//방문평가 조회
	@GetMapping("/mentorMenteeFeedbackMentee")
	public String mentorMenteeFeedbackMentee(Model model, HttpSession session, @RequestParam(name="contractCode", required=false) String contractCode) {

		AllContractInfo contractInfo;
		List<Map<String, Object>> searchList = new ArrayList<>();

		if(contractCode == null) {
			String companyCode = (String) session.getAttribute("sessionCompanyCode");
			Map<String, String> paramMap = new HashMap<String,String>();
			paramMap.put("contractee_company_code", companyCode);
			paramMap.put("contract_type", "mentormentee");
			paramMap.put("contract_approval", "approve");

			Set<String> keySet = paramMap.keySet();


			for(String key : keySet) {
				Map<String, Object> map = new HashMap<>();
				map.put("key", key);
				map.put("value", paramMap.get(key));
				searchList.add(map);
			}
		}

		contractInfo = mentorMenteeService.getMMContractByKeyValue(searchList);
		contractCode = contractInfo.getContractCode();
		List<EvaluationStandard> evaluationStandardList = mentorMenteeService.getEvaluationStandardList();

		Map<String,Object> visitHistoryInfo = mentorMenteeService.getVisitHistoryInfo(contractCode);
		List<VisitHistory> visitHistoryList = (List<VisitHistory>) visitHistoryInfo.get("visitHistoryList");

		model.addAttribute("visitHistoryList",visitHistoryList);
		model.addAttribute("evaluationStandard", evaluationStandardList);
		model.addAttribute("title", "방문평가 조회");
		
		return "farm/mm_feedback_mentee";
	}

	//멘토멘티 방문평가 상세정보 조회
	@GetMapping("/mmFeedbackMenteeDetail")
	public String mentorMenteeFeedbackDetail(Model model, @RequestParam(name="visitCode") String visitCode) {


		List<ResultHistory> resultHistoryList = mentorMenteeService.getResultHistoryList(visitCode);
		log.info("resultHistoryList={}",resultHistoryList);
		ResultHistory resultHistory = null;


		List<EvaluationStandard> evaluationStandardList = mentorMenteeService.getEvaluationStandardList();
		VisitHistory visitHistory = mentorMenteeService.getVisitHistoryByVisitCode(visitCode);

		int maxScore = visitHistory.getTotalDetailItemNum()*3;
		int visitScore = visitHistory.getTotalScoreVisit();

		double feedbackPercent = (double) visitScore/maxScore*100;


		model.addAttribute("feedbackPercent",feedbackPercent);
		model.addAttribute("visitHistory",visitHistory);
		model.addAttribute("resultHistoryList",resultHistoryList);
		model.addAttribute("evaluationStandard", evaluationStandardList);
		model.addAttribute("title", "멘토멘티 방문평가 상세정보");

		return "farm/mm_feedback_mentee_detail";
	}
	
	//멘토멘티 멘티 계약 접수
	@PostMapping("/mmRegisterAction")
	@ResponseBody
	public Map<String,Object> mentorMenteeRegisterAction(HttpSession session, AllContractInfo allContractInfo) {

		boolean isValid = false;
		String memberId = (String) session.getAttribute("sessionId");
		String sessionCompanyCode = (String) session.getAttribute("sessionCompanyCode");
		boolean isRegisterValid = mentorMenteeService.isRegisterValid(sessionCompanyCode);
		Integer mmRegType = (Integer) session.getAttribute("mmRegType");
		allContractInfo.setMemberId(memberId);
		allContractInfo.setContracteeCompanyCode(sessionCompanyCode);
		
		
		
		Map<String,Object> map = new HashMap<String,Object>();

		if(mmRegType != 2) {
			map.put("msg", "멘토멘티 권한이 없습니다.");
		} else if(!isRegisterValid) {
			map.put("msg", "신청하기전 기존계약이 있습니다.");
		} else {
			map = mentorMenteeService.addMMContractReg(allContractInfo);
			isValid = true;
		}
		map.put("isValid", isValid);

		return map;
	}

	//나의 멘토멘티 계약 멘티 목록 조회
	@GetMapping("/mmMyMenteeList")
	public String mentorMenteeMyMenteeList(HttpSession session, Model model) {

		String companyCode = (String)session.getAttribute("sessionCompanyCode");

		Map<String, String> paramMap = new HashMap<String,String>();
		paramMap.put("contractor_company_code", companyCode);
		paramMap.put("contract_type", "mentormentee");
		paramMap.put("contract_approval", "approve");

		Set<String> keySet = paramMap.keySet();
		List<Map<String, Object>> searchList = new ArrayList<>();

		for(String key : keySet) {
			Map<String, Object> map = new HashMap<>();
			map.put("key", key);
			map.put("value", paramMap.get(key));
			searchList.add(map);
		}

		List<AllContractInfo> mmContractInfo = mentorMenteeService.getMMContractListByKeyValue(searchList);

		model.addAttribute("mmContractInfo", mmContractInfo);
		model.addAttribute("title", "나의 멘토멘티 계약");
		return "farm/my_mm_contract_mentor";
	}

	//멘티 목록 조회 상세정보 조회
	@GetMapping("/myMenteeListDetail")
	public String myMentorMenteeDetail(HttpSession session, Model model, @RequestParam(name="companyCode") String companyCode) {

		Map<String, String> paramMap = new HashMap<String,String>();
		paramMap.put("contractee_company_code", companyCode);
		paramMap.put("contract_type", "mentormentee");
		paramMap.put("contract_approval", "approve");

		Set<String> keySet = paramMap.keySet();
		List<Map<String, Object>> searchList = new ArrayList<>();

		for(String key : keySet) {
			Map<String, Object> map = new HashMap<>();
			map.put("key", key);
			map.put("value", paramMap.get(key));
			searchList.add(map);
		}

		AllContractInfo mmContractInfo = mentorMenteeService.getMMContractByKeyValue(searchList);

		Map<String,Object> visitHistoryInfo = mentorMenteeService.getVisitHistoryInfo(mmContractInfo.getContractCode());
		List<VisitHistory> visitHistoryList = (List<VisitHistory>) visitHistoryInfo.get("visitHistoryList");
		int numComplete = (int)visitHistoryInfo.get("numComplete");
		int totalVisit = (int)visitHistoryList.size();

		int contractDays = mmContractInfo.getContractDays();
		int daysLeft = mmContractInfo.getDaysLeft();

		if(daysLeft <= 0) {
			daysLeft = 0;
		}

		double contractPercentDone = ((double) (contractDays-daysLeft)*100)/contractDays;
		double widthVisitBar = (double) 1/totalVisit*100;

		log.info("num={}",contractPercentDone);
		log.info("num={}",contractDays);
		log.info("num={}",daysLeft);
		log.info("numComplete={}",numComplete);
		log.info("totalVisit={}",totalVisit);
		log.info("widthVisitBar={}",widthVisitBar);
		model.addAttribute("mmContractInfo",mmContractInfo);
		model.addAttribute("contractPercentDone", contractPercentDone);
		model.addAttribute("visitHistoryList", visitHistoryList);
		model.addAttribute("numComplete", numComplete);
		model.addAttribute("totalVisit", totalVisit);
		model.addAttribute("widthVisitBar", widthVisitBar);
		model.addAttribute("title", "나의 멘토멘티 계약 진행 현황");
		return "farm/my_mm_contract_mentor_detail";
	}

	//방문평가 수정삭제
	@GetMapping("/myMenteeFeedbackModify")
	public String myMenteeFeedbackModify(Model model,HttpSession session, @RequestParam(name="visitCode", required=false) String visitCode) {


		List<EvaluationLargeCategory> evaluationLargeCategoryList = mentorMenteeService.getEvalLargeCateList();
		VisitHistory visitHistory = mentorMenteeService.getVisitHistoryByVisitCode(visitCode);

		model.addAttribute("evalLargeCateList",evaluationLargeCategoryList);
		model.addAttribute("visitHistory", visitHistory);

		if(visitCode == null) {
			return "farm/my_mentee_feedback_modify";
		}
		String companyCode = (String) session.getAttribute("sessionCompanyCode");

		List<ResultHistory> resultHistoryList = mentorMenteeService.getResultHistoryList(visitCode);
		List<MentorFeedbackToken> mentorFeedbackToken = mentorMenteeService.getMentorFeedbackTokenList(companyCode);

		model.addAttribute("mentorFeedbackToken", mentorFeedbackToken);
		model.addAttribute("resultHistoryList", resultHistoryList);
		model.addAttribute("title","멘토멘티 피드백 작성/수정");

		return "farm/my_mentee_feedback_modify";
	}
	
	//방문평가 등록/수정
	@CrossOrigin("*")
	@PostMapping("/receiveFormData")
	@ResponseBody
	public String receiveFormDataMentorMentee(@RequestBody GoogleFormResponse googleFormResponse, HttpServletRequest request) throws Exception {
		log.info("ACCESS INFO ==================================================");
		log.info("PORT 		::::::::	{}", request.getLocalPort());
		log.info("ServerName		::::::::	{}", request.getServerName());
		log.info("Method 		::::::::	{}", request.getMethod());
		log.info("URI 		::::::::	{}", request.getRequestURI());
		log.info("CLIENT IP		::::::::	{}", request.getRemoteAddr());
		log.info("ACCESS INFO ==================================================");
		
		
		Map<String, String> memberInfo = new HashMap<>();
		List<GoogleFormResult> feedbackList = new ArrayList<>();
		List<GoogleFormResult> feedbackScore = new ArrayList<>();

		List<GoogleFormResult> googleFormResultList = googleFormResponse.getResults();

		log.info("googleFormResponseList={}",googleFormResultList);
		for(GoogleFormResult obj : googleFormResultList) {

			String type = obj.getType();
			String title = obj.getTitle();
			String response = obj.getResponse();

			if(type.equals("PARAGRAPH_TEXT")) {
				feedbackList.add(obj);
				continue;
			}
			if(type.equals("SCALE")) {
				feedbackScore.add(obj);
				continue;
			}
			if(title.equals("Token ID")) {
				memberInfo.put("token_id", response);
			} else if(title.equals("visitCode")) {
				memberInfo.put("visit_code", response);
			} else if(title.equals("memberId")) {
				memberInfo.put("member_id", response);
			} else if(title.equals("contractCode")) {
				memberInfo.put("contract_code", response);
			}
		}
		memberInfo.forEach((key,value)-> {log.info(key+ " : " + value);});

		log.info("feedbackList={}", feedbackList);
		log.info("feedbackScore={}", feedbackScore);
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("memberInfo", memberInfo);
		paramMap.put("feedbackList", feedbackList);
		paramMap.put("feedbackScore", feedbackScore);
		mentorMenteeService.addFeedback(paramMap);


		return "Success";
	}
	
	//멘토멘티 멘티 신청
	@PostMapping("/menteeApply")
	@ResponseBody
	public Map<String, Object> addMenteeApply(MMRegInfoMentee menteeRegInfo, HttpSession session, @RequestParam(name="file[0]", required=false) MultipartFile[] uploadFile){
		
		Map<String, Object> returnMap = mentorMenteeService.addMenteeApply(menteeRegInfo, session);
		String fileAssociateKey = (String) returnMap.get("fileAssociateKey");
		Map<String, Object> returnMap1 = fileService.uploadMenteeDocument(uploadFile, filePath, fileAssociateKey);	
		
		return returnMap1;
	}
}
