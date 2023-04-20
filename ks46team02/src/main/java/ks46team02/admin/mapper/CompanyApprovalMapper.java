package ks46team02.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.company.dto.Company;


@Mapper
public interface CompanyApprovalMapper {
	/* 승인 대기 업체 조회*/
	public List<Company> getCompanyApprovalList();

}
