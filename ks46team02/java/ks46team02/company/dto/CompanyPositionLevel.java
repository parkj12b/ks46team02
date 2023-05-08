package ks46team02.company.dto;

public class CompanyPositionLevel {

    private String positionLevelCode;
    private String memberLevelName;
    private String companyRegDate;
    private String adminId;
    private int memberManagement;
    private int contractManagement;
    private int paymentManagement;
    private int companyInfoManagement;
    private int MentorMenteeManagement;

    public String getPositionLevelCode() {
        return positionLevelCode;
    }

    public void setPositionLevelCode(String positionLevelCode) {
        this.positionLevelCode = positionLevelCode;
    }

    public String getMemberLevelName() {
        return memberLevelName;
    }

    public void setMemberLevelName(String memberLevelName) {
        this.memberLevelName = memberLevelName;
    }

    public String getCompanyRegDate() {
        return companyRegDate;
    }

    public void setCompanyRegDate(String companyRegDate) {
        this.companyRegDate = companyRegDate;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public int getMemberManagement() {
        return memberManagement;
    }

    public void setMemberManagement(int memberManagement) {
        this.memberManagement = memberManagement;
    }

    public int getContractManagement() {
        return contractManagement;
    }

    public void setContractManagement(int contractManagement) {
        this.contractManagement = contractManagement;
    }

    public int getPaymentManagement() {
        return paymentManagement;
    }

    public void setPaymentManagement(int paymentManagement) {
        this.paymentManagement = paymentManagement;
    }

    public int getCompanyInfoManagement() {
        return companyInfoManagement;
    }

    public void setCompanyInfoManagement(int companyInfoManagement) {
        this.companyInfoManagement = companyInfoManagement;
    }

    public int getMentorMenteeManagement() {
        return MentorMenteeManagement;
    }

    public void setMentorMenteeManagement(int mentorMenteeManagement) {
        MentorMenteeManagement = mentorMenteeManagement;
    }

    @Override
    public String toString() {
        return "CompanyPositionLevel{" +
                "positionLevelCode='" + positionLevelCode + '\'' +
                ", memberLevelName='" + memberLevelName + '\'' +
                ", companyRegDate='" + companyRegDate + '\'' +
                ", adminId='" + adminId + '\'' +
                ", memberManagement=" + memberManagement +
                ", contractManagement=" + contractManagement +
                ", paymentManagement=" + paymentManagement +
                ", companyInfoManagement=" + companyInfoManagement +
                ", MentorMenteeManagement=" + MentorMenteeManagement +
                '}';
    }
}
