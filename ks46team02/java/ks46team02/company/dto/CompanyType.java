package ks46team02.company.dto;

public class CompanyType {

    private int companyTypeNum;
    private String companyType;
    private String regDate;
    private String adminId;

    public int getCompanyTypeNum() {
        return companyTypeNum;
    }

    public void setCompanyTypeNum(int companyTypeNum) {
        this.companyTypeNum = companyTypeNum;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "CompanyType{" +
                "companyTypeNum=" + companyTypeNum +
                ", companyType='" + companyType + '\'' +
                ", regDate='" + regDate + '\'' +
                ", adminId='" + adminId + '\'' +
                '}';
    }
}
