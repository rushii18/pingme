package com.pingme.group.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pingme.file.FileContent;
import com.pingme.message.model.Message;
import com.pingme.user.model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "groupChat")
public class GroupChat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String groupImage;
	private String groupName;
	private LocalDate timeStamp;

	@ManyToMany
	private List<User> users = new ArrayList<>();

	
	@OneToMany(mappedBy = "groupChat")
	private List<Message> messages = new ArrayList<>();
	
	
	@OneToMany(mappedBy = "groupChatfile")
	private List<FileContent> file = new ArrayList<>();
	
	
	

}
