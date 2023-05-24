package ks46team02.common.dto;

public class ContractApprovalStandard {
	private String contAppStandCode;
	private String contAppStand;
	private String contAppStandInfo;
	private int contAppStandValue;
	private String contAppStandUnit;
	private String contAppStandRegDate;
	private String adminId;
	private String standardDescription;
	public String getContAppStandCode() {
		return contAppStandCode;
	}
	public void setContAppStandCode(String contAppStandCode) {
		this.contAppStandCode = contAppStandCode;
	}
	public String getContAppStand() {
		return contAppStand;
	}
	public void setContAppStand(String contAppStand) {
		this.contAppStand = contAppStand;
	}
	public String getContAppStandInfo() {
		return contAppStandInfo;
	}
	public void setContAppStandInfo(String contAppStandInfo) {
		this.contAppStandInfo = contAppStandInfo;
	}
	public int getContAppStandValue() {
		return contAppStandValue;
	}
	public void setContAppStandValue(int contAppStandValue) {
		this.contAppStandValue = contAppStandValue;
	}
	public String getContAppStandUnit() {
		return contAppStandUnit;
	}
	public void setContAppStandUnit(String contAppStandUnit) {
		this.contAppStandUnit = contAppStandUnit;
	}
	public String getContAppStandRegDate() {
		return contAppStandRegDate;
	}
	public void setContAppStandRegDate(String contAppStandRegDate) {
		this.contAppStandRegDate = contAppStandRegDate;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getStandardDescription() {
		return standardDescription;
	}
	public void setStandardDescription(String standardDescription) {
		this.standardDescription = standardDescription;
	}
	@Override
	public String toString() {
		return "ContractApprovalStandard [contAppStandCode=" + contAppStandCode + ", contAppStand=" + contAppStand
				+ ", contAppStandInfo=" + contAppStandInfo + ", contAppStandValue=" + contAppStandValue
				+ ", contAppStandUnit=" + contAppStandUnit + ", contAppStandRegDate=" + contAppStandRegDate
				+ ", adminId=" + adminId + ", standardDescription=" + standardDescription + "]";
	}
	
	
}
