package ks46team02.farm.dto;

public class MentorFeedbackToken {
	private String companyCode;
	private String mentorFeedbackToken;
	private String memberId;
	private String registerDate;
	private String expiryDate;
	private String mentorFeedbackTokenCode;
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getMentorFeedbackToken() {
		return mentorFeedbackToken;
	}
	public void setMentorFeedbackToken(String mentorFeedbackToken) {
		this.mentorFeedbackToken = mentorFeedbackToken;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getMentorFeedbackTokenCode() {
		return mentorFeedbackTokenCode;
	}
	public void setMentorFeedbackTokenCode(String mentorFeedbackTokenCode) {
		this.mentorFeedbackTokenCode = mentorFeedbackTokenCode;
	}
	@Override
	public String toString() {
		return "MentorFeedbackToken [companyCode=" + companyCode + ", mentorFeedbackToken=" + mentorFeedbackToken
				+ ", memberId=" + memberId + ", registerDate=" + registerDate + ", expiryDate=" + expiryDate
				+ ", mentorFeedbackTokenCode=" + mentorFeedbackTokenCode + "]";
	}
	
	
}
