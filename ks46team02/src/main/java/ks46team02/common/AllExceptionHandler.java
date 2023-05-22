package ks46team02.common;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class AllExceptionHandler {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

		/* 전역 오류 핸들러 */
	  @ExceptionHandler(Exception.class) 
	  public void defaultExceptionHandler(HttpServletRequest request, HttpServletResponse
	  response, Model model, Exception exception) throws IOException {
		  model.addAttribute("exception", exception); 
		  log.info("error");
//		  response.sendRedirect(request.getContextPath()+"/serverError"); 
	  }
	 
	
	
}
