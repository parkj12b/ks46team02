package ks46team02.topmenu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ks46team02.common.dto.Member;
import ks46team02.customerservice.dto.QuestionDto;
import ks46team02.customerservice.service.MainQuestionService;

public class CheckWriteInterceptor implements HandlerInterceptor {
	
	@Resource(name="loginMemberDto")
	@Lazy
	private Member loginMemberDto;
	
	@Autowired
	private MainQuestionService mainquestionservice;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		
//		String questionCode = String.parseInt(request.getParameter("questionCode"));
//		
//		QuestionDto boardbean = MainQuestionService.selectQuestionInfo(questionCode);
//		
//		if(loginMemberDto.getMemberId() != QuestionDto.getMemberId()) {
//			String cp = request.getContextPath();
//			response.sendRedirect(cp + "/board/not_writer");
//			return false;
//		}
		
		return true;
	}
	
}

