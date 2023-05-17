package ks46team02.common.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import ks46team02.admin.dto.LoginHistory;
import ks46team02.admin.mapper.AddrMapper;
import ks46team02.admin.service.AddrService;
import ks46team02.admin.service.LoginHistoryService;
import ks46team02.common.dto.Addr;
import ks46team02.common.dto.AdminMember;
import ks46team02.common.dto.AllContractInfo;
import ks46team02.common.dto.Member;
import ks46team02.common.dto.MemberLoginInfo;
import ks46team02.common.emailTest.EmailService;
import ks46team02.common.emailTest.EmailServiceImpl;
import ks46team02.common.service.MainService;
import ks46team02.company.dto.Company;
import ks46team02.company.service.CompanyService;
import ks46team02.customerservice.dto.QuestionTypeDto;
import ks46team02.farm.service.MentorMenteeService;
import ks46team02.topmenu.service.TopMenuService;

@Controller
public class CommonController {

	private final MainService mainService;
	private static final Logger log = LoggerFactory.getLogger(CommonController.class);
	
	EmailService emailService;
	MentorMenteeService mentorMenteeService;
	TopMenuService topMenuService;
	CompanyService companyService;
	AddrService addrService; 
	AddrMapper addrMapper;
	LoginHistoryService loginHistoryService;
	

	public CommonController(MainService mainService,TopMenuService topMenuService, EmailServiceImpl emailService, MentorMenteeService mentorMenteeService, CompanyService companyService,AddrService addrService,AddrMapper addrMapper, LoginHistoryService loginHistoryService){
		this.mainService = mainService;
		this.emailService = emailService;
		this.mentorMenteeService = mentorMenteeService;
		this.topMenuService = topMenuService;
		this.companyService = companyService;
		this.addrService = addrService;
		this.addrMapper = addrMapper;
		this.loginHistoryService = loginHistoryService;
	}
	
	
	//아직미구현
	@PostMapping("/signUp")
	public String signUp(Model model) {
		return "main2";
	}

	@PostMapping("/login")
	public String login(MemberLoginInfo memberLoginInfo
			           ,HttpSession session
			           ,LoginHistory loginHistory
					   ) {
		String memberLevel = memberLoginInfo.getLoginLevel();


		Object loginInfo = mainService.getLoginInfo(memberLoginInfo);
		
		if(memberLevel.equals("normal")) {	
			Member memberInfo = (Member) loginInfo;
			log.info("{}",memberInfo);
			System.out.println(memberInfo.isExist());
			int mmRegType = 0;
			if(memberInfo.getCompanyCode() != null) {
				mmRegType = mentorMenteeService.getMMRegType(memberInfo.getCompanyCode());
			}
//			/* 로그인 기록을 db로 저장 */
//			String memberId = memberInfo.getMemberId();
//			loginHistory.setMemberId(memberId);
//			loginHistoryService.addLoginHistory(loginHistory);
			
			
			
			
			if(memberInfo.isExist()) {
				session.setAttribute("sessionId", memberInfo.getMemberId());
				session.setAttribute("sessionName", memberInfo.getMemberName());
				session.setAttribute("sessionLevel", memberInfo.getPositionLevelCode());
				session.setAttribute("sessionCompanyCode", memberInfo.getCompanyCode());
				String sessionId = (String) session.getAttribute("sessionId");
				Company companyInfo = companyService.getCompanyInfoById(sessionId);
				session.setAttribute("sessionCompanyIsExist",companyInfo.isExist());
				session.setAttribute("memberEmail", memberInfo.getMemberEmail());
				if(memberInfo.getPositionLevelCode().equals("level_code_1")) {
					session.setAttribute("isOwner", true);
				} else {
					session.setAttribute("isOwner", false);
				};
				session.setAttribute("companyTypeNum", memberInfo.getCompanyTypeNum());
				session.setAttribute("mmRegType", mmRegType);
			}

		} else if(memberLevel.equals("admin")) {
			AdminMember memberInfo = (AdminMember) loginInfo;
			System.out.println(memberInfo.isExist());
			if(memberInfo.isExist()) {
				session.setAttribute("sessionId", memberInfo.getAdminId());
				session.setAttribute("sessionName", memberInfo.getAdminName());
				session.setAttribute("sessionLevel", "admin");
				session.setAttribute("sessionAdminLevel", memberInfo.getAdminLevel());
				session.setAttribute("sessionEmail", memberInfo.getAdminEmail());
			}
		}


		return "redirect:/";
	}
	//mail
	
	
	
