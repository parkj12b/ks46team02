package ks46team02.company.service;

import ks46team02.company.dto.Company;
import ks46team02.company.dto.CompanyType;
import ks46team02.company.mapper.CompanyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyMapper companyMapper;

    public CompanyService(CompanyMapper companyMapper){
        this.companyMapper = companyMapper;
    }

    public int addCompany(Company company){
        int result = companyMapper.addCompany(company);
        return result;
    }

    public List<CompanyType> getCompanyTypeList(){
        List<CompanyType> companyTypeList = companyMapper.getCompanyTypeList();
        return companyTypeList;
    }

    public List<Company> getCompanyList(){
        List<Company> companyList = companyMapper.getCompanyList();
        return companyList;
    }

}
