package ks46team02.company.controller;


import jakarta.servlet.http.HttpSession;
import ks46team02.admin.mapper.MemberMapper;
import ks46team02.admin.service.MemberService;
import ks46team02.common.dto.Member;
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
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    private static final Logger log = LoggerFactory.getLogger(CompanyController.class);

    public CompanyController(CompanyService companyService
                            , CompanyMapper companyMapper
                            , MemberService memberService
                            , MemberMapper memberMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
        this.memberService = memberService;
        this.memberMapper = memberMapper;

    }

    @PostMapping("/regPassCheck")
    @ResponseBody
    public String regPassCheck(Model model
                              ,HttpSession session
                              ,@RequestParam(name="companyCode") String companyCode){
        Company companyInfo = companyService.getCompanyInfoByCode(companyCode);
        String regPassCheck = companyInfo.getRegPassword();
        return regPassCheck;
    }

    @PostMapping("/addEmployee")
    @ResponseBody
    public void addEmployee(Member member){
    int result = memberMapper.addEmployee(member);
    }

    @GetMapping("/addEmployee")
    public String addEmployee(Model model){

        List<Company> companyList = companyService.getCompanyList();
        model.addAttribute("title", "사원등록");
        model.addAttribute("companyList",companyList);
        return "company/add_employee";
    }

    @PostMapping("/companyApproval")
    @ResponseBody
    public void companyApproval(Company company
                                ,Member member
                                 ,@RequestParam(name="approvalStatus") String approvalStatus
                                 ,@RequestParam(name="approvalDeniedContent") String approvalDeniedContent
                                 ,@RequestParam(name="companyCode") String companyCode
                                 ,@RequestParam(name="memberId") String memberId
                                 ,HttpSession session){
        String sessionId = (String)session.getAttribute("sessionId");
        company.setApprovalStatus(approvalStatus);
        company.setApprovalDeniedContent(approvalDeniedContent);
        company.setCompanyCode(companyCode);
        company.setMemberId(memberId);
        company.setAdminId(sessionId);
        member.setMemberId(memberId);
        member.setCompanyCode(companyCode);
        log.info("ajax로 전달받은 데이터:{}",company);

        companyService.updateApprovalCompany(company);
        companyService.addCompanyCode(member);
    }
    @PostMapping("/modifyEmployeeLevel")
    public String modifyEmployeeLevel(Member member){
        memberService.modifyEmployeeLevel(member);
        return "redirect:/company/companyEmployeeList";
    }

    @GetMapping("/modifyEmployeeLevel")
    public String modifyEmployeeLevel(Model model
                                     ,@RequestParam(name="memberId") String memberId
                                     ,@RequestParam(name="memberName") String memberName
                                     ){
        List<CompanyPositionLevel> companyPositionLevelList = companyService.getCompanyPositionList();
        model.addAttribute("title","직원권한수정");
        model.addAttribute("companyPositionLevelList",companyPositionLevelList);
        model.addAttribute("memberId",memberId);
        model.addAttribute("memberName",memberName);
        return "company/modify_employee_level";
    }

    @GetMapping("/companyEmployeeList")
    public String getCompanyEmployeeList(Model model,
                                         HttpSession session){
        boolean sessionIsOwner = (boolean)session.getAttribute("isOwner");
        String sessionCompanyCode = (String)session.getAttribute("sessionCompanyCode");
        List<Member> employeeList = memberService.getEmployeeList(sessionCompanyCode);
        model.addAttribute("title","직원목록");
        model.addAttribute("employeeList",employeeList);
        model.addAttribute("sessionIsOwner",sessionIsOwner);
        return "company/company_employee_list";
    }


    @PostMapping("/deleteCompany")
    public String deleteCompany(){

        String redirectURI = "redirect:/company/company_delete/deleteCompany?";
        return redirectURI;
    }


    @GetMapping("/companyEmployeeLevel")
    public String getCompanyEmployeeLevel(Model model){
        List<CompanyPositionLevel> companyPositionLevelList = companyService.getCompanyPositionList();
        model.addAttribute("title","업체별 사원 등급관리");
        model.addAttribute("companyPositionLevel",companyPositionLevelList);
        return "company/company_employee_level";
    }

    @GetMapping("/modifyCompanyProductCategory")
    public String modifyProductCategory(Model model){

        model.addAttribute("title","제품카테고리수정");
        return "company/modify_company_product_category";
    }

    @PostMapping("/insertCompanyProduct")
    public String insertCompanyProduct(FarmProductCategory farmProductCategory
                                      ,HttpSession session
                                      ){
        String adminId = (String)session.getAttribute("sessionId");
        farmProductCategory.setAdminId(adminId);
        companyService.insertCompanyProduct(farmProductCategory);
        return "redirect:/company/companyProductCategory";
    };

    @GetMapping("/insertCompanyProduct")
    public String companyProductInsert(Model model
                                      ){

        model.addAttribute("title","제품카테고리등록");
        return "company/insert_company_product";
    }

    @GetMapping("/companyProductCategory")
    public String getCompanyProductCategory(Model model){
        List<FarmProductCategory> farmProductCategoryList = companyService.getFarmProductCategoryList();
        model.addAttribute("title","사육업체제품종류");
        model.addAttribute("farmProductCategoryList",farmProductCategoryList);
        return "company/company_product_category";
    }

    @GetMapping("/insertCompanyType")
    public String companyTypeInsert(Model model){

        model.addAttribute("title","업체종류추가");
        return "company/insert_company_type";
    }

    @GetMapping("/companyTypeList")
    public String getCompanyType(Model model){
        List<CompanyType> companyTypeList = companyService.getCompanyTypeList();
        model.addAttribute("title","업체종류");
        model.addAttribute("companyTypeList", companyTypeList);
        return "company/company_type_list";
    }
    @PostMapping("/addCompany")
    public String addCompany(Company company
                            ,HttpSession session
                            ,Member member){
        String sessionId = (String)session.getAttribute("sessionId");
        company.setMemberId(sessionId);
        member.setMemberId(sessionId);
        log.info("화면에서 전달받은 데이터 : {}", company);
        companyService.addCompany(company);
//        companyService.addCompanyCode(member);
        return "redirect:/admin/applyCompanyRegList";
    }

    @GetMapping("/addCompany")
    public String addCompany(Model model){

        List<CompanyType> companyTypeList = companyService.getCompanyTypeList();

        model.addAttribute("title","업체등록");
        model.addAttribute("companyTypeList",companyTypeList);
        return "company/add_company";
    }

    @GetMapping("/addCompanyIntro")
    public String addCompanyIntro(Model model
                                 ,HttpSession session){
        String sessionId = (String)session.getAttribute("sessionId");
        Company companyInfo = companyService.getCompanyInfoById(sessionId);
        model.addAttribute("companyIsExist",companyInfo.isExist());
        return "company/add_company_intro";
    }
    @PostMapping("/modifyCompany")
    public String modifyCompany(Company company
                               ,HttpSession session){
        String redirect = "";
        String sessionLevel = (String)session.getAttribute("sessionLevel");
        if(sessionLevel == "admin") {
            companyService.modifyCompanyAdmin(company);
            redirect = "redirect:/company/companyList";
        } else {
            String sessionCompanyCode = (String)session.getAttribute("sessionCompanyCode");
            company.setCompanyCode(sessionCompanyCode);
            companyService.modifyCompanyUser(company);
            redirect = "redirect:/company/companyInfoUser";
        }
        return redirect;
    }
    @GetMapping("/modifyCompany")
    public String modifyCompany(Model model
                               ,@RequestParam(name="companyCode") String companyCode
                               ,HttpSession session){
        String positionLevel = (String)session.getAttribute("sessionLevel");
        Company companyInfo = companyService.getCompanyInfoByCode(companyCode);
        model.addAttribute("title","업체수정");
        model.addAttribute("companyInfo", companyInfo);
        model.addAttribute("positionLevel",positionLevel);
        return "company/modify_company";
    }

    @GetMapping("/companyInfoUser")
    public String getCompanyInfoUser(Model model
                                    ,HttpSession session){
        String sessionId = (String)session.getAttribute("sessionId");
        String sessionLevel = (String)session.getAttribute("sessionLevel");
        String sessionCompanyCode = (String)session.getAttribute("sessionCompanyCode");
        Company companyInfo = null;
        if(sessionLevel != null && sessionLevel.equals("level_code_1")) {
            companyInfo = companyService.getCompanyInfoById(sessionId);
        } else {
            companyInfo = companyService.getCompanyInfoByCode(sessionCompanyCode);
        }
        model.addAttribute("title", "업체상세정보");
        model.addAttribute("companyInfo", companyInfo);
        model.addAttribute("sessionLevel",sessionLevel);

        return "company/company_info_user";
    }
    @GetMapping("/companyInfo")
    public String getCompanyInfo(Model model
                                ,@RequestParam(name="companyCode") String companyCode
                                ){
        Company companyInfo = companyService.getCompanyInfoByCode(companyCode);
        model.addAttribute("title", "업체상세정보");
        model.addAttribute("companyInfo", companyInfo);
        return "company/company_info";
    }

    @GetMapping("/companyList")
    public String getCompanyList(Model model){

        List<Company> companyList = companyService.getCompanyList();
        model.addAttribute("title","등록업체목록");
        model.addAttribute("companyList",companyList);
        return "company/company_list";
    }

    
}
