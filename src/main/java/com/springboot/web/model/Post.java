package com.springboot.web.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Post")
public class Post {

	@Id
	private String post_id;
	private String post_title;
	private String post_body;
	private String post_desc;
	private String post_url;
	private byte[] image;
	private int like;

	private int category;
	private Date date;

	private String name;
	private String user_id;

	public Post(String post_id, String post_title, String post_body, String post_desc, String post_url, byte[] image,
			int like, int category, Date date, String name, String user_id) {
		super();
		this.post_id = post_id;
		this.post_title = post_title;
		this.post_body = post_body;
		this.post_desc = post_desc;
		this.post_url = post_url;
		this.image = image;
		this.like = like;
		this.category = category;
		this.date = date;

		this.name = name;
		this.user_id = user_id;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPost_id() {
		return post_id;
	}

	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}

	public Post() {
		// TODO Auto-generated constructor stub
	}

	public String getPost_title() {
		return post_title;
	}

	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}

	public String getPost_body() {
		return post_body;
	}

	public void setPost_body(String post_body) {
		this.post_body = post_body;
	}

	public String getPost_desc() {
		return post_desc;
	}

	public void setPost_desc(String post_desc) {
		this.post_desc = post_desc;
	}

	public String getPost_url() {
		return post_url;
	}

	public void setPost_url(String post_url) {
		this.post_url = post_url;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}



}
