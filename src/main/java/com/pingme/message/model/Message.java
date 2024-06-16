package com.pingme.message.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pingme.chat.model.Chat;
import com.pingme.file.FileContent;
import com.pingme.group.model.GroupChat;
import com.pingme.user.model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "message")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String textMessage;
	private String video;
    private String image;
	private LocalDateTime timeStamp;
	private Integer chatid;
	private Integer groupid;
	private String senderUser;

	@JsonIgnore
	@ManyToOne
	private User user;

	@ManyToOne
	private FileContent fileContent;

	@JsonIgnore
	@ManyToOne
	private Chat chat;

	@JsonIgnore
	@ManyToOne
	private GroupChat groupChat;

}
