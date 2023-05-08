package ks46team02.eco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks46team02.eco.dto.EcoImprovementDto;
import ks46team02.eco.service.EcoImprovementService;

@Controller
@RequestMapping("/eco")
public class EcoImprovementController {
	
	@Autowired
	private EcoImprovementService ecoimprovementservice; 
	
	@GetMapping("/eco_improvement")
	public String getEcoList(Model model) {
		
		List<EcoImprovementDto> ecoList =  ecoimprovementservice.getEcoList();
		model.addAttribute("title", "환경 개선량 조회");
		model.addAttribute("ecoList", ecoList);
		
		return "eco/eco_improvement";
	}
}
