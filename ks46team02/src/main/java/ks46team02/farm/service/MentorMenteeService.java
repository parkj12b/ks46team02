package ks46team02.farm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import ks46team02.common.dto.AllContractInfo;
import ks46team02.common.mapper.MainMapper;
import ks46team02.farm.dto.EvaluationDetailCategory;
import ks46team02.farm.dto.EvaluationLargeCategory;
import ks46team02.farm.dto.EvaluationStandard;
import ks46team02.farm.dto.MMContractInfo;
import ks46team02.farm.dto.ResultHistory;
import ks46team02.farm.dto.VisitHistory;
import ks46team02.farm.mapper.MentorMenteeMapper;

@Service
public class MentorMenteeService {

	private final MentorMenteeMapper mentorMenteeMapper;
	private final MainMapper mainMapper;
	
	public MentorMenteeService(MentorMenteeMapper mentorMenteeMapper, MainMapper mainMapper) {
		this.mentorMenteeMapper = mentorMenteeMapper;
		this.mainMapper = mainMapper;
	}
	
	public Map<String, Object> getMentorMenteeRegisterStatus(String companyCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		int mmRegType = mentorMenteeMapper.getMMRegType(companyCode);
		Object mmRegInfo;
		if(mmRegType == 1) {
			mmRegInfo = mentorMenteeMapper.getMMRegStatMentor(companyCode);			
		} else if(mmRegType == 2) {
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

	public List<MMContractInfo> getMMContractList(String searchKey,String searchValue) {

		List<MMContractInfo> mmContractInfo = mentorMenteeMapper.getMMContractInfo(searchKey,searchValue);
		
		return mmContractInfo;
	}

	public List<AllContractInfo> getMMContractListByKeyValue(List<Map<String, Object>> searchList) {
		// TODO Auto-generated method stub
		
		List<AllContractInfo> contractInfoList = mainMapper.getContractInfoByKeyValueAnd(searchList);
		
		return contractInfoList;
	}
	
	public AllContractInfo getMMContractByKeyValue(List<Map<String, Object>> searchList) {
		List<AllContractInfo> contractInfo = mainMapper.getContractInfoByKeyValueAnd(searchList);
		
		if(contractInfo.isEmpty()) {
			return null;
		}
		
		return contractInfo.get(0);
	}
	
	public AllContractInfo getMMContractDetail(List<Map<String, Object>> searchList) {
		List<AllContractInfo> contractInfo = mainMapper.getContractInfoByKeyValueOr(searchList);
		
		if(contractInfo.isEmpty()) {
			return null;
		}
		
		return contractInfo.get(0);
	}

	public Map<String, Object> getVisitHistoryInfo(String contractCode) {
		// TODO Auto-generated method stub
		
		List<VisitHistory> visitHistoryList = mentorMenteeMapper.getVisitHistoryListByContractCode(contractCode);
		Map<String,Object> visitHistoryInfo = new HashMap<String,Object>();
		int numComplete = 0;
		for(VisitHistory history : visitHistoryList) {
			if(history.getVisitComplete().equals("complete")) {
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

	
	
	
}
