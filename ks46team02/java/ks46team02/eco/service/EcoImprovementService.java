package ks46team02.eco.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks46team02.eco.dto.EcoImprovementDto;
import ks46team02.eco.mapper.EcoImprovementMapper;

@Service
@Transactional
public class EcoImprovementService {

	private final EcoImprovementMapper ecoimprovementmapper;
	
	public EcoImprovementService(EcoImprovementMapper coimprovementmapper, EcoImprovementMapper ecoimprovementmapper) {
		this.ecoimprovementmapper = ecoimprovementmapper;
	}
	
	public List<EcoImprovementDto> getEcoList() {
		List<EcoImprovementDto> ecoList = ecoimprovementmapper.getEcoList();
		return ecoList;
	}
	
	
}
