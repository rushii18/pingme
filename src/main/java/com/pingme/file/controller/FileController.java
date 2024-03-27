package com.pingme.file.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pingme.file.FileContent;
import com.pingme.file.service.FileService;

@RestController
public class FileController {

	@Autowired
	private FileService fileService;

	@PostMapping("/fileupload/{chatid}")
	public String uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Integer chatid) throws Exception {
		System.out.println("I am here...");
		List<FileContent> fc = fileService.sendFileUser(file, chatid);

		return "file upload";
	}

	@PostMapping("/test")
	public String uploadFile() throws Exception {
		System.out.println("I am here...");

		return "file upload";
	}

	@GetMapping("/fileget")
	public List<FileContent> getAllFiles() {

		List<FileContent> fc = fileService.getFileByUser();

		return fc;
	}

	@PostMapping("/fileuploadtoGroup/{groupid}")
	public FileContent uploadFiletoGroup(@RequestParam("file") MultipartFile file, @PathVariable Integer groupid)
			throws Exception {

		FileContent fc = fileService.sendFilestoGroup(file, groupid);

		return fc;
	}

}
