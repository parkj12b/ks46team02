package ks46team02.admin.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import groovy.util.logging.Slf4j;
import jakarta.servlet.http.HttpSession;
import ks46team02.admin.dto.AdminLevel;
import ks46team02.admin.dto.ContractStandard;
import ks46team02.admin.dto.LoginHistory;
import ks46team02.admin.dto.MemberLevel;
import ks46team02.admin.dto.WithdrawalMember;
import ks46team02.admin.mapper.AddrMapper;
import ks46team02.admin.mapper.AdminLevelMapper;
import ks46team02.admin.mapper.AdminMapper;
import ks46team02.admin.mapper.LoginHistoryMapper;
import ks46team02.admin.mapper.MemberLevelMapper;
import ks46team02.admin.mapper.MemberMapper;
import ks46team02.admin.mapper.WithdrawalMemberMapper;
import ks46team02.admin.service.AddrService;
import ks46team02.admin.service.AdminLevelService;
import ks46team02.admin.service.AdminMMservice;
import ks46team02.admin.service.AdminService;
import ks46team02.admin.service.ContractStandardService;
import ks46team02.admin.service.LoginHistoryService;
import ks46team02.admin.service.MemberLevelService;
import ks46team02.admin.service.MemberService;
import ks46team02.admin.service.WithdrawalMemberService;
import ks46team02.common.dto.Addr;
import ks46team02.common.dto.AdminMember;
import ks46team02.common.dto.Member;
import ks46team02.company.dto.Company;
import ks46team02.company.service.CompanyService;
import ks46team02.farm.dto.MMRegInfoMentee;
import ks46team02.farm.dto.MMRegInfoMentor;
import ks46team02.farm.service.MentorMenteeService;

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
	private final CompanyService companyService;
	private final AdminMapper adminMapper;
	private final MemberMapper memberMapper;
	private final AddrMapper addrMapper;
	private final AdminLevelMapper adminLevelMapper;
	private final LoginHistoryMapper loginHistoryMapper;
	private final WithdrawalMemberMapper withdrawalMemberMapper;
	private final MentorMenteeService mentorMenteeService;
	
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
    					  ,AdminMapper adminMapper
    					  ,MemberMapper memberMapper
    					  ,AddrMapper addrMapper
    					  ,MemberLevelMapper memberLevelMapper
    					  ,AdminLevelMapper adminLevelMapper
    					  ,LoginHistoryMapper loginHistoryMapper
    					  ,WithdrawalMemberMapper withdrawalMemberMapper
						  ,CompanyService companyService
						  ,MentorMenteeService mentorMenteeService) {
		this.mentorMenteeService = mentorMenteeService;
		this.addrService = addrService;
		this.adminService = adminService;
		this.withdrawalMemberService = withdrawalMemberService;
		this.mMService = mMService;
		this.adminLevelService = adminLevelService;
	    this.memberservice = memberservice;
	    this.loginHistoryService = loginHistoryService;
	    this.contractStandardService = contractStandardService;
	    this.memberLevelService = memberLevelService;
	    this.adminMapper = adminMapper;
	    this.memberMapper = memberMapper;
	    this.addrMapper = addrMapper;
	    this.adminLevelMapper =  adminLevelMapper;
	    this.loginHistoryMapper = loginHistoryMapper;
	    this.withdrawalMemberMapper = withdrawalMemberMapper;
	    this.companyService = companyService;
	}

	/* 관리자 아이디 중복 체크 */
	@PostMapping("/idCheckAdmin")
	@ResponseBody
	public boolean idCheckAdmin(@RequestParam(name="adminId") String adminId) {
	    boolean isDuplicate = adminMapper.idCheckAdmin(adminId); // 아이디 중복 여부를 boolean 타입으로 반환합니다.
	    log.info("log"+isDuplicate );
	    return isDuplicate;
	}

	
	
	
	/* 탈퇴한 관리자 조회 */
	@GetMapping("/withdrawalAdminList")
	public String getWithdrawalAdminList(Model model) {
		List<AdminMember> withdrawalAdminList = adminService.getWithdrawalAdminList();

		model.addAttribute("title", "탈퇴한 관리자목록 조회");
		model.addAttribute("withdrawalAdminList", withdrawalAdminList);

		return "admin/withdrawaladmin_list";
	}
	/* 탈퇴한 관리자 삭제 */
	@PostMapping("/removewithdrawalAdmin")
	@ResponseBody
	public void removewithdrawalAdmin(String adminId ){
					 
			 adminMapper.removewithdrawalAdmin(adminId);
		 }
	
    /* 승인 대기 업체 조회 */
	@GetMapping("/applyCompanyRegList")
	public String applyCompanyRegList(Model model) {

		List<Company> companyList = companyService.getCompanyList();
		model.addAttribute("title", "승인 대기 업체 조회");
		model.addAttribute("companyList",companyList);
		return "admin/apply_company_reg_list";
	}
    /* 전체 관리자 목록 조회 */
	@GetMapping("/adminList")
	public String getAdminList(Model model) {
		List<AdminMember> adminList = adminService.getAdminList();

		model.addAttribute("title", "관리자목록 조회");
		model.addAttribute("adminList", adminList);

		return "admin/admin_list";
	}
	
	
	/* 관리자 수정 */
	@PostMapping("/modifyAdmin")
	public String modifyAdmin(AdminMember adminMember) {
		
		adminMapper.modifyAdmin(adminMember);
		
		return "redirect:/admin/adminList";
	}
	/* 관리자 수정 */
	
	@GetMapping("/modifyAdmin")
	public String modifyAdmin(Model model
							 ,@RequestParam(name="adminId") String adminId){
		
		AdminMember adminInfo = adminService.getAdminInfoById(adminId);
		List<AdminLevel>adminLevelList =adminLevelService.getAdminLevelList();
		log.info("log"+adminInfo );
		model.addAttribute("title", "관리자 수정");
		model.addAttribute("adminInfo", adminInfo);
		model.addAttribute("adminLevelList", adminLevelList);
		return "admin/modify_admin";
	}
	/* 관리자 비밀번호 확인 */
	@PostMapping("/pwCheckAdmin")
	@ResponseBody
	public Boolean pwCheckAdmin(@RequestParam(name="adminPw") String adminPw
			    			    ,HttpSession session) {
		String adminIdCheck = (String) session.getAttribute("sessionId");
		AdminMember adminInfo = adminService.getAdminInfoById(adminIdCheck);
		String adminPwCheck = adminInfo.getAdminPw();
		Boolean pwCheck = adminPwCheck.equals(adminPw);
		
		return pwCheck; 
		
	}
	
	/* 관리자 삭제 */
	@PostMapping("/removeAdmin")
	@ResponseBody
	public void removeAdmin(String adminId ){			 
		adminMapper.removeAdmin(adminId);
		 }
	/* 관리자 등록 */
	@PostMapping("/addAdmin")
	public String addAdmin(AdminMember adminMeber) {
		adminService.addAdmin(adminMeber);
		return "redirect:/admin/adminList";
	}
	/* 관리자 등록 */
	@GetMapping("/addAdmin")
	public String addAdmin(Model model){
		List<AdminLevel>adminLevelList1 =adminLevelService.getAdminLevelList();
		model.addAttribute("title", "관리자 등록");
		model.addAttribute("adminLevelList1", adminLevelList1);
		return "admin/add_admin";
	}
	/* 관리자 등급 등록 */
	@GetMapping("/addAdminLevel")
	public String addAdminLevel(Model model){
		model.addAttribute("title", "관리자 등급 등록");
		return "admin/add_adminLevel";
	}
	/* 관리자등급 수정   */
	@PostMapping("/modifyAdminLevel")
	@ResponseBody
	public void modifyAdminLevel(AdminLevel adminLevel) {
		adminLevelService.modifyAdminLevel(adminLevel);
		
	}
	
	/* 관리자 등급 삭제  */
	@PostMapping("/removeAdminLevel")
	@ResponseBody
	public void removeAdminLevel(String adminLevel) {
		adminLevelMapper.removeAdminLevel(adminLevel);
		
	}
	/* 회원 등급 등록 */
	@GetMapping("/addMemberLevel")
	public String addMemberLevel(Model model){
		model.addAttribute("title", "회원 등급 등록");
		return "admin/add_memberLevel";
	}
	/* 회원등급 수정 */
	@PostMapping("/modifyMemberLevel")
	@ResponseBody
	public void modifyMemberLevel(MemberLevel memberLevel) {
		memberLevelService.modifyMemberLevel(memberLevel);
	}
	/* 회원 등급 삭제  */
	@PostMapping("/removeMemberLevel")
	@ResponseBody
	public void removeMemberLevel(String positionLevelCode) {
		memberMapper.removeMember(positionLevelCode);
		
	}
	/* 회원 등급 조회 */
	@GetMapping("/memberLevelList")
	public String getMemberLevelList(Model model) {
		List<MemberLevel> memberLevelList = memberLevelService.getMemberLevelList();

		model.addAttribute("title", "회원 등급 조회");
		model.addAttribute("memberLevelList", memberLevelList);

		return "admin/memberLevel_list";
	}
	/* 회원별 배송지 숫자 조회 */
	@PostMapping("/AddrAmountList")
	@ResponseBody
	public int getAddrAmountList(String memberId ){
		int result = addrMapper.getAddrAmountList(memberId);
		return result;
	}
	/* 전체 회원 배송지 목록 조회 */
	@GetMapping("/addrList")
	public String getAddrList(Model model) {
		List<Addr> addrList = addrService.getAddrList();
		model.addAttribute("title", "배송지조회");
		model.addAttribute("addrList", addrList);
		return "admin/addr_list";
	}
	/* 배송지 세부 조회 */
	@GetMapping("/addrMemberList")
	@ResponseBody
	public Addr getAddrMemberList(@RequestParam(name="addrCode")String addrCode) {
	    Addr addr = addrService.getAddrInfoById(addrCode);
	    return addr;
	}
	
	/* 배송지 수정 */
	@PostMapping("/modifyAddr")
	public String modifyAddr(Addr addr) {
		
		addrMapper.modifyAddr(addr);
		
		return "redirect:/admin/addrList";
	}
	/* 배송지 수정 */
	
	@GetMapping("/modifyAddr")
	public String modifyAddr(Model model
							 ,@RequestParam(name="addrCode") String addrCode){
		
		Addr addrInfo = addrService.getAddrInfoById(addrCode);
		log.info("log"+addrInfo );
		model.addAttribute("title", "회원 수정");
		model.addAttribute("addrInfo", addrInfo);
		return "admin/modify_addr";
	}
	/* 배송지 삭제 */
	@PostMapping("/removeAddr")
	@ResponseBody
	public void removeAddr(String addrCode) {
		Addr addr = addrService.getAddrInfoById(addrCode);
		String memberId = addr.getMemberId();
		List<Addr> addrList= addrService.getAddrInfoByMemberId(memberId);
		String addrSeq = addr.getAddrSeq();
		addrMapper.removeAddr(addrCode);
		 if ("primary".equals(addrSeq) && addrList != null) {
		        for (Addr a : addrList) {
		            if ("primary".equals(a.getAddrSeq())) {
		                a.setAddrSeq("primary");
		                addrMapper.modifyAddr(a);
		                break;
		            }
		        }
		    }
	}
	
	/* 배송지 등록 */
	@PostMapping("/addAddr")
	public String addAddr(Addr addr, Model model) {
	    addrService.addAddr(addr);
	    return "redirect:/admin/addrList";
	}

	/* 배송지 등록 */
	@GetMapping("/addAddr")
	public String addAddr(Model model){
		List<Member> memberInfo = memberservice.getMemberList();
		model.addAttribute("title", "배송지 등록");
		model.addAttribute("memberInfo", memberInfo);
		return "admin/add_addr";
	}
	/* 탈퇴한 회원 목록 조회 */
	@GetMapping("/withdrawalMemberList")
	public String getwithdrawalMemberList(Model model) {
		List<WithdrawalMember> withdrawalMemberList = withdrawalMemberService.WithdrawalMemberList();
		model.addAttribute("title", "탈퇴회원리스트");
		model.addAttribute("withdrawalMemberList", withdrawalMemberList);
		return "admin/withdrawalMember_list";

	}
	/* 탈퇴한 회원 영구 삭제   */
	@PostMapping("/removewithdrawalMember")
	@ResponseBody
	public void removewithdrawalMember(String WithdrawalMemberCode ){
		withdrawalMemberMapper.removewithdrawalMember(WithdrawalMemberCode);
					 
			
		 }
	
	@GetMapping("/mentorRegList")
	public String getMentorRegManageList(Model model) {
		List<MMRegInfoMentor> mentorRegList = mMService.getMentorRegList("under review");
		model.addAttribute("mentorRegList", mentorRegList);
		return "admin/mentorReg_list";
	}
	
	@GetMapping("/menteeRegList")
	public String getMenteeRegManageList(Model model) {
		List<MMRegInfoMentee> menteeRegList = mMService.getMenteeRegList("under review");
		model.addAttribute("menteeRegList",menteeRegList);
		return "admin/menteeReg_list";
	}
	
	/* 관리자 레벨 목록 조회 */
	@GetMapping("/adminLevelList")
	public String getAdminLevelList(Model model) {
		List<AdminLevel>adminLevelList =adminLevelService.getAdminLevelList();
		model.addAttribute("title", "관리자등급조회");
		model.addAttribute("adminLevelList", adminLevelList);
		return "admin/adminLevel_list";
	
		}
	/* 회원 등록 */
	@PostMapping("/addMember")
	public String addMember(Member meber) {
		memberservice.addMember(meber);
		return "redirect:/admin/memberList";
	}

	/* 회원 등록 */
	@GetMapping("/addMember")
	public String addMember(Model model){
		List<MemberLevel>memberLevelList =memberLevelService.getMemberLevelList();
		List<Company> companyList = companyService.getCompanyList();
		model.addAttribute("title", "회원 등록");
		model.addAttribute("memberLevelList", memberLevelList);
		model.addAttribute("companyList", companyList);
		return "admin/add_member";
	}

	/* 회원 수정 */
	@PostMapping("/modifyMember")
	public String modifyMember(Member member
							  ,@RequestParam (name="memberStatus")String memberStatus) {
		
	  if(memberStatus.equals("dormant")) {
		  SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  Date date = new Date();
	      String nowTime = form.format(date);
		  member.setDormantMemberRegDate(nowTime);
	  }
		
		memberMapper.modifyMember(member);
		
		return "redirect:/admin/memberList";
	}
	/* 회원 수정 */
	
	@GetMapping("/modifyMember")
	public String modifyMember(Model model
							 ,@RequestParam(name="memberId") String memberId){
		
		Member memberInfo = memberservice.getMemberInfoById(memberId);
		List<MemberLevel> memberLevelList = memberLevelService.getMemberLevelList();
		model.addAttribute("title", "회원 수정");
		model.addAttribute("memberInfo", memberInfo);
		model.addAttribute("memberLevelList", memberLevelList);
		return "admin/modify_member";
	}
		/* 회원 목록 조회 */
	@GetMapping("/memberList")
	public String getMemberList(Model model) {
		List<Member>memberList = memberservice.getMemberList();
		model.addAttribute("title", "회원조회");
		model.addAttribute("memberList", memberList);
		return "admin/member_list";
	
		}
	/* 전체 회원 로그인 기록 조회 */
	@GetMapping("/loginHistoryList")
	public String getLoginHistoryList(Model model) {
		List<LoginHistory>loginHistory = loginHistoryService.getloginHistoryList();
		model.addAttribute("title", "로그인 기록 조회");
		model.addAttribute("loginHistory", loginHistory);
		return "admin/loginHistory_list";
		}
	/* 로그인 기록 삭제  */
	@PostMapping("/removeLogin")
	@ResponseBody
	public void removeLogin(String loginCode){
		loginHistoryMapper.removeLogin(loginCode);
		
		 }
	/* 회원 삭제 */
	@PostMapping("/removeMember")
	@ResponseBody
	public void removeMember(String memberId ){
		memberMapper.removeMember(memberId);
	}
	/* 휴면 회원 삭제 */
	@PostMapping("/removeDormantMember")
	@ResponseBody
	public void removeDormantMember(String memberId ){
		memberMapper.removeMember(memberId);
	}
	
	/* 휴면 회원 조회 */
	@GetMapping("/dormantMemberList")
	public String getDormantMemberList(Model model) {
		List<Member>dormantMemberList = memberservice.getDormantMemberList();
		model.addAttribute("title", "휴면 회원 조회");
		model.addAttribute("dormantMemberList", dormantMemberList);
		return "admin/dormantMember_list";
		}

	/* 휴면회원 되돌리기 */
	@PostMapping("/modifyDormantMember")
	@ResponseBody
	public void modifyDormantMember(@RequestParam(name="memberId")String memberId) {
	    memberservice.modifyDormantMember(memberId);
	    
	}

	/* 승인 기준 등록 */
	@GetMapping("/addContractStandard")
	public String addContractStandard(Model model){
		model.addAttribute("title", "승인 기준 등록");
		return "admin/add_contractStandard";
	}
	/* 승인 기준 조회 */
	@GetMapping("/contractStandardList")
	public String GetContractStandardList(Model model) {
		List<ContractStandard>contractStandardList = contractStandardService.getAdminLevelList();
		model.addAttribute("title", "승인 기준 조회");
		model.addAttribute("contractStandardList", contractStandardList);
		return "admin/contractStandard_list";
		}
	/* 승인 기준 수정   */
	@PostMapping("/modifyContractStandard")
	@ResponseBody
	public void modifyContractStandard(ContractStandard contractStandard) {
		contractStandardService.modifyContractStandard(contractStandard);
	}
	
	@GetMapping("/mentorRegManageList")
	public String mentorApplyList(Model model, String approveStatus) {
		
		
		model.addAttribute("title", "멘토 신청 관리");
		return "admin/mentor_reg_manage_list";
	}
}
