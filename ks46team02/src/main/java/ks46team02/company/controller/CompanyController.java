package ks46team02.company.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
public class CompanyController {

    @GetMapping("/company_product_insert")
    public String companyProductInsert(Model model){

        model.addAttribute("title","제품카테고리등록");
        return "company/company_product_insert";
    }

    @GetMapping("/company_product_category")
    public String companyProductCategory(Model model){

        model.addAttribute("title","사육업체제품종류");
        return "company/company_product_category";
    }

    @GetMapping("/company_type_insert")
    public String companyTypeInsert(Model model){

        model.addAttribute("title","업체종류추가");
        return "company/company_type_insert";
    }

    @GetMapping("/company_type_list")
    public String companyType(Model model){

        model.addAttribute("title","업체종류");
        return "company/company_type_list";
    }

    @GetMapping("/company_insert")
    public String companyInsert(Model model){

        model.addAttribute("title","업체등록");
        return "company/company_insert";
    }

    @GetMapping("/company_list")
    public String getCompanyList(Model model){
        model.addAttribute("title","등록업체목록");

        return "company/company_list";
    }


}
