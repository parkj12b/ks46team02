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
	
	/* 문의 세부내용 조회 */
	public QuestionDto getQuestionByCode(@Param("questionCode") String questionCode);
	
	/* 문의 내용에 대한 답변 등록 */
	public void addAnswer(AnswerDto answerDto);
	
	/* 답변 등록시 문의 상태 수정 */
	public void modifyQuestionStatus(QuestionDto questionDto);
	
	/* 문의애 대한 답변 내역 조회 */
	public List<AnswerDto> getAnswerList();

	/* 답변 수정 */
	public int modifyAnswerList(AnswerDto answerDto);

	/* 답변 세부내용 조회 */
	public AnswerDto getAnswerByCode(@Param("answerCode") String answerCode);
	
	/**/
	public QuestionDto selectQuestionInfo(String questionCode);

	/*답변 삭제*/
	public int updateAnswerDeleteStatus(String answerCode);

	/* 문의 유형 조회 */
	public List<QuestionTypeDto> getQuestionTypeList();

	public int getNextQuestionTypeCode();

	public int insertQuestionType(QuestionTypeDto questionTypeDto);

	public String updateQuestionType(QuestionTypeDto questionTypeDto);

	public boolean deleteQuestionType(int questionTypeCode);


}
