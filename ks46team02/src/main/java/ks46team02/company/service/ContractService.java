package ks46team02.company.service;

import ks46team02.company.dto.Contract;
import ks46team02.company.dto.DryContract;
import ks46team02.company.mapper.ContractMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {
    private final ContractMapper contractMapper;

    public ContractService(ContractMapper contractMapper){
        this.contractMapper = contractMapper;
    }

    /* 계약정보조회 */
    public Contract getContractInfo(String contractCode){
        Contract contractInfo = contractMapper.getContractInfo(contractCode);
        return contractInfo;
    }

    /* 계약조회 */
    public List<Contract> getContractList(String sessionLevel){
        List<Contract> contractList = null;
        if(sessionLevel.equals("admin")) {
            contractList = contractMapper.getContractList();
        } else {
            contractList = contractMapper.getContractList();
        }
        return contractList;
    }
    /* 건조업체 계약 조회 */
    public List<DryContract> getContractListDry(){
        List<DryContract> contractList = contractMapper.getContractListDry();
        return contractList;
    }

}
