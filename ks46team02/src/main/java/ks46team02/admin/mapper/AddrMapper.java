package ks46team02.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.common.dto.Addr;
import ks46team02.common.dto.Member;


@Mapper
public interface AddrMapper {
	/* 전체 회원 배송지 조회*/
	public List<Addr> getAddrList();
	/* 배송지 삭제 */
	public int removeAddr(String addrCode);
	/* 배송지 수정 */
	public int modifyAddr(Addr addr);
	/* 특정 배송지 조회 */	
	public Addr getAddrInfoById(String addrCode);
	/* 배송지 등록 */
	public int addAddr(Addr addr);
	/* 회원별 배송지 숫자 조회 */
	public int getAddrAmountList(String memberId);
	/* 특정회원 배송지 조회 */
	public List<Addr> getAddrMemberList(String memberId);
}
