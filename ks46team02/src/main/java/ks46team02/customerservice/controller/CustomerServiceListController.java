package ks46team02.customerservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ks46team02.customerservice.service.CustomerServiceListService;
import ks46team02.customerservice.dto.AnswerDto;
import ks46team02.customerservice.dto.QuestionDto;
import ks46team02.customerservice.dto.QuestionTypeDto;

@Controller
@RequestMapping("/customerservice")
public class CustomerServiceListController {

	@Autowired
	private CustomerServiceListService customerserviceListService;

	private static final Logger log = LoggerFactory.getLogger(CustomerServiceListController.class);

	@GetMapping("/questionlist")
	public String getQuestionList(Model model) {

		List<QuestionDto> questionList = customerserviceListService.getQuestionList();

		model.addAttribute("title", "문의목록 조회");

		model.addAttribute("questionList", questionList);

		return "customerservice/questionlist";
	}

	/* 답변내용애 대한 정보 조회 */
	@GetMapping("/answerlist")
	public String getAnswerList(Model model) {

		List<AnswerDto> answerList = customerserviceListService.getAnswerList();

		model.addAttribute("title", "문의에 대한 답변 조회");
		model.addAttribute("answerList", answerList);

		return "customerservice/answerlist";
	}

	@GetMapping("/addAnwerList")
	public ResponseEntity<?> addAnswerList(@RequestParam String answerCode) {
		AnswerDto answerDto = customerserviceListService.getAnswerByCode(answerCode);
		return new ResponseEntity<>(answerDto, HttpStatus.OK);
	}

	@GetMapping("/questiontypelist")
	public String getQuestionTypeList(Model model, QuestionTypeDto questionTypeDto) {

		List<QuestionTypeDto> questionTypeList = customerserviceListService.getQuestionTypeList();

		// 자동증가되는 questionTypeCode 가져오기
		int questionTypeCode = customerserviceListService.getNextQuestionTypeCode();

		model.addAttribute("title", "문의 유형 조회");
		model.addAttribute("questionTypeList", questionTypeList);

		// 모달창에 전달할 데이터 설정
		model.addAttribute("questionTypeCode", questionTypeCode);

		questionTypeDto.setQuestionTypeCode(questionTypeCode);

		return "customerservice/questiontypelist";

	}

	@PostMapping("/add_questionType_proc")
	@ResponseBody
	public String registerQuestionType(QuestionTypeDto questionTypeDto) {
		String msg = "fail";
		int result = customerserviceListService.registerQuestionType(questionTypeDto);
		if (result > 0)
			msg = "success";
		return msg;
	}

	@PostMapping("/modify_questionType_proc")
	@ResponseBody
	public String updateQuestionTypeName(QuestionTypeDto questionTypeDto) {
		String msg = "fail";

		// questionTypeName이 공백인 경우를 검사
		if (questionTypeDto.getQuestionTypeName().trim().isEmpty()) {
			msg = "empty";
			return msg;
		}

		String result = customerserviceListService.updateQuestionTypeName(questionTypeDto);
		if (result == null || result.trim().isEmpty())
			msg = "success";

		return msg;
	}

	@PostMapping("/delete_questionType_proc")
	@ResponseBody
	public String deleteQuestionType(@RequestParam("questionTypeCode") int questionTypeCode) {
		boolean success = customerserviceListService.deleteQuestionType(questionTypeCode);
		return success ? "success" : "fail";
	}

}
