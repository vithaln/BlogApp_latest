package com.vitu.code.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vitu.code.config.AppConstants;
import com.vitu.code.entity.Post;
import com.vitu.code.payload.PostDto;
import com.vitu.code.payload.PostResponse;
import com.vitu.code.serviceImpl.FileServiceImpl;
import com.vitu.code.serviceImpl.PostServiceImpl;

@RestController
@RequestMapping("/posts")
public class PostController {
	
	@Autowired
	private PostServiceImpl postimpl;
	
	@Autowired
	private FileServiceImpl fileService;
	
	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postdto,@PathVariable int userId,@PathVariable int categoryId){
		
		PostDto createPost = postimpl.createPost(postdto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<PostResponse> getAllPosts(
	@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false ) Integer pageNumber,
	@RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize,
	@RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
	@RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false)String sortDir

			
			
			){
		
		  PostResponse allPosts = postimpl.getAllPosts(pageNumber,pageSize,sortBy,sortDir);
		
		return new ResponseEntity<PostResponse>(allPosts,HttpStatus.OK);
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getpostByPostId(@PathVariable int postId){
		PostDto postById = postimpl.getPostById(postId);
		
		return new ResponseEntity<PostDto>(postById,HttpStatus.OK);
	}
	
	@DeleteMapping("/{postId}")
	public ResponseEntity<String> deletepostByPostId(@PathVariable int postId){
	postimpl.deletePost(postId);
		return  ResponseEntity.ok("POST HAS BEEN DELTEED SUCCESSFULLY..");
	}

	@PutMapping("/{postId}")
	public ResponseEntity<PostDto> updatepostsByUsigPostId(@RequestBody PostDto postdto,@PathVariable int postId){
		
	PostDto updatePost = postimpl.updatePost(postdto, postId);
		
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}/post")
	public ResponseEntity<List<PostDto>> getpostsByUser(@PathVariable int userId){
		
		List<PostDto> postsByUser = postimpl.getPostsByUser(userId);
		
		return new ResponseEntity<List<PostDto>>(postsByUser,HttpStatus.OK);
	}
	
	@GetMapping("/category/{cateId}/post")
	public ResponseEntity<List<PostDto>> getpostsByCategoryId(@PathVariable int cateId){
		
		List<PostDto> postsByCategory = postimpl.getpostByCategory(cateId);
		
		return new ResponseEntity<List<PostDto>>(postsByCategory,HttpStatus.OK);
	}
	
	//search by title
	
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchByTitle(@PathVariable String keyword){
		List<PostDto> searchPost = postimpl.searchPost(keyword);
		
		return new ResponseEntity<List<PostDto>>(searchPost,HttpStatus.OK);
		
	}
	// post image upload

	@Value("${project.image}")
	private String path;
	
		@PostMapping("/image/upload/{postId}")
		public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
				@PathVariable Integer postId) throws IOException {

			PostDto postDto = this.postimpl.getPostById(postId);
			
			String fileName = this.fileService.uploadImage(path, image);
			postDto.setImageName(fileName);
			PostDto updatePost = this.postimpl.updatePost(postDto, postId);
			return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

		}
		

	    //method to serve files
	    @GetMapping(value = "/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	    public void downloadImage(
	            @PathVariable("imageName") String imageName,
	            HttpServletResponse response
	    ) throws IOException {

	        InputStream resource = this.fileService.getResource(path, imageName);
	        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	        StreamUtils.copy(resource,response.getOutputStream())   ;

	    }

}
