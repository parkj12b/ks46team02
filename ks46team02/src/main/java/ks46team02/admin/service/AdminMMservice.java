package ks46team02.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks46team02.admin.mapper.MMmapper;
import ks46team02.common.mapper.MainMapper;
import ks46team02.farm.dto.EvaluationDetailCategory;
import ks46team02.farm.dto.EvaluationLargeCategory;
import ks46team02.farm.dto.EvaluationStandard;
import ks46team02.farm.dto.MMRegInfoMentee;
import ks46team02.farm.dto.MMRegInfoMentor;
import ks46team02.farm.dto.ResultHistory;
import ks46team02.farm.dto.VisitHistory;

@Service
@Transactional
public class AdminMMservice {

	private final MMmapper mMMapper;
	private final MainMapper mainMapper;
	
	private static final Logger log = LoggerFactory.getLogger(AdminMMservice.class);

	
	public AdminMMservice(MMmapper mMMapper, MainMapper mainMapper) {
		this.mMMapper = mMMapper;
		this.mainMapper = mainMapper;
	}
	
	//멘티 신청 리스트 or로 조회
	public List<MMRegInfoMentee> getMenteeRegListOr(List<Map<String, Object>> searchList) {
		List<MMRegInfoMentee> menteeRegList = mMMapper.getMenteeRegListOr(searchList);
		return menteeRegList;
	}

	//멘토 신청 리스트 or로 조회
	public List<MMRegInfoMentor> getMentorRegListOr(List<Map<String, Object>> searchList) {
		// TODO Auto-generated method stub
		List<MMRegInfoMentor> mentorRegList = mMMapper.getMentorRegListOr(searchList);
		log.info("{}", mentorRegList);
		return mentorRegList;
	}
	
	//멘토 신청 승인
	public int approveMentorRegStatus(MMRegInfoMentor mentorRegInfo) {
		// TODO Auto-generated method stub
		mentorRegInfo.setMentorApproval("approved");
		int result = mMMapper.modifyMentorRegStatus(mentorRegInfo);
		return result;
	}

	//멘토 신청 거부
	public int denyMentorRegStatus(MMRegInfoMentor mentorRegInfo) {
		// TODO Auto-generated method stub
		mentorRegInfo.setMentorApproval("denied");
		int result = mMMapper.modifyMentorRegStatus(mentorRegInfo);
		return result;
	}

	//멘토 신청 기록 삭제
	public int removeMentorRegHistory(MMRegInfoMentor mentorRegInfo) {
		// TODO Auto-generated method stub
		
		int result = mMMapper.removeMentorRegHistory(mentorRegInfo);
		return result;
	}

	//멘티 신청 승인
	public int approveMenteeRegStatus(MMRegInfoMentee menteeRegInfo) {
		// TODO Auto-generated method stub
		menteeRegInfo.setMenteeApproval("approved");
		int result = mMMapper.modifyMenteeRegStatus(menteeRegInfo);
		return result;
	}
	
	//멘티 신청 거부
	public int denyMenteeRegStatus(MMRegInfoMentee menteeRegInfo) {
		// TODO Auto-generated method stub
		menteeRegInfo.setMenteeApproval("denied");
		int result = mMMapper.modifyMenteeRegStatus(menteeRegInfo);
		return result;
	}

	//멘티 신청 기록 삭제
	public int removeMenteeRegHistory(MMRegInfoMentee menteeRegInfo) {
		// TODO Auto-generated method stub
		int result = mMMapper.removeMenteeRegHistory(menteeRegInfo);
		return result;
		
	}

	//방문기록 리셋
	public Map<String, Object> resetVisitHistory(VisitHistory visitHistory) {
		// TODO Auto-generated method stub
		String msg = "방문기록이 삭제되지 않았습니다.";
		boolean isSuccess = false;
		Map<String, Object> returnMap = new HashMap<>();
		
		String visitCode = visitHistory.getVisitCode();
		
		ResultHistory resultHistory = new ResultHistory();
		resultHistory.setVisitCode(visitCode);
		int result1 = mMMapper.removeResultHistoryByVisitCode(resultHistory);
		int result2 = 0;
		if(result1 == 1) {
			result2 = mMMapper.resetVisitHistory(visitHistory);
			if(result2 == 1) {
				msg = "방문기록이 삭제되었습니다.";
				isSuccess = true;
			}
		} 
		returnMap.put("msg", msg);
		returnMap.put("isSuccess", isSuccess);
		
		return returnMap;
	}

