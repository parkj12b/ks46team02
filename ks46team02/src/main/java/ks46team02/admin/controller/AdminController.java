package ks46team02.admin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import groovy.util.logging.Slf4j;
import ks46team02.admin.dto.Addr;
import ks46team02.admin.dto.WithdrawalMember;
import ks46team02.admin.service.AddrService;
import ks46team02.admin.service.AdminService;
import ks46team02.admin.service.WithdrawalMemberService;
import ks46team02.common.dto.AdminMember;

@Controller
@RequestMapping("/admin")
@Slf4j

public class AdminController {

	private final AdminService adminService;
	private final AddrService addrService;
	private final WithdrawalMemberService withdrawalMemberService;

    public AdminController(AddrService addrService,AdminService adminService,WithdrawalMemberService withdrawalMemberService) {
        this.addrService= addrService;
        this.adminService = adminService;
        this.withdrawalMemberService = withdrawalMemberService;
    }
@GetMapping("/adminList")
	public String getAdminList(Model model) {
		List<AdminMember> adminList = adminService.getAdminList();
		
		
		model.addAttribute("title", "관리자목록 조회");
		model.addAttribute("adminList", adminList);
		
		return "admin/adminList";
	}
@GetMapping("/addrList")
public String getAddrList( Model model) {
	List<Addr> addrList = addrService.getAddrList();
	
	
	model.addAttribute("title", "배송지조회");
	model.addAttribute("addrList", addrList);
	
	return "admin/addrList";
	}
@GetMapping("/withdrawalMemberList")
public String getwithdrawalMemberList(Model model) {
	List<WithdrawalMember>withdrawalMemberList =withdrawalMemberService.WithdrawalMemberList();
	model.addAttribute("title", "탈퇴회원리스트");
	model.addAttribute("withdrawalMemberList", withdrawalMemberList);
	return "admin/withdrawalMemberList";

	}
}
