package ks46team02.contract.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks46team02.contract.dto.TerminateReasonDTO;
import ks46team02.contract.service.TerminateReasonService;

@Controller
@RequestMapping("/contract")
public class TerminateReasonController {
	
	@Autowired
	private TerminateReasonService terminatereasonservice;
	
	@GetMapping("/terminate_reason")
	public String getTerminateReasonList(Model model) {
		
		List<TerminateReasonDTO> terminateReasonList = terminatereasonservice.getTerminateReasonList();
		
		model.addAttribute("title", "계약 파기 사유 조회");
		model.addAttribute("terminateReasonList",terminateReasonList);
		return "contract/terminate_reason";
	}
	
}
