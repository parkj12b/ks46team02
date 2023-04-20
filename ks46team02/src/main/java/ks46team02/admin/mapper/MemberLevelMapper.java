package ks46team02.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.admin.dto.MemberLevel;
import ks46team02.admin.dto.MemberLevel;

@Mapper
public interface MemberLevelMapper {
	/* 전체 회원 등급 조회*/
	public List<MemberLevel> getMemberLevelList();
}
