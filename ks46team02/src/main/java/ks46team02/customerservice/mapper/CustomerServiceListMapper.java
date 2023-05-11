package ks46team02.customerservice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.customerservice.dto.AnswerDto;
import ks46team02.customerservice.dto.QuestionDto;
import ks46team02.customerservice.dto.QuestionTypeDto;

@Mapper
public interface CustomerServiceListMapper {

	/* 전체 문의 조회 */
	public List<QuestionDto> getQuestionList();

	/* 문의 조회 중 답변 내역 조회 */
	public List<AnswerDto> getAnswerList();

	/* 문의 유형 조회 */
	public List<QuestionTypeDto> getQuestionTypeList();
	
	/**/
	public void writeQuestionType(QuestionTypeDto questionTypeDto);

	public int getNextQuestionTypeCode();
}
