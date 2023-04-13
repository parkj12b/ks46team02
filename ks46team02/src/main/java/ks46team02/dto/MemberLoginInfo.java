package ks46team02.dto;


public class MemberLoginInfo {
	private String memberId;
	private String memberPw;
	private String loginLevel; //admin or normal
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getLoginLevel() {
		return loginLevel;
	}
	public void setLoginLevel(String loginLevel) {
		this.loginLevel = loginLevel;
	}
	@Override
	public String toString() {
		return "MemberLoginInfo [memberId=" + memberId + ", memberPw=" + memberPw + ", loginLevel=" + loginLevel + "]";
	}
	
}
