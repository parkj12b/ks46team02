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
    // 사육업체상품카테고리삭제
    public boolean removeProductCategory(String productCategoryCode);
    // 사육업체상품카테고리수정
    public boolean modifyProductName(FarmProductCategory farmProductCategory);
    //업체종류 업데이트
    public boolean updateCompanyType(String companyTypeNum, String companyType ,String adminId);
    //업체삭제
    public boolean removeCompany(String companyCode);
    // 사육업체상품카테고리등록
    public int addCompanyProduct(FarmProductCategory farmProductCategory);
    // 사육업체상품카테고리조회
    public  List<FarmProductCategory> getFarmProductCategoryList();
    // 업체직원별권한조회
    public  List<CompanyPositionLevel> getCompanyPositionList();
    // 업체정보 수정
    public int modifyCompany(Company company);
    // 업체승인
    public int updateApprovalCompany(Company company);
    // 업체 상세정보 조회(memberId)
    public Company getCompanyInfoById(String memberId);
    // 업체 상세정보 조회(companyCode)
    public Company getCompanyInfoByCode(String companyCode);
    // 업체등록
    public boolean addCompany(Company company);
    // 업체승인시 회원정보 업체코드 업데이트
    public boolean addCompanyCode(Member member);
    // 업체종류 조회
    public List<CompanyType> getCompanyTypeList();
    // 업체목록조회
    public List<Company> getCompanyList();

}
