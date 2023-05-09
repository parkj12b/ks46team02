package ks46team02.admin.dto;

public class ContractStandard {
	private String contStandCode;
	private String contAppStand;
	private String contStandInfo;
	private String contStandValue;
	private String contStandUnit;
	private String contStandRegDate;
	private String adminId;
	public String getContStandCode() {
		return contStandCode;
	}
	public void setContStandCode(String contStandCode) {
		this.contStandCode = contStandCode;
	}
	public String getContAppStand() {
		return contAppStand;
	}
	public void setContAppStand(String contAppStand) {
		this.contAppStand = contAppStand;
	}
	public String getContStandInfo() {
		return contStandInfo;
	}
	public void setContStandInfo(String contStandInfo) {
		this.contStandInfo = contStandInfo;
	}
	public String getContStandValue() {
		return contStandValue;
	}
	public void setContStandValue(String contStandValue) {
		this.contStandValue = contStandValue;
	}
	public String getContStandUnit() {
		return contStandUnit;
	}
	public void setContStandUnit(String contStandUnit) {
		this.contStandUnit = contStandUnit;
	}
	public String getContStandRegDate() {
		return contStandRegDate;
	}
	public void setContStandRegDate(String contStandRegDate) {
		this.contStandRegDate = contStandRegDate;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	@Override
	public String toString() {
		return "contractStandard [contStandCode=" + contStandCode + ", contAppStand=" + contAppStand
				+ ", contStandInfo=" + contStandInfo + ", contStandValue=" + contStandValue + ", contStandUnit="
				+ contStandUnit + ", contStandRegDate=" + contStandRegDate + ", adminId=" + adminId + "]";
	}
	
}
