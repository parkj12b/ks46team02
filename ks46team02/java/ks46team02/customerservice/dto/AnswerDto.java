package ks46team02.customerservice.dto;



public class AnswerDto {

	private String answerCode;
	private String questionCode;
	private String answerTitle;
	private String answerContent;
	private String answerFile;
	private String adminId;
	private String answerRegDate;
	private String answerModityDate;
	private String answerDelYN;
	private String answerDelDate;

	public String getAnswerCode() {
		return answerCode;
	}

	public void setAnswerCode(String answerCode) {
		this.answerCode = answerCode;
	}

	public String getQuestionCode() {
		return questionCode;
	}

	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}

	public String getAnswerTitle() {
		return answerTitle;
	}

	public void setAnswerTitle(String answerTitle) {
		this.answerTitle = answerTitle;
	}

	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	public String getAnswerFile() {
		return answerFile;
	}

	public void setAnswerFile(String answerFile) {
		this.answerFile = answerFile;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAnswerRegDate() {
		return answerRegDate;
	}

	public void setAnswerRegDate(String answerRegDate) {
		this.answerRegDate = answerRegDate;
	}

	public String getAnswerModityDate() {
		return answerModityDate;
	}

	public void setAnswerModityDate(String answerModityDate) {
		this.answerModityDate = answerModityDate;
	}

	public String getAnswerDelYN() {
		return answerDelYN;
	}

	public void setAnswerDelYN(String answerDelYN) {
		this.answerDelYN = answerDelYN;
	}

	public String getAnswerDelDate() {
		return answerDelDate;
	}

	public void setAnswerDelDate(String answerDelDate) {
		this.answerDelDate = answerDelDate;
	}

	@Override
	public String toString() {
		return "AnswerDto [answerCode=" + answerCode + ", questionCode=" + questionCode + ", answerTitle=" + answerTitle
				+ ", answerContent=" + answerContent + ", answerFile=" + answerFile + ", adminId=" + adminId
				+ ", answerRegDate=" + answerRegDate + ", answerModityDate=" + answerModityDate + ", answerDelYN="
				+ answerDelYN + ", answerDelDate=" + answerDelDate + "]";
	}

}
