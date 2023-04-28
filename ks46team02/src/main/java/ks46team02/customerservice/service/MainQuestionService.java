package ks46team02.customerservice.service;


import java.util.List;


import org.apache.ibatis.session.RowBounds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import jakarta.annotation.Resource;
import ks46team02.customerservice.mapper.MainQuestionMapper;
import ks46team02.common.dto.Member;
import ks46team02.customerservice.dto.PageDto;
import ks46team02.customerservice.dto.QuestionDto;


@Service
@PropertySource("classpath:properties/option.properties")
public class MainQuestionService {

	@Value("${path.upload}")
	private String path_upload;
	

	@Autowired
	private  MainQuestionMapper questionmapper;
	
	@Resource(name="loginMemberDto")
	@Lazy
	private Member loginMemberDto;
	
	@Value("${page.listcnt}")
	private int pagelistcnt;

	@Value("${page.paginationcnt}")
	private int pagenationcnt;
	
	public String getQuestionTypeName(int questionTypeCode) {
		return questionmapper.getQuestionTypeName(questionTypeCode);
	}
	
	public void writeQuestion(QuestionDto questiondto) {
		
	}
	


	public QuestionDto selectQuestionInfo(String questionCode) {
		return questionmapper.selectQuestionInfo(questionCode);
	}

	public List<QuestionDto> selectQuestionList(int questionTypeCode, int page) {

		int start = (page - 1) * pagelistcnt;
		RowBounds rowbounds = new RowBounds(start, pagelistcnt);

		return questionmapper.selectQuestionList(questionTypeCode, rowbounds);
	}

	public PageDto getTotalCount(String questionTypeCode, int page) {
		
		int totalcnt = questionmapper.getTotalCount(page);

		PageDto pagedto = new PageDto(totalcnt, page, pagelistcnt, pagenationcnt);

		return pagedto;
	}

	public void updateQuestion(@Validated QuestionDto questiondto) {
		
		
		
	}

	public void deletequestion(String questionCode) {
		questionmapper.deleteQuestion(questionCode);
		
	}
	
	public PageDto getTotalCount(int idx, int page) {

		int totalcnt = questionmapper.getTotalCount(idx);
		
		PageDto pagebean = new PageDto(totalcnt, page, pagelistcnt, pagenationcnt);
		
		return pagebean;
	}




	
	



}
