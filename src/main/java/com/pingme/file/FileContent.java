package com.pingme.file;

import com.pingme.chat.model.Chat;
import com.pingme.group.model.GroupChat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "files")
public class FileContent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String fileName;
	private String fileContent;
	private Long fileSize;


	@ManyToOne
	private Chat chat;

	@ManyToOne
	private GroupChat groupChatfile;
}
