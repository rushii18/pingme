package com.pingme.chat.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ChatRequest {

	private Integer userid;
	private String firstName;
	private String lastName;
	private String contact;
	private String senderFirstName;
	private String reqFirstName;
}
