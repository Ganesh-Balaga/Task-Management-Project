package com.taskManagement.TaskManagementProject.service;

import java.util.List;

import com.taskManagement.TaskManagementProject.payload.Task_DTO;

public interface TaskService {
	
	public Task_DTO saveTask(long userid, Task_DTO taskDto);
	
	public List<Task_DTO> getAllTasks(long userid);
	
	public Task_DTO getTask(long userid,long taskid);
	
	public void deleteTask(long userid,long taskid);

}
