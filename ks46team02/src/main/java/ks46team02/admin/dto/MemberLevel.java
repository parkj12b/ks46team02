package ks46team02.admin.dto;

public class MemberLevel {
	private String positionLevelCode;
	private String memberLevelName;
	private String compayRegDate;
	private String adminId;
	private String memberManagement;
	private String contractManagement;
	private String paymentManagement;
	private String companyInfoManagement;
	private String mentoMenteeManagement;
	public String getPositionLevelCode() {
		return positionLevelCode;
	}
	public void setPositionLevelCode(String positionLevelCode) {
		this.positionLevelCode = positionLevelCode;
	}
	public String getMemberLevelName() {
		return memberLevelName;
	}
	public void setMemberLevelName(String memberLevelName) {
		this.memberLevelName = memberLevelName;
	}
	public String getCompayRegDate() {
		return compayRegDate;
	}
	public void setCompayRegDate(String compayRegDate) {
		this.compayRegDate = compayRegDate;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getMemberManagement() {
		return memberManagement;
	}
	public void setMemberManagement(String memberManagement) {
		this.memberManagement = memberManagement;
	}
	public String getContractManagement() {
		return contractManagement;
	}
	public void setContractManagement(String contractManagement) {
		this.contractManagement = contractManagement;
	}
	public String getPaymentManagement() {
		return paymentManagement;
	}
	public void setPaymentManagement(String paymentManagement) {
		this.paymentManagement = paymentManagement;
	}
	public String getCompanyInfoManagement() {
		return companyInfoManagement;
	}
	public void setCompanyInfoManagement(String companyInfoManagement) {
		this.companyInfoManagement = companyInfoManagement;
	}
	public String getMentoMenteeManagement() {
		return mentoMenteeManagement;
	}
	public void setMentoMenteeManagement(String mentoMenteeManagement) {
		this.mentoMenteeManagement = mentoMenteeManagement;
	}
	@Override
	public String toString() {
		return "MemberLevel [positionLevelCode=" + positionLevelCode + ", memberLevelName=" + memberLevelName
				+ ", compayRegDate=" + compayRegDate + ", adminId=" + adminId + ", memberManagement=" + memberManagement
				+ ", contractManagement=" + contractManagement + ", paymentManagement=" + paymentManagement
				+ ", companyInfoManagement=" + companyInfoManagement + ", mentoMenteeManagement="
				+ mentoMenteeManagement + "]";
	}
	
	
}
