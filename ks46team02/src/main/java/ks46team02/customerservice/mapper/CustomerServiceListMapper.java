package ks46team02.customerservice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import ks46team02.customerservice.dto.AnswerDto;
import ks46team02.customerservice.dto.QuestionDto;
import ks46team02.customerservice.dto.QuestionTypeDto;

@Mapper
public interface CustomerServiceListMapper {

	/* 전체 문의 조회 */
	public List<QuestionDto> getQuestionList();

	/* 문의 조회 중 답변 내역 조회 */
	public List<AnswerDto> getAnswerList();

	/* 답변 수정 */
	public int modifyAnswerList(AnswerDto answerDto);

	/* 답변 세부내용 조회 */
	public AnswerDto getAnswerByCode(@Param("answerCode") String answerCode);

	/*
	 * 답변 삭제 public boolean removeAnswer(String answerCode);
	 */

	/* 문의 유형 조회 */
	public List<QuestionTypeDto> getQuestionTypeList();

	public int getNextQuestionTypeCode();

	public int insertQuestionType(QuestionTypeDto questionTypeDto);

	public String updateQuestionType(QuestionTypeDto questionTypeDto);

	public boolean deleteQuestionType(int questionTypeCode);

}
