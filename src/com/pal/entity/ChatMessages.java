package com.pal.entity;

import java.sql.Date;

import com.pal.dao.UsersDao;

/**
 * 聊天信息表ChatMessages
 * @author Administrator
 *
 */
public class ChatMessages {
	private int cmId;//聊天信息编号，主键，自增
	private int touserId;//接收人id，外键
	private int fromuserId;//发送人id，外键
	private Date sendTime;//发送时间
	private String content;//信息内容
	private String readstate;//是否已读
	private Users users;//用户表，外键
	
	UsersDao usersDao=new UsersDao();
	
	public int getCmId() {
		return cmId;
	}
	public void setCmId(int cmId) {
		this.cmId = cmId;
	}
	public int getTouserId() {
		return touserId;
	}
	public void setTouserId(int touserId) {
		this.touserId = touserId;
	}
	public int getFromuserId() {
		return fromuserId;
	}
	public void setFromuserId(int fromuserId) {
		this.fromuserId = fromuserId;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	public String getReadstate() {
		return readstate;
	}
	public void setReadstate(String readstate) {
		this.readstate = readstate;
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
	public ChatMessages() {
		super();
	}
	public ChatMessages(int cmId, int touserId, int fromuserId, Date sendTime,
			String content, String readstate, Users users) {
		super();
		this.cmId = cmId;
		this.touserId = touserId;
		this.fromuserId = fromuserId;
		this.sendTime = sendTime;
		this.content = content;
		this.readstate = readstate;
		this.users = users;
	}
	@Override
	public String toString() {
		return "ChatMessages [cmId=" + cmId + ", touserId=" + touserId
				+ ", fromuserId=" + fromuserId + ", sendTime=" + sendTime
				+ ", content=" + content + ", readstate=" + readstate + "]";
	}
	
}
