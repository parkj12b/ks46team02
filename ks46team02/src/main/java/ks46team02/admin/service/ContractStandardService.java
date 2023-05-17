package ks46team02.admin.service;

import jakarta.servlet.http.HttpSession;
import ks46team02.admin.dto.ContractStandard;
import ks46team02.admin.mapper.ContractStandardMapper;
import ks46team02.common.mapper.MainMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ContractStandardService {
	
	private static final Logger log = LoggerFactory.getLogger(ContractStandardService.class);

	
private final ContractStandardMapper contractStandardMapper;
private final MainMapper mainMapper;

	public ContractStandardService(ContractStandardMapper contractStandardMapper,MainMapper mainMapper) {
		this.contractStandardMapper = contractStandardMapper;
		this.mainMapper = mainMapper;

	}
	/* 승인 기준 조회*/
	public List<ContractStandard> getAdminLevelList(){
		List<ContractStandard> ContractStandardList = contractStandardMapper.getContractStandardList();
		return ContractStandardList;
	}
	/* 승인 기준 수정*/
	public void modifyContractStandard(ContractStandard contractStandard) {
		contractStandardMapper.modifyContractStandard(contractStandard);
	}
	/* 승인 기준 등록*/
	public int addContractStandard(ContractStandard contractStandard, HttpSession session){
		String column = "cont_app_stand_code";
		String table = "contract_approval_standard";
		String contStandCode = mainMapper.autoIncrement(table, column);
		String adminId = (String)session.getAttribute("sessionId");
		contractStandard.setContStandCode(contStandCode);
		contractStandard.setAdminId(adminId);
		int result = contractStandardMapper.addContractStandard(contractStandard);
		return result;
	}

	
}
