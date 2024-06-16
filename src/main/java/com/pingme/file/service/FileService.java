package com.pingme.file.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pingme.file.FileContent;

public interface FileService {

	public List<FileContent> sendFileUser(MultipartFile file,String filename) throws Exception;

	public List<FileContent> getFileByUser();
	
	public FileContent sendFilestoGroup(MultipartFile file , String integer) throws Exception;

}
