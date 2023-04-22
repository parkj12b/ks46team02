package ks46team02.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks46team02.admin.mapper.CompanyApprovalMapper;
import ks46team02.company.dto.Company;
@Service
@Transactional
public class CompanyApprovalService {
	
private final CompanyApprovalMapper companyApprovalMapper;
	
	public CompanyApprovalService(CompanyApprovalMapper companyApprovalMapper) {
		this.companyApprovalMapper = companyApprovalMapper;

	}
	
	public List<Company> getCompanyApprovalList(){
		List<Company> CompanyApprovalList = companyApprovalMapper.getCompanyApprovalList();
		return CompanyApprovalList;
	}
	
}
