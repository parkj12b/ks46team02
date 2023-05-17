package ks46team02.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandler {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public String defaultExpcetionHandler(HttpServletRequest request,Model model, Exception exception) {
		model.addAttribute("exception", exception);
		 return "";
	}
	
	
}
