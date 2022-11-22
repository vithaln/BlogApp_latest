package com.vitu.code.payload;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {

	private int userId;
	
	@NotEmpty
	@Size(min=3, message = "name must be at least 3 charcters  please try again!!")
	private String name;
	@Email(message = "Invalid Email please check it properly!...")
	private String email;
	@NotEmpty
	@Size(min=3,max=100, message = "about must be at least 3 charcters to 100 characters, please try again!!")
	private String about;
	@NotEmpty
	@Size(min=3, message = "Invalid password please try again!!")
	@Pattern(regexp = "^.*(?=.{8,})(?=.*\\d)(?=.*[a-zA-Z])|(?=.{8,})(?=.*\\d)(?=.*[!@#$%^&])|(?=.{8,})(?=.*[a-zA-Z])(?=.*[!@#$%^&]).*$")
	private String password;
}
