package ks46team02.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.admin.dto.WithdrawalMember;

@Mapper
public interface WithdrawalMemberMapper{
	/* 탈퇴 회원 조회 */
	public List<WithdrawalMember> getWithdrawalMember();
	/* 탈퇴한 회원 영구 삭제 */
	public int removewithdrawalMember(String WithdrawalMemberCode);
	
	

}
