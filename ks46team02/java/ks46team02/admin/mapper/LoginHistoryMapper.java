package ks46team02.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.admin.dto.LoginHistory;


@Mapper
public interface LoginHistoryMapper {
	/*  전체 회원 로그인 기록 조회*/
	public List<LoginHistory> getLoginHistoryList();
	/* 로그인 기록 삭제 */
	public int removeLogin(String loginCode);
	
	/* 휴면 처리될 회원 조회*/
	public List<LoginHistory> getDormentLoginList();
}
