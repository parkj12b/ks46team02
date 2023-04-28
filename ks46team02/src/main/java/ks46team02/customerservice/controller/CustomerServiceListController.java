package ks46team02.customerservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks46team02.customerservice.service.CustomerServiceListService;
import ks46team02.customerservice.dto.AnswerDto;
import ks46team02.customerservice.dto.QuestionDto;
import ks46team02.customerservice.dto.QuestionTypeDto;

@Controller
@RequestMapping("/customerservice")
public class CustomerServiceListController {

	@Autowired
	private CustomerServiceListService  customerserviceListService;
	
	@GetMapping("/questionlist")
	public String getQuestionList(Model model) {

		List<QuestionDto> questionList = customerserviceListService.getQuestionList();

		model.addAttribute("title", "문의목록 조회");

		model.addAttribute("questionList", questionList);

		return "customerservice/questionlist";
	}
	
	@GetMapping("answerlist")
	public String getAnswerList(Model model) {
		List<AnswerDto> answerList = customerserviceListService.getAnswerList();
		model.addAttribute("title", "문의에 대한 답변 조회");
		model.addAttribute("answerList", answerList);
		return "customerservice/answerlist";
	}
	
	@GetMapping("questiontypelist")
	public String getQuestionTypeList(Model model) {
		List<QuestionTypeDto> questionTypeList = customerserviceListService.getQuestionTypeList();
		model.addAttribute("title", "문의 유형 조회");
		model.addAttribute("questionTypeList", questionTypeList);
		return "customerservice/questiontypelist";
		
	}
}
