package ks46team02.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {
	
	
	private static final Logger log = LoggerFactory.getLogger(MyErrorController.class);
	
	/* http 애러 컨트롤러 */
	
	@RequestMapping("${server.error.path}")
	public String handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		log.info("error entered");
		
		if(status != null) {
			Integer statusCode = Integer.valueOf(status.toString());
		    
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	            return "error/error_404";
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	            return "error/error_500";
	        }
	    }
	    return "error/error_500";
	}
}
