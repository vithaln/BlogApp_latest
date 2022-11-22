package com.vitu.code.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitu.code.payload.UserDto;
import com.vitu.code.serviceImpl.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserServiceImpl userimpl;

	@PostMapping
	public ResponseEntity<UserDto> createUsers(@Valid @RequestBody UserDto userDto) {

		UserDto createUser = userimpl.createUser(userDto);
		System.out.println(createUser);
		return new ResponseEntity<UserDto>(createUser, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers() {

		List<UserDto> allUsers = userimpl.getAllUsers();
		return new ResponseEntity<List<UserDto>>(allUsers, HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable int userId) {

		UserDto userById = userimpl.getUserById(userId);
		return new ResponseEntity<UserDto>(userById, HttpStatus.OK);

	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUserById(@PathVariable int userId) {

	 String deleteUserById = userimpl.deleteUserById(userId);
		return new ResponseEntity<String>(deleteUserById, HttpStatus.OK);

	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUsers(@Valid @RequestBody UserDto userDto,@PathVariable int userId){
		
		UserDto updateUserById = userimpl.updateUserById(userDto, userId);
		return new  ResponseEntity<UserDto>(updateUserById,HttpStatus.OK);
	}
}
