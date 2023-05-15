package ks46team02.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.common.dto.FileDto;
import ks46team02.common.dto.FileRelation;

@Mapper
public interface FileMapper {
	
	public int addFile(List<FileDto> fileList); 
	
	public List<FileDto> getFileList();
	
	public FileDto getFileInfoByIdx(String fileIdx);

	public int addFileRelation(FileRelation fileRelation);
	
}
