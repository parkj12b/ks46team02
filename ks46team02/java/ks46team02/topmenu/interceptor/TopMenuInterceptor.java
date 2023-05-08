package ks46team02.topmenu.interceptor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ks46team02.common.dto.Member;
import ks46team02.customerservice.dto.QuestionTypeDto;
import ks46team02.topmenu.service.TopMenuService;

public class TopMenuInterceptor implements HandlerInterceptor {

		@Autowired
		private TopMenuService topmenuservice;
		
		@Resource(name="loginMemberDto")
		@Lazy
		private Member loginMemberDto;
		
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
			
			List<QuestionTypeDto> topMenuList = topmenuservice.getTopMenuCustomerServiceList();
			request.setAttribute("topMenuList", topMenuList);
			request.setAttribute("loginMemberDto", loginMemberDto);
			return true;
			
		}

}
