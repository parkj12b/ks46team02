package ks46team02.farm.dto;

public class MMRegInfoMentor {
	private String mentorAppCode;
	private String companyCode;
	private String memberId;
	private String mentorApplyDate;
	private String mentorApprovalDate;
	private String mentorApproval;
	private String adminId;
	private String previousYear;
	private String previousYearSales;
	private boolean salesSuitability;
	private String careerYear;
	private String careerMonth;
	private boolean careerSuitability;
	private String documentaryEvidence;
	public String getMentorAppCode() {
		return mentorAppCode;
	}
	public void setMentorAppCode(String mentorAppCode) {
		this.mentorAppCode = mentorAppCode;
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
	public String getMentorApplyDate() {
		return mentorApplyDate;
	}
	public void setMentorApplyDate(String mentorApplyDate) {
		this.mentorApplyDate = mentorApplyDate;
	}
	public String getMentorApprovalDate() {
		return mentorApprovalDate;
	}
	public void setMentorApprovalDate(String mentorApprovalDate) {
		this.mentorApprovalDate = mentorApprovalDate;
	}
	public String getMentorApproval() {
		return mentorApproval;
	}
	public void setMentorApproval(String mentorApproval) {
		this.mentorApproval = mentorApproval;
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
	
	public boolean isSalesSuitability() {
		return salesSuitability;
	}
	public void setSalesSuitability(boolean salesSuitability) {
		this.salesSuitability = salesSuitability;
	}
	public String getCareerYear() {
		return careerYear;
	}
	public void setCareerYear(String careerYear) {
		this.careerYear = careerYear;
	}
	public String getCareerMonth() {
		return careerMonth;
	}
	public void setCareerMonth(String careerMonth) {
		this.careerMonth = careerMonth;
	}
	
	public boolean isCareerSuitability() {
		return careerSuitability;
	}
	public void setCareerSuitability(boolean careerSuitability) {
		this.careerSuitability = careerSuitability;
	}
	public String getDocumentaryEvidence() {
		return documentaryEvidence;
	}
	public void setDocumentaryEvidence(String documentaryEvidence) {
		this.documentaryEvidence = documentaryEvidence;
	}
	@Override
	public String toString() {
		return "MMRegInfoMentor [mentorAppCode=" + mentorAppCode + ", companyCode=" + companyCode + ", memberId="
				+ memberId + ", mentorApplyDate=" + mentorApplyDate + ", mentorApprovalDate=" + mentorApprovalDate
				+ ", mentorApproval=" + mentorApproval + ", adminId=" + adminId + ", previousYear=" + previousYear
				+ ", previousYearSales=" + previousYearSales + ", salesSuitability=" + salesSuitability
				+ ", careerYear=" + careerYear + ", careerMonth=" + careerMonth + ", careerSuitability="
				+ careerSuitability + ", documentaryEvidence=" + documentaryEvidence + "]";
	}
	
	
}
