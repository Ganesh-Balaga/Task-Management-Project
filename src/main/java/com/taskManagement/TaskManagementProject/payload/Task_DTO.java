package com.taskManagement.TaskManagementProject.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Task_DTO {
	
	private long id;
	private String taskname;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	
	

}
