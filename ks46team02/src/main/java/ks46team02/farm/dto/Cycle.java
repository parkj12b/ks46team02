package ks46team02.farm.dto;

public class Cycle {
    private String cycleCode;
    private String companyCode;
    private String farmCode;
    private String cageCode;
    private String harvestStartDate;
    private String estimatedHarvestDate;
    private double inputEgg;
    private String calculationStandardCode;
    private double estimatedProduction;
    private String memberId;
    private String estimatedProductionRegDate;

    public String getCycleCode() {
        return cycleCode;
    }

    public void setCycleCode(String cycleCode) {
        this.cycleCode = cycleCode;
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

    public String getCageCode() {
        return cageCode;
    }

    public void setCageCode(String cageCode) {
        this.cageCode = cageCode;
    }

    public String getHarvestStartDate() {
        return harvestStartDate;
    }

    public void setHarvestStartDate(String harvestStartDate) {
        this.harvestStartDate = harvestStartDate;
    }

    public String getEstimatedHarvestDate() {
        return estimatedHarvestDate;
    }

    public void setEstimatedHarvestDate(String estimatedHarvestDate) {
        this.estimatedHarvestDate = estimatedHarvestDate;
    }

    public double getInputEgg() {
        return inputEgg;
    }

    public void setInputEgg(double inputEgg) {
        this.inputEgg = inputEgg;
    }

    public String getCalculationStandardCode() {
        return calculationStandardCode;
    }

    public void setCalculationStandardCode(String calculationStandardCode) {
        this.calculationStandardCode = calculationStandardCode;
    }

    public double getEstimatedProduction() {
        return estimatedProduction;
    }

    public void setEstimatedProduction(double estimatedProduction) {
        this.estimatedProduction = estimatedProduction;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getEstimatedProductionRegDate() {
        return estimatedProductionRegDate;
    }

    public void setEstimatedProductionRegDate(String estimatedProductionRegDate) {
        this.estimatedProductionRegDate = estimatedProductionRegDate;
    }
}
