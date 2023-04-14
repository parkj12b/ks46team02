package ks46team02.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.admin.dto.Addr;


@Mapper
public interface AddrMapper {
	/* 전체 회원 배송지 조회*/
	public List<Addr> getAddrList();
}
