package com.springboot.web.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.BasicDBObject;
import com.mongodb.client.result.UpdateResult;
import com.springboot.web.model.Comment;
import com.springboot.web.model.Like;
import com.springboot.web.model.Post;
import com.springboot.web.model.User;
import com.springboot.web.repository.BlogRepository;
import com.springboot.web.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	BlogRepository repository;

	@Autowired
	PostRepository postRepository;

	@Autowired
	MongoTemplate mongoTemplate;

	public Post store(MultipartFile file, String pbody, String pTitle, String mDesc, String slug, int category,
			String user_id) {

		Date d = new Date();

		Post p = new Post();

		try {
			p.setImage(file.getBytes());
		} catch (IOException e) {

			e.printStackTrace();
		}

		p.setDate(d);
		p.setPost_body(pbody);
		p.setPost_desc(mDesc);
		p.setCategory(category);
		p.setPost_url(slug);
		p.setPost_title(pTitle);
		p.setLike(0);
		p.setUser_id(user_id);

		User u = repository.findUserById(user_id);

		p.setName(u.getName());

		return postRepository.save(p);

	}

	public List<Post> findUserPosts(String id) {

		return postRepository.findAllPost(id);
	}

	// edit service
	public Post updatePost(String pbody, String pTitle, String mDesc, String slug, int ca_id, String u_id,
			String post_id) {
		Query query = new Query(Criteria.where("_id").is(post_id).and("user_id").is(u_id));
		Post p = mongoTemplate.findOne(query, Post.class);

		Date d = new Date();

		p.setDate(d);
		p.setPost_body(pbody);
		p.setPost_desc(mDesc);
		p.setCategory(ca_id);
		p.setPost_url(slug);
		p.setPost_title(pTitle);
		p.setPost_id(post_id);
		p.setUser_id(u_id);

		return mongoTemplate.save(p);
		
		

	}

//editImageService
	public String editPostImageService(MultipartFile file, String post_id, String u_id) {
		try {
			Query query = new Query(Criteria.where("_id").is(post_id).and("user_id").is(u_id));
			Post p = mongoTemplate.findOne(query, Post.class);
			p.setImage(file.getBytes());
			mongoTemplate.save(p);
		} catch (IOException e) {

			e.printStackTrace();
		}
		Date d = new Date();

		return "Image Updated Sucessfully";
	}
	
	
	
	public int UpdateLike(Post p,String u_id) {
		Post p1=p;
		p1.setLike(p.getLike()+1);
			
		List<Like> l1 = new ArrayList<Like>();
		if(p1.getLikeList()!=null) {
			l1.addAll(p1.getLikeList());
		}
		
		l1.add(new Like(u_id,true));
		System.out.println(l1);
		p1.setLikeList(l1);
		mongoTemplate.save(p1);
		
		return p1.getLike();
	}
		
	public void deleteUser(Post p,String u_id) {
		Post p1 = p;
		List<Like> l1 = new ArrayList<Like>();
		l1.addAll(p1.getLikeList());
		ListIterator<Like> it = l1.listIterator();
		//System.out.println(l1);
		
		try {
		while(it.hasNext()) {
			//System.out.println(it.next());
			Like pp = (Like) it.next();
			//System.out.println(pp);
			if(pp.getUser_id().equals(u_id)) {
				l1.remove(pp);
				p1.setLike(p.getLike()-1);
				System.out.println("deleted");
				System.out.println(l1);
			}
			
		}
		}catch(Exception e) {
			System.out.println(e);
		}
		//System.out.println(l1);
		p1.setLikeList(l1);
		mongoTemplate.save(p1);
	}


	public void saveComments(Post pp, Comment c) {
		Post p2 = pp;
		Date d  = new Date();
		c.setCdate(d);
		List<Comment> l = new ArrayList<Comment>();
		if(p2.getComments()!=null) {
			l.addAll(p2.getComments());
		}	
		l.add(c);
		System.out.println(l);
		//System.out.println(pp);
		p2.setComments(l);
		mongoTemplate.save(p2);
	}


	public List<Comment> fetchAllComment(Post p) {
		System.out.println(p.getComments());
		List<Comment> l = new ArrayList<Comment>();
		if(p.getComments()!=null) {
			l.addAll(p.getComments());
		}
		
		return l;
	}

}
