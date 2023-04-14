package ks46team02.admin.dto;

public class WithdrawalMember {
	private String WithdrawalMemberCode;
	private String WithdrawalMemberId;
	private String WithdrawalMemberReason;
	private String WithdrawalMemberMemberCi;
	private String WithdrawalMemberPhone;
	private String WithdrawalMemberDate;
	public String getWithdrawalMemberCode() {
		return WithdrawalMemberCode;
	}
	public void setWithdrawalMemberCode(String withdrawalMemberCode) {
		WithdrawalMemberCode = withdrawalMemberCode;
	}
	public String getWithdrawalMemberId() {
		return WithdrawalMemberId;
	}
	public void setWithdrawalMemberId(String withdrawalMemberId) {
		WithdrawalMemberId = withdrawalMemberId;
	}
	public String getWithdrawalMemberReason() {
		return WithdrawalMemberReason;
	}
	public void setWithdrawalMemberReason(String withdrawalMemberReason) {
		WithdrawalMemberReason = withdrawalMemberReason;
	}
	public String getWithdrawalMemberMemberCi() {
		return WithdrawalMemberMemberCi;
	}
	public void setWithdrawalMemberMemberCi(String withdrawalMemberMemberCi) {
		WithdrawalMemberMemberCi = withdrawalMemberMemberCi;
	}
	public String getWithdrawalMemberPhone() {
		return WithdrawalMemberPhone;
	}
	public void setWithdrawalMemberPhone(String withdrawalMemberPhone) {
		WithdrawalMemberPhone = withdrawalMemberPhone;
	}
	public String getWithdrawalMemberDate() {
		return WithdrawalMemberDate;
	}
	public void setWithdrawalMemberDate(String withdrawalMemberDate) {
		WithdrawalMemberDate = withdrawalMemberDate;
	}
	@Override
	public String toString() {
		return "withdrawalMember [WithdrawalMemberCode=" + WithdrawalMemberCode + ", WithdrawalMemberId="
				+ WithdrawalMemberId + ", WithdrawalMemberReason=" + WithdrawalMemberReason
				+ ", WithdrawalMemberMemberCi=" + WithdrawalMemberMemberCi + ", WithdrawalMemberPhone="
				+ WithdrawalMemberPhone + ", WithdrawalMemberDate=" + WithdrawalMemberDate + "]";
	}
		
	}

