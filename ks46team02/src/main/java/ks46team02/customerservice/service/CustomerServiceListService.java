package ks46team02.customerservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks46team02.customerservice.mapper.CustomerServiceListMapper;
import ks46team02.customerservice.dto.AnswerDto;
import ks46team02.customerservice.dto.QuestionDto;
import ks46team02.customerservice.dto.QuestionTypeDto;

@Service
@Transactional
public class CustomerServiceListService {

	private final CustomerServiceListMapper customerserviceListMapper;

	public CustomerServiceListService(CustomerServiceListMapper customeerserviceListMapper,
			CustomerServiceListMapper customerserviceListMapper) {
		this.customerserviceListMapper = customerserviceListMapper;

	}

	public List<QuestionDto> getQuestionList() {
		List<QuestionDto> QuestionList = customerserviceListMapper.getQuestionList();
		return QuestionList;
	}

	public QuestionDto getQuestionByCode(String questionCode) {
		return customerserviceListMapper.getQuestionByCode(questionCode);
	}

	public void addAnswer(AnswerDto answerDto) {
		customerserviceListMapper.addAnswer(answerDto);
	}
	
	public void modifyQuestionStatus(QuestionDto questionDto) {
        customerserviceListMapper.modifyQuestionStatus(questionDto);
    }


	public List<AnswerDto> getAnswerList() {
		List<AnswerDto> AnswerList = customerserviceListMapper.getAnswerList();
		return AnswerList;
	}

	public AnswerDto getAnswerByCode(String answerCode) {
		return customerserviceListMapper.getAnswerByCode(answerCode);
	}

	public boolean removeAnswer(String answerCode) {
		int rowsAffected = customerserviceListMapper.updateAnswerDeleteStatus(answerCode);
		return rowsAffected > 0;
	}

	public List<QuestionTypeDto> getQuestionTypeList() {
		List<QuestionTypeDto> QuestionTypeList = customerserviceListMapper.getQuestionTypeList();
		return QuestionTypeList;
	}

	public int getNextQuestionTypeCode() {
		return customerserviceListMapper.getNextQuestionTypeCode();
	}

	public int registerQuestionType(QuestionTypeDto questionTypeDto) {
		return customerserviceListMapper.insertQuestionType(questionTypeDto);

	}

	public String updateQuestionTypeName(QuestionTypeDto questionTypeDto) {
		return customerserviceListMapper.updateQuestionType(questionTypeDto);

	}

	public boolean deleteQuestionType(int questionTypeCode) {
		return customerserviceListMapper.deleteQuestionType(questionTypeCode);
	}
}