	//방문평가 카테고리 대분류 삭제
	public Map<String, Object> removeEvaluationLargeCategory(EvaluationLargeCategory evalLargeCate) {
		
		String msg = "대분류 카테고리가 삭제되지 않았습니다.";
		boolean isSuccess = false;
		Map<String, Object> returnMap = new HashMap<>();
		int result = mMMapper.removeEvaluationLargeCategory(evalLargeCate);
		if(result > 0) {
			isSuccess = true;
			msg = "대분류 카테고리가 삭제되었습니다.";
		}
		
		returnMap.put("msg", msg);
		returnMap.put("isSuccess", isSuccess);
		return returnMap;
	}

	//방문평가 카테고리 대분류 수정
	public Map<String, Object> modifyLargeCategory(EvaluationLargeCategory evalLargeCate) {
		
		String msg = "대분류 카테고리가 수정되지 않았습니다.";
		boolean isSuccess = false;
		Map<String, Object> returnMap = new HashMap<>();
		int result = mMMapper.modifyEvaluationLargeCategory(evalLargeCate);
		if(result > 0) {
			isSuccess = true;
			msg = "대분류 카테고리가 수정되었습니다.";
		}
		
		returnMap.put("msg", msg);
		returnMap.put("isSuccess", isSuccess);
		return returnMap;
	}

	//방문평가 카테고리 대분류 생성
	public Map<String, Object> addEvaluationLargeCategory(EvaluationLargeCategory evalLargeCate) {
		String msg = "대분류 카테고리가 추가되지 않았습니다.";
		boolean isSuccess = false;
		Map<String, Object> returnMap = new HashMap<>();
		String largeCategoryCode = mainMapper.autoIncrement("evaluation_large_category", "large_category_code");
		if(largeCategoryCode == null) {
			largeCategoryCode = "L_1";
		}
		
		evalLargeCate.setLargeCategoryCode(largeCategoryCode);
		int result = mMMapper.addEvaluationLargeCategory(evalLargeCate);
		if(result > 0) {
			isSuccess = true;
			msg = "대분류 카테고리가 추가되었습니다.";
		}
		
		returnMap.put("msg", msg);
		returnMap.put("isSuccess", isSuccess);
		return returnMap;
	}

	//방문평가 세부 카테고리 수정
	public Map<String, Object> modifyDetailCategory(EvaluationDetailCategory evalDetailCate) {
		String msg = "평가 세부항목이 수정되지 않았습니다.";
		boolean isSuccess = false;
		Map<String, Object> returnMap = new HashMap<>();
		int result = mMMapper.modifyEvaluationDetailCategory(evalDetailCate);
		if(result > 0) {
			isSuccess = true;
			msg = "평가 세부항목이 수정되었습니다.";
		}
		
		returnMap.put("msg", msg);
		returnMap.put("isSuccess", isSuccess);
		return returnMap;
	}

	//방문평가 세부 카테코리 삭제
	public Map<String, Object> deleteDetailCategory(EvaluationDetailCategory evalDetailCate) {
		String msg = "평가 세부항목이 삭제되지 않았습니다.";
		boolean isSuccess = false;
		Map<String, Object> returnMap = new HashMap<>();
		int result = mMMapper.removeEvaluationDetailCategory(evalDetailCate);
		if(result > 0) {
			isSuccess = true;
			msg = "평가 세부항목이 삭제되었습니다.";
		}
		
		returnMap.put("msg", msg);
		returnMap.put("isSuccess", isSuccess);
		return returnMap;
	}

