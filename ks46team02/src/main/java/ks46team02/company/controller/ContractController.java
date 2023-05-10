package ks46team02.company.controller;


import jakarta.servlet.http.HttpSession;
import ks46team02.company.dto.Company;
import ks46team02.company.dto.Contract;
import ks46team02.company.service.CompanyService;
import ks46team02.company.service.ContractService;
import ks46team02.farm.dto.MMContractInfo;
import ks46team02.farm.service.MentorMenteeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/contract")
public class ContractController {

    private final ContractService contractService;
    private final CompanyService companyService;
    private final MentorMenteeService mentorMenteeService;
    private static final Logger log = LoggerFactory.getLogger(ContractController.class);

    public ContractController(ContractService contractService
                             ,CompanyService companyService
                             ,MentorMenteeService mentorMenteeService)
    {
        this.contractService = contractService;
        this.companyService = companyService;
        this.mentorMenteeService = mentorMenteeService;
    }

    /* 계약공고 등록 */
    @GetMapping("/addContract")
    public String addContract(){

        return "company/add_contract";
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
        List<Contract> contractList = contractService.getContractList();
        List<Contract> contractListDry = contractService.getContractListDry();
        List<Contract> contractListBreed = contractService.getContractListBreed();
        String searchKey = "company_code";
        List<MMContractInfo> mmContractInfo = mentorMenteeService.getMMContractList(searchKey,"");
        model.addAttribute("mmContractInfo", mmContractInfo);
        model.addAttribute("title","계약관리");
        model.addAttribute("companyTypeNum",companyTypeNum);
        model.addAttribute("contractList",contractList);
        model.addAttribute("contractListDry",contractListDry);
        model.addAttribute("contractListBreed",contractListBreed);
        return "/company/contract_list";
    }

    /* 건조업체 계약공고 조회 */
    @GetMapping("/contractListDry")
    public String contractListDry(Model model){

        List<Contract> contractList = contractService.getContractListDry();
        model.addAttribute("title","계약공고");
        model.addAttribute("contractList",contractList);
        log.info("건조계약목록 :{}",contractList);
        return "/company/contract_list_dry";
    }

}