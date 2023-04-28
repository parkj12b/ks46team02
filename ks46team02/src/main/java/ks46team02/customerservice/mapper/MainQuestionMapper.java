package ks46team02.customerservice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.data.repository.query.Param;

import ks46team02.customerservice.dto.QuestionDto;

@Mapper
public interface MainQuestionMapper {
	


	public void writeQuestion(QuestionDto questionDto);
	
	public String getQuestionTypeName(int questionTypeCode);

	public List<QuestionDto> selectQuestionList(int questionTypeCode, RowBounds rowbounds);

	public QuestionDto selectQuestionInfo(String questionCode);

	public void updateQuestion(QuestionDto questiondto);

	public void deleteQuestion(String questionCode);

	public int getTotalCount(int idx);

}
