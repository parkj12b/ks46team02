package ks46team02.customerservice.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import ks46team02.customerservice.dto.QuestionDto;

@Mapper
public interface MainQuestionMapper {

	public void writeQuestion(QuestionDto questionDto);
	
	public String getQuestionTypeName(int question_type_code);
	
	public List<QuestionDto> selectQuestionList(int question_type_code, RowBounds row);

	public MainQuestionMapper selectQuestionInfo(String code);

	public int getTotalCount(int code);
	
	

	
	
//	public int getTotalCount(int page) {
//		return 0;
//	}
//
//	public List<QuestionDto> selectQuestionList(String code, RowBounds row) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public QuestionBean selectQuestionInfo(String code) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public String getQuestionTypeName(String code) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
}

	
