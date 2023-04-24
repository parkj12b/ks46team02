package ks46team02.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import ks46team02.common.dto.AdminMember;
import ks46team02.common.dto.Member;
import ks46team02.common.dto.MemberLoginInfo;
import ks46team02.common.emailTest.EmailService;
import ks46team02.common.emailTest.EmailServiceImpl;
import ks46team02.common.service.MainService;
import ks46team02.farm.service.MentorMenteeService;

@Controller
public class CommonController {

	private final MainService mainService;
	private static final Logger log = LoggerFactory.getLogger(CommonController.class);
	
	EmailService emailService;
	MentorMenteeService mentorMenteeService;
	
	public CommonController(MainService mainService, EmailServiceImpl emailService, MentorMenteeService mentorMenteeService){
		this.mainService = mainService;
		this.emailService = emailService;
		this.mentorMenteeService = mentorMenteeService;
	}
	
	
	//아직미구현
	@PostMapping("/signUp")
	public String signUp(Model model) {
		return "main2";
	}

	@PostMapping("/login")
	public String login(MemberLoginInfo memberLoginInfo, HttpSession session) {
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
			
			if(memberInfo.isExist()) {
				session.setAttribute("sessionId", memberInfo.getMemberId());
				session.setAttribute("sessionName", memberInfo.getMemberName());
				session.setAttribute("sessionLevel", memberInfo.getPositionLevelCode());
				session.setAttribute("sessionCompanyCode", memberInfo.getCompanyCode());
				session.setAttribute("memberEmail", memberInfo.getMemberEmail());
				session.setAttribute("isOwner", memberInfo.isOwner());
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
	public String mainPage() {
		return "mainPage";
	}
	
	@GetMapping("/mypage")
	public String mypage() {
		return "mypage";
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
	public String getContractPaperDetail(HttpSession session) {
		
		return "contractPaper";
	}
}
