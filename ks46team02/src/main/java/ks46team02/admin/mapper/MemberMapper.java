package ks46team02.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.common.dto.Member;


@Mapper
public interface MemberMapper {
	/* 전체 회원  조회*/
	public List<Member> getMemberList();
	/* 휴면 회원 조회 */
	public List<Member> getDormantMemberList();
}
