package ks46team02.company.service;

import com.sun.tools.javac.Main;
import ks46team02.common.mapper.MainMapper;
import ks46team02.company.dto.Contract;
import ks46team02.company.mapper.CompanyMapper;
import ks46team02.company.mapper.ContractMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContractService {
    private final ContractMapper contractMapper;
    private final MainMapper mainMapper;
    private final CompanyMapper companyMapper;

    public ContractService(ContractMapper contractMapper
                          ,MainMapper mainMapper
                          ,CompanyMapper companyMapper){
        this.contractMapper = contractMapper;
        this.mainMapper = mainMapper;
        this.companyMapper = companyMapper;
    }

    /* 계약신청 */
    public boolean applyDryContract(Contract contract){
        String column = "contract_code";
        String table = "all_contract";
        String contractCode = mainMapper.autoIncrement(table, column);
        contract.setContractCode(contractCode);
        contract.setContractType("dry");
        contractMapper.applyDryContract(contract);

        return true;
    }
    /* 계약등록 */
    public boolean addContract(Contract contract
                              ,int companyTypeNum){
        String column = "";
        String table = "";
        String contractRegCode = "";
        String breedRegCode = "";
        if(companyTypeNum==1){
            column = "contract_reg_code";
            table = "dry_contract_registraion";
            contractRegCode = mainMapper.autoIncrement(table, column);
            contract.setContractRegCode(contractRegCode);
            contractMapper.addDryContract(contract);
        } else if(companyTypeNum==2) {
            column = "breed_reg_code";
            table = "breed_contract_registraion";
            breedRegCode = mainMapper.autoIncrement(table, column);
            contract.setBreedRegCode(breedRegCode);
            contractMapper.addBreedContract(contract);
        };


        return true;
    }

    /* 계약정보조회 */
    public Contract getContractInfo(String contractCode){
        Contract contractInfo = null;
        String validCode = contractCode.substring(0,2);
        if(validCode.equals("cd")){

            contractInfo = contractMapper.getDryContractInfo(contractCode);
        } else if (validCode.equals("cf")) {

            contractInfo = contractMapper.getBreedContractInfo(contractCode);
        } else {
            contractInfo = contractMapper.getContractInfo(contractCode);
        }

        return contractInfo;
    }

    /* 계약조회 */
    public List<Contract> getContractList(){
        List<Contract> contractList = contractMapper.getContractList();
        return contractList;
    }
    /* 사육업체 계약 조회 */
    public List<Contract> getContractListBreed(){
        List<Contract> contractList = contractMapper.getContractListBreed();
        return contractList;
    }


    /* 건조업체 계약 조회 */
    public List<Contract> getContractListDry(){
        List<Contract> contractList = contractMapper.getContractListDry();
        return contractList;
    }

}
