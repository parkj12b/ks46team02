package ks46team02.customerservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



import ks46team02.customerservice.service.MainQuestionService;

import ks46team02.customerservice.dto.PageDto;
import ks46team02.customerservice.dto.QuestionDto;

@Controller
@RequestMapping("/customerservice")
public class MainQuestionController {
	
	@Autowired
	private MainQuestionService questionservice;
	

	@GetMapping("/main")
	public String main(@RequestParam("question_type_code") int question_type_code,
			@RequestParam(value = "page", defaultValue = "1") int page, Model model) {

		model.addAttribute("question_type_code", question_type_code);

		String question_type_name = questionservice.getQuestionTypeName(question_type_code);
		model.addAttribute("question_type_name", question_type_name);

		List<QuestionDto> list = questionservice.selectQuestionList(question_type_code, page);
		model.addAttribute("question_list", list);

		PageDto pagebean = questionservice.getTotalCount(question_type_code, page);
		model.addAttribute("pagebean", pagebean);

		model.addAttribute("page", page);

		return "customerservice/main";
	}

	@GetMapping("/read")
	public String read(Model model) {
		
		return "question/read";
	}

	@GetMapping("/write")
	public String write(Model model) {
		return "question/write";
	}

	@GetMapping("/modify")
	public String modify(Model model) {
		return "question/modify";
	}

	@GetMapping("/delete")
	public String delete(Model model) {
		return "question/delete";
	}

}
