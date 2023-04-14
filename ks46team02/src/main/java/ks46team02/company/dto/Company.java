package ks46team02.dto.company;

public class Company {

    private String companyCode;
    private int companyTypeNum;
    private String companyRegNum;
    private String memberId;
    private String companyTel;
    private String companyAddr;
    private String companyName;
    private String applyDate;
    private String approvalStatus;
    private String approvalDeniedContent;
    private String adminId;
    private String approvalDate;
    private int farmLevelNum;
    private String regPassword;
    private int farmCnt;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public int getCompanyTypeNum() {
        return companyTypeNum;
    }

    public void setCompanyTypeNum(int companyTypeNum) {
        this.companyTypeNum = companyTypeNum;
    }

    public String getCompanyRegNum() {
        return companyRegNum;
    }

    public void setCompanyRegNum(String companyRegNum) {
        this.companyRegNum = companyRegNum;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public String getCompanyAddr() {
        return companyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getApprovalDeniedContent() {
        return approvalDeniedContent;
    }

    public void setApprovalDeniedContent(String approvalDeniedContent) {
        this.approvalDeniedContent = approvalDeniedContent;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    public int getFarmLevelNum() {
        return farmLevelNum;
    }

    public void setFarmLevelNum(int farmLevelNum) {
        this.farmLevelNum = farmLevelNum;
    }

    public String getRegPassword() {
        return regPassword;
    }

    public void setRegPassword(String regPassword) {
        this.regPassword = regPassword;
    }

    public int getFarmCnt() {
        return farmCnt;
    }

    public void setFarmCnt(int farmCnt) {
        this.farmCnt = farmCnt;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyCode='" + companyCode + '\'' +
                ", companyTypeNum=" + companyTypeNum +
                ", companyRegNum='" + companyRegNum + '\'' +
                ", memberId='" + memberId + '\'' +
                ", companyTel='" + companyTel + '\'' +
                ", companyAddr='" + companyAddr + '\'' +
                ", companyName='" + companyName + '\'' +
                ", applyDate='" + applyDate + '\'' +
                ", approvalStatus='" + approvalStatus + '\'' +
                ", approvalDeniedContent='" + approvalDeniedContent + '\'' +
                ", adminId='" + adminId + '\'' +
                ", approvalDate='" + approvalDate + '\'' +
                ", farmLevelNum=" + farmLevelNum +
                ", regPassword='" + regPassword + '\'' +
                ", farmCnt=" + farmCnt +
                '}';
    }
}
