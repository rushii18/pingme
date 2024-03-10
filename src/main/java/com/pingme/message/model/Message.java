package com.pingme.message.model;

import java.time.LocalDateTime;

import com.pingme.chat.model.Chat;
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
	private String image;
	private String file;
	private String video;
	private String recivedUserName;

	private LocalDateTime timeStamp;
	@ManyToOne
	private User user;
	@ManyToOne
	private Chat chat;
	
	@ManyToOne
	private GroupChat groupChat;

	

}
