package ks46team02.farm.dto;

public class Feed {
    private String feedingNum;
    private String companyCode;
    private String farmCode;
    private String expectedCageProductionCode;
    private Double feedingAmount;
    private String feedingDate;
    private String memberId;

    public String getFeedingNum() {
        return feedingNum;
    }

    public void setFeedingNum(String feedingNum) {
        this.feedingNum = feedingNum;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getFarmCode() {
        return farmCode;
    }

    public void setFarmCode(String farmCode) {
        this.farmCode = farmCode;
    }

    public String getExpectedCageProductionCode() {
        return expectedCageProductionCode;
    }

    public void setExpectedCageProductionCode(String expectedCageProductionCode) {
        this.expectedCageProductionCode = expectedCageProductionCode;
    }

    public Double getFeedingAmount() {
        return feedingAmount;
    }

    public void setFeedingAmount(Double feedingAmount) {
        this.feedingAmount = feedingAmount;
    }

    public String getFeedingDate() {
        return feedingDate;
    }

    public void setFeedingDate(String feedingDate) {
        this.feedingDate = feedingDate;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
