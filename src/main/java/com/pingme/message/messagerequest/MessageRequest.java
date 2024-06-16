package com.pingme.message.messagerequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class MessageRequest {

	private Integer userid;
	private String chatName;
	private String groupName;
	private String firstNme;
	 
	private String msg;
	
	
	
}
