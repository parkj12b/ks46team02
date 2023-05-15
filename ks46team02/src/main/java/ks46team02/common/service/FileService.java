package ks46team02.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ks46team02.common.dto.FileDto;
import ks46team02.common.dto.FileRelation;
import ks46team02.common.mapper.FileMapper;
import ks46team02.common.mapper.MainMapper;
import ks46team02.common.util.FileUtil;

@Service
@Transactional
public class FileService {
	
	private FileUtil fileUtil;
	private FileMapper fileMapper;
	private MainMapper mainMapper;
	
	public FileService(FileUtil fileUtil, FileMapper fileMapper, MainMapper mainMapper) {
			
			this.mainMapper = mainMapper;
			this.fileUtil = fileUtil;
			this.fileMapper = fileMapper;
		}
	
	public Map<String,Object> uploadMenteeDocument(MultipartFile[] uploadfile, String fileRealPath, String fileAssociateKey) {
		
		List<FileDto> fileList= fileUtil.parseFileInfo(uploadfile, fileRealPath, fileAssociateKey);
		
		
		int numFiles = fileList.size();
		boolean isSuccess = false;
		Map<String, Object> returnMap = new HashMap<>();
		int result = 0;
		String msg = "";
		if(fileList != null) {
			result = fileMapper.addFile(fileList);
		}
		
		if(result == numFiles) {
			msg = numFiles + "개의 파일 업로드가 성공했습니다.";
			isSuccess = true;
		} else {
			msg = numFiles + "개의 파일중 " + (numFiles - result) +"개의 파일이 실패했습니다."; 
			
		}
		returnMap.put("msg", msg);
		returnMap.put("isSuccess", isSuccess);
		
		return returnMap;
	}
	
	public List<FileDto> getFileList(){
		List<FileDto> fileList = fileMapper.getFileList();
		
		return fileList;
	}
	
	public FileDto getFileInfoByIdx(String fileIdx) {
		return fileMapper.getFileInfoByIdx(fileIdx);
	}
	
}
