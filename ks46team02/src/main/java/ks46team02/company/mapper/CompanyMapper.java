package ks46team02.company.mapper;
import ks46team02.company.dto.Company;
import ks46team02.company.dto.CompanyType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyMapper {
    // 업체등록
    public int addCompany(Company company);
    // 업체종류 조회
    public List<CompanyType> getCompanyTypeList();
    // 업체목록조회
    public List<Company> getCompanyList();

}
