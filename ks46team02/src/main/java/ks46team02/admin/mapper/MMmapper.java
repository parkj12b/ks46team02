package ks46team02.admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.farm.dto.EvaluationDetailCategory;
import ks46team02.farm.dto.EvaluationLargeCategory;
import ks46team02.farm.dto.EvaluationStandard;
import ks46team02.farm.dto.MMRegInfoMentee;
import ks46team02.farm.dto.MMRegInfoMentor;
import ks46team02.farm.dto.ResultHistory;
import ks46team02.farm.dto.VisitHistory;

@Mapper
public interface MMmapper {
	
	//멘티 신청기록 or 로 조회
	public List<MMRegInfoMentee> getMenteeRegListOr(List<Map<String, Object>> searchList);

	//멘토 신청기록 or 로 조회
	public List<MMRegInfoMentor> getMentorRegListOr(List<Map<String, Object>> searchList);

	//멘토 신청상태 수정
	public int modifyMentorRegStatus(MMRegInfoMentor mentorRegInfo);

	//멘토 신청상태 삭제
	public int removeMentorRegHistory(MMRegInfoMentor mentorRegInfo);

	//멘티 신청기록 수정 
	public int modifyMenteeRegStatus(MMRegInfoMentee menteeRegInfo);

	//멘티 신청기록 삭제
	public int removeMenteeRegHistory(MMRegInfoMentee menteeRegInfo);

	//방문기록 리셋
	public int resetVisitHistory(VisitHistory visitHistory);

	//방문코드로 방문기록 삭제
	public int removeResultHistoryByVisitCode(ResultHistory resultHistory);

	//방문평가 카테고리 대분류 삭제
	public int removeEvaluationLargeCategory(EvaluationLargeCategory evalLargeCate);

	//방문평가 카테고리 대분류 수정
	public int modifyEvaluationLargeCategory(EvaluationLargeCategory evalLargeCate);

	//방문평가 카테고리 대분류 생성
	public int addEvaluationLargeCategory(EvaluationLargeCategory evalLargeCate);

	//방문평가 세부 카테고리 수정
	public int modifyEvaluationDetailCategory(EvaluationDetailCategory evalDetailCate);

	//방문평가 세부 카테코리 삭제
	public int removeEvaluationDetailCategory(EvaluationDetailCategory evalDetailCate);

	//방문평가 세부 카테고리 생성
	public int addEvaluationDetailCategory(EvaluationDetailCategory evalDetailCate);

	//방문평가 기준 수정
	public int modifyEvaluationStandard(EvaluationStandard evaluationStandard);

	//방문평가 기준 삭제
	public int removeEvaluationStandard(EvaluationStandard evaluationStandard);

	//방문평가 기준 생성
	public int addEvaluationStandard(EvaluationStandard evaluationStandard);

	//방문평가 평가기록 조회
	public List<ResultHistory> getResultHistoryList();

	//방문평가 평가기록 수정
	public int modifyResultHistory(ResultHistory resultHistory);

	//방문평가 평가기록 삭제
	public int removeResultHistory(ResultHistory resultHistory);

	
}
