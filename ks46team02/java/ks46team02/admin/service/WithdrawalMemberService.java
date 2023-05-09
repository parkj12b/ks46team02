package ks46team02.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks46team02.admin.dto.WithdrawalMember;
import ks46team02.admin.mapper.WithdrawalMemberMapper;

@Service
@Transactional
public class WithdrawalMemberService {

private final WithdrawalMemberMapper withdrawalMemberMapper;
	
	public WithdrawalMemberService(WithdrawalMemberMapper withdrawalMemberMapper) {
		this.withdrawalMemberMapper = withdrawalMemberMapper;

	}
	public List<WithdrawalMember> WithdrawalMemberList(){
		List<WithdrawalMember> WithdrawalMemberList = withdrawalMemberMapper.getWithdrawalMember();
		return WithdrawalMemberList;
	}
}
	
	