package ks46team02.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.common.dto.AdminMember;
import ks46team02.common.dto.Member;


@Mapper
public interface MemberMapper {
	/* 업체등록시 회원정보-업체코드 업데이트 */
	public int updateCompanyCode(Member member);
	/* 업체직원직위수정 */
	public int modifyEmployeeLevel(Member member);
	/* 업체별 직원 조회 */
	public List<Member> getEmployeeList(String companyCode);
	/* 전체 회원  조회*/
	public List<Member> getMemberList();
	/* 휴면 회원 조회 */
	public List<Member> getDormantMemberList();
	/* 회원 정보 수정 */
	public int modifyMember(Member member);
	/* 특정 회원 아이디 조회 */	
	public Member getMemberInfoById(String memberId);
	/* 휴면 회원을 일반회원으로 */
	public int modifyDormantMember(String memberId);

}
