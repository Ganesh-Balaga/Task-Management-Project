package com.taskManagement.TaskManagementProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskManagement.TaskManagementProject.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

	List<Task> findAllByUsersId(long userid);

}
