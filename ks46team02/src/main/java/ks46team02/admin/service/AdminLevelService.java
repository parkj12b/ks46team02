package ks46team02.admin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import groovy.util.logging.Log;
import ks46team02.admin.dto.AdminLevel;
import ks46team02.admin.dto.MemberLevel;
import ks46team02.admin.mapper.AdminLevelMapper;
@Service
@Transactional
public class AdminLevelService {
	
	private static final Logger log = LoggerFactory.getLogger(AdminLevelService.class);

	
private final AdminLevelMapper adminLevelMapper; 

	public AdminLevelService(AdminLevelMapper adminLevelMapper) {
		this.adminLevelMapper = adminLevelMapper;

	}
	
	public List<AdminLevel> getAdminLevelList(){
		List<AdminLevel> AdminLevelList = adminLevelMapper.getAdminLevelList(); 
		log.info("{}",AdminLevelList);
		return AdminLevelList;
	}
	
	public void modifyAdminLevel(AdminLevel adminLevel) {
		adminLevelMapper.modifyAdminLevel(adminLevel);
	}
	
}
