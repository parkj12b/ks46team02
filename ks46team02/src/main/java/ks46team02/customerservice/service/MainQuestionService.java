package ks46team02.customerservice.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import ks46team02.customerservice.mapper.MainQuestionMapper;
import ks46team02.common.dto.Member;
import ks46team02.common.mapper.MainMapper;
import ks46team02.customerservice.dto.PageDto;
import ks46team02.customerservice.dto.QuestionDto;
import ks46team02.customerservice.dto.QuestionTypeDto;

@Service
@PropertySource("classpath:properties/option.properties")
public class MainQuestionService {

	@Value("${path.upload}")
	private String path_upload;

	@Autowired
	private MainQuestionMapper mainquestionmapper;
	@Autowired
	private MainMapper mainMapper;

	@Resource(name="loginMemberBean")
	@Lazy
	private Member loginMemberBean;

	@Value("${page.listcnt}")
	private int pagelistcnt;

	@Value("${page.paginationcnt}")
	private int pagenationcnt;

	public List<QuestionTypeDto> getQuestionTypeList(int questionTypeCode) {
		List<QuestionTypeDto> questionTypeList = mainquestionmapper.getQuestionTypeList();
		return questionTypeList;
	}

	public String getQuestionTypeName(int questionTypeCode) {
		return mainquestionmapper.getQuestionTypeName(questionTypeCode);
	}

	public void writeQuestion(QuestionDto questiondto) {
		mainquestionmapper.writeQuestion(questiondto);
	}

	public QuestionDto selectQuestionInfo(String questionCode) {
		return mainquestionmapper.selectQuestionInfo(questionCode);
	}

	public List<QuestionDto> selectQuestionList(int questionTypeCode, int page) {

		int start = (page - 1) * pagelistcnt;
		RowBounds rowbounds = new RowBounds(start, pagelistcnt);

		return mainquestionmapper.selectQuestionList(questionTypeCode, rowbounds);
	}

	public void modifyQuestionDto(QuestionDto questiondto) {
		mainquestionmapper.modifyQuestionDto(questiondto);
	}

	public void deletequestion(String questionCode) {
		mainquestionmapper.deleteQuestion(questionCode);

	}

	public PageDto getTotalCount(int idx, int page) {

		int totalcnt = mainquestionmapper.getTotalCount(idx);

		PageDto pagebean = new PageDto(totalcnt, page, pagelistcnt, pagenationcnt);

		return pagebean;
	}

}
