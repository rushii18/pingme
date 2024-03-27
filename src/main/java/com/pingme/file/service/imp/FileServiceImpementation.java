package com.pingme.file.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pingme.chat.model.Chat;
import com.pingme.chat.repo.ChatRepository;
import com.pingme.chat.service.ChatService;
import com.pingme.file.FileContent;
import com.pingme.file.repo.FileRepository;
import com.pingme.file.service.FileService;
import com.pingme.group.model.GroupChat;
import com.pingme.group.repository.GroupChatRepository;
import com.pingme.group.service.GroupChatService;

@Service
public class FileServiceImpementation implements FileService {

	@Autowired
	private FileRepository fileRepository;

	@Autowired
	private GroupChatService groupChatService;

	@Autowired
	private GroupChatRepository groupChatRepository;

	@Autowired
	private ChatRepository chatRepository;

	@Autowired
	private ChatService chatService;

	@Override
	public List<FileContent> sendFileUser(MultipartFile file, Integer chatid) throws Exception {

		List<FileContent> addfile = new ArrayList<>();
		// Optional<Chat> chat = chatRepository.findById(chatid);

		Chat chat = chatService.findByid(chatid);

		FileContent fileContent = new FileContent(null, file.getOriginalFilename(), file.getContentType(),
				file.getSize(), chat, null);

		addfile.add(fileContent);

		this.fileRepository.saveAll(addfile);
		chat.setFiles(addfile);
		this.chatRepository.save(chat);
		return addfile;
	}

	@Override
	public List<FileContent> getFileByUser() {

		List<FileContent> getAll = fileRepository.findAll();

		return getAll;
	}

	@Override
	public FileContent sendFilestoGroup(MultipartFile file, Integer groupid) throws Exception {

		List<FileContent> addfile = new ArrayList<>();

		GroupChat groupChat = groupChatService.findByGroupId(groupid);

		FileContent fileContent = new FileContent(null, file.getOriginalFilename(), file.getContentType(),
				file.getSize(), null, groupChat);

		addfile.add(fileContent);
		groupChat.getFile().add(fileContent);
		this.groupChatRepository.save(groupChat);

		this.fileRepository.saveAll(addfile);

		return fileContent;

	}

}
