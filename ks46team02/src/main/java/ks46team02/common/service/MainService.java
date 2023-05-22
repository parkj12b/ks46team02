package ks46team02.common.service;

import jakarta.servlet.http.HttpSession;
import ks46team02.common.dto.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks46team02.common.dto.MemberLoginInfo;
import ks46team02.common.mapper.MainMapper;

@Service
@Transactional
public class MainService {
	private final MainMapper mainMapper;
	
	
	private static final Logger log = LoggerFactory.getLogger(MainService.class);

	
	public MainService(MainMapper mainMapper) {
		this.mainMapper = mainMapper;
	}

	public Member getMemberInfoById(String memberId){
		Member memberInfo = mainMapper.getMemberInfoById(memberId);
		return memberInfo;
	}
	
	public boolean isDuplicateId(String memberId) {
		boolean isDuplicate = false;
		isDuplicate = mainMapper.isDuplicateId(memberId);
		
		return isDuplicate;
	}

	public Object getLoginInfo(MemberLoginInfo memberLoginInfo) {
		Object loginInfo;
		if(memberLoginInfo.getLoginLevel().equals("normal")) {
			loginInfo = mainMapper.getMemberLoginInfo(memberLoginInfo);
			
		} else {
			loginInfo = mainMapper.getAdminLoginInfo(memberLoginInfo);
		}
		
		return loginInfo;
	}

	public boolean isEmailUsed(String email) {
		boolean isEmailUsed = mainMapper.isEmailUsed(email);
		return isEmailUsed;
	}
	public Member getMemberInfoAll(Member member, HttpSession session){
		String memberId = (String)session.getAttribute("sessionId");
		member.setMemberId(memberId);
		Member memberInfoAll = mainMapper.getMemberInfoAll(member);
		return memberInfoAll;
	}
	
	
}
