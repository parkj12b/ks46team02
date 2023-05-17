package ks46team02.farm.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class FarmInterceptor implements HandlerInterceptor{
	
	
	private static final Logger log = LoggerFactory.getLogger(FarmInterceptor.class);

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
	
		HttpSession session = request.getSession();
		log.info("{}", session);
		Integer sessionCompanyTypeNum = (Integer) session.getAttribute("companyTypeNum");
		if(sessionCompanyTypeNum == null || sessionCompanyTypeNum != 2) {
			response.sendRedirect(request.getContextPath()+"/unauthorizedRedirect");
			return false;
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
}
