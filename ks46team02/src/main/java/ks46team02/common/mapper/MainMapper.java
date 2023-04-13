package ks46team02.common.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.common.dto.AdminMember;
import ks46team02.common.dto.Member;
import ks46team02.common.dto.MemberLoginInfo;

@Mapper
public interface MainMapper {
	public Member getMemberLoginInfo(MemberLoginInfo memberLoginInfo);
	public AdminMember getAdminLoginInfo(MemberLoginInfo memberLoginInfo);
	public boolean isDuplicateId (String memberId);
	public boolean isEmailUsed(String email);
	
}
