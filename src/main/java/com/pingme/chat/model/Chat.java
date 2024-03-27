package com.pingme.chat.model;

import java.time.LocalDate;
import java.util.ArrayList;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chat")
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String chatName;

	private String contact;

	private LocalDate timeStamp;

	@ManyToMany
	private List<User> users = new ArrayList<User>();

	@JsonIgnore
	@OneToMany(mappedBy = "chat")
	private List<Message> message = new ArrayList<>();
	
	@OneToMany
	private List<FileContent> files = new ArrayList<>();

}
