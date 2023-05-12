package ks46team02.customerservice.dto;

public class QuestionTypeDto {

	private int questionTypeCode;
	private String questionTypeName;
	private String questionTypeEngName;
	private String adminId;
	private String questionTypeRegDate;
	private String questionTypeModifyDate;
	private String questionTypeDelDate;
	public int getQuestionTypeCode() {
		return questionTypeCode;
	}
	public void setQuestionTypeCode(int questionTypeCode) {
		this.questionTypeCode = questionTypeCode;
	}
	public String getQuestionTypeName() {
		return questionTypeName;
	}
	public void setQuestionTypeName(String questionTypeName) {
		this.questionTypeName = questionTypeName;
	}
	public String getQuestionTypeEngName() {
		return questionTypeEngName;
	}
	public void setQuestionTypeEngName(String questionTypeEngName) {
		this.questionTypeEngName = questionTypeEngName;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getQuestionTypeRegDate() {
		return questionTypeRegDate;
	}
	public void setQuestionTypeRegDate(String questionTypeRegDate) {
		this.questionTypeRegDate = questionTypeRegDate;
	}
	public String getQuestionTypeModifyDate() {
		return questionTypeModifyDate;
	}
	public void setQuestionTypeModifyDate(String questionTypeModifyDate) {
		this.questionTypeModifyDate = questionTypeModifyDate;
	}
	public String getQuestionTypeDelDate() {
		return questionTypeDelDate;
	}
	public void setQuestionTypeDelDate(String questionTypeDelDate) {
		this.questionTypeDelDate = questionTypeDelDate;
	}
	@Override
	public String toString() {
		return "QuestionTypeDto [questionTypeCode=" + questionTypeCode + ", questionTypeName=" + questionTypeName
				+ ", questionTypeEngName=" + questionTypeEngName + ", adminId=" + adminId + ", questionTypeRegDate="
				+ questionTypeRegDate + ", questionTypeModifyDate=" + questionTypeModifyDate + ", questionTypeDelDate="
				+ questionTypeDelDate + "]";
	}

	

}
