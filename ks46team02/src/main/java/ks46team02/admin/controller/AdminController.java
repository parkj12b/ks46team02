package ks46team02.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import groovy.util.logging.Slf4j;
import ks46team02.admin.dto.Addr;
import ks46team02.admin.dto.AdminLevel;
import ks46team02.admin.dto.LoginHistory;
import ks46team02.admin.dto.WithdrawalMember;
import ks46team02.admin.service.AddrService;
import ks46team02.admin.service.AdminLevelService;
import ks46team02.admin.service.AdminMMservice;
import ks46team02.admin.service.AdminService;
import ks46team02.admin.service.LoginHistoryService;
import ks46team02.admin.service.MemberService;
import ks46team02.admin.service.WithdrawalMemberService;
import ks46team02.common.dto.AdminMember;
import ks46team02.common.dto.Member;
import ks46team02.farm.dto.MMRegInfoMentee;
import ks46team02.farm.dto.MMRegInfoMentor;

@Controller
@RequestMapping("/admin")
@Slf4j

public class AdminController {

	private final AdminService adminService;
	private final AddrService addrService;
	private final WithdrawalMemberService withdrawalMemberService;
	private final AdminMMservice mMService;
	private final AdminLevelService adminLevelService;
	private final MemberService memberservice;
	private final LoginHistoryService loginHistoryService;
	
	
	private static final Logger log = LoggerFactory.getLogger(AdminController.class);

	
	public AdminController(AddrService addrService
						  ,AdminService adminService
						  ,WithdrawalMemberService withdrawalMemberService
						  ,AdminMMservice mMService
						  ,AdminLevelService adminLevelService
    					  ,MemberService memberservice
    					  ,LoginHistoryService loginHistoryService) {
		this.addrService = addrService;
		this.adminService = adminService;
		this.withdrawalMemberService = withdrawalMemberService;
		this.mMService = mMService;
		this.adminLevelService = adminLevelService;
	    this.memberservice = memberservice;
	    this.loginHistoryService = loginHistoryService;
	}
    /* 전체 관리자 목록 조회 */
	@GetMapping("/adminList")
	public String getAdminList(Model model) {
		List<AdminMember> adminList = adminService.getAdminList();

		model.addAttribute("title", "관리자목록 조회");
		model.addAttribute("adminList", adminList);

		return "admin/adminList";
	}
	/* 전체 회원 배송지 목록 조회 */
	@GetMapping("/addrList")
	public String getAddrList(Model model) {
		List<Addr> addrList = addrService.getAddrList();

		model.addAttribute("title", "배송지조회");
		model.addAttribute("addrList", addrList);

		return "admin/addrList";
	}
	/* 탈퇴한 회원 목록 조회 */
	@GetMapping("/withdrawalMemberList")
	public String getwithdrawalMemberList(Model model) {
		List<WithdrawalMember> withdrawalMemberList = withdrawalMemberService.WithdrawalMemberList();
		model.addAttribute("title", "탈퇴회원리스트");
		model.addAttribute("withdrawalMemberList", withdrawalMemberList);
		return "admin/withdrawalMemberList";

	}
	
	@GetMapping("/mentorRegManageList")
	public String getMentorRegManageList(Model model) {
		List<MMRegInfoMentor> mentorRegList = mMService.getMentorRegList("under review");
		model.addAttribute("mentorRegList", mentorRegList);
		return "admin/mentorRegList";
	}
	
	@GetMapping("/menteeRegManageList")
	public String getMenteeRegManageList(Model model) {
		List<MMRegInfoMentee> menteeRegList = mMService.getMenteeRegList("under review");
		model.addAttribute("menteeRegList",menteeRegList);
		return "admin/menteeRegList";
	}
	/* 관리자 레벨 목록 조회 */
@GetMapping("/adminLevelList")
public String getAdminLevelList(Model model) {
	List<AdminLevel>adminLevelList =adminLevelService.getAdminLevelList();
	model.addAttribute("title", "관리자등급조회");
	model.addAttribute("adminLevelList", adminLevelList);
	return "admin/adminLevelList";

	}
	/* 관리자 레벨 목록 조회 */
@GetMapping("/memberList")
public String getMemberList(Model model) {
	List<Member>memberList = memberservice.getMemberList();
	model.addAttribute("title", "회원조회");
	model.addAttribute("memberList", memberList);
	return "admin/memberList";

	}
/* 전체 회원 로그인 기록 조회 */
@GetMapping("/loginHistory")
public String getLoginHistoryList(Model model) {
	List<LoginHistory>loginHistory = loginHistoryService.getloginHistoryList();
	model.addAttribute("title", "로그인 기록 조회");
	model.addAttribute("loginHistory", loginHistory);
	return "admin/loginHistory";
	}

/* 휴면 회원 조회 */
@GetMapping("/dormantMemberList")
public String getDormantMemberList(Model model) {
	List<Member>dormantMemberList = memberservice.getDormantMemberList();
	model.addAttribute("title", "로그인 기록 조회");
	model.addAttribute("dormantMemberList", dormantMemberList);
	return "admin/dormantMemberList";
	}
}
