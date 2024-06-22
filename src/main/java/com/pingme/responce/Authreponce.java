package com.pingme.responce;

import com.pingme.user.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authreponce  {

	private String message;
	private String token;
	private User loguser;
	
}
