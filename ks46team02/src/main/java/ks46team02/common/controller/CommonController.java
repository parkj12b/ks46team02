package ks46team02.common.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import ks46team02.dto.AdminMember;
import ks46team02.dto.Member;
import ks46team02.dto.MemberLoginInfo;
import ks46team02.emailTest.EmailService;
import ks46team02.emailTest.EmailServiceImpl;
import ks46team02.service.MainService;

@Controller
public class CommonController {

	private final MainService mainService;
	private static final Logger log = LoggerFactory.getLogger(CommonController.class);
	
	EmailService emailService;
	
	public CommonController(MainService mainService, EmailServiceImpl emailService){
		this.mainService = mainService;
		this.emailService = emailService;
	}
	
	
	
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
			System.out.println(memberInfo.isExist());
			if(memberInfo.isExist()) {
				session.setAttribute("sessionId", memberInfo.getMemberId());
				session.setAttribute("sessionName", memberInfo.getMemberName());
				session.setAttribute("sessionLevel", memberInfo.getPositionLevelCode());
				session.setAttribute("sessionCompanyCode", memberInfo.getPositionLevelCode());
				session.setAttribute("memberEmail", memberInfo.getMemberEmail());
				session.setAttribute("isOwner", memberInfo.isOwner());
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
		return "loginAfterDb#signup";
	}
	
	@GetMapping("/")
	public String mainPage() {
		return "main2";
	}
	
	@GetMapping("/mypage")
	public String mypage(HttpServletRequest request) {
		return "mypage";
	}
	
	@GetMapping("/loginAfterDb")
	public String loginAfterDb(Model model) {
		return "loginAfterDb";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
