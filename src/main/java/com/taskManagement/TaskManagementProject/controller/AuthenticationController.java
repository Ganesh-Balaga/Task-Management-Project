package com.taskManagement.TaskManagementProject.controller;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskManagement.TaskManagementProject.payload.JWTAuthenticationResponse;
import com.taskManagement.TaskManagementProject.payload.Login_DTO;
import com.taskManagement.TaskManagementProject.payload.User_DTO;
import com.taskManagement.TaskManagementProject.security.JwtTokenProvider;
import com.taskManagement.TaskManagementProject.service.UserService;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@PostMapping("/register")
	public ResponseEntity<User_DTO> createUser(@RequestBody User_DTO userDto) {
		return new ResponseEntity<>(userService.createUser(userDto),HttpStatus.CREATED);
	}
	@PostMapping("/login")
	public ResponseEntity<JWTAuthenticationResponse> loginUser(@RequestBody Login_DTO loginDto){
		Authentication authentication =
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtTokenProvider.generateToken(authentication);
		
		return ResponseEntity.ok(new JWTAuthenticationResponse(token));
	}
	@DeleteMapping("/users/{userid}")
	public ResponseEntity<String> deleteUser(@PathVariable long userid) {
	    userService.deleteUser(userid);
	    return new ResponseEntity<>("User deleted successfully!", HttpStatus.OK);
	}

}
