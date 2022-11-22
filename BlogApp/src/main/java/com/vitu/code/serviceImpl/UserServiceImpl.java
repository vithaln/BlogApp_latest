package com.vitu.code.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitu.code.entity.User;
import com.vitu.code.exceptions.ResourceNotFoundException;
import com.vitu.code.payload.UserDto;
import com.vitu.code.repository.UserRepo;
import com.vitu.code.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Object UserDto = null;

	private static final Object User = null;

	@Autowired
	private UserRepo userrepo; 
	
	@Autowired
	private ModelMapper modelmapper;
	@Override
	public UserDto createUser(UserDto userDto) {
		
	User users = this.dtoToUser(userDto);
	User savedUser = userrepo.save(users);
	UserDto userToUserDto = userToUserDto(savedUser);
	return userToUserDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
	
		List<User> allUsers = userrepo.findAll();
		List<UserDto> userDtos = allUsers.stream().map(u->this.userToUserDto(u)).collect(Collectors.toList());
		
		return userDtos;
	}

	@Override
	public UserDto getUserById(int userId) {
		
		User user = userrepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user not Found By using  this ID "+userId+" please try to again by using new ID"));
	System.out.println(user);
		UserDto userToUserDto = this.userToUserDto(user);
	 return userToUserDto;
	}

	@Override
	public UserDto updateUserById(UserDto userDto, int userId) {

		
		User user = userrepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user not Found By using this ID "+userId+" please try to again by using new ID"));

		//user.setUserId(userDto.getUserId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User savedUser = userrepo.save(user);
		UserDto userToUserDto = this.userToUserDto(savedUser);
		
		return userToUserDto;
	}
	@Override
	public String deleteUserById(int userId) {


		User user = userrepo.findById(userId).orElseThrow(()->new ResourceNotFoundException());
		userrepo.delete(user);
		return "User has been deleted";
	}
	
	//................................
	public  User dtoToUser(UserDto userDto) {
	//	User usr = this.modelmapper.map(UserDto, User.class);
		
		  User usr=new User(); usr.setUserId(userDto.getUserId());
		  usr.setName(userDto.getName()); usr.setEmail(userDto.getEmail());
		  usr.setAbout(userDto.getAbout()); usr.setPassword(userDto.getPassword());
		 
		
		
		

		return usr;
	}

private UserDto userToUserDto(User user) {
		
	
	  UserDto userDto=new UserDto(); userDto.setUserId(user.getUserId());
	  userDto.setName(user.getName()); userDto.setEmail(user.getEmail());
	  userDto.setAbout(user.getAbout()); userDto.setPassword(user.getPassword());
	 
	//UserDto userDto = this.modelmapper.map(User, UserDto.class);
	
		return userDto;
	}

}
