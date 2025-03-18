package com.taskManagement.TaskManagementProject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskManagement.TaskManagementProject.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long>{

	Optional<Users> findByEmail(String email);

}
