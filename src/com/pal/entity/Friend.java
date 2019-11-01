package com.pal.entity;

import com.pal.dao.UsersDao;

/**
 * 好友表Friend
 * @author Administrator
 *
 */
public class Friend {
	private int f_id;//好友编号id主键，自增
	private int fromuserId;//关系请求者的id，外键
	private int touserId;//关系请求者的id，外键
	private Users users;//用户表，外键
	
	UsersDao usersDao=new UsersDao();
	public int getF_id() {
		return f_id;
	}
	public void setF_id(int f_id) {
		this.f_id = f_id;
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

	public Users geFromtUsers() {
		return usersDao.getUser(this.fromuserId);
	}
	public void setFromUsers(Users users) {
		this.users = users;
	}
	public Users geTotUsers() {
		return usersDao.getUser(this.touserId);
	}
	public void setToUsers(Users users) {
		this.users = users;
	}
	public Friend() {
		super();
	}

	public Friend(int f_id, int fromuserId, int touserId, int rs_id,
			Users users) {
		super();
		this.f_id = f_id;
		this.fromuserId = fromuserId;
		this.touserId = touserId;
		this.users = users;
	}
	@Override
	public String toString() {
		return "Friend [f_id=" + f_id + ", fromuserId=" + fromuserId
				+ ", touserId=" + touserId + "]";
	}
	
}
