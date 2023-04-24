package ks46team02.customerservice.dto;

public class QuestionTypeDto {

	private String questionTypeCode;
	private String questionTypeName;
	private String adminId;
	private String questionTypeRegDate;
	private String questionTypeModifyDate;
	private String questionTypeDelDate;

	public String getQuestionTypeCode() {
		return questionTypeCode;
	}

	public void setQuestionTypeCode(String questionTypeCode) {
		this.questionTypeCode = questionTypeCode;
	}

	public String getQuestionTypeName() {
		return questionTypeName;
	}

	public void setQuestionTypeName(String questionTypeName) {
		this.questionTypeName = questionTypeName;
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
				+ ", adminId=" + adminId + ", questionTypeRegDate=" + questionTypeRegDate + ", questionTypeModifyDate="
				+ questionTypeModifyDate + ", questionTypeDelDate=" + questionTypeDelDate + "]";
	}

}
