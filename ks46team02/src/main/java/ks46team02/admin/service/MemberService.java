package ks46team02.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks46team02.admin.mapper.MemberMapper;
import ks46team02.common.dto.Member;
@Service
@Transactional
public class MemberService {
	
private final MemberMapper memberMapper;
	
	public MemberService(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;

	}
	/* 전체 회원 조회*/
	public List<Member> getMemberList(){
		List<Member> MemberList = memberMapper.getMemberList();
		return MemberList;
	}
	/* 휴면 회원 조회 */
	public List<Member> getDormantMemberList(){
		List<Member> DormantMemberList = memberMapper.getDormantMemberList();
		return DormantMemberList;
	}
	
}
