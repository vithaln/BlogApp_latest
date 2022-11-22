package com.vitu.code.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	
	@Column(name="post_title", nullable=false)
	private String title;
	
	@Column(name="post_content", nullable = false)
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	
	@ManyToOne()
	@JoinColumn(name="categoryId")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name ="userId")
	private User user;

	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	//@JoinColumn(name = "postId")
	private Set<Comment> comments =new HashSet<>();

}

