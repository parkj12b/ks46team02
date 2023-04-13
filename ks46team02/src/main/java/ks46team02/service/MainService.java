package ks46team02.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks46team02.dto.AdminMember;
import ks46team02.dto.Member;
import ks46team02.dto.MemberLoginInfo;
import ks46team02.mapper.TestMapper;

@Service
@Transactional
public class MainService {
	private final TestMapper testMapper;
	
	
	private static final Logger log = LoggerFactory.getLogger(MainService.class);

	
	public MainService(TestMapper testMapper) {
		this.testMapper = testMapper;
	}
	
	public boolean isDuplicateId(String memberId) {
		boolean isDuplicate = false;
		isDuplicate = testMapper.isDuplicateId(memberId);
		
		return isDuplicate;
	}

	public Object getLoginInfo(MemberLoginInfo memberLoginInfo) {
		Object loginInfo;
		if(memberLoginInfo.getLoginLevel().equals("normal")) {
			loginInfo = testMapper.getMemberLoginInfo(memberLoginInfo);
			
		} else {
			loginInfo = testMapper.getAdminLoginInfo(memberLoginInfo);
		}
		
		return loginInfo;
	}

	public boolean isEmailUsed(String email) {
		boolean isEmailUsed = testMapper.isEmailUsed(email);
		return isEmailUsed;
	}
	
	
}
