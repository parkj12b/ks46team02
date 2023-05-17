package ks46team02.admin.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AdminInterceptor implements HandlerInterceptor {
	
	
	private static final Logger log = LoggerFactory.getLogger(AdminInterceptor.class);

		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			// TODO Auto-generated method stub
		
			HttpSession session = request.getSession();
			String sessionLevel = (String) session.getAttribute("sessionLevel");
			if(sessionLevel == null || !sessionLevel.equals("admin")) {
				response.sendRedirect(request.getContextPath()+"/unauthorizedRedirect");
				return false;
			}
			
			return HandlerInterceptor.super.preHandle(request, response, handler);
		}
}
