package ks46team02.farm.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ks46team02.common.dto.AllContractInfo;
import ks46team02.common.dto.Member;
import ks46team02.common.mapper.MainMapper;
import ks46team02.farm.dto.EvaluationDetailCategory;
import ks46team02.farm.dto.EvaluationLargeCategory;
import ks46team02.farm.dto.EvaluationStandard;
import ks46team02.farm.dto.GoogleFormResult;
import ks46team02.farm.dto.MMContractInfo;
import ks46team02.farm.dto.MentorFeedbackToken;
import ks46team02.farm.dto.ResultHistory;
import ks46team02.farm.dto.VisitHistory;
import ks46team02.farm.mapper.MentorMenteeMapper;

@Service
public class MentorMenteeService {

	private final MentorMenteeMapper mentorMenteeMapper;
	private final MainMapper mainMapper;

	
	private static final Logger log = LoggerFactory.getLogger(MentorMenteeService.class);

	
	public MentorMenteeService(MentorMenteeMapper mentorMenteeMapper, MainMapper mainMapper) {
		this.mentorMenteeMapper = mentorMenteeMapper;
		this.mainMapper = mainMapper;
	}

	public Map<String, Object> getMentorMenteeRegisterStatus(String companyCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		int mmRegType = mentorMenteeMapper.getMMRegType(companyCode);
		Object mmRegInfo;
		if (mmRegType == 1) {
			mmRegInfo = mentorMenteeMapper.getMMRegStatMentor(companyCode);
		} else if (mmRegType == 2) {
			mmRegInfo = mentorMenteeMapper.getMMRegStatMentee(companyCode);
		} else {
			return null;
		}
		map.put("mmRegInfo", mmRegInfo);
		map.put("mmRegType", mmRegType);

		return map;

	}

	public Integer getMMRegType(String companyCode) {
		return mentorMenteeMapper.getMMRegType(companyCode);
	}

	public List<MMContractInfo> getMMContractList(String searchKey, String searchValue) {

		List<MMContractInfo> mmContractInfo = mentorMenteeMapper.getMMContractInfo(searchKey, searchValue);

		return mmContractInfo;
	}

	public List<AllContractInfo> getMMContractListByKeyValue(List<Map<String, Object>> searchList) {
		// TODO Auto-generated method stub

		List<AllContractInfo> contractInfoList = mainMapper.getContractInfoByKeyValueAnd(searchList);

		return contractInfoList;
	}

	public AllContractInfo getMMContractByKeyValue(List<Map<String, Object>> searchList) {
		List<AllContractInfo> contractInfo = mainMapper.getContractInfoByKeyValueAnd(searchList);

		if (contractInfo.isEmpty()) {
			return null;
		}

		return contractInfo.get(0);
	}

	public AllContractInfo getMMContractDetail(List<Map<String, Object>> searchList) {
		List<AllContractInfo> contractInfo = mainMapper.getContractInfoByKeyValueOr(searchList);

		if (contractInfo.isEmpty()) {
			return null;
		}

		return contractInfo.get(0);
	}

	public Map<String, Object> getVisitHistoryInfo(String contractCode) {
		// TODO Auto-generated method stub

		List<VisitHistory> visitHistoryList = mentorMenteeMapper.getVisitHistoryListByContractCode(contractCode);
		Map<String, Object> visitHistoryInfo = new HashMap<String, Object>();
		int numComplete = 0;
		for (VisitHistory history : visitHistoryList) {
			if (history.getVisitComplete().equals("complete")) {
				numComplete++;
			}
		}
		visitHistoryInfo.put("numComplete", numComplete);
		visitHistoryInfo.put("visitHistoryList", visitHistoryList);

		return visitHistoryInfo;
	}

	public List<ResultHistory> getResultHistoryList(String visitCode) {
		// TODO Auto-generated method stub

		List<ResultHistory> resultHistoryList = mentorMenteeMapper.getResultHistoryListByVisitCode(visitCode);
		return resultHistoryList;
	}

