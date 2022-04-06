package com.springboot.web.model;

import org.springframework.data.annotation.Id;

public class Like {
	
	
	private String user_id;
	private boolean like;

	public Like() {
		
	}

	public Like(String user_id, boolean like) {
		super();
		this.user_id = user_id;
		this.like = like;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public boolean isLike() {
		return like;
	}
	public void setLike(boolean like) {
		this.like = like;
	}
	
	
	@Override
	public String toString() {
		return "Like [user_id=" + user_id + ", like=" + like + "]";
	}
}
