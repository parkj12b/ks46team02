package ks46team02.dto;

public class AdminMember {
	private String adminId;
	private String adminPw;
	private String adminName;
	private String adminEmail;
	private String adminLevel;
	private String adminPhone;
	private String adminRegDate;
	private boolean isExist;
	public boolean isExist() {
		return isExist;
	}
	public void setExist(boolean isExist) {
		this.isExist = isExist;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminPw() {
		return adminPw;
	}
	public void setAdminPw(String adminPw) {
		this.adminPw = adminPw;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public String getAdminLevel() {
		return adminLevel;
	}
	public void setAdminLevel(String adminLevel) {
		this.adminLevel = adminLevel;
	}
	public String getAdminPhone() {
		return adminPhone;
	}
	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}
	public String getAdminRegDate() {
		return adminRegDate;
	}
	public void setAdminRegDate(String adminRegDate) {
		this.adminRegDate = adminRegDate;
	}
	@Override
	public String toString() {
		return "AdminMember [adminId=" + adminId + ", adminPw=" + adminPw + ", adminName=" + adminName + ", adminEmail="
				+ adminEmail + ", adminLevel=" + adminLevel + ", adminPhone=" + adminPhone + ", adminRegDate="
				+ adminRegDate + "]";
	}
	
}
