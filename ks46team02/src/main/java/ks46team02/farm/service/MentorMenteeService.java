package ks46team02.farm.service;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpSession;
import ks46team02.common.dto.AllContractInfo;
import ks46team02.common.dto.ContractApprovalStandard;
import ks46team02.common.dto.FileRelation;
import ks46team02.common.dto.Member;
import ks46team02.common.mapper.MainMapper;
import ks46team02.farm.dto.EvaluationDetailCategory;
import ks46team02.farm.dto.EvaluationLargeCategory;
import ks46team02.farm.dto.EvaluationStandard;
import ks46team02.farm.dto.GoogleFormResult;
import ks46team02.farm.dto.MMContractInfo;
import ks46team02.farm.dto.MMRegInfoMentee;
import ks46team02.farm.dto.MMRegInfoMentor;
import ks46team02.farm.dto.MentorFeedbackToken;
import ks46team02.farm.dto.ResultHistory;
import ks46team02.farm.dto.VisitHistory;
import ks46team02.farm.mapper.MentorMenteeMapper;

@Service
@Transactional
public class MentorMenteeService {

	private final MentorMenteeMapper mentorMenteeMapper;
	private final MainMapper mainMapper;
	private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe
	
	private static final Logger log = LoggerFactory.getLogger(MentorMenteeService.class);

	
	public MentorMenteeService(MentorMenteeMapper mentorMenteeMapper, MainMapper mainMapper) {
		this.mentorMenteeMapper = mentorMenteeMapper;
		this.mainMapper = mainMapper;
	}

	//멘토멘티 신청 기록 조회
	public Map<String, Object> getMentorMenteeRegisterStatus(String companyCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		MMRegInfoMentee mmRegInfoMentee;
		MMRegInfoMentor mmRegInfoMentor;

		mmRegInfoMentor = mentorMenteeMapper.getMMRegStatMentor(companyCode);
		mmRegInfoMentee = mentorMenteeMapper.getMMRegStatMentee(companyCode);
		
		map.put("mmRegType", 0);
		if(mmRegInfoMentee != null) {
			map.put("mmRegInfo", mmRegInfoMentee);
			map.put("mmRegType", 2);
			
		} 
		if(mmRegInfoMentor != null) {
			map.put("mmRegInfo", mmRegInfoMentor);
			map.put("mmRegType", 1);
			
		}	

		return map;

	}
	
	//멘토멘티 권한 조회
	public Integer getMMRegType(String companyCode) {
		return mentorMenteeMapper.getMMRegType(companyCode);
	}

	//멘토멘티 계약 정보조회
	public List<MMContractInfo> getMMContractList(String searchKey, String searchValue) {

		List<MMContractInfo> mmContractInfo = mentorMenteeMapper.getMMContractInfo(searchKey, searchValue);

		return mmContractInfo;
	}

	//멘토멘티 계약 정보 조회
	public List<AllContractInfo> getMMContractListByKeyValue(List<Map<String, Object>> searchList) {
		// TODO Auto-generated method stub

		List<AllContractInfo> contractInfoList = mainMapper.getContractInfoByKeyValueAnd(searchList);

		return contractInfoList;
	}

	//멘토멘티 계약 조회
	public AllContractInfo getMMContractByKeyValue(List<Map<String, Object>> searchList) {
		List<AllContractInfo> contractInfo = mainMapper.getContractInfoByKeyValueAnd(searchList);

		if (contractInfo.isEmpty()) {
			return null;
		}

		return contractInfo.get(0);
	}

	//멘토멘티 계약 상세정보
	public AllContractInfo getMMContractDetail(List<Map<String, Object>> searchList) {
		List<AllContractInfo> contractInfo = mainMapper.getContractInfoByKeyValueOr(searchList);

		if (contractInfo.isEmpty()) {
			return null;
		}

		return contractInfo.get(0);
	}

	//멘토멘티 방문기록 조회
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

	//멘토멘티 방문평가 조회
	public List<ResultHistory> getResultHistoryList(String visitCode) {
		// TODO Auto-generated method stub

		List<ResultHistory> resultHistoryList = mentorMenteeMapper.getResultHistoryListByVisitCode(visitCode);
		return resultHistoryList;
	}

	//멘토멘티 방문평가 기준 조회
	public List<EvaluationStandard> getEvaluationStandardList() {
		// TODO Auto-generated method stub
		List<EvaluationStandard> evaluationStandardList = mentorMenteeMapper.getEvaluationStandardList();

		return evaluationStandardList;
	}

	//멘토멘티 방문기록 조회
	public VisitHistory getVisitHistoryByVisitCode(String visitCode) {
		// TODO Auto-generated method stub

		VisitHistory visitHistory = mentorMenteeMapper.getVisitHistoryByVisitCode(visitCode);
		return visitHistory;
	}

	//멘토멘티 신청유무
	public boolean mentorMenteeIsApply(String companyCode) {
		// TODO Auto-generated method stub

		boolean isApply = mentorMenteeMapper.mentorMenteeIsApply(companyCode);

		return isApply;
	}

	//멘토멘티 계약 유부
	public boolean isRegisterValid(String companyCode) {
		// TODO Auto-generated method stub

		boolean hasNoContract = mentorMenteeMapper.hasNoMentorMenteeContract(companyCode);

		return !hasNoContract;
	}

