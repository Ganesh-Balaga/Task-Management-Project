package com.taskManagement.TaskManagementProject.service_implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.taskManagement.TaskManagementProject.entity.Users;
import com.taskManagement.TaskManagementProject.exception.UserNotFound;
import com.taskManagement.TaskManagementProject.payload.User_DTO;
import com.taskManagement.TaskManagementProject.repository.UserRepository;
import com.taskManagement.TaskManagementProject.service.UserService;

@Service
public class UserServiceImplentation implements UserService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User_DTO createUser(User_DTO userDto) {
		Users user = userDtoToEntity(userDto);
		Users savedUser = userRepository.save(user);
		return entityToUserDto(savedUser);
	}

	private Users userDtoToEntity(User_DTO userDto) {
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		Users users = new Users();
		users.setName(userDto.getName());
		users.setEmail(userDto.getEmail());
		users.setPassword(userDto.getPassword());
		return users;
	}
	
	private User_DTO entityToUserDto(Users savedUser) {
		User_DTO userDto = new User_DTO();
		userDto.setId(savedUser.getId());
		userDto.setEmail(savedUser.getEmail());
		userDto.setPassword(savedUser.getPassword());
		userDto.setName(savedUser.getName());
		return userDto;
	}

	@Override
	public void deleteUser(long userid) {
		Users user = userRepository.findById(userid)
		        .orElseThrow(() -> new UserNotFound(String.format("User Id %d not found", userid)));

		    userRepository.delete(user);
		
	}

}
