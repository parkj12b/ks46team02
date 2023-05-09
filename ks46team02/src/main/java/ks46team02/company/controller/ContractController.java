package ks46team02.company.controller;


import jakarta.servlet.http.HttpSession;
import ks46team02.company.dto.Company;
import ks46team02.company.dto.Contract;
import ks46team02.company.service.CompanyService;
import ks46team02.company.service.ContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/company")
public class ContractController {

    private final ContractService contractService;
    private final CompanyService companyService;
    private static final Logger log = LoggerFactory.getLogger(ContractController.class);

    public ContractController(ContractService contractService
                             ,CompanyService companyService)
    {
        this.contractService = contractService;
        this.companyService = companyService;
    }

    @GetMapping("/contractInfo")
    public String getContractInfo(Model model
                                 ,@RequestParam(name="contractCode") String contractCode){
        Contract contractInfo = contractService.getContractInfo(contractCode);
        log.info("쿼리결과값: {}" , contractInfo);
        model.addAttribute("title","계약상세정보");
        model.addAttribute("contractInfo",contractInfo);
        return "company/contract_info";
    }

    @GetMapping("/contractList")
    public String getContractList(Model model
                                 ,HttpSession session){
        String sessionLevel = (String)session.getAttribute("sessionLevel");
        String companyCode = (String)session.getAttribute("sessionCompanyCode");
        Company companyInfo = companyService.getCompanyInfoByCode(companyCode);
        int companyTypeNum = companyInfo.getCompanyTypeNum();
        List<Contract> contractList = contractService.getContractList(sessionLevel);
        model.addAttribute("title","계약관리");
        model.addAttribute("contractList",contractList);
        model.addAttribute("companyTypeNum",companyTypeNum);
        return "company/contract_list";
    }


}