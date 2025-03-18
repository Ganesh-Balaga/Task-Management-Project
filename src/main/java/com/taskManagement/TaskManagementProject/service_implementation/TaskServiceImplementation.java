package com.taskManagement.TaskManagementProject.service_implementation;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskManagement.TaskManagementProject.entity.Task;
import com.taskManagement.TaskManagementProject.entity.Users;
import com.taskManagement.TaskManagementProject.exception.APIException;
import com.taskManagement.TaskManagementProject.exception.TaskNotFound;
import com.taskManagement.TaskManagementProject.exception.UserNotFound;
import com.taskManagement.TaskManagementProject.payload.Task_DTO;
import com.taskManagement.TaskManagementProject.repository.TaskRepository;
import com.taskManagement.TaskManagementProject.repository.UserRepository;
import com.taskManagement.TaskManagementProject.service.TaskService;

@Service
public class TaskServiceImplementation implements TaskService{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Override
	public Task_DTO saveTask(long userid, Task_DTO taskDto) {
		Users user = userRepository.findById(userid)
			    .orElseThrow(() -> new UserNotFound(String.format("User Id %d not found", userid)));
		Task task = new Task();
		task.setTaskname(taskDto.getTaskname()); // ✅ Ensure taskname is not null
		task.setUsers(user);
		Task savedTask = taskRepository.save(task);

		return modelMapper.map(savedTask, Task_DTO.class);
//		return null;
	}

	@Override
	public List<Task_DTO> getAllTasks(long userid) {
		userRepository.findById(userid)
	    .orElseThrow(() -> new UserNotFound(String.format("User Id %d not found", userid)));
		
		List<Task> tasks = taskRepository.findAllByUsersId(userid);
		
		
		
		return tasks.stream().map(
				task -> modelMapper.map(task,Task_DTO.class)
				).collect(Collectors.toList());
	}

	@Override
	public Task_DTO getTask(long userid, long taskid) {
		Users users = userRepository.findById(userid)
			    .orElseThrow(() -> new UserNotFound(String.format("User Id %d not found", userid)));
		
		Task task = taskRepository.findById(taskid).orElseThrow(
				() -> new TaskNotFound(String.format("Task Id %d not found", taskid)));
		if(users.getId() != task.getUsers().getId()) {
			throw new APIException(String.format("Task Id %d is not belongs to User Id %d", taskid,userid));
		}
		return modelMapper.map(task,Task_DTO.class);
	}

	@Override
	public void deleteTask(long userid, long taskid) {
		Users users = userRepository.findById(userid)
			    .orElseThrow(() -> new UserNotFound(String.format("User Id %d not found", userid)));
		
		Task task = taskRepository.findById(taskid).orElseThrow(
				() -> new TaskNotFound(String.format("Task Id %d not found", taskid)));
		if(users.getId() != task.getUsers().getId()) {
			throw new APIException(String.format("Task Id %d is not belongs to User Id %d", taskid,userid));
		}
		taskRepository.deleteById(taskid);
		
	}

}
