package ks46team02.farm.dto;

public class EvaluationDetailCategory {
	private String evaluationUnitCode;
	private String largeCategoryCode;
	private String questionContentSmall;
	private String questionContentRegDate;
	private String adminId;
	public String getEvaluationUnitCode() {
		return evaluationUnitCode;
	}
	public void setEvaluationUnitCode(String evaluationUnitCode) {
		this.evaluationUnitCode = evaluationUnitCode;
	}
	public String getLargeCategoryCode() {
		return largeCategoryCode;
	}
	public void setLargeCategoryCode(String largeCategoryCode) {
		this.largeCategoryCode = largeCategoryCode;
	}
	public String getQuestionContentSmall() {
		return questionContentSmall;
	}
	public void setQuestionContentSmall(String questionContentSmall) {
		this.questionContentSmall = questionContentSmall;
	}
	public String getQuestionContentRegDate() {
		return questionContentRegDate;
	}
	public void setQuestionContentRegDate(String questionContentRegDate) {
		this.questionContentRegDate = questionContentRegDate;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	@Override
	public String toString() {
		return "EvaluationDetailCategory [evaluationUnitCode=" + evaluationUnitCode + ", largeCategoryCode="
				+ largeCategoryCode + ", questionContentSmall=" + questionContentSmall + ", questionContentRegDate="
				+ questionContentRegDate + ", adminId=" + adminId + "]";
	}
	
	
}
