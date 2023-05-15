package ks46team02.farm.dto;

public class FarmStatus {
    private String farmStatusCode;
    private String companyCode;
    private String farmCode;
    private String farmStatusHum;
    private String farmStatusIllum;
    private String farmStatusTemp;
    private String farmStatusRegDate;

    public String getFarmStatusCode() {
        return farmStatusCode;
    }

    public void setFarmStatusCode(String farmStatusCode) {
        this.farmStatusCode = farmStatusCode;
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

    public String getFarmStatusHum() {
        return farmStatusHum;
    }

    public void setFarmStatusHum(String farmStatusHum) {
        this.farmStatusHum = farmStatusHum;
    }

    public String getFarmStatusIllum() {
        return farmStatusIllum;
    }

    public void setFarmStatusIllum(String farmStatusIllum) {
        this.farmStatusIllum = farmStatusIllum;
    }

    public String getFarmStatusTemp() {
        return farmStatusTemp;
    }

    public void setFarmStatusTemp(String farmStatusTemp) {
        this.farmStatusTemp = farmStatusTemp;
    }

    public String getFarmStatusRegDate() {
        return farmStatusRegDate;
    }

    public void setFarmStatusRegDate(String farmStatusRegDate) {
        this.farmStatusRegDate = farmStatusRegDate;
    }
}
