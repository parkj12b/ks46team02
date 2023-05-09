package ks46team02.farm.dto;

public class VisitHistory {

	private String visitCode;
	private String contractCode;
	private String periodCount;
	private String periodStartDate;
	private String periodEndDate;
	private String visitComplete;
	private String visitCompleteFinal;
	private int totalScoreVisit;
	private int totalDetailItemNum;
	

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
	public String getPeriodCount() {
		return periodCount;
	}
	public void setPeriodCount(String periodCount) {
		this.periodCount = periodCount;
	}
	public String getPeriodStartDate() {
		return periodStartDate;
	}
	public void setPeriodStartDate(String periodStartDate) {
		this.periodStartDate = periodStartDate;
	}
	public String getPeriodEndDate() {
		return periodEndDate;
	}
	public void setPeriodEndDate(String periodEndDate) {
		this.periodEndDate = periodEndDate;
	}
	public String getVisitComplete() {
		return visitComplete;
	}
	public void setVisitComplete(String visitComplete) {
		this.visitComplete = visitComplete;
	}
	public String getVisitCompleteFinal() {
		return visitCompleteFinal;
	}
	public void setVisitCompleteFinal(String visitCompleteFinal) {
		this.visitCompleteFinal = visitCompleteFinal;
	}
	
	public int getTotalScoreVisit() {
		return totalScoreVisit;
	}
	public void setTotalScoreVisit(int totalScoreVisit) {
		this.totalScoreVisit = totalScoreVisit;
	}
	public int getTotalDetailItemNum() {
		return totalDetailItemNum;
	}
	public void setTotalDetailItemNum(int totalDetailItemNum) {
		this.totalDetailItemNum = totalDetailItemNum;
	}
	
	@Override
	public String toString() {
		return "VisitHistory [visitCode=" + visitCode + ", contractCode=" + contractCode + ", periodCount="
				+ periodCount + ", periodStartDate=" + periodStartDate + ", periodEndDate=" + periodEndDate
				+ ", visitComplete=" + visitComplete + ", visitCompleteFinal=" + visitCompleteFinal
				+ ", totalScoreVisit=" + totalScoreVisit + ", totalDetailItemNum=" + totalDetailItemNum + ", numVisits="
				+ "]";
	}
	
}
