package ks46team02.common.dto;

public class Addr {
	private String addrCode;
	private String addrRoad;
	private String addrLoca;
	private String addrName;
	private String addrRecipient;
	private String addrTel;
	private String addrPhone;
	private String addrSeq;
	private String memberId;
	public String getAddrcode() {
		return addrCode;
	}
	public void setAddrcode(String addrcode) {
		this.addrCode = addrcode;
	}
	public String getAddrRoad() {
		return addrRoad;
	}
	public void setAddrRoad(String addrRoad) {
		this.addrRoad = addrRoad;
	}
	public String getAddrLoca() {
		return addrLoca;
	}
	public void setAddrLoca(String addrLoca) {
		this.addrLoca = addrLoca;
	}
	public String getAddrName() {
		return addrName;
	}
	public void setAddrName(String addrName) {
		this.addrName = addrName;
	}
	public String getAddrRecipient() {
		return addrRecipient;
	}
	public void setAddrRecipient(String addrRecipient) {
		this.addrRecipient = addrRecipient;
	}
	public String getAddrTel() {
		return addrTel;
	}
	public void setAddrTel(String addrTel) {
		this.addrTel = addrTel;
	}
	public String getAddrPhone() {
		return addrPhone;
	}
	public void setAddrPhone(String addrPhone) {
		this.addrPhone = addrPhone;
	}
	public String getAddrSeq() {
		return addrSeq;
	}
	public void setAddrSeq(String addrSeq) {
		this.addrSeq = addrSeq;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	@Override
	public String toString() {
		return "Addr [addrcode=" + addrCode + ", addrRoad=" + addrRoad + ", addrLoca=" + addrLoca + ", addrName="
				+ addrName + ", addrRecipient=" + addrRecipient + ", addrTel=" + addrTel + ", addrPhone=" + addrPhone
				+ ", addrSeq=" + addrSeq + ", memberId=" + memberId + "]";
	}

}
