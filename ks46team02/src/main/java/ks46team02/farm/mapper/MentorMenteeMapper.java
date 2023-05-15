package ks46team02.farm.mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.common.dto.AllContractInfo;
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

	public MMRegInfoMentee getMMRegStatMentee(String companyCode);
	public MMRegInfoMentor getMMRegStatMentor(String companyCode);

	public int getMMRegType(String companyCode);
	public List<MMContractInfo> getMMContractInfo(String searchKey, String searchValue);
	public List<VisitHistory> getVisitHistoryListByContractCode(String contractCode);
	public List<ResultHistory> getResultHistoryListByVisitCode(String visitCode);
	public List<EvaluationStandard> getEvaluationStandardList();
	public VisitHistory getVisitHistoryByVisitCode(String visitCode);
	public boolean mentorMenteeIsApply(String companyCode);
	public boolean hasNoMentorMenteeContract(String companyCode);
	public List<EvaluationDetailCategory> getEvalDetailCateList();
	public List<EvaluationLargeCategory> getEvalLargeCateList();
	public List<MentorFeedbackToken> getMentorFeedbackTokenByCompanyCode(String companyCode);
	public int addResultHistory(ResultHistory resultHistory);
	public int modifyVisitHistory(VisitHistory visitHistoryParam);
	public int modifyResultHistory(ResultHistory resultHistory);
	public List<MentorFeedbackToken> getMentorFeedbackTokenListByCompanyCode(String companyCode);
	public int removeTokenByTokenCode(String tokenCode);
	public int addMentorFeedbackToken(MentorFeedbackToken token);
	public MentorFeedbackToken getMentorFeedbackTokenByTokenCode(String tokenCode);
	public List<VisitHistory> getVisitHistoryList();
	public List<EvaluationLargeCategory> getEvaluationLargeCategoryNoDetailCate();
	
	
}