	//멘토멘티 방문평가 상세캬 기준조회
	public List<EvaluationDetailCategory> getEvalDetailCateList() {
		// TODO Auto-generated method stub

		List<EvaluationDetailCategory> evalDetailCateList = mentorMenteeMapper.getEvalDetailCateList();

		return evalDetailCateList;
	}

	//멘토멘티 방문평가 대분류 조회
	public List<EvaluationLargeCategory> getEvalLargeCateList() {
		// TODO Auto-generated method stub

		List<EvaluationLargeCategory> evalLargeCateList = mentorMenteeMapper.getEvalLargeCateList();

		return evalLargeCateList;
	}

	//멘토멘티 방문평가 작성/수정
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
			if(resultCode == null) {
				resultCode = "survey_num_1";
			}
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

	//멘토멘티 방문평가 작성토큰 조회
	public List<MentorFeedbackToken> getMentorFeedbackTokenList(String companyCode) {
		// TODO Auto-generated method stub
		List<MentorFeedbackToken> mentorFeedbackToken = mentorMenteeMapper.getMentorFeedbackTokenListByCompanyCode(companyCode);
		
		
		return mentorFeedbackToken;
	}

	//멘토멘티 방문평가 작성토큰 삭제
	public int removeTokenByTokenCode(String tokenCode) {
		// TODO Auto-generated method stub
		int result = mentorMenteeMapper.removeTokenByTokenCode(tokenCode);
		
		return result;
	}

	//멘토멘티 방문평가 작성토큰 등록
	public MentorFeedbackToken addMentorFeedbackToken(MentorFeedbackToken token) {
		// TODO Auto-generated method stub
		String tokenCode = mainMapper.autoIncrement("mentor_feedback_token","mentor_feedback_token_code");
		if(tokenCode == null) {
			tokenCode = "token_code_1";
		}
		token.setMentorFeedbackTokenCode(tokenCode);
		
		byte[] randomBytes = new byte[24];
	    secureRandom.nextBytes(randomBytes);
	    String tokenNum = base64Encoder.encodeToString(randomBytes);
		token.setMentorFeedbackToken(tokenNum);
	    
		int result = mentorMenteeMapper.addMentorFeedbackToken(token);
		MentorFeedbackToken tokenInfo = mentorMenteeMapper.getMentorFeedbackTokenByTokenCode(tokenCode);
		
		return tokenInfo;
	}

	//멘토멘티 방문평가 작성토큰 조회
	public MentorFeedbackToken getMentorFeedbackTokenByTokenCode(String tokenCode) {
		// TODO Auto-generated method stub
		MentorFeedbackToken tokenInfo = mentorMenteeMapper.getMentorFeedbackTokenByTokenCode(tokenCode);
		return tokenInfo;
	}

	//멘토멘티 방문평가 기준 대분류 조회
	public List<EvaluationLargeCategory> getEvaluationLargeCategoryNoDetailCate() {
		// TODO Auto-generated method stub
		List<EvaluationLargeCategory> list = mentorMenteeMapper.getEvaluationLargeCategoryNoDetailCate();
		
		return list;
	}

	//멘토멘티 멘티 신청
	public Map<String, Object> addMenteeApply(MMRegInfoMentee menteeRegInfo, HttpSession session) {
		Map<String,Object> returnMap = new HashMap<>();
		FileRelation fileRelation = new FileRelation();
		ContractApprovalStandard contApprStand = new ContractApprovalStandard();
		
		contApprStand.setContAppStand("멘티승인기준");
		contApprStand.setStandardDescription("last_year_sales_lower");
		
		//menteeRegInfo에 들어갈 정보들
		String sessionCompanyCode = (String) session.getAttribute("sessionCompanyCode");
		String memberId = (String) session.getAttribute("sessionId");
		ContractApprovalStandard menteeApprovalStandard = mainMapper.getContractApprovalStandard(contApprStand);
		int salesStandard = menteeApprovalStandard.getContAppStandValue();
		
		boolean salesSuitability = menteeRegInfo.getPreviousYearSales() <= salesStandard;
		
		
		String fileAssociateKey = mainMapper.autoIncrement("file_relation", "file_associate_key");
		if(fileAssociateKey == null) {fileAssociateKey = "file_relation_1";}
		
		fileRelation.setFileAssociateKey(fileAssociateKey);
		fileRelation.setTableName("mentee_apply");
	
		String menteeAppCode = mainMapper.autoIncrement("mentee_apply", "mentee_app_code");
		if(menteeAppCode == null) {menteeAppCode = "mentee_app_code_1";}
		
		fileRelation.setTablePrimaryKey(menteeAppCode);
		mainMapper.addFileRelation(fileRelation);
		
		menteeRegInfo.setMenteeAppCode(menteeAppCode);
		menteeRegInfo.setDocumentaryEvidence(fileAssociateKey);
		menteeRegInfo.setCompanyCode(sessionCompanyCode);
		menteeRegInfo.setMemberId(memberId);
		menteeRegInfo.setSalesSuitability(salesSuitability);
		
		boolean isSuccess = false;
		String msg = "멘티 신청에 실패하였습니다.";
		
		int result = mentorMenteeMapper.addMenteeApply(menteeRegInfo);
		if(result > 0) {
			isSuccess = true;
			msg = "멘티 신청에 성공하였습니다.";
		}
		
		returnMap.put("msg", msg);
		returnMap.put("isSuccess", isSuccess);
		returnMap.put("fileAssociateKey", fileAssociateKey);
		
		return returnMap;
	}

	
}
