package ks46team02.farm.dto;

public class FarmInfo {
    private String farmCode;
    private String farmName;
    private String farmDetail;
    private String companyCode;
    private String farmRegDate;
    private String memberId;
    private String cageAmount;
    private String totalCapacity;
    private String farmAddr;

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

    public String getFarmDetail() {
        return farmDetail;
    }

    public void setFarmDetail(String farmDetail) {
        this.farmDetail = farmDetail;
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

    public String getCageAmount() {
        return cageAmount;
    }

    public void setCageAmount(String cageAmount) {
        this.cageAmount = cageAmount;
    }

    public String getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(String totalCapacity) {
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
                ", farmDetail='" + farmDetail + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", farmRegDate='" + farmRegDate + '\'' +
                ", memberId='" + memberId + '\'' +
                ", cageAmount='" + cageAmount + '\'' +
                ", totalCapacity='" + totalCapacity + '\'' +
                ", farmAddr='" + farmAddr + '\'' +
                '}';
    }
}
