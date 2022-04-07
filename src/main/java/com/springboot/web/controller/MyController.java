package com.springboot.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.web.model.Comment;
import com.springboot.web.model.Post;
import com.springboot.web.model.User;
import com.springboot.web.repository.BlogRepository;
import com.springboot.web.repository.PostRepository;
import com.springboot.web.service.PostService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MyController {

	@Autowired
	BlogRepository repository;

	@Autowired
	PostService PostService;

	@Autowired
	PostRepository postRepository;

	@RequestMapping("/home")
	@ResponseBody
	public String getHome() {
		return "hello from home";
	}

	//create blog
	@PostMapping("/post")
	public Post createPost(@RequestParam("selectedImage") MultipartFile file, @RequestParam("value") String pbody,
			@RequestParam("userPost") String pTitle, @RequestParam("desc") String mDesc,
			@RequestParam("slug") String slug, @RequestParam(required = false, name = "c_id") String c_id,
			@RequestParam(required = false, name = "u_id") String u_id) {

		int ca_id = new Integer(c_id);

		return PostService.store(file, pbody, pTitle, mDesc, slug, ca_id, u_id);

	}

	//register user
	@PostMapping("/register")
	public String registerUser(@RequestBody User user) {
	User u = repository.findByEmail(user.getEmail());
		
		if(u != null) {
			return "Already registered with this email";
		}
		else {
			repository.save(user);
			return "Successfully Register";
		}
	
	}

	//login
	@PostMapping("/login")
	public User loginUser(@RequestBody User user) {
		User u = repository.findByEmailAndPassword(user.getEmail(), user.getPassword());



		return u;
	}

	//get all post of user
	@GetMapping("/profile/{id}")
	public List<Post> UserPosts(@PathVariable String id) {

		return PostService.findUserPosts(id);
	}

	//get all blogs
	@GetMapping("/allPost")
	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}

	//delete blog
	@DeleteMapping("/profile/{postId}")
	public void deletePost(@PathVariable String postId) {
		postRepository.deleteById(postId);
	}

	//fetching blog for editing
	@GetMapping("/profile/editPost/{post_url}/{post_id}")
	public Post GetPost(@PathVariable String post_url, @PathVariable String post_id) {
		return postRepository.findByUrlAndId(post_url, post_id);
	}

	//viewPost
	@GetMapping("/profile/viewPost/{post_url}/{post_id}")
	public Post GetPostToView(@PathVariable String post_url, @PathVariable String post_id) {
		return postRepository.findByUrlAndId(post_url, post_id);
	}
	
	//edit blog
	@PutMapping("/profile/editPost/{post_url}/{post_id}")
	public Post editPost( @RequestParam("value") String pbody,
			@RequestParam("userPost") String pTitle, @RequestParam("desc") String mDesc,
			@RequestParam("slug") String slug, @RequestParam(required = false, name = "c_id") String c_id,
			@RequestParam(required = false, name = "u_id") String u_id, @RequestParam("post_id") String post_id) {
		int ca_id = new Integer(c_id);

		return PostService.updatePost( pbody, pTitle, mDesc, slug, ca_id, u_id, post_id);

	}
	
	//editImage
	@PutMapping("/profile/editImage/{post_url}/{post_id}")
	public String editPostImage(@RequestParam("selectedImage") MultipartFile file,@RequestParam(required = false, name = "u_id") String u_id, @RequestParam("post_id") String post_id) {
	
		System.out.println(u_id);
		return PostService.editPostImageService(file,post_id,u_id);

	}
	
	@PutMapping("/like")
	public int liked(@RequestBody Post post) {
		System.out.println("liked"+post.getLike());
		Optional<Post> p = postRepository.findById(post.getPost_id());
		Post p1 = p.get();

		System.out.println(post.getUser_id());
		
		return PostService.UpdateLike(p1,post.getUser_id());
	}
	@PutMapping("/unlike")
	public int unliked(@RequestBody Post post) {
		System.out.println("unliked"+post.getLike());
		Optional<Post> p = postRepository.findById(post.getPost_id());
		Post p1 = p.get();
		//p1.setUser_id(post.getUser_id());	
		System.out.println(post.getUser_id());
		PostService.deleteUser(p1,post.getUser_id());
		return p1.getLike();
	}
	
	@PutMapping("/saveComment/{comments}")
	public String saveComment(@RequestBody Post p,@PathVariable String comments) {
		System.out.println(p+" "+comments);
		Comment c = new Comment();
		c.setComment(comments);
		User u = repository.findUserById(p.getUser_id());
		c.setUserName(u.getName());
		c.setUser_id(p.getUser_id());
		Optional<Post> p1 = postRepository.findById(p.getPost_id());
		Post pp = p1.get();
		pp.setPost_id(p.getPost_id());
		System.out.println(c);
		PostService.saveComments(pp,c);	
		return "ok";
	}
	
	@GetMapping("fetchComment/{post_id}")
	public List<Comment> fetchComment(@PathVariable String post_id){
		Optional<Post> p1 = postRepository.findById(post_id);
		Post p = p1.get();
		
		return PostService.fetchAllComment(p);
	}
	
	@GetMapping("/allPost/{category}")
	public List<Post> getAllPosts(@PathVariable String category) {
		int c = Integer.parseInt(category);
		
//		System.out.println(category);
		if(c == 1) {
			return postRepository.findAll();
		}
		else {
			return postRepository.findByCategory(c);
		}
		
	}
	
	

}
