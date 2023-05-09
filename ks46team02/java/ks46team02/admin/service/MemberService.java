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
	/* 업체 직원 직위 수정 */
	public int modifyEmployeeLevel(Member member){
		int result = memberMapper.modifyEmployeeLevel(member);
		return result;
	}
	/* 업체별 직원 조회 */
	public List<Member> getEmployeeList(String companyCode){
		List<Member> employeeList = memberMapper.getEmployeeList(companyCode);
		return employeeList;
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
	/* 회원 등록 */
	public int addMember(Member member) {
		int result = memberMapper.addMember(member);
		return result;
	}
	/* 특정 회원 조회 */
	public Member getMemberInfoById(String memberId) {
		Member memberInfo =memberMapper.getMemberInfoById(memberId);
		return memberInfo;
	}
	/* 휴면 회원 돌리기 */
	public void modifyDormantMember(String memberId) {
	    memberMapper.modifyDormantMember(memberId);
	}
}
