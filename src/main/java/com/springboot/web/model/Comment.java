package com.springboot.web.model;

import java.util.Date;

public class Comment {

	private String comment;
	private Date cdate;
	private String userName;
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Comment() {
		
	}
	public Comment(String comment, Date cdate, String userName) {
		super();
		this.comment = comment;
		this.cdate = cdate;
		this.userName = userName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "Comment [comment=" + comment + ", cdate=" + cdate + ", userName=" + userName + ", user_id=" + user_id
				+ "]";
	}
	

}
