package ks46team02.admin.controller;

import groovy.util.logging.Slf4j;
import jakarta.servlet.http.HttpSession;
import ks46team02.admin.dto.*;
import ks46team02.admin.mapper.*;
import ks46team02.admin.service.*;
import ks46team02.common.dto.Addr;
import ks46team02.common.dto.AdminMember;
import ks46team02.common.dto.Member;
import ks46team02.common.mapper.MainMapper;
import ks46team02.company.dto.Company;
import ks46team02.company.service.CompanyService;
import ks46team02.farm.dto.*;
import ks46team02.farm.mapper.MentorMenteeMapper;
import ks46team02.farm.service.MentorMenteeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

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
	private final MentorMenteeMapper mentorMenteeMapper;
	private final MainMapper mainMapper;
	private final ContractStandardMapper contractStandardMapper;
	
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
						  ,MentorMenteeService mentorMenteeService
						  ,MentorMenteeMapper mentorMenteeMapper
						  ,MainMapper mainMapper
						  ,ContractStandardMapper contractStandardMapper) {
		this.mentorMenteeMapper = mentorMenteeMapper;
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
		this.mainMapper = mainMapper;
		this.contractStandardMapper = contractStandardMapper;
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
	public String applyCompanyRegList(Model model
									 ,HttpSession session) {

		String sessionId = (String)session.getAttribute("sessionId");
		List<Company> companyList = companyService.getCompanyList();
		model.addAttribute("title", "승인 대기 업체 조회");
		model.addAttribute("companyList",companyList);
		model.addAttribute("sessionId",sessionId);
		return "admin/apply_company_reg_list";
	}
	/* 등급별 관리자 목록 조회*/
	@PostMapping("/adminLevelCheck")
	@ResponseBody
	public List<AdminMember> getAdminLevelSearchList(@RequestParam(name="adminLevel") String adminLevel){
		List<AdminMember> levelInfo = adminService.getAdminLevelSearchList(adminLevel);

		return levelInfo;
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
	/* 관리자 등급 등록*/
	@PostMapping("/addAdminLevel")
	public String addAdminLevel(AdminLevel adminLevel) {
		adminLevelService.addAdminLevel(adminLevel);
		return "redirect:/admin/adminLevelList";
	}
	/* 관리자 등급 등록 */
	@GetMapping("/addAdminLevel")
	public String addAdminLevel(Model model){
		model.addAttribute("title","관리자 등급 등록");
		return "admin/add_adminLevel";
	}
	/* 관리자 등급 수정   */
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
	/* 회원 등급 등록*/
	@PostMapping("/addMemberLevel")
	public String addMemberLevel(MemberLevel memberLevel,HttpSession session) {
		memberLevelService.addMemberLevel(memberLevel,session);
		return "redirect:/admin/memberLevelList";
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
	public void removeMember(String memberId,WithdrawalMember withdrawalMember){
		memberMapper.removeMember(memberId);
		Member member = memberservice.getMemberInfoById(memberId);
		String phone = member.getMemberPhone();
		String column = "withdrawal_member_code";
		String table = "withdrawal_member";
		String WithdrawalMemberCode = mainMapper.autoIncrement(table, column);
		withdrawalMember.setWithdrawalMemberCode(WithdrawalMemberCode);
		withdrawalMember.setWithdrawalMemberId(memberId);
		withdrawalMember.setWithdrawalMemberPhone(phone);
		withdrawalMemberMapper.addwithdrawalMember(withdrawalMember);

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
	/* 승인 기준 등록*/
	@PostMapping("/addContractStandard")
	public String addContractStandard(ContractStandard contractStandard,HttpSession session){
		contractStandardService.addContractStandard(contractStandard,session);
		return "redirect:/admin/contractStandardList";
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
	/* 승인기준 삭제*/
	@PostMapping("/removeContractStandard")
	@ResponseBody
	public void removeContractStandard(String contStandCode) {
		contractStandardMapper.removeContractStandard(contStandCode);


	}
	
	@PostMapping("/mentorRegApprove")
	@ResponseBody
	public Map<String,Object> mentorRegApprove(Model model, MMRegInfoMentor mentorRegInfo) {
		
		String msg = "";
		boolean isSuccess = false;
		int result = mMService.approveMentorRegStatus(mentorRegInfo);
		Map<String,Object> returnMap = new HashMap<>();
		if(result == 1) {
			msg = "승인되었습니다.";
			isSuccess = true;
		} else {
			msg = "승인을 처리하지 못하였습니다.";
		}
		
		returnMap.put("msg", msg);
		returnMap.put("isSuccess", isSuccess);
		return returnMap;
	}
	
	@PostMapping("/mentorRegDeny")
	@ResponseBody
	public Map<String,Object> mentorRegDeny(Model model, MMRegInfoMentor mentorRegInfo) {
		
		String msg = "";
		boolean isSuccess = false;
		int result = mMService.denyMentorRegStatus(mentorRegInfo);
		Map<String,Object> returnMap = new HashMap<>();
		if(result == 1) {
			msg = "승인거부 되었습니다.";
			isSuccess = true;
		} else {
			msg = "승인거부를 처리하지 못하였습니다.";
		}
		
		returnMap.put("msg", msg);
		returnMap.put("isSuccess", isSuccess);
		return returnMap;
	}
	@GetMapping("/mentorRegList")
	public String getMentorRegManageList(Model model) {
		Map<String,String> paramMap = new HashMap<>();
		List<Map<String,Object>> searchList = new ArrayList<>();
		paramMap.put("mentor_approval", "under review");
		Set<String> keySet = paramMap.keySet();
		for(String key: keySet) {
			Map<String, Object> map = new HashMap<>();
			map.put("key", key);
			map.put("value", paramMap.get(key));
			searchList.add(map);
		}
		
		MultiValueMap<String,String> paramMap2 = new LinkedMultiValueMap<>();
		List<Map<String,Object>> searchList2 = new ArrayList<>();
		paramMap2.add("mentor_approval", "denied");
		paramMap2.add("mentor_approval", "approved");
		Set<String> keySet2 = paramMap2.keySet();
		for(String key: keySet2) {
			List<String> list = paramMap2.get(key);
			for(String value: list) {
				Map<String, Object> map = new HashMap<>();
				map.put("key", key);
				map.put("value", value);
				searchList2.add(map);				
			}
		}
		log.info("List{}",searchList2);
		List<MMRegInfoMentor> mentorRegList = mMService.getMentorRegListOr(searchList);
		List<MMRegInfoMentor> mentorList = mMService.getMentorRegListOr(searchList2);
		model.addAttribute("title","멘토 신청 관리");
		model.addAttribute("mentorRegList",mentorRegList);
		model.addAttribute("mentorList",mentorList);
		log.info("mentorList={}", mentorList);
		return "admin/mentor_reg_list";
	}
	
	@GetMapping("/menteeRegList")
	public String getMenteeRegManageList(Model model) {
		Map<String,String> paramMap = new HashMap<>();
		List<Map<String,Object>> searchList = new ArrayList<>();
		paramMap.put("mentee_approval", "under review");
		Set<String> keySet = paramMap.keySet();
		for(String key: keySet) {
			Map<String, Object> map = new HashMap<>();
			map.put("key", key);
			map.put("value", paramMap.get(key));
			searchList.add(map);
		}
		
		MultiValueMap<String,String> paramMap2 = new LinkedMultiValueMap<>();
		List<Map<String,Object>> searchList2 = new ArrayList<>();
		paramMap2.add("mentee_approval", "denied");
		paramMap2.add("mentee_approval", "approved");
		Set<String> keySet2 = paramMap2.keySet();
		for(String key: keySet2) {
			List<String> list = paramMap2.get(key);
			for(String value: list) {
				Map<String, Object> map = new HashMap<>();
				map.put("key", key);
				map.put("value", value);
				searchList2.add(map);				
			}
		}
		
		List<MMRegInfoMentee> menteeRegList = mMService.getMenteeRegListOr(searchList);
		List<MMRegInfoMentee> menteeList = mMService.getMenteeRegListOr(searchList2);
		model.addAttribute("title","멘티 신청 관리");
		model.addAttribute("menteeRegList",menteeRegList);
		model.addAttribute("menteeList",menteeList);
		return "admin/mentee_reg_list";
	}
	
	@PostMapping("/mentorRegDelete")
	@ResponseBody
	public Map<String, Object> removeMentorRegHistory(MMRegInfoMentor mentorRegInfo){
		
		Map<String, Object> returnMap = new HashMap<>();
		boolean isSuccess = false;
		String msg;
		int result = mMService.removeMentorRegHistory(mentorRegInfo);
		if(result == 1) {
			msg = "삭제되었습니다.";
			isSuccess = true;
		} else {
			msg = "삭제 처리하지 못하였습니다.";
		}
		
		returnMap.put("msg", msg);
		returnMap.put("isSuccess", isSuccess);

		return returnMap;
	}
	@PostMapping("/menteeRegApprove")
	@ResponseBody
	public Map<String,Object> menteeRegApprove(Model model, MMRegInfoMentee menteeRegInfo) {
		
		String msg = "";
		boolean isSuccess = false;
		int result = mMService.approveMenteeRegStatus(menteeRegInfo);
		Map<String,Object> returnMap = new HashMap<>();
		if(result == 1) {
			msg = "승인되었습니다.";
			isSuccess = true;
		} else {
			msg = "승인을 처리하지 못하였습니다.";
		}
		
		returnMap.put("msg", msg);
		returnMap.put("isSuccess", isSuccess);
		return returnMap;
	}
	
	@PostMapping("/menteeRegDeny")
	@ResponseBody
	public Map<String,Object> menteeRegDeny(Model model, MMRegInfoMentee menteeRegInfo) {
		
		String msg = "";
		boolean isSuccess = false;
		int result = mMService.denyMenteeRegStatus(menteeRegInfo);
		Map<String,Object> returnMap = new HashMap<>();
		if(result == 1) {
			msg = "승인거부 되었습니다.";
			isSuccess = true;
		} else {
			msg = "승인거부를 처리하지 못하였습니다.";
		}
		
		returnMap.put("msg", msg);
		returnMap.put("isSuccess", isSuccess);
		return returnMap;
	}
	
	@PostMapping("/menteeRegDelete")
	@ResponseBody
	public Map<String, Object> removeMenteeRegHistory(MMRegInfoMentee menteeRegInfo){
		
		Map<String, Object> returnMap = new HashMap<>();
		boolean isSuccess = false;
		String msg;
		int result = mMService.removeMenteeRegHistory(menteeRegInfo);
		if(result == 1) {
			msg = "삭제되었습니다.";
			isSuccess = true;
		} else {
			msg = "삭제 처리하지 못하였습니다.";
		}
		
		returnMap.put("msg", msg);
		returnMap.put("isSuccess", isSuccess);

		return returnMap;
	}
	
	@GetMapping("/mentorVisitHistory")
	public String getMentorVisitHistoryList(Model model) {
		Map<String, String> paramMap = new HashMap<String,String>();
		
		List<VisitHistory> visitHistoryList = mentorMenteeMapper.getVisitHistoryList();
		model.addAttribute("title","멘토 방문기록 관리");
		model.addAttribute("visitHistoryList",visitHistoryList);
		return "admin/mm_contract_visit_history";
	}
	
	@PostMapping("/removeVisitHistory")
	@ResponseBody
	public Map<String, Object> removeVisitHistory(VisitHistory visitHistory){
		
		Map<String, Object> returnMap = mMService.resetVisitHistory(visitHistory);
		
		return returnMap;
	}
	
	@GetMapping("/mentorResultHistory")
	public String getMentorResultHistoryList(Model model) {
		model.addAttribute("title", "멘토 평가결과 기록 관리");
		
		return "admin/mm_contract_result_history";
	}
	
	@GetMapping("/mentorEvaluationLargeCategory")
	public String getEvaluationLargeCategoryList (Model model) {
		model.addAttribute("title", "멘토멘티 평가 대분류 관리");
		List<EvaluationLargeCategory> largeCategoryList = mentorMenteeService.getEvaluationLargeCategoryNoDetailCate();
		
		log.info("eval={}",largeCategoryList);
		
		model.addAttribute("largeCategoryList",largeCategoryList);
		return "admin/mm_evaluation_large_category";
	}
	
	@PostMapping("/removeLargeCategory")
	@ResponseBody
	public Map<String, Object> removeVisitHistory(EvaluationLargeCategory evalLargeCate){
		
		Map<String, Object> returnMap = mMService.removeEvaluationLargeCategory(evalLargeCate);
		
		return returnMap;
	}
	
	@PostMapping("/modifyLargeCategory")
	@ResponseBody
	public Map<String, Object> modifyLargeCategory(EvaluationLargeCategory evalLargeCate){
		Map<String, Object> returnMap = mMService.modifyLargeCategory(evalLargeCate);
		
		return returnMap;
	}
	
	@PostMapping("/addLargeCategory")
	@ResponseBody
	public Map<String, Object> addLargeCategory(EvaluationLargeCategory evalLargeCate) {
		
		Map<String, Object> returnMap = mMService.addEvaluationLargeCategory(evalLargeCate);
		
		return returnMap;
	}
	
	@GetMapping("/mentorEvaluationDetailCategory")
	public String mentorEvaluationDetailCategory(Model model) {
		
		List<EvaluationDetailCategory> detailCategoryList = mentorMenteeService.getEvalDetailCateList();
		List<EvaluationLargeCategory> largeCategoryList = mentorMenteeService.getEvaluationLargeCategoryNoDetailCate();
		
		model.addAttribute("title", "멘토멘티 평가 세부항목 관리");
		model.addAttribute("detailCategoryList",detailCategoryList);
		model.addAttribute("largeCategoryList",largeCategoryList);
		
		return "admin/mm_evaluation_detail_category";
	}
	
	@PostMapping("/modifyDetailCategory")
	@ResponseBody
	public Map<String, Object> modifyDetailCategory(EvaluationDetailCategory evalDetailCate){
		Map<String,Object> returnMap = mMService.modifyDetailCategory(evalDetailCate);
		
		return returnMap;
	}

	@PostMapping("/deleteDetailCategory")
	@ResponseBody
	public Map<String, Object> deleteDetailCategory(EvaluationDetailCategory evalDetailCate){
		Map<String,Object> returnMap = mMService.deleteDetailCategory(evalDetailCate);
		
		return returnMap;
	}
}
