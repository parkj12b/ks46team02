package ks46team02.farm.dto;

public class FarmInfo {
    private String farmCode;
    private String farmName;
    private String farmDetail;
    private String companyCode;
    private String farmRegDate;
    private String memberId;
    private int cageAmount;
    private int totalCapacity;
    private String farmAddr;

    public String getFarmDetail() {
        return farmDetail;
    }

    public void setFarmDetail(String farmDetail) {
        this.farmDetail = farmDetail;
    }

    public String getFarmCode() {
        return farmCode;
    }

    public void setFarmCode(String farmCode) {
        this.farmCode = farmCode;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getFarmRegDate() {
        return farmRegDate;
    }

    public void setFarmRegDate(String farmRegDate) {
        this.farmRegDate = farmRegDate;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getCageAmount() {
        return cageAmount;
    }

    public void setCageAmount(int cageAmount) {
        this.cageAmount = cageAmount;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public String getFarmAddr() {
        return farmAddr;
    }

    public void setFarmAddr(String farmAddr) {
        this.farmAddr = farmAddr;
    }

    @Override
    public String toString() {
        return "FarmInfo{" +
                "farmCode='" + farmCode + '\'' +
                ", farmName='" + farmName + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", farmRegDate='" + farmRegDate + '\'' +
                ", memberId='" + memberId + '\'' +
                ", cageAmount=" + cageAmount +
                ", totalCapacity=" + totalCapacity +
                ", farmAddr='" + farmAddr + '\'' +
                '}';
    }
}
