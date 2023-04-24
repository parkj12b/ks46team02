package ks46team02.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import groovy.util.logging.Slf4j;
import ks46team02.admin.dto.AdminLevel;
import ks46team02.admin.dto.ContractStandard;
import ks46team02.admin.dto.LoginHistory;
import ks46team02.admin.dto.MemberLevel;
import ks46team02.admin.dto.WithdrawalMember;
import ks46team02.admin.mapper.AdminMapper;
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
import ks46team02.common.dto.Addr;
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
	private final AdminMapper adminMapper; 
	
	
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
    					  ,CompanyApprovalService companyApprovalService
    					  ,AdminMapper adminMapper) {
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
	    this.adminMapper = adminMapper;
	}
    /* 승인 대기 업체 조회 */
	@GetMapping("/companyApproval_list")
	public String getCompanyApprovalList(Model model) {
		List<Company> companyApprovalList = companyApprovalService.getCompanyApprovalList();
		model.addAttribute("title", "승인 대기 업체 조회");
		model.addAttribute("companyApprovalList", companyApprovalList);

		return "admin/companyApproval_list";
	}
    /* 전체 관리자 목록 조회 */
	@GetMapping("/admin_list")
	public String getAdminList(Model model) {
		List<AdminMember> adminList = adminService.getAdminList();

		model.addAttribute("title", "관리자목록 조회");
		model.addAttribute("adminList", adminList);

		return "admin/admin_list";
	}
	
	/* 관리자 수정 */
	@PostMapping("/admin_modify")
	public String modifyGoods(AdminMember adminMember) {
		
		adminMapper.modifyAdmin(adminMember);
		
		return "redirect:/admin/admin_list";
	}
	/* 관리자 수정 */
	
	@GetMapping("/admin_modify")
	public String modifyGoods(Model model
							 ,@RequestParam(name="adminId") String adminId) {
		
		AdminMember adminInfo = adminService.getAdminInfoById(adminId);
		model.addAttribute("title", "관리자 수정");
		model.addAttribute("adminInfo", adminInfo);
		
		return "admin/admin_modify";
	}

	/* 관리자 등록 */
	@GetMapping("/admin_add")
	public String addAdmin(Model model){
		List<AdminLevel>adminLevelList1 =adminLevelService.getAdminLevelList();
		model.addAttribute("title", "관리자 등록");
		model.addAttribute("adminLevelList1", adminLevelList1);
		return "admin/admin_add";
	}
	/* 관리자 등급 등록 */
	@GetMapping("/adminLevel_add")
	public String addAdminLevel(Model model){
		model.addAttribute("title", "관리자 등급 등록");
		return "admin/adminLevel_add";
	}
	/* 회원 등급 등록 */
	@GetMapping("/memberLevel_add")
	public String addMemberLevel(Model model){
		model.addAttribute("title", "회원 등급 등록");
		return "admin/memberLevel_add";
	}
	/* 회원 등급 조회 */
	@GetMapping("/memberLevel_list")
	public String getMemberLevelList(Model model) {
		List<MemberLevel> memberLevelList = memberLevelService.getAdminLevelList();

		model.addAttribute("title", "회원 등급 조회");
		model.addAttribute("memberLevelList", memberLevelList);

		return "admin/memberLevel_list";
	}
	/* 전체 회원 배송지 목록 조회 */
	@GetMapping("/addr_list")
	public String getAddrList(Model model) {
		List<Addr> addrList = addrService.getAddrList();

		model.addAttribute("title", "배송지조회");
		model.addAttribute("addrList", addrList);

		return "admin/addr_list";
	}
	/* 배송지 삭제 */
	/* 배송지 등록 */
	@GetMapping("/addr_add")
	public String addAddr(Model model){
		model.addAttribute("title", "배송지등록");
		return "admin/addr_add";
	}
	/* 탈퇴한 회원 목록 조회 */
	@GetMapping("/withdrawalMember_list")
	public String getwithdrawalMemberList(Model model) {
		List<WithdrawalMember> withdrawalMemberList = withdrawalMemberService.WithdrawalMemberList();
		model.addAttribute("title", "탈퇴회원리스트");
		model.addAttribute("withdrawalMemberList", withdrawalMemberList);
		return "admin/withdrawalMember_list";

	}
	
	@GetMapping("/mentorReg_list")
	public String getMentorRegManageList(Model model) {
		List<MMRegInfoMentor> mentorRegList = mMService.getMentorRegList("under review");
		model.addAttribute("mentorRegList", mentorRegList);
		return "admin/mentorReg_list";
	}
	
	@GetMapping("/menteeReg_list")
	public String getMenteeRegManageList(Model model) {
		List<MMRegInfoMentee> menteeRegList = mMService.getMenteeRegList("under review");
		model.addAttribute("menteeRegList",menteeRegList);
		return "admin/menteeReg_list";
	}
	/* 관리자 레벨 목록 조회 */
	@GetMapping("/adminLevel_list")
	public String getAdminLevelList(Model model) {
		List<AdminLevel>adminLevelList =adminLevelService.getAdminLevelList();
		model.addAttribute("title", "관리자등급조회");
		model.addAttribute("adminLevelList", adminLevelList);
		return "admin/adminLevel_list";
	
		}
		/* 회원 목록 조회 */
	@GetMapping("/member_list")
	public String getMemberList(Model model) {
		List<Member>memberList = memberservice.getMemberList();
		model.addAttribute("title", "회원조회");
		model.addAttribute("memberList", memberList);
		return "admin/member_list";
	
		}
	/* 전체 회원 로그인 기록 조회 */
	@GetMapping("/loginHistory_list")
	public String getLoginHistoryList(Model model) {
		List<LoginHistory>loginHistory = loginHistoryService.getloginHistoryList();
		model.addAttribute("title", "로그인 기록 조회");
		model.addAttribute("loginHistory", loginHistory);
		return "admin/loginHistory_list";
		}
	/* 회원 등록 */
	@GetMapping("/member_add")
	public String addMember(Model model){
		model.addAttribute("title", "회원 등록");
		return "admin/member_add";
	}
	
	/* 휴면 회원 조회 */
	@GetMapping("/dormantMember_list")
	public String getDormantMemberList(Model model) {
		List<Member>dormantMemberList = memberservice.getDormantMemberList();
		model.addAttribute("title", "로그인 기록 조회");
		model.addAttribute("dormantMemberList", dormantMemberList);
		return "admin/dormantMember_list";
		}
	/* 승인 기준 등록 */
	@GetMapping("/contractStandard_add")
	public String addContractStandard(Model model){
		model.addAttribute("title", "승인 기준 등록");
		return "admin/contractStandard_add";
	}
	/* 승인 기준 조회 */
	@GetMapping("/contractStandard_list")
	public String GetContractStandardList(Model model) {
		List<ContractStandard>contractStandardList = contractStandardService.getAdminLevelList();
		model.addAttribute("title", "승인 기준 조회");
		model.addAttribute("contractStandardList", contractStandardList);
		return "admin/contractStandard_list";
		}
}
