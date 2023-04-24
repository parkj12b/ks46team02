package ks46team02.customerservice.service;

import java.util.List;


import org.apache.ibatis.session.RowBounds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import ks46team02.customerservice.mapper.MainQuestionMapper;
import ks46team02.customerservice.dto.PageDto;
import ks46team02.customerservice.dto.QuestionDto;


@Service
@PropertySource("classpath:properties/option.properties")
public class MainQuestionService {

//	@Value("${path.upload}")
//	private String path_upload;
	

	@Autowired
	private  MainQuestionMapper questionmapper;
	
	@Value("${page.listcnt}")
	private int pagelistcnt;

	@Value("${page.paginationcnt}")
	private int pagenationcnt;

	public String getQuestionTypeName(int question_type_code) {
		return questionmapper.getQuestionTypeName(question_type_code);
	}
	
	public void writeQuestion(QuestionDto questiondto) {
		
//		MultipartFile file = questiondto.getUpload_file();
//		
//		if(file.getSize() > 0) {
//			
//			String file_name = saveUploadFile(file);
//			//파일이름 저장
//			questiondto.setQuestion_file(file_name);
//		}
//		
//		questiondto.setMember(user.getJ_idx());
		
		questionmapper.writeQuestion(questiondto);
		
	}
	
	

	public MainQuestionMapper selectQuestionInfo(String code) {
		return questionmapper.selectQuestionInfo(code);
	}

	public List<QuestionDto> selectQuestionList(int question_type_code, int page) {

		int start = (page - 1) * pagelistcnt;
		RowBounds row = new RowBounds(start, pagelistcnt);

		return questionmapper.selectQuestionList(question_type_code, row);
	}

	public PageDto getTotalCount(int question_type_code, int page) {
		
		int totalcnt = questionmapper.getTotalCount(page);

		PageDto pagebean = new PageDto(totalcnt, page, pagelistcnt, pagenationcnt);

		return pagebean;
	}

	




}
