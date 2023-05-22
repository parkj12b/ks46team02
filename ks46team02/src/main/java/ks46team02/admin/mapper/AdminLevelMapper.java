package ks46team02.admin.mapper;

import java.util.List;

import ks46team02.common.dto.AdminMember;
import org.apache.ibatis.annotations.Mapper;

import ks46team02.admin.dto.AdminLevel;
import ks46team02.admin.dto.MemberLevel;

@Mapper
public interface AdminLevelMapper {
	/* 관리자 등급 조회*/
	public List<AdminLevel> getAdminLevelList();
	/* 관리자 등급 등록 addAdminLevel*/
	public int addAdminLevel(AdminLevel adminLevel);
	
	/* 관리자 등급 수정 */
	public int modifyAdminLevel(AdminLevel adminLevel);
	/* 관리자 등급 삭제 */
	public int removeAdminLevel(String adminLevel);
	/* 숫자만 증가하는 자동 증가 함수*/
	public String autoNumIncrement(String table, String column);
}
