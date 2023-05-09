package ks46team02.farm.dto;

import java.util.List;

public class EvaluationLargeCategory {
	private String largeCategoryCode;
	private String evaluationItem;
	private String evaluationItemRegDate;
	private String adminId;
	private int largeCategoryNum;
	private List<EvaluationDetailCategory> detailCategory;
	
	
	public List<EvaluationDetailCategory> getDetailCategory() {
		return detailCategory;
	}
	public void setDetailCategory(List<EvaluationDetailCategory> detailCategory) {
		this.detailCategory = detailCategory;
	}
	public int getLargeCategoryNum() {
		return largeCategoryNum;
	}
	public void setLargeCategoryNum(int largeCategoryNum) {
		this.largeCategoryNum = largeCategoryNum;
	}
	public String getLargeCategoryCode() {
		return largeCategoryCode;
	}
	public void setLargeCategoryCode(String largeCategoryCode) {
		this.largeCategoryCode = largeCategoryCode;
	}
	public String getEvaluationItem() {
		return evaluationItem;
	}
	public void setEvaluationItem(String evaluationItem) {
		this.evaluationItem = evaluationItem;
	}
	public String getEvaluationItemRegDate() {
		return evaluationItemRegDate;
	}
	public void setEvaluationItemRegDate(String evaluationItemRegDate) {
		this.evaluationItemRegDate = evaluationItemRegDate;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	@Override
	public String toString() {
		return "EvaluationLargeCategory [largeCategoryCode=" + largeCategoryCode + ", evaluationItem=" + evaluationItem
				+ ", evaluationItemRegDate=" + evaluationItemRegDate + ", adminId=" + adminId + ", largeCategoryNum="
				+ largeCategoryNum + ", detailCategory=" + detailCategory + "]";
	}
	
	
}
