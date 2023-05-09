package ks46team02.customerservice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import ks46team02.customerservice.dto.QuestionDto;
import ks46team02.customerservice.dto.QuestionTypeDto;

@Mapper
public interface MainQuestionMapper {
	
	/*고객센터 메인페이지 문의 유형 조회 */
	public List<QuestionTypeDto>getQuestionTypeList(); 
	/*문의 페이지 글쓰기*/
	public void writeQuestion(QuestionDto questionDto);
	/*  */
	public String getQuestionTypeName(int questionTypeCode);
	
	public List<QuestionDto> selectQuestionList(int questionTypeCode, RowBounds rowbounds);

	public QuestionDto selectQuestionInfo(String questionCode);

	public void modifyQuestionDto(QuestionDto questiondto);

	public void deleteQuestion(String questionCode);

	public int getTotalCount(int idx);

}
