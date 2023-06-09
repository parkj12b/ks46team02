package ks46team02.admin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks46team02.admin.controller.AdminController;
import ks46team02.admin.mapper.MemberMapper;
import ks46team02.common.dto.Member;
@Service
@Transactional
public class MemberService {
	
private final MemberMapper memberMapper;

private static final Logger log = LoggerFactory.getLogger(AdminController.class);
	
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
		log.info("employeeList :{}",employeeList );
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
		
		 if(member.getCompanyCode().equals("")) { 
			 member.setCompanyCode(null);}		 
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
	/* 일반 회원 휴면회원으로 전환 */
	public void modifyMemberDormant(String memberId){

		memberMapper.modifyMemberDormant(memberId);
	}

}
