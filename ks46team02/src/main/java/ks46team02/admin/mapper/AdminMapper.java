package ks46team02.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.common.dto.AdminMember;

@Mapper
public interface AdminMapper {
	/* 전체 관리자 회원 조회*/
	public List<AdminMember> getAdminList();
	/* 관리자 정보 수정 */
	public int modifyAdmin(AdminMember adminMember);
	/* 특정 관리자 아이디 조회 */	
	public AdminMember getAdminInfoById(String adminId);
	/* 관리자 아이디 삭제 */
	public int removeAdmin(String adminId);
	/* 탈퇴한 관리자 아이디 조회 */
	public List<AdminMember> getWithdrawalAdminList();
	/* 탈퇴한 관리자 아이디 삭제 */
	public int removewithdrawalAdmin(String adminId);
	/* 관리자 아이디 중복 체크 */
	public boolean idCheckAdmin(String adminId);
	/* 관리자 등록 */
	public int addAdmin(AdminMember adminMember);
}
