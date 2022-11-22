package com.vitu.code.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	
	@Column(nullable = false,length = 10)
	private String name;
	private String email;
	private String about;
	private String password;
	
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Post> posts=new ArrayList<>();
	

}
