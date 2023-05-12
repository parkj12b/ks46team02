package ks46team02.contract.dto;

public class TerminateReasonDTO {
 
	private String terminateReasonCode;
	private String terminateReason;
	private String terminateReasonDetail;
	private String terminateReasonRegDate;
	private String adminId;

	public String getTerminateReasonCode() {
		return terminateReasonCode;
	}
	public void setTerminateReasonCode(String terminateReasonCode) {
		this.terminateReasonCode = terminateReasonCode;
	}
	public String getTerminateReason() {
		return terminateReason;
	}
	public void setTerminateReason(String terminateReason) {
		this.terminateReason = terminateReason;
	}
	public String getTerminateReasonDetail() {
		return terminateReasonDetail;
	}
	public void setTerminateReasonDetail(String terminateReasonDetail) {
		this.terminateReasonDetail = terminateReasonDetail;
	}
	public String getTerminateReasonRegDate() {
		return terminateReasonRegDate;
	}
	public void setTerminateReasonRegDate(String terminateReasonRegDate) {
		this.terminateReasonRegDate = terminateReasonRegDate;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	
	@Override
	public String toString() {
		return "TerminateReasonDTO [terminateReasonCode=" + terminateReasonCode + ", terminateReason=" + terminateReason
				+ ", terminateReasonDetail=" + terminateReasonDetail + ", terminateReasonRegDate="
				+ terminateReasonRegDate + ", adminId=" + adminId + "]";
	}
	
	
	
}
