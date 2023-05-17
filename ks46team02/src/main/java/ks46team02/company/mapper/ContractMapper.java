package ks46team02.company.mapper;

import ks46team02.company.dto.Contract;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContractMapper {
    //사육업체계약등록
    public boolean addBreedContract(Contract contract);
    //건조업체계약등록
    public boolean addDryContract(Contract contract);
    //계약상세정보
    public Contract getContractInfo(String contractCode);
    public Contract getDryContractInfo(String contractCode);
    public Contract getBreedContractInfo(String contractCode);
    // 계약조회
    public List<Contract> getContractList();
    // 사육업체 계약조회
    public List<Contract> getContractListBreed();
    // 건조업체 계약조회
    public List<Contract> getContractListDry();

}
