package ks46team02.eco.dto;

public class EcoImprovementDto {

	private String ecoCode;
	private String companyCode;
	private String companyName;
	private int totalProcessed;
	private int gasWeight;
	private int treeEquivalent;
	private int wasteSaved;
	private String renewalDate;
	
	public String getEcoCode() {
		return ecoCode;
	}
	public void setEcoCode(String ecoCode) {
		this.ecoCode = ecoCode;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getTotalProcessed() {
		return totalProcessed;
	}
	public void setTotalProcessed(int totalProcessed) {
		this.totalProcessed = totalProcessed;
	}
	public int getGasWeight() {
		return gasWeight;
	}
	public void setGasWeight(int gasWeight) {
		this.gasWeight = gasWeight;
	}
	public int getTreeEquivalent() {
		return treeEquivalent;
	}
	public void setTreeEquivalent(int treeEquivalent) {
		this.treeEquivalent = treeEquivalent;
	}
	public int getWasteSaved() {
		return wasteSaved;
	}
	public void setWasteSaved(int wasteSaved) {
		this.wasteSaved = wasteSaved;
	}
	public String getRenewalDate() {
		return renewalDate;
	}
	public void setRenewalDate(String renewalDate) {
		this.renewalDate = renewalDate;
	}
	
	@Override
	public String toString() {
		return "EcoImprovementDto [ecoCode=" + ecoCode + ", companyCode=" + companyCode + ", companyName=" + companyName
				+ ", totalProcessed=" + totalProcessed + ", gasWeight=" + gasWeight + ", treeEquivalent="
				+ treeEquivalent + ", wasteSaved=" + wasteSaved + ", renewalDate=" + renewalDate + "]";
	}
	
	
	
}