	public List<EvaluationStandard> getEvaluationStandardList() {
		// TODO Auto-generated method stub
		List<EvaluationStandard> evaluationStandardList = mentorMenteeMapper.getEvaluationStandardList();

		return evaluationStandardList;
	}

	public VisitHistory getVisitHistoryByVisitCode(String visitCode) {
		// TODO Auto-generated method stub

		VisitHistory visitHistory = mentorMenteeMapper.getVisitHistoryByVisitCode(visitCode);
		return visitHistory;
	}

	public boolean mentorMenteeIsApply(String companyCode) {
		// TODO Auto-generated method stub

		boolean isApply = mentorMenteeMapper.mentorMenteeIsApply(companyCode);

		return isApply;
	}

	public boolean isRegisterValid(String companyCode) {
		// TODO Auto-generated method stub

		boolean hasNoContract = mentorMenteeMapper.hasNoMentorMenteeContract(companyCode);

		return !hasNoContract;
	}

	public List<EvaluationDetailCategory> getEvalDetailCateList() {
		// TODO Auto-generated method stub

		List<EvaluationDetailCategory> evalDetailCateList = mentorMenteeMapper.getEvalDetailCateList();

		return evalDetailCateList;
	}

	public List<EvaluationLargeCategory> getEvalLargeCateList() {
		// TODO Auto-generated method stub

		List<EvaluationLargeCategory> evalLargeCateList = mentorMenteeMapper.getEvalLargeCateList();

		return evalLargeCateList;
	}

