package ks46team02.customerservice.dto;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;

public class QuestionDto {

	private String questionCode;

	@NotNull
	private String questionTitle;

	@NotNull
	private String questionContent;

//	private MultipartFile upload_file;

	private String questionFile;
	private String memberId;
	private String questionTypeCode;
	private String questionStatus;
	private String questionRegDate;
	private String questionModityDate;
	private String questionDelDate;
	private String questionDelYN;

	public String getQuestionCode() {
		return questionCode;
	}

	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public String getQuestionFile() {
		return questionFile;
	}

	public void setQuestionFile(String questionFile) {
		this.questionFile = questionFile;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getQuestionTypeCode() {
		return questionTypeCode;
	}

	public void setQuestionTypeCode(String questionTypeCode) {
		this.questionTypeCode = questionTypeCode;
	}

	public String getQuestionStatus() {
		return questionStatus;
	}

	public void setQuestionStatus(String questionStatus) {
		this.questionStatus = questionStatus;
	}

	public String getQuestionRegDate() {
		return questionRegDate;
	}

	public void setQuestionRegDate(String questionRegDate) {
		this.questionRegDate = questionRegDate;
	}

	public String getQuestionModityDate() {
		return questionModityDate;
	}

	public void setQuestionModityDate(String questionModityDate) {
		this.questionModityDate = questionModityDate;
	}

	public String getQuestionDelDate() {
		return questionDelDate;
	}

	public void setQuestionDelDate(String questionDelDate) {
		this.questionDelDate = questionDelDate;
	}

	public String getQuestionDelYN() {
		return questionDelYN;
	}

	public void setQuestionDelYN(String questionDelYN) {
		this.questionDelYN = questionDelYN;
	}

	@Override
	public String toString() {
		return "QuestionDto [questionCode=" + questionCode + ", questionTitle=" + questionTitle + ", questionContent="
				+ questionContent + ", questionFile=" + questionFile + ", memberId=" + memberId + ", questionTypeCode="
				+ questionTypeCode + ", questionStarus=" + questionStatus + ", qustionRegDate=" + questionRegDate
				+ ", uestionModityDate=" + questionModityDate + ", questionDelDate=" + questionDelDate
				+ ", questionDelYN=" + questionDelYN + "]";
	}

}
