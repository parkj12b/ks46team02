package ks46team02.company.mapper;
import ks46team02.common.dto.Member;
import ks46team02.company.dto.Company;
import ks46team02.company.dto.CompanyPositionLevel;
import ks46team02.company.dto.CompanyType;
import ks46team02.company.dto.FarmProductCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyMapper {

    // 사육업체상품카테고리등록
    public int insertCompanyProduct(FarmProductCategory farmProductCategory);
    // 사육업체상품카테고리조회
    public  List<FarmProductCategory> getFarmProductCategoryList();
    // 업체직원별권한조회
    public  List<CompanyPositionLevel> getCompanyPositionList();
    // 업체정보 수정
    public int modifyCompany(Company company);
    // 업체 상세정보 조회
    public Company getCompanyInfoByCode(String companyCode);
    // 업체등록
    public int addCompany(Company company);
    // 업체종류 조회
    public List<CompanyType> getCompanyTypeList();
    // 업체목록조회
    public List<Company> getCompanyList();

}
