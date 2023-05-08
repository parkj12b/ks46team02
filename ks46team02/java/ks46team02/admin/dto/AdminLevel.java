package ks46team02.admin.dto;

public class AdminLevel {
	private String adminLevel;
	private String adminPosition;
	private String adminMember;
	private String adminContract;
	private String adminFarm;
	private String adminQuestion;
	private String adminSetting;
	private String adminPayment;
	private String adminRegDate;
	
	public String getAdminLevel() {
		return adminLevel;
	}
	public void setAdminLevel(String adminLevel) {
		this.adminLevel = adminLevel;
	}
	public String getAdminPosition() {
		return adminPosition;
	}
	public void setAdminPosition(String adminPosition) {
		this.adminPosition = adminPosition;
	}
	public String getAdminMember() {
		return adminMember;
	}
	public void setAdminMember(String adminMember) {
		this.adminMember = adminMember;
	}
	public String getAdminContract() {
		return adminContract;
	}
	public void setAdminContract(String adminContract) {
		this.adminContract = adminContract;
	}
	public String getAdminFarm() {
		return adminFarm;
	}
	public void setAdminFarm(String adminFarm) {
		this.adminFarm = adminFarm;
	}
	public String getAdminQuestion() {
		return adminQuestion;
	}
	public void setAdminQuestion(String adminQuestion) {
		this.adminQuestion = adminQuestion;
	}
	public String getAdminSetting() {
		return adminSetting;
	}
	public void setAdminSetting(String adminSetting) {
		this.adminSetting = adminSetting;
	}
	public String getAdminPayment() {
		return adminPayment;
	}
	public void setAdminPayment(String adminPayment) {
		this.adminPayment = adminPayment;
	}
	public String getAdminRegDate() {
		return adminRegDate;
	}
	public void setAdminRegDate(String adminRegDate) {
		this.adminRegDate = adminRegDate;
	}
	@Override
	public String toString() {
		return "AdminLevel [adminLevel=" + adminLevel + ", adminPosition=" + adminPosition + ", adminMember="
				+ adminMember + ", adminContract=" + adminContract + ", adminFarm=" + adminFarm + ", adminQuestion="
				+ adminQuestion + ", adminSetting=" + adminSetting + ", adminPayment=" + adminPayment
				+ ", adminRegDate=" + adminRegDate + "]";
	}
	
}