	@SuppressWarnings("unchecked")
	public void addFeedback(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		/**
		 *  memberInfo 입력된 멤버 정보
		 *  feedbackList 입력된 피드백 문장 리스트
		 *  feedbackScore Unit 별 점수 리스트
		 */
		Map<String, String> memberInfo = (Map<String, String>) paramMap.get("memberInfo");
		List<GoogleFormResult> feedbackList = (List<GoogleFormResult>) paramMap.get("feedbackList");
		List<GoogleFormResult> feedbackScore = (List<GoogleFormResult>) paramMap.get("feedbackScore");

		String tokenId = memberInfo.get("token_id");
		String memberId = memberInfo.get("member_id");
		Member member = mainMapper.getMemberInfoById(memberId);
		String companyCode = member.getCompanyCode();

		//checking token value
		List<MentorFeedbackToken> mentorFeedbackTokenList = mentorMenteeMapper
				.getMentorFeedbackTokenByCompanyCode(companyCode);
		boolean isTokenMatch = false;
		for(MentorFeedbackToken token: mentorFeedbackTokenList) {
			String tokenIdDB = token.getMentorFeedbackToken();
			if (tokenId.equals(tokenIdDB)) {
				isTokenMatch = true;
				break;
			}
		}
		if(!isTokenMatch) {
			return;
		}
		
		Map<String, String> feedbackTextMap = new HashMap<>();
		//피드백 리스트를 Map 형태로 바꿔서 title 로 검색 할수 있게 만든다.
		for (GoogleFormResult feedbackText : feedbackList) {
			String title = feedbackText.getTitle() ;
			String response = feedbackText.getResponse();
			
			feedbackTextMap.put(title, response);
		}
		/**
		 * 구글폼 에서 가져온 데이터
		 * db에 visitCode와 contractCode가 맞는 visitHistory 가 존재하는지
		 * validation 체크 없다면 수정이 일어남
		 */
		String visitCode = memberInfo.get("visit_code");
		String contractCode = memberInfo.get("contract_code");
		
		VisitHistory visitHistory = mentorMenteeMapper.getVisitHistoryByVisitCode(visitCode);
		String DBContractCode = visitHistory.getContractCode();
		
		if(!contractCode.equals(DBContractCode)) {
			log.info(visitCode + " : " + DBContractCode);
			return;
		}
		
		//이계약이 이멤버의 업체에게 종속되있는지도 join 을 통해 체크해줘야한다.
		/**
		 * 토큰 확인을 완료했으니 companyCode 는 valid 함으로 companyCode 를 사용해
		 * 성사된 계약이 회원의 업체의 계약이 맞는지 확인. (권한체크)
		 */
		List<Map<String, Object>> searchList = new ArrayList<>();
		Map <String,Object> contractParam = new HashMap<>();
		contractParam.put("contractor_company_code", companyCode);
		contractParam.put("contract_code", contractCode);
		Set<String> keyset = contractParam.keySet();
		for(String key: keyset) {
			Map<String, Object> map = new HashMap<>();
			map.put("key", key);
			map.put("value", contractParam.get(key));
			searchList.add(map);
		}
		List<AllContractInfo> contractInfo = mainMapper.getContractInfoByKeyValueAnd(searchList);
		// if 가져온 계약 정보가 한개보다 많거나 적으면 return void
		if(contractInfo.size() != 1) {
			log.info("contractInfo size={}",contractInfo.size());
			 return;
		}
		/**
		 *  권한 체크 끝
		 *  오늘 작성/수정 할수 있는 히스토리인지 체크
		 */
		String startDateString = visitHistory.getPeriodStartDate();
		String endDateString = visitHistory.getPeriodEndDate();
		
		Date dateToday = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd",Locale.KOREAN);
		Date startDate = format.parse(startDateString);
		Date endDate = format.parse(endDateString);
		
		boolean isbetween = dateToday.after(startDate) && dateToday.before(endDate);
		/**
		 * 오늘 날짜가 기간 안에 있지 않으면 return void
		 */
		if(!isbetween) {
			log.info(startDateString + " : " + endDateString);
			return;
		}
		
		//db 수정 update visitHistory, insert result_history
		
		int totalScore = 0;
		
		for (GoogleFormResult score : feedbackScore) {
			log.info(score.getTitle());
			String[] titleSplit = score.getTitle().split("\\.");
			
			String title = titleSplit[0];
			int numScore = Integer.parseInt(score.getResponse());
			String feedbackText = feedbackTextMap.get(title);
			String evaluationUnitCode = title.replaceAll("[0-9]", "") + "_" + title.replaceAll("[a-zA-Z]", "");
			
			
			//map to pass to mapper
			String resultCode = mainMapper.autoIncrement("result_history", "result_code");
			ResultHistory resultHistory = new ResultHistory();
			resultHistory.setResultCode(resultCode);
			resultHistory.setEvaluationUnitCode(evaluationUnitCode);
			resultHistory.setVisitCode(visitCode);
			resultHistory.setResultFeedback(feedbackText);
			resultHistory.setContractCode(DBContractCode);
			resultHistory.setMemberId(memberId);
			resultHistory.setResultEvaluationPoint(numScore);
						
			totalScore += numScore;  
			List<ResultHistory> resultHistoryDB = mentorMenteeMapper.getResultHistoryListByVisitCode(visitCode);
			int resultHistoryDBSize = resultHistoryDB.size();
			if(resultHistoryDBSize == 0) {
				int result = mentorMenteeMapper.addResultHistory(resultHistory);				
			} else if(resultHistoryDBSize == 1) {
				int result = mentorMenteeMapper.modifyResultHistory(resultHistory);
			}
			
			
		}
		
		int numQuestionUsed = feedbackScore.size();
		
		
		VisitHistory visitHistoryParam = new VisitHistory();
		visitHistoryParam.setVisitCode(visitCode);
		visitHistoryParam.setVisitComplete("complete");
		visitHistoryParam.setTotalScoreVisit(totalScore);
		visitHistoryParam.setTotalDetailItemNum(numQuestionUsed);
		
		
		int result = mentorMenteeMapper.modifyVisitHistory(visitHistoryParam);
	}

}
