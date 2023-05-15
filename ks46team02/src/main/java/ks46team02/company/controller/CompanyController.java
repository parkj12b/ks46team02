package ks46team02.company.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
import ks46team02.farm.dto.MentorFeedbackToken;
import ks46team02.farm.service.MentorMenteeService;

@Controller
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyMapper companyMapper;
    private final MemberService memberService;
    private final MemberMapper memberMapper;
    private final MentorMenteeService mentorMenteeService;
    
    private static final Logger log = LoggerFactory.getLogger(CompanyController.class);

    public CompanyController(CompanyService companyService
                            , CompanyMapper companyMapper
                            , MemberService memberService
                            , MemberMapper memberMapper
                            , MentorMenteeService mentorMenteeService) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
        this.memberService = memberService;
        this.memberMapper = memberMapper;
        this.mentorMenteeService = mentorMenteeService;
    }



    /* 업체직원등록암호체크 */
    @PostMapping("/regPassCheck")
    @ResponseBody
    public String regPassCheck(Model model
                              ,HttpSession session
                              ,@RequestParam(name="companyCode") String companyCode){
        Company companyInfo = companyService.getCompanyInfoByCode(companyCode);
        String regPassCheck = companyInfo.getRegPassword();
        return regPassCheck;
    }

    /* 업체직원등록 */
    @PostMapping("/addEmployee")
    @ResponseBody
    public void addEmployee(Member member){

        int result = memberMapper.addEmployee(member);
    }

    /* 업체직원등록 */
    @GetMapping("/addEmployee")
    public String addEmployee(Model model){

        List<Company> companyList = companyService.getCompanyList();
        model.addAttribute("title", "사원등록");
        model.addAttribute("companyList",companyList);
        return "company/add_employee";
    }

    /* 업체승인 */
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
        member.setCompanyCode(companyCode);
        member.setMemberId(memberId);
        member.setPositionLevelCode("level_code_1");
        log.info("ajax로 전달받은 데이터:{}",company);
        companyService.updateApprovalCompany(company);
        companyService.addCompanyCode(member);
    }

    /* 업체 직원 직위 수정*/
    @PostMapping("/modifyEmployeeLevel")
    public String modifyEmployeeLevel(Member member){
        memberService.modifyEmployeeLevel(member);
        return "redirect:/company/companyEmployeeList";
    }

    /* 업체 직원 직위 수정*/
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

    /* 업체직원목록 */
    @GetMapping("/companyEmployeeList")
    public String getCompanyEmployeeList(Model model,
                                         HttpSession session){
        session.getAttribute("isOwner");
        String sessionCompanyCode = (String)session.getAttribute("sessionCompanyCode");
        List<Member> employeeList = memberService.getEmployeeList(sessionCompanyCode);
        model.addAttribute("title","직원목록");
        model.addAttribute("employeeList",employeeList);
        return "company/company_employee_list";
    }

    /* 업체 삭제 */
    @PostMapping("/deleteCompany")
    public String deleteCompany(){

        String redirectURI = "redirect:/company/company_delete/deleteCompany?";
        return redirectURI;
    }

    /* 업체직원지위 */
    @GetMapping("/companyEmployeeLevel")
    public String getCompanyEmployeeLevel(Model model){
        List<CompanyPositionLevel> companyPositionLevelList = companyService.getCompanyPositionList();
        model.addAttribute("title","업체별 사원 등급관리");
        model.addAttribute("companyPositionLevel",companyPositionLevelList);
        return "company/company_employee_level";
    }

    /* 업체상품카테고리 수정 */
    @GetMapping("/modifyCompanyProductCategory")
    public String modifyProductCategory(Model model){

        model.addAttribute("title","제품카테고리수정");
        return "company/modify_company_product_category";
    }

    /* 업체상품 카테고리 등록 */
    @PostMapping("/insertCompanyProduct")
    public String insertCompanyProduct(FarmProductCategory farmProductCategory
                                      ,HttpSession session
                                      ){
        String adminId = (String)session.getAttribute("sessionId");
        farmProductCategory.setAdminId(adminId);
        companyService.insertCompanyProduct(farmProductCategory);
        return "redirect:/company/companyProductCategory";
    };

    /* 업체상품 카테고리 등록 */
    @GetMapping("/addCompanyProduct")
    public String companyProductInsert(Model model
                                      ){

        model.addAttribute("title","제품카테고리등록");
        return "/company/add_company_product";
    }

    /* 동애등에업체 상품 카테고리 */
    @GetMapping("/companyProductCategory")
    public String getCompanyProductCategory(Model model){
        List<FarmProductCategory> farmProductCategoryList = companyService.getFarmProductCategoryList();
        model.addAttribute("title","사육업체제품종류");
        model.addAttribute("farmProductCategoryList",farmProductCategoryList);
        return "company/company_product_category";
    }

    /* 업체종류등록 */
    @GetMapping("/addCompanyType")
    public String companyTypeInsert(Model model){

        model.addAttribute("title","업체종류추가");
        return "/company/add_company_type";
    }

    /* 업체종류목록 */
    @GetMapping("/companyTypeList")
    public String getCompanyType(Model model){
        List<CompanyType> companyTypeList = companyService.getCompanyTypeList();
        model.addAttribute("title","업체종류");
        model.addAttribute("companyTypeList", companyTypeList);
        return "company/company_type_list";
    }

    /* 업체등록 */
    @PostMapping("/addCompany")
    public String addCompany(Company company
                            ,HttpSession session){
        String sessionId = (String)session.getAttribute("sessionId");
        company.setMemberId(sessionId);
        companyService.addCompany(company);
        return "redirect:/company/companyInfoUser";
    }

    /* 업체등록 */
    @GetMapping("/addCompany")
    public String addCompany(Model model){

        List<CompanyType> companyTypeList = companyService.getCompanyTypeList();
        model.addAttribute("title","업체등록");
        model.addAttribute("companyTypeList",companyTypeList);
        return "company/add_company";
    }

    /* 업체등록 초기화면 */
    @GetMapping("/addCompanyIntro")
    public String addCompanyIntro(Model model
                                 ,HttpSession session){
        String sessionId = (String)session.getAttribute("sessionId");
        Company companyInfo = companyService.getCompanyInfoById(sessionId);
        model.addAttribute("companyIsExist",companyInfo.isExist());
        return "company/add_company_intro";
    }

    /* 업체정보 수정 */
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
    /* 업체정보 수정 */
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

    /* 자신의 업체 상세정보 */
    @GetMapping("/companyInfoUser")
    public String getCompanyInfoUser(Model model
                                    ,HttpSession session){
        String sessionId = (String)session.getAttribute("sessionId");
        String sessionLevel = (String)session.getAttribute("sessionLevel");
        String sessionCompanyCode = (String)session.getAttribute("sessionCompanyCode");
        Company companyInfo = null;
        if(sessionLevel != null && sessionLevel.equals("level_code_1") || sessionLevel != null && sessionLevel.equals("level_code_4")) {
            companyInfo = companyService.getCompanyInfoById(sessionId);
        } else {
            companyInfo = companyService.getCompanyInfoByCode(sessionCompanyCode);
        }
        model.addAttribute("title", "업체상세정보");
        model.addAttribute("companyInfo", companyInfo);
        model.addAttribute("sessionLevel",sessionLevel);

        return "company/company_info_user";
    }

    /* 업체 상세정보 */
    @GetMapping("/companyInfo")
    public String getCompanyInfo(Model model
                                ,@RequestParam(name="companyCode") String companyCode
                                ){
        Company companyInfo = companyService.getCompanyInfoByCode(companyCode);
        model.addAttribute("title", "업체상세정보");
        model.addAttribute("companyInfo", companyInfo);
        return "company/company_info";
    }

    /* 업체목록 조회*/
    @GetMapping("/companyList")
    public String getCompanyList(Model model){

        List<Company> companyList = companyService.getCompanyList();
        model.addAttribute("title","등록업체목록");
        model.addAttribute("companyList",companyList);
        return "company/company_list";
    }

    @GetMapping("/mentorFeedbackToken")
    public String getMentorFeedbackTokenList(Model model, HttpSession session) {
    	String companyCode = (String) session.getAttribute("sessionCompanyCode");
    	List<MentorFeedbackToken> mentorFeedbackToken = mentorMenteeService.getMentorFeedbackTokenList(companyCode);
    	
    	model.addAttribute("mentorFeedbackTokenList", mentorFeedbackToken);
    	
    	return "company/mentor_feedback_token";
    }
    
    @PostMapping("/deleteTokenAction")
    @ResponseBody
    public Map<String, Object> removeTokenAction(Model model, String tokenCode, HttpSession session) {
    	String msg;
    	boolean isSuccess = false;
    	String positionLevelCode = (String) session.getAttribute("sessionLevel");
    	String sessionId = (String) session.getAttribute("sessionId");
    	MentorFeedbackToken tokenInfo = mentorMenteeService.getMentorFeedbackTokenByTokenCode(tokenCode);
    	String memberId = tokenInfo.getMemberId();
    	
    	int result = 0;
    	
    	if(positionLevelCode.equals("level_code_1") || sessionId.equals(memberId)) {
    		result = mentorMenteeService.removeTokenByTokenCode(tokenCode);    		
    		msg = "삭제되었습니다.";
    		isSuccess = true;
    	} else {
    		msg = "권한이 없습니다.";
    		
    	}
    	Map<String,Object> returnMap = new HashMap<String,Object>();
    	
    	returnMap.put("isSuccess", isSuccess);
    	returnMap.put("msg", msg);
    	
    	return returnMap;
    }
    
    @GetMapping("/addMentorFeedbackToken")
    public String addMentorFeedbackToken(Model model) {
    	model.addAttribute("title", "멘토 피드백 토큰 생성");
    	return "company/add_mentor_feedback_token";
    }
    
    @PostMapping("/addMentorFeedbackTokenAction")
    public String addMentorFeedbackTokenAction(MentorFeedbackToken token, RedirectAttributes reAttr) {
    	
    	MentorFeedbackToken tokenInfo = mentorMenteeService.addMentorFeedbackToken(token);
  
    	reAttr.addFlashAttribute("token", tokenInfo);
    	return "redirect:/company/newTokenPage";
    }
    
    @GetMapping("/newTokenPage")
    public String newTokenPage(Model model) {
    	MentorFeedbackToken tokenInfo = (MentorFeedbackToken) model.asMap().get("token");
    	log.info("{}",tokenInfo);
    	if(tokenInfo == null) {
    		return "redirect:/";
    	}
    	model.addAttribute("mentorFeedbackToken",tokenInfo);
    	model.addAttribute("title", "멘토 피드백 토큰 생성완료");
    	return "company/new_token_page";
    }
}
