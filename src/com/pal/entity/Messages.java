package com.pal.entity;

import java.sql.Date;

import com.pal.dao.UsersDao;

/**
 * 留言表Messages
 * @author Administrator
 *
 */
public class Messages {
	private int m_id;//编号，主键
	private int fromuserId;//留言人id，外键
	private int touserId;//被留言人id，外键
	private String content;//留言内容
	private Date time;//留言时间
	private Users users;//用户表，外键
	
	UsersDao usersDao=new UsersDao();
	public int getM_id() {
		return m_id;
	}
	public void setM_id(int m_id) {
		this.m_id = m_id;
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
	public Messages() {
		super();
	}
	public Messages(int m_id, int fromuserId, int touserId, String content,
			Date time, Users users) {
		super();
		this.m_id = m_id;
		this.fromuserId = fromuserId;
		this.touserId = touserId;
		this.content = content;
		this.time = time;
		this.users = users;
	}
	@Override
	public String toString() {
		return "Messages [m_id=" + m_id + ", fromuserId=" + fromuserId
				+ ", touserId=" + touserId + ", content=" + content + ", time="
				+ time + "]";
	}
	
}
