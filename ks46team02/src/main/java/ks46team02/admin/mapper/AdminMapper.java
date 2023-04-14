package ks46team02.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.common.dto.AdminMember;

@Mapper
public interface AdminMapper {
	/* 전체 관리자 회원 조회*/
	public List<AdminMember> getAdminList();
}
