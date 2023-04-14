package ks46team02.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.admin.dto.WithdrawalMember;

@Mapper
public interface WithdrawalMemberMapper{
	
	public List<WithdrawalMember> getWithdrawalMember();
	
	

}
