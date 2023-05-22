package ks46team02.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.common.dto.FileDto;
import ks46team02.common.dto.FileRelation;

@Mapper
public interface FileMapper {
	
	//파일 추가
	public int addFile(List<FileDto> fileList); 
	
	//파일 리스트 조회
	public List<FileDto> getFileList();
	
	//파일 코드로 파일 정보 조회
	public FileDto getFileInfoByIdx(String fileIdx);

	//파일관계 생성
	public int addFileRelation(FileRelation fileRelation);
	
}
