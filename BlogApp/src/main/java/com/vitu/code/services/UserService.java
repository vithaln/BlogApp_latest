package com.vitu.code.services;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.vitu.code.payload.UserDto;

public interface UserService {

	UserDto createUser(UserDto userDto);
	List<UserDto> getAllUsers();
	UserDto getUserById(int userId);
	UserDto updateUserById(UserDto userDto,@PathVariable int userId );
	String deleteUserById(int userId);
}
