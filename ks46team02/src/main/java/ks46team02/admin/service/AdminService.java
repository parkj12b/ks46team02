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
	
	public List<AdminMember> getAdminList(){
		List<AdminMember> AdminList = adminMapper.getAdminList();
		return AdminList;
	}
	public AdminMember getAdminInfoById(String adminId) {
		AdminMember adminInfo =adminMapper.getAdminInfoById(adminId);
		return adminInfo;
	}
	
}
