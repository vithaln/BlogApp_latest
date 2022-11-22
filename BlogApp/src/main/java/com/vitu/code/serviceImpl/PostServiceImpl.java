package com.vitu.code.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vitu.code.entity.Category;
import com.vitu.code.entity.Post;
import com.vitu.code.entity.User;
import com.vitu.code.exceptions.ResourceNotFoundException;
import com.vitu.code.payload.PostDto;
import com.vitu.code.payload.PostResponse;
import com.vitu.code.repository.CategoryRepo;
import com.vitu.code.repository.PostRepo;
import com.vitu.code.repository.UserRepo;
import com.vitu.code.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	
 Logger log =LoggerFactory.getLogger(PostServiceImpl.class);
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo cateRepo;
	
	
	@Override
	public PostDto createPost(PostDto Postdto, Integer userId, Integer categoryId) {
		User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("USER NOT FOUND WITH THIS ID IS "+ userId));
		
		log.info("====="+user);
		Category category = cateRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("CATEGORY NOT FOUND WITH THIS ID ID "+categoryId));
	
		log.info("====="+category);
		
		Post post=	this.modelmapper.map(Postdto, Post.class);
	post.setCategory(category);
	post.setAddedDate(new Date());
	post.setUser(user);
	post.setImageName("default.png");
	
	Post posts = postRepo.save(post);
	PostDto postDto = this.modelmapper.map(posts, PostDto.class);
		return postDto;
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		
		Sort sort=null;
		
		if(sortDir.equalsIgnoreCase("ascending")) {
			sort=Sort.by(sortBy).ascending();
		}
		else {
			sort=Sort.by(sortBy).descending();
		}
		
Pageable p=PageRequest.of(pageNumber, pageSize,sort );


		 Page<Post> pagepost = postRepo.findAll(p);
		 
		 List<Post> posts = pagepost.getContent();

		
		
		
		List<PostDto> postDtos = posts.stream().map((post)-> this.modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
     
PostResponse postResponse=new PostResponse();

postResponse.setContents(postDtos);
postResponse.setPageSize(pagepost.getSize());
postResponse.setPageNumber(pagepost.getNumber());
postResponse.setTotalpages(pagepost.getTotalPages());
postResponse.setTotalElements(pagepost.getTotalElements());
postResponse.setLastPage(pagepost.isLast());
		
		return  postResponse;
	}

	@Override
	public PostDto getPostById(int postId) {
		
	
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("POST NOT FOUND WITH THIS ID "+postId));
		PostDto postdto = this.modelmapper.map(post, PostDto.class);
		return postdto;
	}

	@Override
	public PostDto updatePost(PostDto postDto, int postId) {
		
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("POST NOT FOUND TO UPDATE RESOURCE BY USING THIS ID ID "+postId));
		
		//post.setAddedDate(postDto.getAddedDate());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		//post.setPostId(postDto.getPostId());
		post.setTitle(postDto.getTitle());
		//post.setCategory(postDto.getCategory());
		//post.setUser(postDto.getUser());
	Post posts = postRepo.save(post);
		PostDto postdto = this.modelmapper.map(posts, PostDto.class);
		return postdto;
	}

	@Override
	public void deletePost(int postId) {


		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("POST NOT FOUND WITH THIS ID IS "+postId));
		
		postRepo.delete(post);
	}

	@Override
	public List<PostDto> getPostsByUser(int userId) {

		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("USER NOT FOUND THIS ID IS "+userId));
List<Post> post = postRepo.findByUser(user);
List<PostDto> postDtos = post.stream().map((p)->this.modelmapper.map(p, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> getpostByCategory(int categoryId) {

Category category = cateRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("CATEGORY NOT FOUND BY USING THIS ID ID "+categoryId));
		List<Post> post = postRepo.findByCategory(category);
		List<PostDto> postdtos = post.stream().map(p-> this.modelmapper.map(p, PostDto.class)).collect(Collectors.toList());
		


return postdtos;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {


		List<Post> posts = postRepo.findByTitleContaining(keyword);
	 List<PostDto> postDtos = posts.stream().map((p)->this.modelmapper.map(p, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}
