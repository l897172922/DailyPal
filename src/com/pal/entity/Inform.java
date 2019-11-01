package com.pal.entity;

import java.sql.Date;

import com.pal.dao.ReasonTypeDao;
import com.pal.dao.UsersDao;

/**
 * 举报表Inform
 * @author Administrator
 *
 */
public class Inform {
	private int i_id;//主键，自增
	private int rt_typeId;//原因类别id
	private int u_id;//被举报人id,外键关系
	private Date time;//举报时间
	private int isdeal;//未处理的举报信息，默认为0
	private Users users;//用户表，外键
	private ReasonType rType;//原因类别表，外键
	ReasonTypeDao  rtd = new ReasonTypeDao();
	UsersDao  ud = new  UsersDao();
	public int getIsdeal() {
		return isdeal;
	}
	public void setIsdeal(int isdeal) {
		this.isdeal = isdeal;
	}
	public int getI_id() {
		return i_id;
	}
	public void setI_id(int i_id) {
		this.i_id = i_id;
	}
	public int getRt_typeId() {
		return rt_typeId;
	}
	public void setRt_typeId(int rt_typeId) {
		this.rt_typeId = rt_typeId;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Users getUsers() {
		return ud.getUser(this.u_id);
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public Inform() {
		super();
	}
	
	public ReasonType getrType() {
		return rtd.getReasonType(this.rt_typeId);
	}
	public void setrType(ReasonType rType) {
		this.rType = rType;
	}
	public Inform(int i_id, int rt_typeId, int u_id, Date time, int isdeal,
			Users users, ReasonType rType) {
		super();
		this.i_id = i_id;
		this.rt_typeId = rt_typeId;
		this.u_id = u_id;
		this.time = time;
		this.isdeal = isdeal;
		this.users = users;
		this.rType = rType;
	}
	@Override
	public String toString() {
		return "Inform [i_id=" + i_id + ", rt_typeId=" + rt_typeId + ", u_id="
				+ u_id + ", time=" + time + ", isdeal=" + isdeal + ", users="
				+ users + ", rType=" + rType + "]";
	}
	

}
