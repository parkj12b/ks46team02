package ks46team02.admin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks46team02.admin.dto.MemberLevel;
import ks46team02.admin.mapper.MemberLevelMapper;
@Service
@Transactional
public class MemberLevelService {
	
	private static final Logger log = LoggerFactory.getLogger(MemberLevelService.class);

	
private final MemberLevelMapper memberLevelMapper; 

	public MemberLevelService(MemberLevelMapper memberLevelMapper) {
		this.memberLevelMapper = memberLevelMapper;

	}
	
	public List<MemberLevel> getAdminLevelList(){
		List<MemberLevel> MemberLevelList = memberLevelMapper.getMemberLevelList();
		return MemberLevelList;
	}
	
}
