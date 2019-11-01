package com.pal.dao;

import java.util.ArrayList;

import com.pal.entity.Admin;

public class AdminDao extends BaseDao<Admin>{
	
	/**
	 * 获取所有管理员信息
	 * @return
	 */
	public ArrayList<Admin> getAllAdmin(){
		String sql="select * from admin order by a_id desc ";//降序
		return this.query(sql);
	}
	
	/**
	 * 获取单个管理员信息
	 * @param a_id
	 * @return
	 */
	public Admin getAdmin(int a_id){
		String sql="select * from admin where a_id=?";
		return this.queryOne(sql, a_id);
	}
	public Admin getAdmin(String a_name){
		String sql="select * from admin where a_name=?";
		return this.queryOne(sql, a_name);
	}
	public Admin getAdminPwd(String a_name){
		String sql="select a_pwd from admin where a_name=?";
		return this.queryOne(sql, a_name);
	}
	/**
	 * 增加管理员信息
	 * @param admin
	 * @return
	 */
	public int addAdmin(Admin admin){
		String sql="INSERT INTO `admin` (`a_name`,`a_pwd`) VALUES (?,?)";
		return this.exeSql(sql, admin.getA_name(),admin.getA_pwd());
	}
	
	/**
	 * 删除管理员信息
	 * @param a_id
	 * @return
	 */
	public int delAdmin(int a_id){
		String sql="delete from admin where a_id=?";
		return this.exeSql(sql, a_id);
	}
	
	/**
	 * 修改管理员信息
	 * @param admin
	 * @return
	 */
	public int modAdmin(Admin admin){
		String sql="update admin set a_name=?,a_pwd=? where a_id=?";
		return this.exeSql(sql, admin.getA_name(),admin.getA_pwd());
	}
	
	/**
	 * 获取总笔数
	 * @return
	 */
	public int getTotal(){
		String sql="select count(*) from admin";
		long l=this.getCount(sql);
		return (int)l;
	}
	
	public ArrayList<Admin> getAllAdmin(int start,int pageSize){
		String sql="select * from admin order by a_id asc limit ?,?";
		return this.query(sql, start,pageSize);
	}
}
