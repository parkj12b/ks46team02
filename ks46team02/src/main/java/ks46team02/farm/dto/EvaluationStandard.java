package ks46team02.farm.dto;

public class EvaluationStandard {
	private String evaluationStandardCode;
	private String evaluationStandardExp;
	private String evaluationLevel;
	private int evaluationGradeScore;
	private String adminId;
	private String evaluationRegDate;
	public String getEvaluationStandardCode() {
		return evaluationStandardCode;
	}
	public void setEvaluationStandardCode(String evaluationStandardCode) {
		this.evaluationStandardCode = evaluationStandardCode;
	}
	public String getEvaluationStandardExp() {
		return evaluationStandardExp;
	}
	public void setEvaluationStandardExp(String evaluationStandardExp) {
		this.evaluationStandardExp = evaluationStandardExp;
	}
	public String getEvaluationLevel() {
		return evaluationLevel;
	}
	public void setEvaluationLevel(String evaluationLevel) {
		this.evaluationLevel = evaluationLevel;
	}
	public int getEvaluationGradeScore() {
		return evaluationGradeScore;
	}
	public void setEvaluationGradeScore(int evaluationGradeScore) {
		this.evaluationGradeScore = evaluationGradeScore;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getEvaluationRegDate() {
		return evaluationRegDate;
	}
	public void setEvaluationRegDate(String evaluationRegDate) {
		this.evaluationRegDate = evaluationRegDate;
	}
	@Override
	public String toString() {
		return "EvaluationStandard [evaluationStandardCode=" + evaluationStandardCode + ", evaluationStandardExp="
				+ evaluationStandardExp + ", evaluationLevel=" + evaluationLevel + ", evaluationGradeScore="
				+ evaluationGradeScore + ", adminId=" + adminId + ", evaluationRegDate=" + evaluationRegDate + "]";
	}
	
	
}
