package ks46team02.company.service;

import ks46team02.company.dto.Contract;
import ks46team02.company.mapper.ContractMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {
    private final ContractMapper contractMapper;

    public ContractService(ContractMapper contractMapper){
        this.contractMapper = contractMapper;
    }

    /* 계약조회 */
    public List<Contract> getContractList(){
        List<Contract> contractList = contractMapper.getContractList();
        return contractList;
    }

}
