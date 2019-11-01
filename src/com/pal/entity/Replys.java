package com.pal.entity;

import java.sql.Date;

import com.pal.dao.CommentsDao;
import com.pal.dao.UsersDao;

/**
 * 评论回复表Replys
 * @author Administrator
 *
 */
public class Replys {
	private int replyId;//编号，主键自增
	private int commentsId;//所回复的评论id，外键
	private int fromuserId;//回复人id，外键
	private int touserId;//被回复人id，外键
	private String content;//回复内容
	private Date time;//回复时间
	private Comments comments;//动态评论表，外键
	private Users users;//用户表，外键
	
	CommentsDao cDao=new CommentsDao();
	UsersDao usersDao=new UsersDao();
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public int getCommentsId() {
		return commentsId;
	}
	public void setCommentsId(int commentsId) {
		this.commentsId = commentsId;
	}
	public int getFromuserId() {
		return fromuserId;
	}
	public void setFromuserId(int fromuserId) {
		this.fromuserId = fromuserId;
	}
	public int getTouserId() {
		return touserId;
	}
	public void setTouserId(int touserId) {
		this.touserId = touserId;
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
	public Comments getComments() {
		return cDao.getComments(this.commentsId);
	}
	public void setComments(Comments comments) {
		this.comments = comments;
	}
	public Users getFromUsers() {
		return usersDao.getUser(this.fromuserId);
	}
	public void setFromUsers(Users users) {
		this.users = users;
	}
	public Users getToUsers() {
		return usersDao.getUser(this.touserId);
	}
	public void setToUsers(Users users) {
		this.users = users;
	}
	
	public Replys() {
		super();
	}
	public Replys(int replyId, int commentsId, int fromuserId, int touserId,
			String content, Date time, Comments comments, Users users) {
		super();
		this.replyId = replyId;
		this.commentsId = commentsId;
		this.fromuserId = fromuserId;
		this.touserId = touserId;
		this.content = content;
		this.time = time;
		this.comments = comments;
		this.users = users;
	}
	@Override
	public String toString() {
		return "Replys [replyId=" + replyId + ", commentsId=" + commentsId
				+ ", fromuserId=" + fromuserId + ", touserId=" + touserId
				+ ", content=" + content + ", time=" + time + "]";
	}
	
}
