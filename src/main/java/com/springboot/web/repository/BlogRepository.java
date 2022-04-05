package com.springboot.web.repository;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import com.springboot.web.model.Post;
import com.springboot.web.model.User;

@Repository

public interface BlogRepository extends MongoRepository<User, String> {



	User findByEmailAndPassword(String email, String password);

	User findByEmailAndPassword(String email, String password, List<Post> posts);

	User findUserById(String user_id);

	User findByEmail(String email);










	








	



}
