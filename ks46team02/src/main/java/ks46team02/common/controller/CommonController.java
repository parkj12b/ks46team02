package ks46team02.common.controller;


import jakarta.servlet.http.HttpSession;
import ks46team02.admin.dto.AdminLevel;
import ks46team02.admin.dto.LoginHistory;
import ks46team02.admin.mapper.AddrMapper;
import ks46team02.admin.mapper.AdminMapper;
import ks46team02.admin.mapper.MemberMapper;
import ks46team02.admin.service.AddrService;
import ks46team02.admin.service.AdminService;
import ks46team02.admin.service.ApiExamRomanService;
import ks46team02.admin.service.LoginHistoryService;
import ks46team02.common.dto.*;
import ks46team02.common.emailTest.EmailService;
import ks46team02.common.emailTest.EmailServiceImpl;
import ks46team02.common.service.MainService;
import ks46team02.company.dto.Company;
import ks46team02.company.mapper.ContractMapper;
import ks46team02.company.service.CompanyService;
import ks46team02.customerservice.dto.QuestionTypeDto;
import ks46team02.farm.service.MentorMenteeService;
import ks46team02.topmenu.service.TopMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
	MemberMapper memberMapper;
	ApiExamRomanService apiExamRomanService;
	AdminMapper adminMapper;
	AdminService adminService;

	ContractMapper contractMapper;


	public CommonController(MainService mainService,TopMenuService topMenuService, EmailServiceImpl emailService, MentorMenteeService mentorMenteeService, CompanyService companyService,AddrService addrService,AddrMapper addrMapper, LoginHistoryService loginHistoryService,MemberMapper memberMapper,ApiExamRomanService apiExamRomanService,AdminMapper adminMapper,AdminService adminService,ContractMapper contractMapper){
		this.mainService = mainService;
		this.emailService = emailService;
		this.mentorMenteeService = mentorMenteeService;
		this.topMenuService = topMenuService;
		this.companyService = companyService;
		this.addrService = addrService;
		this.addrMapper = addrMapper;
		this.loginHistoryService = loginHistoryService;
		this.memberMapper = memberMapper;
		this.apiExamRomanService = apiExamRomanService;
		this.adminMapper = adminMapper;
		this.adminService = adminService;
		this.contractMapper = contractMapper;
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

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String mainPage(@RequestParam(name = "questionTypeCode", defaultValue = "0") int questionTypeCode
						   , Model model
						   ,HttpSession session) {

	    List<QuestionTypeDto> topMenuList = topMenuService.getTopMenuCustomerServiceList();
	    log.info("{}", topMenuList);
	    session.setAttribute("topMenuList", topMenuList);


	    return "mainPage";
	}

	@GetMapping("/mypage")
	public String mypage(Member member,Model model,HttpSession session) {
		Member memberInfo = mainService.getMemberInfoAll(member,session);
		String memberId = (String) session.getAttribute("sessionId");
		String romaMemberId = apiExamRomanService.getRomanization(session);
		model.addAttribute("memberInfo",memberInfo);
		model.addAttribute("romaMemberId",romaMemberId);
		String memberLevel = (String) session.getAttribute("sessionLevel");
		if(memberLevel.equals("admin")){
			AdminMember adminList = adminService.getAdminInfoById(memberId);
			model.addAttribute("adminList",adminList);
			return "mypage2";
		}
		else{
			int contractAmount = contractMapper.getContractAmount(memberId);
			int endContractAmount = contractMapper.getEndContractAmount(memberId);
			int breakContractAmount =contractMapper.getBreakContractAmount(memberId);
			int reviewContractAmount = contractMapper.getReviewContractAmount(memberId);
			model.addAttribute("contractAmount",contractAmount);
			model.addAttribute("endContractAmount",endContractAmount);
			model.addAttribute("breakContractAmount",breakContractAmount);
			model.addAttribute("reviewContractAmount",reviewContractAmount);
			return "mypage";
		}
	}

	@GetMapping("/login")
	public String loginAndSignUp(Model model) {
		return "loginAndSignUp";
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

		return "redirect:/addrMemberList";
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
	/* 회원 정보 수정 */
	@PostMapping("/modifyMember")
	public String modifyMember(Member member) {

		memberMapper.modifyMember(member);
		return "redirect:/mypage";
	}

	/* 회원 정보 수정 */
	@GetMapping("/modifyMember")
	public String modifyMember(Model model
							  ,HttpSession session){
		String memberId = (String)session.getAttribute("sessionId");
		Member memberInfo = mainService.getMemberInfoById(memberId);
		model.addAttribute("title", "회원 수정");
		model.addAttribute("memberInfo", memberInfo);
		return "/modify_member";
	}
	/* 관리자별 수정 */
	@PostMapping("/modifyAdmin")
	public String modifyAdmin(AdminMember adminMember) {

		adminMapper.modifyAdmin(adminMember);

		return "redirect:/mypage";
	}
	/* 관리자별 수정 */

	@GetMapping("/modifyAdmin")
	public String modifyAdmin(Model model,HttpSession session){
		String adminId= (String)session.getAttribute("sessionId");
		AdminMember adminInfo = adminService.getAdminInfoById(adminId);
		model.addAttribute("title", "관리자 수정");
		model.addAttribute("adminInfo", adminInfo);
		return "/modify_admin";
	}


}
