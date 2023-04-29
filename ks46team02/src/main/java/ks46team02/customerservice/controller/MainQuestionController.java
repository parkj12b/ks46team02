package ks46team02.customerservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.annotation.Resource;
import ks46team02.customerservice.service.MainQuestionService;
import ks46team02.common.dto.Member;
import ks46team02.customerservice.dto.PageDto;
import ks46team02.customerservice.dto.QuestionDto;

@Controller
@RequestMapping("/customerservice")
public class MainQuestionController {

	@Resource(name = "loginMemberDto")
	@Lazy
	private Member loginMemberDto;
	
	private static final Logger log = LoggerFactory.getLogger(MainQuestionController.class);

	@Autowired
	private MainQuestionService questionservice;

	@GetMapping("/main") //@RequestParam 어노테이션을 사용하여 요청 매개변수를 지정하고, 매개변수 이름과 매개변수 값의 형태로 요청 매개변수를 받는다. -> 이 메서드에서는 "questionTypeCode"와 "page"라는 두 개의 요청 매개변수.
	public String main(
			@RequestParam(name="questionTypeCode")int questionTypeCode,
			@RequestParam(value = "page", defaultValue = "1") int page,
			Model model) {
		
		  model.addAttribute("questionTypeCode", questionTypeCode);
		
        /* 질문 유형의 이름을 검색 */
		String questionTypeName = questionservice.getQuestionTypeName(questionTypeCode);
		model.addAttribute("questionTypeName", questionTypeName);
		
		/* 해당하는 페이지의 질문 목록을 검색 */
		List<QuestionDto> questionList = questionservice.selectQuestionList(questionTypeCode, page);
		log.info("{}", questionList);
		model.addAttribute("questionList", questionList);
		
		PageDto pageDto = questionservice.getTotalCount(questionTypeCode, page);
		model.addAttribute("pageDto", pageDto);

		model.addAttribute("page", page);

		return "customerservice/main";
	}

	@GetMapping("/read")
	public String read(@RequestParam("questionTypeCode") int questionTypeCode,
			@RequestParam("quesitonCode") String questionCode, 
			@RequestParam("page") int page, Model model) {

		model.addAttribute("questionTypeCode", questionTypeCode);
		model.addAttribute("questionCode", questionCode);

		QuestionDto questionDto = (QuestionDto) questionservice.selectQuestionInfo(questionCode);
		model.addAttribute("readQuestionDto", questionDto);

		model.addAttribute("loginMemberDto", loginMemberDto);

		model.addAttribute("page", page);
		return "customerservice/read";

	}

	@GetMapping("/write")
	public String write(@ModelAttribute("writeQuestionDto") QuestionDto questiondto,
			@RequestParam("QuesitonTypeCode") int questionTypeCode, 
			@RequestParam("page") int page, 
			Model model) {

		model.addAttribute("questionTypeCode", questionTypeCode);
		model.addAttribute("page", page);

		questiondto.setQuestionTypeCode(questionTypeCode);

		return "customerservice/write";
	}

	@PostMapping("/write_proc")
	public String write_proc(@Validated @ModelAttribute("writeQuestionDto") QuestionDto questiondto,
			BindingResult bindingresult, 
			@RequestParam("page") int page, 
			Model model) {

		model.addAttribute("page", page);

		if (bindingresult.hasErrors()) {
			return "customerservice/write";
		}

		questionservice.writeQuestion(questiondto);

		return "curstomerservice/write_success";
	}

	@GetMapping("/modify")
	public String modify(@RequestParam("questiontypeCode") int questionTypeCode,
			@RequestParam("questionCode") String questionCode,
			@RequestParam("modifyQuestionDto") QuestionDto questiondto, 
			@RequestParam("page") int page, Model model) {

		model.addAttribute("questionTypeCode", questionTypeCode);
		model.addAttribute("quesitonCode", questionCode);
		model.addAttribute("page", page);

		QuestionDto questiontemp = questionservice.selectQuestionInfo(questionCode);

		questiondto.setMemberId(questiontemp.getMemberId());
		questiondto.setQuestionRegDate(questiontemp.getQuestionRegDate());
		questiondto.setQuestionTitle(questiontemp.getQuestionTitle());
		questiondto.setQuestionContent(questiontemp.getQuestionContent());
		questiondto.setQuestionFile(questiontemp.getQuestionFile());
		questiondto.setQuestionTypeCode(questionTypeCode);
		questiondto.setQuestionCode(questionCode);

		return "curstomerservice/modify";
	}

	@PostMapping("/modify_proc")
	public String modify_proc(@Validated @ModelAttribute("modifyQuestionDto") QuestionDto questiondto,
			BindingResult result, 
			@RequestParam("page") int page, 
			Model model) {

		model.addAttribute("page", page);
		model.addAttribute("questionTypeCode", questiondto.getQuestionTypeCode());
		model.addAttribute("questionCode", questiondto.getQuestionCode());

		if (result.hasErrors()) {
			return "curstomerservice/modify";
		}

		questionservice.updateQuestion(questiondto);

		return "/modify_success";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("questionTypeCode") int questionTypeCode,
			@RequestParam("questionCode") String questionCode, @RequestParam("page") int page, Model model) {

		model.addAttribute("questionTypeCode", questionTypeCode);
		model.addAttribute("page", page);

		questionservice.deletequestion(questionCode);

		return "curstomerservice/delete";
	}

	@GetMapping("/not_writer")
	public String not_writer() {
		return "curstomerservice/not_writer";
	}

	@GetMapping("/checkout")
	public String question_checkout() {
		return "curstomerservice/checkout";
	}

}
