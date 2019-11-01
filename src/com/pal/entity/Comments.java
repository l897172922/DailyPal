package com.pal.entity;

import java.sql.Date;

import com.pal.dao.BlogsDao;
import com.pal.dao.UsersDao;

/**
 * 动态评论表Comments
 * @author Administrator
 *
 */
public class Comments {
	private int commentsId;//编号，主键自增
	private int toblogsId;//评论的动态id，外键
	private int fromuserId;//评论人id，外键
	private String content;//评论内容
	private Date time;//评论时间
	private Blogs blogs;//动态表，外键
	private Users users;//用户表，外键
	BlogsDao blogDao =new BlogsDao();
	UsersDao usersDao=new UsersDao();
	public int getCommentsId() {
		return commentsId;
	}
	public void setCommentsId(int commentsId) {
		this.commentsId = commentsId;
	}
	public int getToblogsId() {
		return toblogsId;
	}
	public void setToblogsId(int toblogsId) {
		this.toblogsId = toblogsId;
	}
	public int getFromuserId() {
		return fromuserId;
	}
	public void setFromuserId(int fromuserId) {
		this.fromuserId = fromuserId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Blogs getBlogs() {
		return blogDao.getBlogs(this.toblogsId);
	}
	public void setBlogs(Blogs blogs) {
		this.blogs = blogs;
	}
	public Users getToUsers() {
		return usersDao.getUser(this.toblogsId);
	}
	public void setToUsers(Users users) {
		this.users = users;
	}
	public Users getFromUsers() {
		return usersDao.getUser(this.fromuserId);
	}
	public void setFromUsers(Users users) {
		this.users = users;
	}
	public Comments() {
		super();
	}
	public Comments(int commentsId, int toblogsId, int fromuserId,
			String content, Date time, Blogs blogs, Users users) {
		super();
		this.commentsId = commentsId;
		this.toblogsId = toblogsId;
		this.fromuserId = fromuserId;
		this.content = content;
		this.time = time;
		this.blogs = blogs;
		this.users = users;
	}
	@Override
	public String toString() {
		return "Comments [commentsId=" + commentsId + ", toblogsId="
				+ toblogsId + ", fromuserId=" + fromuserId + ", content="
				+ content + ", time=" + time + "]";
	}
	
}
