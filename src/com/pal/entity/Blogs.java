package com.pal.entity;

import java.sql.Date;

import com.pal.dao.BlogsDao;
import com.pal.dao.UsersDao;

/**
 * 动态表Blogs
 * 
 * @author Administrator
 * 
 */
public class Blogs {
	private int b_id;// 编号，主键
	private int userId;// 发表者id，外键
	private String picturePath;// 图片路径
	private String text;// 文字
	private Date sendTime;// 发表时间
	private int likeCounts;// 点赞数量
	private int commentCounts;// 评论数量
	private Users users;// 用户表，外键
	UsersDao ud = new UsersDao();
	public int getB_id() {
		return b_id;
	}

	public void setB_id(int b_id) {
		this.b_id = b_id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public int getLikeCounts() {
		return likeCounts;
	}

	public void setLikeCounts(int likeCounts) {
		this.likeCounts = likeCounts;
	}

	public int getCommentCounts() {
		return commentCounts;
	}

	public void setCommentCounts(int commentCounts) {
		this.commentCounts = commentCounts;
	}

	public Users getUsers() {
		return ud.getUser(this.userId);
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Blogs() {
		super();
	}

	public Blogs(int b_id, int userId, String picturePath, String text,
			Date sendTime, int likeCounts, int commentCounts, Users users) {
		super();
		this.b_id = b_id;
		this.userId = userId;
		this.picturePath = picturePath;
		this.text = text;
		this.sendTime = sendTime;
		this.likeCounts = likeCounts;
		this.commentCounts = commentCounts;
		this.users = users;
	}

	@Override
	public String toString() {
		return "Blogs [b_id=" + b_id + ", userId=" + userId + ", picturePath="
				+ picturePath + ", text=" + text + ", sendTime=" + sendTime
				+ ", likeCounts=" + likeCounts + ", commentCounts="
				+ commentCounts + "]";

	}

}
