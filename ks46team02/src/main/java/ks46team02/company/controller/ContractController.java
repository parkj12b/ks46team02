package ks46team02.company.controller;


import ks46team02.company.dto.Contract;
import ks46team02.company.service.ContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/company")
public class ContractController {

    private final ContractService contractService;
    private static final Logger log = LoggerFactory.getLogger(CompanyController.class);

    public ContractController(ContractService contractService){
        this.contractService = contractService;
    }
    @GetMapping("/contractInfo")
    public String getContractInfo(Model model){
        model.addAttribute("title","계약상세정보");

        return "company/contract_info";
    }

    @GetMapping("/contractList")
    public String getContractList(Model model){

        List<Contract> contractList = contractService.getContractList();

        model.addAttribute("title","계약관리");
        model.addAttribute("contractList",contractList);
        return "company/contract_list";
    }


}