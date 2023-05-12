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
    private String companyCode;
    private int minAmount;
    private int maxAmount;
    private int minMonth;
    private int maxMonth;
    private String contractRegDate;
    private String region;
    private String companyName;
    private String breedRegCode;
    private String breedRegDate;
    private int minDate;
    private int maxDate;
    private String productCategoryCode;
    private int monthPrice;
    private String breedLocal;
    private int breedCompanySize;
    private String productName;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public int getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(int minAmount) {
        this.minAmount = minAmount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    public int getMinMonth() {
        return minMonth;
    }

    public void setMinMonth(int minMonth) {
        this.minMonth = minMonth;
    }

    public int getMaxMonth() {
        return maxMonth;
    }

    public void setMaxMonth(int maxMonth) {
        this.maxMonth = maxMonth;
    }

    public String getContractRegDate() {
        return contractRegDate;
    }

    public void setContractRegDate(String contractRegDate) {
        this.contractRegDate = contractRegDate;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBreedRegCode() {
        return breedRegCode;
    }

    public void setBreedRegCode(String breedRegCode) {
        this.breedRegCode = breedRegCode;
    }

    public String getBreedRegDate() {
        return breedRegDate;
    }

    public void setBreedRegDate(String breedRegDate) {
        this.breedRegDate = breedRegDate;
    }

    public int getMinDate() {
        return minDate;
    }

    public void setMinDate(int minDate) {
        this.minDate = minDate;
    }

    public int getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(int maxDate) {
        this.maxDate = maxDate;
    }

    public String getProductCategoryCode() {
        return productCategoryCode;
    }

    public void setProductCategoryCode(String productCategoryCode) {
        this.productCategoryCode = productCategoryCode;
    }

    public int getMonthPrice() {
        return monthPrice;
    }

    public void setMonthPrice(int monthPrice) {
        this.monthPrice = monthPrice;
    }

    public String getBreedLocal() {
        return breedLocal;
    }

    public void setBreedLocal(String breedLocal) {
        this.breedLocal = breedLocal;
    }

    public int getBreedCompanySize() {
        return breedCompanySize;
    }

    public void setBreedCompanySize(int breedCompanySize) {
        this.breedCompanySize = breedCompanySize;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

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
                ", companyCode='" + companyCode + '\'' +
                ", minAmount=" + minAmount +
                ", maxAmount=" + maxAmount +
                ", minMonth=" + minMonth +
                ", maxMonth=" + maxMonth +
                ", contractRegDate='" + contractRegDate + '\'' +
                ", region='" + region + '\'' +
                ", companyName='" + companyName + '\'' +
                ", breedRegCode='" + breedRegCode + '\'' +
                ", breedRegDate='" + breedRegDate + '\'' +
                ", minDate=" + minDate +
                ", maxDate=" + maxDate +
                ", productCategoryCode='" + productCategoryCode + '\'' +
                ", monthPrice=" + monthPrice +
                ", breedLocal='" + breedLocal + '\'' +
                ", breedCompanySize=" + breedCompanySize +
                ", productName='" + productName + '\'' +
                '}';
    }
}
