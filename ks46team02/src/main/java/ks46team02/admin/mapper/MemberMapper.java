package ks46team02.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.common.dto.Member;


@Mapper
public interface MemberMapper {
	/* 업체직원직위수정 */
	public int modifyEmployeeLevel(Member member);
	/* 업체별 직원 조회 */
	public List<Member> getEmployeeList(String companyCode);
	/* 전체 회원  조회*/
	public List<Member> getMemberList();
	/* 휴면 회원 조회 */
	public List<Member> getDormantMemberList();
}
