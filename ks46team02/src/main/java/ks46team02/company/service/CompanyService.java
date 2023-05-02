package ks46team02.company.service;

import ks46team02.company.dto.Company;
import ks46team02.company.dto.CompanyPositionLevel;
import ks46team02.company.dto.CompanyType;
import ks46team02.company.dto.FarmProductCategory;
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


    public int modifyCompanyAdmin(Company company){
        int result = companyMapper.modifyCompany(company);
        return result;
    }

    public int modifyCompanyUser(Company company){
        int result = companyMapper.modifyCompany(company);
        return result;
    }
    public int insertCompanyProduct(FarmProductCategory farmProductCategory){
        int result = companyMapper.insertCompanyProduct(farmProductCategory);
        return result;
    }

    public List<FarmProductCategory> getFarmProductCategoryList(){
        List<FarmProductCategory> farmProductCategoryList = companyMapper.getFarmProductCategoryList();
        return farmProductCategoryList;
    }

    public List<CompanyPositionLevel> getCompanyPositionList(){
        List<CompanyPositionLevel> companyPositionLevelList = companyMapper.getCompanyPositionList();
        return companyPositionLevelList;
    }

    public Company getCompanyInfoById(String memberId){

        Company companyInfo = companyMapper.getCompanyInfoById(memberId);
        return companyInfo;
    }
    public Company getCompanyInfoByCode(String companyCode){

        Company companyInfo = companyMapper.getCompanyInfoByCode(companyCode);
        return companyInfo;
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
