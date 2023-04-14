package ks46team02.farm.dto;

public class MMRegInfo {
	private String menteeAppCode;
	private String companyCode;
	private String memberId;
	private String menteeApplyDate;
	private String menteeApprovalDate;
	private String menteeApproval;
	private String adminId;
	private String previousYear;
	private String previousYearSales;
	private String salesSuitability;
	private String documentaryEvidence;
	public String getMenteeAppCode() {
		return menteeAppCode;
	}
	public void setMenteeAppCode(String menteeAppCode) {
		this.menteeAppCode = menteeAppCode;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMenteeApplyDate() {
		return menteeApplyDate;
	}
	public void setMenteeApplyDate(String menteeApplyDate) {
		this.menteeApplyDate = menteeApplyDate;
	}
	public String getMenteeApprovalDate() {
		return menteeApprovalDate;
	}
	public void setMenteeApprovalDate(String menteeApprovalDate) {
		this.menteeApprovalDate = menteeApprovalDate;
	}
	public String getMenteeApproval() {
		return menteeApproval;
	}
	public void setMenteeApproval(String menteeApproval) {
		this.menteeApproval = menteeApproval;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getPreviousYear() {
		return previousYear;
	}
	public void setPreviousYear(String previousYear) {
		this.previousYear = previousYear;
	}
	public String getPreviousYearSales() {
		return previousYearSales;
	}
	public void setPreviousYearSales(String previousYearSales) {
		this.previousYearSales = previousYearSales;
	}
	public String getSalesSuitability() {
		return salesSuitability;
	}
	public void setSalesSuitability(String salesSuitability) {
		this.salesSuitability = salesSuitability;
	}
	public String getDocumentaryEvidence() {
		return documentaryEvidence;
	}
	public void setDocumentaryEvidence(String documentaryEvidence) {
		this.documentaryEvidence = documentaryEvidence;
	}
	@Override
	public String toString() {
		return "MMRegInfo [menteeAppCode=" + menteeAppCode + ", companyCode=" + companyCode + ", memberId=" + memberId
				+ ", menteeApplyDate=" + menteeApplyDate + ", menteeApprovalDate=" + menteeApprovalDate
				+ ", menteeApproval=" + menteeApproval + ", adminId=" + adminId + ", previousYear=" + previousYear
				+ ", previousYearSales=" + previousYearSales + ", salesSuitability=" + salesSuitability
				+ ", documentaryEvidence=" + documentaryEvidence + "]";
	}
	
}
