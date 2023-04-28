package ks46team02.farm.dto;

public class ResultHistory {
	private String resultCode;
	private String evaluationUnitCode;
	private String visitCode;
	private String contractCode;
	private String resultFeedback;
	private String memberId;
	private String resultRegDate;
	private int resultEvaluationPoint;
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
	public String getEvaluationUnitCode() {
		return evaluationUnitCode;
	}
	public void setEvaluationUnitCode(String evaluationUnitCode) {
		this.evaluationUnitCode = evaluationUnitCode;
	}
	public String getVisitCode() {
		return visitCode;
	}
	public void setVisitCode(String visitCode) {
		this.visitCode = visitCode;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getResultFeedback() {
		return resultFeedback;
	}
	public void setResultFeedback(String resultFeedback) {
		this.resultFeedback = resultFeedback;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getResultRegDate() {
		return resultRegDate;
	}
	public void setResultRegDate(String resultRegDate) {
		this.resultRegDate = resultRegDate;
	}
	
	public int getResultEvaluationPoint() {
		return resultEvaluationPoint;
	}
	public void setResultEvaluationPoint(int resultEvaluationPoint) {
		this.resultEvaluationPoint = resultEvaluationPoint;
	}
	@Override
	public String toString() {
		return "ResultHistory [resultCode=" + resultCode + ", evaluationUnitCode=" + evaluationUnitCode + ", visitCode="
				+ visitCode + ", contractCode=" + contractCode + ", resultFeedback=" + resultFeedback + ", memberId="
				+ memberId + ", resultRegDate=" + resultRegDate + ", resultEvaluationPoint=" + resultEvaluationPoint
				+ "]";
	}
	
	
}
