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
import jakarta.servlet.http.HttpSession;
import ks46team02.customerservice.service.MainQuestionService;
import ks46team02.topmenu.service.TopMenuService;
import ks46team02.common.dto.Member;
import ks46team02.customerservice.dto.PageDto;
import ks46team02.customerservice.dto.QuestionDto;
import ks46team02.customerservice.dto.QuestionTypeDto;

@Controller
@RequestMapping("/customerservice")
public class MainQuestionController {

	@Resource(name = "loginMemberBean")
	@Lazy
	private Member loginMemberBean;

	private static final Logger log = LoggerFactory.getLogger(MainQuestionController.class);

	@Autowired
	private MainQuestionService mainquestionservice;

	TopMenuService topMenuService;

	@GetMapping("/main") // @RequestParam 어노테이션을 사용하여 요청 매개변수를 지정하고, 매개변수 이름과 매개변수 값의 형태로 요청 매개변수를 받는다.
							// -> 이 메서드에서는 "questionTypeCode"와 "page"라는 두 개의 요청 매개변수.
	public String main(@RequestParam(name = "questionTypeCode", defaultValue = "0")int questionTypeCode,
			@RequestParam(value = "page", defaultValue = "1") int page,HttpSession session, Model model) {

		if (questionTypeCode == 5) {
			return "redirect:/customerservice/main?questionTypeCode=1";
		} else if (questionTypeCode == 4) {
		}

		model.addAttribute("questionTypeCode", questionTypeCode);

		/* 질문 유형의 이름을 검색 */
		String questionTypeName = mainquestionservice.getQuestionTypeName(questionTypeCode);
		model.addAttribute("questionTypeName", questionTypeName);

		/* 해당하는 페이지의 질문 목록을 검색 */
		List<QuestionDto> questionList = mainquestionservice.selectQuestionList(questionTypeCode, page);
		log.info("{}", questionList);
		model.addAttribute("questionList", questionList);

		/* 해당하는 질문정보록을 select */
		List<QuestionTypeDto> questionTypeList = mainquestionservice.getQuestionTypeList(questionTypeCode);
		log.info("{}", questionTypeList);
		model.addAttribute("questionTypeList", questionTypeList);

		PageDto pageDto = mainquestionservice.getTotalCount(questionTypeCode, page);
		model.addAttribute("pageDto", pageDto);

		model.addAttribute("page", page);

		return "customerservice/main";
	}

	
	@GetMapping("/read")
	public String read(@RequestParam("questionTypeCode") int questionTypeCode,
			@RequestParam("questionCode") String questionCode, @RequestParam("page") int page, Model model) {

		model.addAttribute("questionTypeCode", questionTypeCode);
		model.addAttribute("questionCode", questionCode);

		QuestionDto questionDto = mainquestionservice.selectQuestionInfo(questionCode);
		model.addAttribute("readQuestionDto", questionDto);
		System.out.println(questionDto);

		model.addAttribute("loginMemberBean", loginMemberBean);
		log.info("{}", loginMemberBean);

		model.addAttribute("page", page);
		log.info("log={}", questionDto);
		return "customerservice/read";

	}

	@GetMapping("/write")
	public String write(@ModelAttribute("writeQuestionDto") QuestionDto questiondto,
			@RequestParam("questionTypeCode") int questionTypeCode, @RequestParam("page") int page, Model model) {

		model.addAttribute("questionTypeCode", questionTypeCode);
		log.info("{}", questionTypeCode);
		model.addAttribute("page", page);
		System.out.println(page + "<<<<<<<<<<<<<<<<<<<<<");

		questiondto.setQuestionTypeCode(questionTypeCode);
		System.out.println(questiondto + "PPPPPPPPPPP");

		return "customerservice/write";
	}

	@PostMapping("/write_proc")
	public String write_proc(@Validated @ModelAttribute("writeQuestionDto") QuestionDto questiondto,
							 HttpSession session,
							 BindingResult bindingresult, 
							 @RequestParam("page") int page, 
							 Model model) {
		
		model.addAttribute("page", page);
		String memberId = (String) session.getAttribute("sessionId");
		questiondto.setMemberId(memberId);
		log.info("아이디 : {}", memberId);

		if (bindingresult.hasErrors()) {
			return "customerservice/write";
		}

		mainquestionservice.writeQuestion(questiondto);

		return "customerservice/write_success";
	}

	@GetMapping("/modify")
	public String modify(@RequestParam("questionTypeCode") int questionTypeCode,
			@RequestParam("questionCode") String questionCode,
			@ModelAttribute("modifyQuestionDto") QuestionDto questiondto, @RequestParam("page") int page, Model model) {

		model.addAttribute("questionTypeCode", questionTypeCode);
		model.addAttribute("quesitonCode", questionCode);
		model.addAttribute("page", page);

		QuestionDto questiontemp = mainquestionservice.selectQuestionInfo(questionCode);

		questiondto.setMemberId(questiontemp.getMemberId());
		questiondto.setQuestionRegDate(questiontemp.getQuestionRegDate());
		questiondto.setQuestionTitle(questiontemp.getQuestionTitle());
		questiondto.setQuestionContent(questiontemp.getQuestionContent());
		questiondto.setQuestionFile(questiontemp.getQuestionFile());
		questiondto.setQuestionTypeCode(questionTypeCode);
		questiondto.setQuestionCode(questionCode);

		return "customerservice/modify";
	}

	@PostMapping("/modify_proc")
	public String modify_proc(@Validated @ModelAttribute("modifyQuestionDto") QuestionDto questiondto,
			BindingResult bindingresult, @RequestParam("page") int page, Model model) {

		model.addAttribute("page", page);
		model.addAttribute("questionTypeCode", questiondto.getQuestionTypeCode());
		model.addAttribute("questionCode", questiondto.getQuestionCode());

		if (bindingresult.hasErrors()) {
			return "customerservice/modify";
		}

		mainquestionservice.modifyQuestionDto(questiondto);

		return "customerservice/modify_success";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("questionTypeCode") int questionTypeCode,
			@RequestParam("questionCode") String questionCode, @RequestParam("page") int page, Model model) {

		model.addAttribute("questionTypeCode", questionTypeCode);
		model.addAttribute("page", page);

		mainquestionservice.deletequestion(questionCode);

		return "customerservice/delete";
	}

	@GetMapping("/not_writer")
	public String not_writer() {
		return "customerservice/not_writer";
	}
}
