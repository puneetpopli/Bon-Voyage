package com.hangout.amigos.intf;

import com.hangout.amigos.dto.UserDTO;

public interface UserOperationIntf {

	public UserDTO createUser(UserDTO user);
	
	public UserDTO getUser(String userId);
	
}
