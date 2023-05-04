package ks46team02.company.mapper;

import ks46team02.company.dto.Contract;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContractMapper {
    // 계약조회
    public List<Contract> getContractList();

}
