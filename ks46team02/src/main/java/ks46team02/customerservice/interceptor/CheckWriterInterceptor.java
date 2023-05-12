package ks46team02.customerservice.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ks46team02.common.dto.Member;
import ks46team02.customerservice.dto.QuestionDto;
import ks46team02.customerservice.service.MainQuestionService;

//  수정/삭제 등의 권한이 필요한 작업을 수행하기 전에 로그인한 사용자와 작성자를 비교하여, 권한이 없는 경우 작업을 거부
public class CheckWriterInterceptor implements HandlerInterceptor {

	private Member Member;
	private MainQuestionService mainquestionservice;
	
	public CheckWriterInterceptor(Member Member, MainQuestionService mainquestionservice) {
		this.Member = Member;
		this.mainquestionservice = mainquestionservice;
	}
		
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String questionCode = request.getParameter("questionCode");
		
		QuestionDto questiondto = mainquestionservice.selectQuestionInfo(questionCode);
		
		if(Member.getMemberId() != questiondto.getMemberId()) {
			String cp = request.getContextPath();
			response.sendRedirect(cp + "/customerservice/not_writer");
			return false;
		}
		
		return true;
	}
}
