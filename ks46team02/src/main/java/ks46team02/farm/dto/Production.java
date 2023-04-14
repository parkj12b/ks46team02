package ks46team02.farm.dto;

public class Production {
    private String productionCode;
    private String companyCode;
    private String farmCode;
    private String expectedCageProductionCode;
    private String realHarvestDay;
    private Double realProduction;
    private Double lossRate;
    private String memberId;
    private String productionRegDate;

    public String getProductionCode() {
        return productionCode;
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
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

    public String getRealHarvestDay() {
        return realHarvestDay;
    }

    public void setRealHarvestDay(String realHarvestDay) {
        this.realHarvestDay = realHarvestDay;
    }

    public Double getRealProduction() {
        return realProduction;
    }

    public void setRealProduction(Double realProduction) {
        this.realProduction = realProduction;
    }

    public Double getLossRate() {
        return lossRate;
    }

    public void setLossRate(Double lossRate) {
        this.lossRate = lossRate;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getProductionRegDate() {
        return productionRegDate;
    }

    public void setProductionRegDate(String productionRegDate) {
        this.productionRegDate = productionRegDate;
    }
}
