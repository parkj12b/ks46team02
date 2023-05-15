package ks46team02.admin.dto;

public class LoginHistory {
	
	private String loginCode;
	private String LoginDate;
	private String logoutDate;
	private String memberId;
	private String ipv4Address;

	private Member memberInfo;

	public Member getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(Member memberInfo) {
		this.memberInfo = memberInfo;
	}

	public String getLoginCode() {
		return loginCode;
	}
	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}
	public String getLoginDate() {
		return LoginDate;
	}
	public void setLoginDate(String loginDate) {
		LoginDate = loginDate;
	}
	public String getLogoutDate() {
		return logoutDate;
	}
	public void setLogoutDate(String logoutDate) {
		this.logoutDate = logoutDate;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getIpv4Address() {
		return ipv4Address;
	}
	public void setIpv4Address(String ipv4Address) {
		this.ipv4Address = ipv4Address;
	}

	@java.lang.Override
	public java.lang.String toString() {
		return "LoginHistory{" +
				"loginCode='" + loginCode + '\'' +
				", LoginDate='" + LoginDate + '\'' +
				", logoutDate='" + logoutDate + '\'' +
				", memberId='" + memberId + '\'' +
				", ipv4Address='" + ipv4Address + '\'' +
				", memberInfo=" + memberInfo +
				'}';
	}

}
