package ks46team02.company.controller;


import ks46team02.company.dto.Company;
import ks46team02.company.dto.CompanyPositionLevel;
import ks46team02.company.dto.CompanyType;
import ks46team02.company.dto.FarmProductCategory;
import ks46team02.company.mapper.CompanyMapper;
import ks46team02.company.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    private static final Logger log = LoggerFactory.getLogger(CompanyController.class);

    public CompanyController(CompanyService companyService
                            ,CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;

    }

    @PostMapping("/company_delete")
    public String deleteCompany(){
        String redirectURI = "redirect:/company/company_delete/deleteCompany?";
        return redirectURI;
    }
    @GetMapping("/company_employee_level")
    public String getCompanyEmployeeLevel(Model model){
        List<CompanyPositionLevel> companyPositionLevelList = companyService.getCompanyPositionList();
        model.addAttribute("title","업체별 사원 등급관리");
        model.addAttribute("companyPositionLevel",companyPositionLevelList);
        return "company/company_employee_level";
    }

    @GetMapping("/company_product_category_modify")
    public String modifyProductCategory(Model model){

        model.addAttribute("title","제품카테고리수정");
        return "company/company_product_category_modify";
    }

    @GetMapping("/company_product_insert")
    public String companyProductInsert(Model model){

        model.addAttribute("title","제품카테고리등록");
        return "company/company_product_insert";
    }

    @GetMapping("/company_product_category")
    public String getCompanyProductCategory(Model model){
        List<FarmProductCategory> farmProductCategoryList = companyService.getFarmProductCategoryList();
        model.addAttribute("title","사육업체제품종류");
        model.addAttribute("farmProductCategoryList",farmProductCategoryList);
        return "company/company_product_category";
    }

    @GetMapping("/company_type_insert")
    public String companyTypeInsert(Model model){

        model.addAttribute("title","업체종류추가");
        return "company/company_type_insert";
    }

    @GetMapping("/company_type_list")
    public String getCompanyType(Model model){
        List<CompanyType> companyTypeList = companyService.getCompanyTypeList();
        model.addAttribute("title","업체종류");
        model.addAttribute("companyTypeList", companyTypeList);
        return "company/company_type_list";
    }
    @PostMapping("/company_add")
    public String addCompany(Company company){
        log.info("화면에서 전달받은 데이터 : {}", company);
        companyService.addCompany(company);
        return "redirect:/company/company_add";
    }

    @GetMapping("/company_add")
    public String companyAdd(Model model){

        List<CompanyType> companyTypeList = companyService.getCompanyTypeList();

        model.addAttribute("title","업체등록");
        model.addAttribute("companyTypeList",companyTypeList);
        return "company/company_add";
    }

    @PostMapping("/company_modify")
    public String modifyCompany(Company company){

        companyMapper.modifyCompany(company);
        return "redirect:/company/company_list";
    }
    @GetMapping("/company_modify")
    public String modifyCompany(Model model
                               ,@RequestParam(name="companyCode") String companyCode){
        Company companyInfo = companyService.getCompanyInfoByCode(companyCode);
        model.addAttribute("title","업체수정");
        model.addAttribute("companyInfo", companyInfo);
        return "company/company_modify";
    }

    @GetMapping("/company_info")
    public String getCompanyInfo(Model model
                                , @RequestParam(name="companyCode") String companyCode){
        Company companyInfo = companyService.getCompanyInfoByCode(companyCode);

        model.addAttribute("title", "업체상세정보");
        model.addAttribute("companyInfo", companyInfo);

        return "company/company_info";
    }

    @GetMapping("/company_list")
    public String getCompanyList(Model model){

        List<Company> companyList = companyService.getCompanyList();
        model.addAttribute("title","등록업체목록");
        model.addAttribute("companyList",companyList);
        return "company/company_list";
    }

    
}