	@GetMapping("/signUp")
	public String signUp() {
		return "redirect:/login#signup";
	}
	
	@GetMapping("/")
	public String mainPage(@RequestParam(name = "questionTypeCode", defaultValue = "0") int questionTypeCode
						   , Model model
						   ,HttpSession session) {
	
	    List<QuestionTypeDto> topMenuList = topMenuService.getTopMenuCustomerServiceList();
	    log.info("{}", topMenuList);
	    session.setAttribute("topMenuList", topMenuList);
	    model.addAttribute("title", "환경을 생각하는 라바링크");
	 
	    return "mainpage";
	}
	
	@GetMapping("/mypage")
	public String mypage() {
		return "mypage";
	}
	
	@GetMapping("/login")
	public String loginAndSignUp(Model model) {
		model.addAttribute("title", "라바링크 로그인");
		return "login_and_signup";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/testing")
    public String testing() {
    	return "dataTableTest";
    }
	
	@GetMapping("/contractPaper")
	public String getContractPaperDetail(Model model, HttpSession session, @RequestParam(name="contractCode") String contractCode) {
		Map<String,String> keyValue = new HashMap<String,String>();
		String companyCode = (String) session.getAttribute("sessionCompanyCode");
		
		List<Map<String, Object>> searchList = new ArrayList<>();
		
		
		
		keyValue.put("contract_code", contractCode);

		Set<String> keySet = keyValue.keySet();
		
		for(String key : keySet) {
			Map<String, Object> map = new HashMap<>();
			map.put("key", key);
			map.put("value", keyValue.get(key));
			searchList.add(map);
		}
		
		AllContractInfo contractInfo = mentorMenteeService.getMMContractDetail(searchList);
		if(contractInfo == null) {
			return "redirect:/";
		}
		
		String mentorCompanyCode = contractInfo.getContractorCompanyCode();
		String menteeCompanyCode = contractInfo.getContracteeCompanyCode();
		
		Company contractorCompany = companyService.getCompanyInfoByCode(mentorCompanyCode);
		Company contracteeCompany = companyService.getCompanyInfoByCode(menteeCompanyCode);
		
		
		log.info("here{}",contractInfo);
		model.addAttribute("contractInfo", contractInfo);
		model.addAttribute("contractorInfo", contractorCompany);
		model.addAttribute("contracteeInfo", contracteeCompany);
		model.addAttribute("title", "멘토멘티 계약서");
		
		return "contract_paper_mm";
	}
	/* 회원별 배송지 목록 조회 */
	@GetMapping("/addrMemberList")
	public String getAddrList(Model model
							 ,HttpSession session) {
		String memberId = (String) session.getAttribute("sessionId");
		List<Addr> addrList = addrService.getAddrInfoByMemberId(memberId);
		model.addAttribute("title", "배송지조회");
		model.addAttribute("addrList", addrList);
		return "addr_member_list";
	}
	
	/* 회원별 배송지 수정 */
	@PostMapping("/modifyMemberAddr")
	public String modifyAddr(Addr addr) {
		
		addrMapper.modifyAddr(addr);
		
		return "redirect:/admin/addrList";
	}
	
	/* 회원별 배송지 수정 */
	@GetMapping("/modifyMemberAddr")
	public String modifyAddr(Model model
							 ,@RequestParam(name="addrCode") String addrCode){
		
		Addr addrInfo = addrService.getAddrInfoById(addrCode);
		log.info("log"+addrInfo );
		model.addAttribute("title", "회원 수정");
		model.addAttribute("addrInfo", addrInfo);
		return "/modify_member_addr";
	}
	/* 배송지 등록 */
	@PostMapping("/addMemberAddr")
	public String addMemberAddr(Addr addr, Model model) {
		addrService.addAddr(addr);
		return "redirect:/addrList";
	}

	/* 배송지 등록 */
	@GetMapping("/addMemberAddr")
	public String addMemberAddr(Model model){
		model.addAttribute("title", "배송지 등록");
		return "/add_member_addr";
	}
	
	/**
	 * 
	 */
	@GetMapping("/unauthorizedRedirect")
	public String accessDenied() {
		return "invalid_access";
	}
}
