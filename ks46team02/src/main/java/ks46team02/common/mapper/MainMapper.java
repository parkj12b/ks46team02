package ks46team02.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.common.dto.AdminMember;
import ks46team02.common.dto.AllContractInfo;
import ks46team02.common.dto.ContractApprovalStandard;
import ks46team02.common.dto.FileRelation;
import ks46team02.common.dto.Member;
import ks46team02.common.dto.MemberLoginInfo;

@Mapper
public interface MainMapper {

	// 회원정보 조회
	public Member getMemberInfoById(String memberId);
	
	// 자동 증가 코드
	public String autoIncrement(String table, String column);
	
	//멤버 로그인 정보 조회
	public Member getMemberLoginInfo(MemberLoginInfo memberLoginInfo);
	
	//관리자 로그인 정보 조회
	public AdminMember getAdminLoginInfo(MemberLoginInfo memberLoginInfo);
	
	//아이디 중복체크
	public boolean isDuplicateId (String memberId);
	
	//이메일 중복체크
	public boolean isEmailUsed(String email);
	
	//계약 키 벨류 and로 정보조회
	public List<AllContractInfo> getContractInfoByKeyValueAnd(List<Map<String, Object>> searchList);
	
	//계약 키 벨류 or로 정보조회
	public List<AllContractInfo> getContractInfoByKeyValueOr(List<Map<String, Object>> searchList);
	
	//계약 승인기준 조회
	public ContractApprovalStandard getContractApprovalStandard(ContractApprovalStandard contApprStand);
	
	//파일관계 생성
	public void addFileRelation(FileRelation fileRelation);
	
}
