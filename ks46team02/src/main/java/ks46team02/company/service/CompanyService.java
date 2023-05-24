package ks46team02.company.service;

import ks46team02.admin.controller.AdminController;
import ks46team02.common.dto.Member;
import ks46team02.common.mapper.MainMapper;
import ks46team02.company.dto.Company;
import ks46team02.company.dto.CompanyPositionLevel;
import ks46team02.company.dto.CompanyType;
import ks46team02.company.dto.FarmProductCategory;
import ks46team02.company.mapper.CompanyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompanyService {
    private final CompanyMapper companyMapper;
    private final MainMapper mainMapper;

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    public CompanyService(CompanyMapper companyMapper
                         ,MainMapper mainMapper){
        this.companyMapper = companyMapper;
        this.mainMapper = mainMapper;
    }

    /* 업체삭제*/
    public boolean removeCompany(String companyCode){

        boolean result = companyMapper.removeCompany(companyCode);
        return result;
    }

    /* 업체승인 */
    public int updateApprovalCompany(Company company){
        String companyCode = company.getCompanyCode();
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
    public int addCompanyProduct(FarmProductCategory farmProductCategory){
        String column = "product_category_code";
        String table = "farm_product_category";
        String productCategoryCode =  mainMapper.autoIncrement(table, column);
        farmProductCategory.setProductCategoryCode(productCategoryCode);
        int result = companyMapper.addCompanyProduct(farmProductCategory);
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
    public boolean addCompany(Company company){
        String column = "company_code";
        String table = "company_info";
        String companyCode =  mainMapper.autoIncrement(table, column);
        company.setCompanyCode(companyCode);
        boolean addCompanyResult = companyMapper.addCompany(company);
        log.info("addCompany result : {}",addCompanyResult);
        return true;
    }

    /* 회원테이블에 업체코드 업데이트 */
    public boolean addCompanyCode(Member member){
        boolean result = companyMapper.addCompanyCode(member);
        log.info("addCompanyCode result : {}", result);
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
