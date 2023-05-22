package ks46team02.common.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import ks46team02.common.dto.FileRelation;
import ks46team02.common.service.FileService;

@Controller
@RequestMapping("/file")
public class FileController {

	private static final Logger log = LoggerFactory.getLogger(FileController.class);

	@Value("${files.path}")
	private String filePath;

	private FileService fileService;

	public FileController(FileService fileService) {
		this.fileService = fileService;
	}

	/* 멘토멘티 멘티 매출 증빙서류 업로드 */
	@PostMapping("/uploadMenteeApplyEvidence")
	@ResponseBody
	public void archiveUpload(@RequestParam("file") MultipartFile[] file,  HttpServletRequest request, FileRelation fileRelation) {
		log.info("received request");
		//Map<String, Object> returnMap = fileService.uploadMenteeDocument(uploadfile, filePath, fileRelation);
		
	}
}
