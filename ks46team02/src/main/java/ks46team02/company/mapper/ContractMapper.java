package ks46team02.company.mapper;

import ks46team02.company.dto.Contract;
import ks46team02.company.dto.DryContract;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContractMapper {
    //계약상세정보
    public Contract getContractInfo(String contractCode);
    // 계약조회
    public List<Contract> getContractList();
    // 건조업체 계약조회
    public List<DryContract> getContractListDry();

}
