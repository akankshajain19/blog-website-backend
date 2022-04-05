package com.springboot.web.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.web.model.Post;

public interface PostRepository extends MongoRepository<Post, String> {

	@Query(value="{ 'user_id' : ?0 }")
	List<Post> findAllPost(String id);

	@Query(value="{ 'post_url' : ?0 ,'_id' : ?1}")
	Post findByUrlAndId(String post_url, String post_id);



	

	
	
	



	

	

}
