package com.pal.entity;

import java.sql.Date;

import com.pal.dao.UsersDao;

/**
 * 发送好友请求表FriendRequest
 * @author Administrator
 *
 */
public class FriendRequest {
	private int fr_id;//编号，主键自增
	private int fromuserId;//发送人id，外键
	private int touserId;//接收人id，外键
	private Date sendTime;//请求时间
	private String readState;//是否已读
	private Users users;//用户表，外键
	
	UsersDao usersDao=new UsersDao();
	public int getFr_id() {
		return fr_id;
	}
	public void setFr_id(int fr_id) {
		this.fr_id = fr_id;
	}
	public int getFromuserId() {
		return fromuserId;
	}
	public void setFromuserId(int fromouseId) {
		this.fromuserId = fromouseId;
	}
	public int getTouserId() {
		return touserId;
	}
	public void setTouserId(int touserId) {
		this.touserId = touserId;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getReadState() {
		return readState;
	}
	public void setReadState(String readState) {
		this.readState = readState;
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
	public FriendRequest() {
		super();
	}
	public FriendRequest(int fr_id, int fromuserId, int touserId,
			Date sendTime, String readState, Users users) {
		super();
		this.fr_id = fr_id;
		this.fromuserId = fromuserId;
		this.touserId = touserId;
		this.sendTime = sendTime;
		this.readState = readState;
		this.users = users;
	}
	@Override
	public String toString() {
		return "FriendRequest [fr_id=" + fr_id + ", fromouseId=" + fromuserId
				+ ", touserId=" + touserId + ", sendTime=" + sendTime
				+ ", readState=" + readState + "]";
	}
	
}