	//방문평가 세부 카테고리 생성
	public Map<String, Object> addDetailCategory(EvaluationDetailCategory evalDetailCate) {
		String msg = "평가 세부항목이 추가되지 않았습니다.";
		boolean isSuccess = false;
		Map<String, Object> returnMap = new HashMap<>();
		
		String evaluationUnitCode = mainMapper.autoIncrement("evaluation_detail_category", "evaluation_unit_code");
		if(evaluationUnitCode == null) {
			evaluationUnitCode = "unit_1";
		}
		evalDetailCate.setEvaluationUnitCode(evaluationUnitCode);
		int result = mMMapper.addEvaluationDetailCategory(evalDetailCate);
		if(result > 0) {
			isSuccess = true;
			msg = "평가 세부항목이 추가되었습니다.";
		}
		
		returnMap.put("msg", msg);
		returnMap.put("isSuccess", isSuccess);
		return returnMap;
	}

	//방문평가 기준 수정
	public Map<String, Object> modifyEvaluationStandard(EvaluationStandard evaluationStandard) {
		String msg = "멘토멘티 평가 기준이 수정되지 않았습니다.";
		boolean isSuccess = false;
		Map<String, Object> returnMap = new HashMap<>();
		int result = mMMapper.modifyEvaluationStandard(evaluationStandard);
		if(result > 0) {
			isSuccess = true;
			msg = "멘토멘티 평가 기준이 수정되었습니다.";
		}
		
		returnMap.put("msg", msg);
		returnMap.put("isSuccess", isSuccess);
		return returnMap;
	}

	//방문평가 기준 삭제
	public Map<String, Object> removeEvaluationStandard(EvaluationStandard evaluationStandard) {
		String msg = "멘토멘티 평가 기준이 삭제되지 않았습니다.";
		boolean isSuccess = false;
		Map<String, Object> returnMap = new HashMap<>();
		int result = mMMapper.removeEvaluationStandard(evaluationStandard);
		if(result > 0) {
			isSuccess = true;
			msg = "멘토멘티 평가 기준이 삭제되었습니다.";
		}
		
		returnMap.put("msg", msg);
		returnMap.put("isSuccess", isSuccess);
		return returnMap;
	}

	//방문평가 기준 생성
	public Map<String, Object> addEvaluationStandard(EvaluationStandard evaluationStandard) {
		String msg = "멘토멘티 평가 기준이 추가되지 않았습니다.";
		boolean isSuccess = false;
		Map<String, Object> returnMap = new HashMap<>();
		
		String evaluationStandardCode = mainMapper.autoIncrement("evaluation_standard", "evaluation_standard_code");
		if(evaluationStandardCode == null) {
			evaluationStandardCode = "es_5";
		}
		evaluationStandard.setEvaluationStandardCode(evaluationStandardCode);
		int result = mMMapper.addEvaluationStandard(evaluationStandard);
		if(result > 0) {
			isSuccess = true;
			msg = "멘토멘티 평가 기준이 추가되었습니다.";
		}
		
		returnMap.put("msg", msg);
		returnMap.put("isSuccess", isSuccess);
		return returnMap;
	}

	//방문평가 평가기록 수정
	public Map<String, Object> modifyResultHistory(ResultHistory resultHistory) {
		String msg = "멘토 평가결과 기록이 추가되지 않았습니다.";
		boolean isSuccess = false;
		Map<String, Object> returnMap = new HashMap<>();
		
		int result = mMMapper.modifyResultHistory(resultHistory);
		if(result > 0) {
			isSuccess = true;
			msg = "멘토 평가결과 기록이 추가되었습니다.";
		}
		
		returnMap.put("msg", msg);
		returnMap.put("isSuccess", isSuccess);
		return returnMap;
	}

	//방문평가 평가기록 삭제
	public Map<String, Object> removeResultHistory(ResultHistory resultHistory) {
		String msg = "멘토 평가결과 기록이 삭제되지 않았습니다.";
		boolean isSuccess = false;
		Map<String, Object> returnMap = new HashMap<>();
		
		int result = mMMapper.removeResultHistory(resultHistory);
		if(result > 0) {
			isSuccess = true;
			msg = "멘토 평가결과 기록이 삭제되었습니다.";
		}
		
		returnMap.put("msg", msg);
		returnMap.put("isSuccess", isSuccess);
		return returnMap;
	}


}