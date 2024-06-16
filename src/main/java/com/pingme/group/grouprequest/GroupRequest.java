package com.pingme.group.grouprequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupRequest {

	private	Integer userid;
	private Integer groupid;
	private String groupName;
	private String contact;

}
