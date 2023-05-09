package ks46team02.common.dto;

public class Member {
	
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberPhone;
	private String memberEmail;
	private String positionLevelCode;
	private String companyCode;
	private String memberRegDate;
	private String memberCi;
	private String memberStatus;
	private String dormantMemberRegDate;
	private boolean isOwner;
	private boolean isExist;
	private String companyTypeNum;

	public String getMemberLevelName() {
		return memberLevelName;
	}
	public void setMemberLevelName(String memberLevelName) {
		this.memberLevelName = memberLevelName;
	}

	private String memberLevelName;
	
	public String getCompanyTypeNum() {
		return companyTypeNum;
	}
	public void setCompanyTypeNum(String companyTypeNum) {
		this.companyTypeNum = companyTypeNum;
	}
	public boolean isExist() {
		return isExist;
	}
	public void setExist(boolean isExist) {
		this.isExist = isExist;
	}
	public boolean isOwner() {
		return isOwner;
	}
	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}
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
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getPositionLevelCode() {
		return positionLevelCode;
	}
	public void setPositionLevelCode(String positionLevelCode) {
		this.positionLevelCode = positionLevelCode;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getMemberRegDate() {
		return memberRegDate;
	}
	public void setMemberRegDate(String memberRegDate) {
		this.memberRegDate = memberRegDate;
	}
	public String getMemberCi() {
		return memberCi;
	}
	public void setMemberCi(String memberCi) {
		this.memberCi = memberCi;
	}
	public String getMemberStatus() {
		return memberStatus;
	}
	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}
	public String getDormantMemberRegDate() {
		return dormantMemberRegDate;
	}
	public void setDormantMemberRegDate(String dormantMemberRegDate) {
		this.dormantMemberRegDate = dormantMemberRegDate;
	}

	@Override
	public String toString() {
		return "Member{" +
				"memberId='" + memberId + '\'' +
				", memberPw='" + memberPw + '\'' +
				", memberName='" + memberName + '\'' +
				", memberPhone='" + memberPhone + '\'' +
				", memberEmail='" + memberEmail + '\'' +
				", positionLevelCode='" + positionLevelCode + '\'' +
				", companyCode='" + companyCode + '\'' +
				", memberRegDate='" + memberRegDate + '\'' +
				", memberCi='" + memberCi + '\'' +
				", memberStatus='" + memberStatus + '\'' +
				", dormantMemberRegDate='" + dormantMemberRegDate + '\'' +
				", isOwner=" + isOwner +
				", isExist=" + isExist +
				", companyTypeNum='" + companyTypeNum + '\'' +
				", memberLevelName='" + memberLevelName + '\'' +
				'}';
	}


}
