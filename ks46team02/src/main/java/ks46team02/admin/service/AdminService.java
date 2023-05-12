package ks46team02.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks46team02.admin.mapper.AdminMapper;
import ks46team02.common.dto.AdminMember;
@Service
@Transactional
public class AdminService {
	
private final AdminMapper adminMapper;
	
	public AdminService(AdminMapper adminMapper) {
		this.adminMapper = adminMapper;

	}
	/* 전체 관리자 조회*/
	public List<AdminMember> getAdminList(){
		List<AdminMember> AdminList = adminMapper.getAdminList();
		return AdminList;
	}
	/* 특정 관리자 아이디 조회 */
	public AdminMember getAdminInfoById(String adminId) {
		AdminMember adminInfo =adminMapper.getAdminInfoById(adminId);
		return adminInfo;
	}
	/* 탈퇴한 관리자 조회  */
	public List<AdminMember> getWithdrawalAdminList(){
		List<AdminMember> withdrawalAdminList = adminMapper.getWithdrawalAdminList();
		return withdrawalAdminList;
	}
	/* 관리자 등록 */
	public int addAdmin(AdminMember adminMember) {
		int result = adminMapper.addAdmin(adminMember);
		return result;
	}
	
}
