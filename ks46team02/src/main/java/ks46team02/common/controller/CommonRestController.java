package ks46team02.common.controller;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import ks46team02.dto.Message;
import ks46team02.dto.StatusEnum;
import ks46team02.emailTest.EmailService;
import ks46team02.emailTest.EmailServiceImpl;
import ks46team02.service.MainService;

@RestController
public class CommonRestController {
	
	private final MainService mainService;
	private EmailService emailService;
	
	public CommonRestController(MainService mainService, EmailServiceImpl emailService) {
		this.mainService = mainService;
		this.emailService = emailService;
	}
	
	
	private static final Logger log = LoggerFactory.getLogger(CommonRestController.class);
	
	
	//restController 로 뺴줘야 하나?
	
	@PostMapping("/isDuplicateId")
	public boolean isDuplicateId(String memberId) {
		boolean isDuplicate = mainService.isDuplicateId(memberId);
		log.info("{}",isDuplicate);
		return isDuplicate;
	}
	
	@GetMapping("/mailVerification")
	public String mailVerification(@RequestParam String email, HttpSession session) throws Exception {
		String ePw = emailService.sendSimpleMessage(email);
		
		session.setAttribute("ePw", ePw);
		
		return ePw;
	}
	
	@GetMapping("/isEmailUsed")
	public boolean isEmailUsed(@RequestParam(name="email") String email) {
		
		boolean isEmailUsed = mainService.isEmailUsed(email);
		return isEmailUsed;
	}
}
