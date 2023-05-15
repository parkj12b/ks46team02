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

	public CustomerServiceListService(CustomerServiceListMapper customeerserviceListMapper, CustomerServiceListMapper customerserviceListMapper) {
		this.customerserviceListMapper = customerserviceListMapper;

	}

	public List<QuestionDto> getQuestionList() {
		List<QuestionDto> QuestionList = customerserviceListMapper.getQuestionList();
		return QuestionList;
	}


	public List<AnswerDto> getAnswerList() {
		List<AnswerDto> AnswerList = customerserviceListMapper.getAnswerList();
		return AnswerList;
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




		
	





	


	
}
	

	


