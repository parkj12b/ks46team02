package ks46team02.customerservice.dto;

import org.springframework.web.multipart.MultipartFile;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;

public class QuestionDto {

	private String questionCode;

	@NotNull
	private String questionTitle;

	@NotNull
	private String questionContent;
	
	private MultipartFile uploadFile;

	private String questionFile;
	

	private String memberId;
	private int questionTypeCode;
	private String questionStatus;
	private String questionRegDate;
	private String questionModityDate;
	private String questionDelDate;
	private String questionDelYN;
	private String questionTypeName;

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
	
	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
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

	public int getQuestionTypeCode() {
		return questionTypeCode;
	}

	public void setQuestionTypeCode(int questionTypeCode) {
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

	public String getQuestionTypeName() {
		return questionTypeName;
	}

	public void setQuestionTypeName(String questionTypeName) {
		this.questionTypeName = questionTypeName;
	}

	@Override
	public String toString() {
		return "QuestionDto [questionCode=" + questionCode + ", questionTitle=" + questionTitle + ", questionContent="
				+ questionContent + ", uploadFile=" + uploadFile + ", questionFile=" + questionFile + ", memberId="
				+ memberId + ", questionTypeCode=" + questionTypeCode + ", questionStatus=" + questionStatus
				+ ", questionRegDate=" + questionRegDate + ", questionModityDate=" + questionModityDate
				+ ", questionDelDate=" + questionDelDate + ", questionDelYN=" + questionDelYN + ", quesitonTypeName="
				+ questionTypeName + "]";
	}

	
	

}