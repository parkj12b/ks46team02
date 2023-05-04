package ks46team02.company.dto;

public class Contract {
    private String contractCode;
    private String contractRegCode;
    private String contractType;
    private String memberId;
    private String contractApplyDate;
    private String contractStartDate;
    private String contractEndDate;
    private String contractPaper;
    private String adminId;
    private String contractApprovalDate;
    private String contractApproval;
    private int deposit;
    private int deliveryCycle;
    private int contractAmount;
    private int totalAmount;
    private String contracteeCompanyCode;
    private String contractorCompanyCode;

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getContractRegCode() {
        return contractRegCode;
    }

    public void setContractRegCode(String contractRegCode) {
        this.contractRegCode = contractRegCode;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getContractApplyDate() {
        return contractApplyDate;
    }

    public void setContractApplyDate(String contractApplyDate) {
        this.contractApplyDate = contractApplyDate;
    }

    public String getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(String contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public String getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(String contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public String getContractPaper() {
        return contractPaper;
    }

    public void setContractPaper(String contractPaper) {
        this.contractPaper = contractPaper;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getContractApprovalDate() {
        return contractApprovalDate;
    }

    public void setContractApprovalDate(String contractApprovalDate) {
        this.contractApprovalDate = contractApprovalDate;
    }

    public String getContractApproval() {
        return contractApproval;
    }

    public void setContractApproval(String contractApproval) {
        this.contractApproval = contractApproval;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public int getDeliveryCycle() {
        return deliveryCycle;
    }

    public void setDeliveryCycle(int deliveryCycle) {
        this.deliveryCycle = deliveryCycle;
    }

    public int getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(int contractAmount) {
        this.contractAmount = contractAmount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getContracteeCompanyCode() {
        return contracteeCompanyCode;
    }

    public void setContracteeCompanyCode(String contracteeCompanyCode) {
        this.contracteeCompanyCode = contracteeCompanyCode;
    }

    public String getContractorCompanyCode() {
        return contractorCompanyCode;
    }

    public void setContractorCompanyCode(String contractorCompanyCode) {
        this.contractorCompanyCode = contractorCompanyCode;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "contractCode='" + contractCode + '\'' +
                ", contractRegCode='" + contractRegCode + '\'' +
                ", contractType='" + contractType + '\'' +
                ", memberId='" + memberId + '\'' +
                ", contractApplyDate='" + contractApplyDate + '\'' +
                ", contractStartDate='" + contractStartDate + '\'' +
                ", contractEndDate='" + contractEndDate + '\'' +
                ", contractPaper='" + contractPaper + '\'' +
                ", adminId='" + adminId + '\'' +
                ", contractApprovalDate='" + contractApprovalDate + '\'' +
                ", contractApproval='" + contractApproval + '\'' +
                ", deposit=" + deposit +
                ", deliveryCycle=" + deliveryCycle +
                ", contractAmount=" + contractAmount +
                ", totalAmount=" + totalAmount +
                ", contracteeCompanyCode='" + contracteeCompanyCode + '\'' +
                ", contractorCompanyCode='" + contractorCompanyCode + '\'' +
                '}';
    }
}
