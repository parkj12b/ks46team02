package ks46team02.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.dto.AdminMember;
import ks46team02.dto.Member;
import ks46team02.dto.MemberLoginInfo;

@Mapper
public interface TestMapper {
	public Member getMemberLoginInfo(MemberLoginInfo memberLoginInfo);
	public AdminMember getAdminLoginInfo(MemberLoginInfo memberLoginInfo);
	public boolean isDuplicateId (String memberId);
	public boolean isEmailUsed(String email);
	
}
