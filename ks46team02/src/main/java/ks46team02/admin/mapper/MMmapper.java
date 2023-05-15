package ks46team02.admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.farm.dto.EvaluationDetailCategory;
import ks46team02.farm.dto.EvaluationLargeCategory;
import ks46team02.farm.dto.MMRegInfoMentee;
import ks46team02.farm.dto.MMRegInfoMentor;
import ks46team02.farm.dto.ResultHistory;
import ks46team02.farm.dto.VisitHistory;

@Mapper
public interface MMmapper {

	public List<MMRegInfoMentee> getMenteeRegListOr(List<Map<String, Object>> searchList);

	public List<MMRegInfoMentor> getMentorRegListOr(List<Map<String, Object>> searchList);

	public int modifyMentorRegStatus(MMRegInfoMentor mentorRegInfo);

	public int removeMentorRegHistory(MMRegInfoMentor mentorRegInfo);

	public int modifyMenteeRegStatus(MMRegInfoMentee menteeRegInfo);

	public int removeMenteeRegHistory(MMRegInfoMentee menteeRegInfo);

	public int resetVisitHistory(VisitHistory visitHistory);

	public int removeResultHistory(ResultHistory resultHistory);

	public int removeEvaluationLargeCategory(EvaluationLargeCategory evalLargeCate);

	public int modifyEvaluationLargeCategory(EvaluationLargeCategory evalLargeCate);

	public int addEvaluationLargeCategory(EvaluationLargeCategory evalLargeCate);

	public int modifyEvaluationDetailCategory(EvaluationDetailCategory evalDetailCate);

	public int removeEvaluationDetailCategory(EvaluationDetailCategory evalDetailCate);

	
}
