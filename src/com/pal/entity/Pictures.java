package com.pal.entity;

import java.sql.Date;

import com.pal.dao.UsersDao;

/**
 * 相册表Pictures
 * @author Administrator
 *
 */
public class Pictures {
	private int pictureId;//图片编号，主键自增
	private int userId;//上传者id，外键
	private String path;//图片路径
	private Users users;//用户表，外键
	private Date time;
	
	UsersDao usersDao=new UsersDao();
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getPictureId() {
		return pictureId;
	}
	public void setPictureId(int pictureId) {
		this.pictureId = pictureId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Users getUsers() {
		return usersDao.getUser(this.userId);
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public Pictures() {
		super();
	}
	
	public Pictures(int pictureId, int userId, String path, Date time,
			Users users) {
		super();
		this.pictureId = pictureId;
		this.userId = userId;
		this.path = path;
		this.time = time;
		this.users = users;
	}
	@Override
	public String toString() {
		return "Pictures [pictureId=" + pictureId + ", userId=" + userId
				+ ", path=" + path + ", time=" + time + "]";
	}
	
	
}
