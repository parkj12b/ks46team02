package ks46team02.admin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks46team02.admin.dto.ContractStandard;
import ks46team02.admin.dto.MemberLevel;
import ks46team02.admin.mapper.ContractStandardMapper;
@Service
@Transactional
public class ContractStandardService {
	
	private static final Logger log = LoggerFactory.getLogger(ContractStandardService.class);

	
private final ContractStandardMapper contractStandardMapper; 

	public ContractStandardService(ContractStandardMapper contractStandardMapper) {
		this.contractStandardMapper = contractStandardMapper;

	}
	
	public List<ContractStandard> getAdminLevelList(){
		List<ContractStandard> ContractStandardList = contractStandardMapper.getContractStandardList();
		return ContractStandardList;
	}
	public void modifyContractStandard(ContractStandard contractStandard) {
		contractStandardMapper.modifyContractStandard(contractStandard);
		
	}
	
}
