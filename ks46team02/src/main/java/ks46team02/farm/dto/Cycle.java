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
    private String cageNum;
    private String cageVolume;
    private String cageTotal;
    private String dayDiffHarvest;

    @Override
    public String toString() {
        return "Cycle{" +
                "cycleCode='" + cycleCode + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", farmCode='" + farmCode + '\'' +
                ", cageCode='" + cageCode + '\'' +
                ", harvestStartDate='" + harvestStartDate + '\'' +
                ", estimatedHarvestDate='" + estimatedHarvestDate + '\'' +
                ", inputEgg=" + inputEgg +
                ", calculationStandardCode='" + calculationStandardCode + '\'' +
                ", estimatedProduction=" + estimatedProduction +
                ", memberId='" + memberId + '\'' +
                ", estimatedProductionRegDate='" + estimatedProductionRegDate + '\'' +
                ", cageNum='" + cageNum + '\'' +
                ", cageVolume='" + cageVolume + '\'' +
                ", cageTotal='" + cageTotal + '\'' +
                ", dayDiffHarvest='" + dayDiffHarvest + '\'' +
                '}';
    }

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

    public String getCageNum() {
        return cageNum;
    }

    public void setCageNum(String cageNum) {
        this.cageNum = cageNum;
    }

    public String getCageVolume() {
        return cageVolume;
    }

    public void setCageVolume(String cageVolume) {
        this.cageVolume = cageVolume;
    }

    public String getCageTotal() {
        return cageTotal;
    }

    public void setCageTotal(String cageTotal) {
        this.cageTotal = cageTotal;
    }

    public String getDayDiffHarvest() {
        return dayDiffHarvest;
    }

    public void setDayDiffHarvest(String dayDiffHarvest) {
        this.dayDiffHarvest = dayDiffHarvest;
    }
}
