package ks46team02.farm.dto;

public class Cage {
    private String cageCode;
    private String cageName;
    private String companyCode;
    private String farmCode;
    private double optimalInputEgg;
    private String memberId;
    private String cageRegDate;
    private int cageNum;
    private double cageVolume;
    private String cageVolumeUnit;
    private double cageTotal;

    public String getCageCode() {
        return cageCode;
    }

    public void setCageCode(String cageCode) {
        this.cageCode = cageCode;
    }

    public String getCageName() {
        return cageName;
    }

    public void setCageName(String cageName) {
        this.cageName = cageName;
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

    public double getOptimalInputEgg() {
        return optimalInputEgg;
    }

    public void setOptimalInputEgg(double optimalInputEgg) {
        this.optimalInputEgg = optimalInputEgg;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getCageRegDate() {
        return cageRegDate;
    }

    public void setCageRegDate(String cageRegDate) {
        this.cageRegDate = cageRegDate;
    }

    public int getCageNum() {
        return cageNum;
    }

    public void setCageNum(int cageNum) {
        this.cageNum = cageNum;
    }

    public double getCageVolume() {
        return cageVolume;
    }

    public void setCageVolume(double cageVolume) {
        this.cageVolume = cageVolume;
    }

    public String getCageVolumeUnit() {
        return cageVolumeUnit;
    }

    public void setCageVolumeUnit(String cageVolumeUnit) {
        this.cageVolumeUnit = cageVolumeUnit;
    }

    public double getCageTotal() {
        return cageTotal;
    }

    public void setCageTotal(double cageTotal) {
        this.cageTotal = cageTotal;
    }

    @Override
    public String toString() {
        return "Cage{" +
                "cageCode='" + cageCode + '\'' +
                ", cageName='" + cageName + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", farmCode='" + farmCode + '\'' +
                ", optimalInputEgg=" + optimalInputEgg +
                ", memberId='" + memberId + '\'' +
                ", cageRegDate='" + cageRegDate + '\'' +
                ", cageNum=" + cageNum +
                ", cageVolume=" + cageVolume +
                ", cageVolumeUnit='" + cageVolumeUnit + '\'' +
                ", cageTotal=" + cageTotal +
                '}';
    }
}
