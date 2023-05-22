package ks46team02.farm.mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.common.dto.AllContractInfo;
import ks46team02.common.dto.ContractApprovalStandard;
import ks46team02.farm.dto.EvaluationDetailCategory;
import ks46team02.farm.dto.EvaluationLargeCategory;
import ks46team02.farm.dto.EvaluationStandard;
import ks46team02.farm.dto.MMContractInfo;
import ks46team02.farm.dto.MMRegInfoMentee;
import ks46team02.farm.dto.MMRegInfoMentor;
import ks46team02.farm.dto.MentorFeedbackToken;
import ks46team02.farm.dto.ResultHistory;
import ks46team02.farm.dto.VisitHistory;

@Mapper
public interface MentorMenteeMapper {
	
	//멘티 신청 기록 조회
	public MMRegInfoMentee getMMRegStatMentee(String companyCode);
	
	//멘토 신청 기록 조회
	public MMRegInfoMentor getMMRegStatMentor(String companyCode);

	//맨토멘티 권한 조회
	public int getMMRegType(String companyCode);
	
	//멘토멘티 계약 정보 조회
	public List<MMContractInfo> getMMContractInfo(String searchKey, String searchValue);
	
	//멘토멘티 방문기록 
	public List<VisitHistory> getVisitHistoryListByContractCode(String contractCode);
	
	//멘토멘티 방문평가 결과 조회
	public List<ResultHistory> getResultHistoryListByVisitCode(String visitCode);
	
	//평가 기준 조회
	public List<EvaluationStandard> getEvaluationStandardList();
	
	//방문기록 조회
	public VisitHistory getVisitHistoryByVisitCode(String visitCode);
	
	//멘토멘티 신청유무 조회
	public boolean mentorMenteeIsApply(String companyCode);
	
	//멘토멘티 계약유무 조회
	public boolean hasNoMentorMenteeContract(String companyCode);
	
	//멘토멘티 평가 세부항목 조회
	public List<EvaluationDetailCategory> getEvalDetailCateList();
	
	//멘토멘티 평가 대분류 조회
	public List<EvaluationLargeCategory> getEvalLargeCateList();
	
	//멘토피드백 등록 토큰 조회 
	public List<MentorFeedbackToken> getMentorFeedbackTokenByCompanyCode(String companyCode);
	
	//멘토멘티 방문기록 등록
	public int addResultHistory(ResultHistory resultHistory);
	
	//멘토멘티 방문기록 수정
	public int modifyVisitHistory(VisitHistory visitHistoryParam);
	
	//멘토멘티 방문평가기록 수정
	public int modifyResultHistory(ResultHistory resultHistory);
	
	//멘토피드백 등록 토큰 조회 
	public List<MentorFeedbackToken> getMentorFeedbackTokenListByCompanyCode(String companyCode);
	
	//멘토피드백 등록 토큰 삭제
	public int removeTokenByTokenCode(String tokenCode);
	
	//멘토피드백 등록 토큰 등록
	public int addMentorFeedbackToken(MentorFeedbackToken token);
	
	//멘토피드백 등록 토큰 조회 (토큰코드)
	public MentorFeedbackToken getMentorFeedbackTokenByTokenCode(String tokenCode);
	
	//방문기록 조회
	public List<VisitHistory> getVisitHistoryList();

	//방문 평가 대분류 조회
	public List<EvaluationLargeCategory> getEvaluationLargeCategoryNoDetailCate();
	
	//방문기록 조회
	public List<ResultHistory> getResultHistoryList();
	
	//멘티신청 등록
	public int addMenteeApply(MMRegInfoMentee menteeRegInfo);
	
	
}
