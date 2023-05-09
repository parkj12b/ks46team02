package ks46team02.company.dto;

public class DryContract {
    private String contractRegCode;
    private String companyCode;
    private String memberId;
    private int minAmount;
    private int maxAmount;
    private int minMonth;
    private int maxMonth;
    private String contractRegDate;
    private String region;
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContractRegCode() {
        return contractRegCode;
    }

    public void setContractRegCode(String contractRegCode) {
        this.contractRegCode = contractRegCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
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

    @Override
    public String toString() {
        return "DryContract{" +
                "contractRegCode='" + contractRegCode + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", memberId='" + memberId + '\'' +
                ", minAmount=" + minAmount +
                ", maxAmount=" + maxAmount +
                ", minMonth=" + minMonth +
                ", maxMonth=" + maxMonth +
                ", contractRegDate='" + contractRegDate + '\'' +
                ", region='" + region + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
