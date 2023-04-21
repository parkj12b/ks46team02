package ks46team02.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import groovy.util.logging.Log;
import groovy.util.logging.Slf4j;
import ks46team02.admin.dto.Addr;
import ks46team02.admin.dto.AdminLevel;
import ks46team02.admin.dto.ContractStandard;
import ks46team02.admin.dto.LoginHistory;
import ks46team02.admin.dto.MemberLevel;
import ks46team02.admin.dto.WithdrawalMember;
import ks46team02.admin.service.AddrService;
import ks46team02.admin.service.AdminLevelService;
import ks46team02.admin.service.AdminMMservice;
import ks46team02.admin.service.AdminService;
import ks46team02.admin.service.CompanyApprovalService;
import ks46team02.admin.service.ContractStandardService;
import ks46team02.admin.service.LoginHistoryService;
import ks46team02.admin.service.MemberLevelService;
import ks46team02.admin.service.MemberService;
import ks46team02.admin.service.WithdrawalMemberService;
import ks46team02.common.dto.AdminMember;
import ks46team02.common.dto.Member;
import ks46team02.company.dto.Company;
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
	private final ContractStandardService contractStandardService;
	private final MemberLevelService memberLevelService; 
	private final CompanyApprovalService companyApprovalService;
	
	
	private static final Logger log = LoggerFactory.getLogger(AdminController.class);

	
	public AdminController(AddrService addrService
						  ,AdminService adminService
						  ,WithdrawalMemberService withdrawalMemberService
						  ,AdminMMservice mMService
						  ,AdminLevelService adminLevelService
    					  ,MemberService memberservice
    					  ,LoginHistoryService loginHistoryService
    					  ,ContractStandardService contractStandardService
    					  ,MemberLevelService memberLevelService
    					  ,CompanyApprovalService companyApprovalService) {
		this.addrService = addrService;
		this.adminService = adminService;
		this.withdrawalMemberService = withdrawalMemberService;
		this.mMService = mMService;
		this.adminLevelService = adminLevelService;
	    this.memberservice = memberservice;
	    this.loginHistoryService = loginHistoryService;
	    this.contractStandardService = contractStandardService;
	    this.memberLevelService = memberLevelService;
	    this.companyApprovalService = companyApprovalService;
	}
    /* 승인 대기 업체 조회 */
	@GetMapping("/companyApprovalList")
	public String getCompanyApprovalList(Model model) {
		List<Company> companyApprovalList = companyApprovalService.getCompanyApprovalList();
		model.addAttribute("title", "승인 대기 업체 조회");
		model.addAttribute("companyApprovalList", companyApprovalList);

		return "admin/companyApprovalList";
	}
    /* 전체 관리자 목록 조회 */
	@GetMapping("/adminList")
	public String getAdminList(Model model) {
		List<AdminMember> adminList = adminService.getAdminList();

		model.addAttribute("title", "관리자목록 조회");
		model.addAttribute("adminList", adminList);

		return "admin/adminList";
	}
	/* 관리자 등록 */
	@GetMapping("/addAdmin")
	public String addAdmin(Model model){
		List<AdminLevel>adminLevelList1 =adminLevelService.getAdminLevelList();
		model.addAttribute("title", "관리자 등록");
		model.addAttribute("adminLevelList1", adminLevelList1);
		return "admin/addAdmin";
	}
	/* 관리자 등급 등록 */
	@GetMapping("/addAdminLevel")
	public String addAdminLevel(Model model){
		model.addAttribute("title", "관리자 등급 등록");
		return "admin/addAdminLevel";
	}
	/* 회원 등급 등록 */
	@GetMapping("/addMemberLevel")
	public String addMemberLevel(Model model){
		model.addAttribute("title", "회원 등급 등록");
		return "admin/addMemberLevel";
	}
	 /* 회원 등급 조회 */
		@GetMapping("/memberLevelList")
		public String getMemberLevelList(Model model) {
			List<MemberLevel> memberLevelList = memberLevelService.getAdminLevelList();

			model.addAttribute("title", "회원 등급 조회");
			model.addAttribute("memberLevelList", memberLevelList);

			return "admin/memberLevelList";
		}
	/* 전체 회원 배송지 목록 조회 */
	@GetMapping("/addrList")
	public String getAddrList(Model model) {
		List<Addr> addrList = addrService.getAddrList();

		model.addAttribute("title", "배송지조회");
		model.addAttribute("addrList", addrList);

		return "admin/addrList";
	}
	/* 배송지 등록 */
	@GetMapping("/addAddr")
	public String addAddr(Model model){
		model.addAttribute("title", "배송지등록");
		return "admin/addAddr";
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
		/* 회원 목록 조회 */
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
	/* 회원 등록 */
	@GetMapping("/addMember")
	public String addMember(Model model){
		model.addAttribute("title", "회원 등록");
		return "admin/addMember";
	}
	
	/* 휴면 회원 조회 */
	@GetMapping("/dormantMemberList")
	public String getDormantMemberList(Model model) {
		List<Member>dormantMemberList = memberservice.getDormantMemberList();
		model.addAttribute("title", "로그인 기록 조회");
		model.addAttribute("dormantMemberList", dormantMemberList);
		return "admin/dormantMemberList";
		}
	/* 승인 기준 등록 */
	@GetMapping("/addContractStandard")
	public String addContractStandard(Model model){
		model.addAttribute("title", "승인 기준 등록");
		return "admin/addContractStandard";
	}
	/* 승인 기준 조회 */
	@GetMapping("/contractStandardList")
	public String GetContractStandardList(Model model) {
		List<ContractStandard>contractStandardList = contractStandardService.getAdminLevelList();
		model.addAttribute("title", "승인 기준 조회");
		model.addAttribute("contractStandardList", contractStandardList);
		return "admin/contractStandardList";
		}
}
