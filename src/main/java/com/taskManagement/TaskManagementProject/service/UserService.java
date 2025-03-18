package com.taskManagement.TaskManagementProject.service;

import com.taskManagement.TaskManagementProject.payload.User_DTO;

public interface UserService {
	
	public User_DTO createUser(User_DTO userDto);
	
	void deleteUser(long userid);

}
