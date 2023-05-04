package ks46team02.company.service;

import ks46team02.common.dto.Member;
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

    /* 업체승인 */
    public int updateApprovalCompany(Company company){
        int result = companyMapper.updateApprovalCompany(company);
        return result;
    }
    /* 업체정보수정(관리자) */
    public int modifyCompanyAdmin(Company company){
        int result = companyMapper.modifyCompany(company);
        return result;
    }

    /* 업체정보수정(회원) */
    public int modifyCompanyUser(Company company){
        int result = companyMapper.modifyCompany(company);
        return result;
    }
    /* 제품카테고리 등록 */
    public int insertCompanyProduct(FarmProductCategory farmProductCategory){
        int result = companyMapper.insertCompanyProduct(farmProductCategory);
        return result;
    }
    /* 제품카테고리 코드조회 */
    public List<FarmProductCategory> getFarmProductCategoryList(){
        List<FarmProductCategory> farmProductCategoryList = companyMapper.getFarmProductCategoryList();
        return farmProductCategoryList;
    }
    /* 직위목록조회 */
    public List<CompanyPositionLevel> getCompanyPositionList(){
        List<CompanyPositionLevel> companyPositionLevelList = companyMapper.getCompanyPositionList();
        return companyPositionLevelList;
    }
    /* 회원아이디로 업체정보 조회 */
    public Company getCompanyInfoById(String memberId){

        Company companyInfo = companyMapper.getCompanyInfoById(memberId);
        return companyInfo;
    }
    /* 업체코드로 업체정보 조회 */
    public Company getCompanyInfoByCode(String companyCode){

        Company companyInfo = companyMapper.getCompanyInfoByCode(companyCode);
        return companyInfo;
    }

    /* 업체등록 조회 */
    public int addCompany(Company company){
        int result = companyMapper.addCompany(company);
        return result;
    }

    /* 회원테이블에 업체코드 업데이트 */
    public int addCompanyCode(Member member){
        int result = companyMapper.addCompanyCode(member);
        return result;
    }

    /* 업체종류목록 조회 */
    public List<CompanyType> getCompanyTypeList(){
        List<CompanyType> companyTypeList = companyMapper.getCompanyTypeList();
        return companyTypeList;
    }
    /* 업체목록 조회 */
    public List<Company> getCompanyList(){
        List<Company> companyList = companyMapper.getCompanyList();
        return companyList;
    }

}
