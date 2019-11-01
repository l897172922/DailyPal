package com.pal.dao;

import java.util.ArrayList;

import com.pal.entity.Friend;
import com.pal.entity.Users;

public class UsersDao extends BaseDao<Users>{
	
	/**
	 * 获取所有用户信息
	 * @return
	 */
	public ArrayList<Users> getAllUsers(){
		String sql="select * from users order by u_id desc";
		return this.query(sql);
	}
	
	/**

	 * 查询两天内注册的新用户个数

	 * 三十天内注册的用户
	 * @return
	 */
	public ArrayList<Users> getIndexPeople(){
		String sql="SELECT * FROM users WHERE registertime>=DATE_SUB(NOW(),INTERVAL 30 DAY)";
		return this.query(sql);
	}
	
	public int getNumUser(){
		String sql="SELECT count(*) FROM users WHERE registertime>=DATE_SUB(NOW(),INTERVAL 30 DAY)";
		long l=this.getCount(sql);
		return (int)l;
	}
	/*public int getNumUser1(){
		String sql="SELECT count(*) FROM users WHERE registertime>=DATE_SUB(NOW(),INTERVAL 30 DAY);";
		long l=this.getCount(sql);
		return (int)l;
	}*/
	/**
	 * 查询两天内注册的新用户

	 * @return
	 */
	public int getNewUsers(){
		String sql="SELECT count(*) from users WHERE  registertime>=DATE_SUB(NOW(),INTERVAL 2 DAY)";//近两天数据
		return this.exeSql(sql);
	}
	
	/**
	 * 当天注册的新用户数量
	 * @return
	 */
	public int getNumUsers1(){
		String sql="SELECT count(*) from users WHERE  registertime>=DATE_SUB(NOW(),INTERVAL 1 DAY)";
		long l=this.getCount(sql);
		return (int)l;
	}
	
	/**
	 * 获取单个用户信息
	 * @param u_id
	 * @return
	 */
	public Users getUser(int u_id){
		String sql="select * from users where u_id=?";
		return this.queryOne(sql,u_id);
	}
	
	public Users getUser(String u_name){
		String sql="select * from users where u_name=?";
		return this.queryOne(sql,u_name);
	}
	
	public Users getUserPwd(String u_name){
		String sql="select u_pwd from users where u_name=?";
		return this.queryOne(sql,u_name);
	}
	
	public Users getUser(String u_name,String u_pwd){
		String sql="select * from users where u_name=? and u_pwd=?";
		return this.queryOne(sql,u_name,u_pwd);
	}
	
	/**
	 * 增加用户信息
	 * @param users
	 * @return
	 */
	public int addUser(Users users){
		Object[] params={users.getU_name(),users.getU_pwd(),users.getHeadPic(),users.getNickName(),users.getSex(),users.getAge(),users.getRegisterTime()};
		String sql="INSERT INTO `users` (`u_name`, `u_pwd`,`headpic`, `nickname`,`sex`, `age`,`address`, `phone`, `email`, `hobby`,`introdution`, `isopen`, `remark`,`offtime`, `registertime`, `career`) VALUES (?,?,?,?,?,?,null,null,null,null,null,null,null,null,?,null)";
		return this.exeSql(sql,params);
	}
	
	/**
	 * 删除用户信息
	 * @param u_name
	 * @return
	 */
	public int delUser(String u_name){
		String sql="delete from users where u_name=?";
		return this.exeSql(sql, u_name);
	}
	public int delUser(int u_id){
		String sql="delete from users where u_id=?";
		return this.exeSql(sql, u_id);
	}
	
	/**
	 * 修改用户信息
	 * @param users
	 * @return
	 */
	public int modUser(Users users){
		Object[] params={users.getNickName(),users.getSex(),users.getAge(),users.getAddress(),users.getPhone(),users.getEmail(),users.getHobby(),users.getIntrodution(),users.getCareer(),users.getU_id()};
		String sql="update users set nickname=?,sex=?,age=?,address=?,phone=?,email=?,hobby=?,introdution=?,career=? where u_id=?";
		return this.exeSql(sql, params);
	}
	/**
	 * 修改头像
	 * @param picPath
	 * @param u_id
	 * @return
	 */
	public int modUser(String picPath,int u_id){
		String sql="update users set headpic=? where u_id=?";
		return this.exeSql(sql, picPath,u_id);
	}
	
	/**
	 * 获取总笔数
	 * @return
	 */
	public int getTotal(){
		String sql="select count(*) from users";
		long l=this.getCount(sql);
		return (int)l;
	}
	public int getBanTotal(){
		String sql="select count(*) from users where isban=1";
		long l=this.getCount(sql);
		return (int)l;
	}
	
	public ArrayList<Users> getAllUsers(int start,int pageSize){
		String sql="select * from users order by u_id asc limit ?,?";
		return this.query(sql, start,pageSize);
	}
	
	/**
	 * 查看用户是否被封禁
	 * @param u_id
	 * @return
	 */
	public int isBanUser(int u_id){
		String sql="update users set isban=1 where u_id=?";
		return this.exeSql(sql,u_id);
	}
	
	public int isBanUserByName(String u_name){
		String sql="update users set isban=1 where u_name=?";
		return this.exeSql(sql,u_name);
	}
	
	/**
	 * 释放用户
	 * @param u_id
	 * @return
	 */
	public int freeUser(int u_id){
		String sql="update users set isban=0 where u_id=?";
		return this.exeSql(sql,u_id);
	}
	
	/**
	 * 查询被封禁用户信息
	 * @param start
	 * @param pageSize
	 * @return
	 */
	public ArrayList<Users> getIsBanUsers(int start,int pageSize){
		String sql="select * from users where isban=1 order by u_id asc limit ?,?";
		return this.query(sql, start,pageSize);
	}
	
	

	
	// 模糊查找好友功能
	/**
	 * 昵称模糊查找
	 * 
	 * @param nickName
	 * @return
	 */
	public int getTotal(String nickName) {
		String sql = "SELECT COUNT(*) FROM users WHERE nickname LIKE ?";
		long l = this.getCount(sql, nickName);
		return (int) l;
	}

	public ArrayList<Users> getAllUsers(String nickName, int start, int pageSize) {
		String sql = "SELECT * FROM users WHERE nickname LIKE ? limit ?,?";
		return this.query(sql,nickName, start, pageSize);
	}
	

	/**
	 * 通过性别
	 * 
	 * @param add
	 * @return
	 */
	public int getTotalBySex(String sex) {
		String sql = "SELECT COUNT(*) FROM users WHERE sex LIKE ? ";
		long l = this.getCount(sql, sex);
		return (int) l;
	}
//limit
	public ArrayList<Users> getAllUsersBySex(String sex,  int start, int pageSize) {
		String sql = "SELECT * FROM users WHERE sex LIKE ? LIMIT ?,?";
		return this.query(sql, sex, start, pageSize);
	}



	/**
	 * 通过省份/市查找
	 * 
	 * @param sex
	 * @return
	 */
	public int getTotalByAdd(String add) {
		String sql = "SELECT COUNT(*) FROM users WHERE address LIKE ?";
		long l = this.getCount(sql, add);
		return (int) l;
	}

	public ArrayList<Users> getAllUsersByAdd(String add, int start, int pageSize) {
		String sql = "SELECT * FROM users WHERE address LIKE ? LIMIT ?,?";
		return this.query(sql, add, start, pageSize);
	}

	/**
	 * 通过年龄查找
	 * 
	 * @param add
	 * @return
	 */
	public int getTotalByAge(int minage, int maxage) {
		String sql = "SELECT COUNT(*) FROM users WHERE age BETWEEN ? and ?";
		long l = this.getCount(sql, minage, maxage);
		return (int) l;
	}

	public ArrayList<Users> getAllUsersByAge(int minage, int maxage, int start,
			int pageSize) {
		String sql = "SELECT * FROM users WHERE age BETWEEN ? and ? LIMIT ?,?";
		return this.query(sql, minage, maxage, start, pageSize);
	}

	/**
	 * 性别,省份 查找
	 * 
	 * @param sex
	 * @return
	 */
	public int getTotalBySexAdd(String sex, String add) {
		String sql = "SELECT COUNT(*) FROM users WHERE sex LIKE ? and address LIKE ?";
		long l = this.getCount(sql, sex,add);
		return (int) l;
	}
//limit
	public ArrayList<Users> getAllUsersBySexAdd(String sex,String add, int start,
			int pageSize) {
		String sql = "SELECT * FROM users WHERE sex LIKE ? and address LIKE ? LIMIT ?,?";
		return this.query(sql, sex,add, start, pageSize);
	}

	/**
	 * 通过性别,年龄
	 * 
	 * @param add
	 * @return
	 */
	public int getTotalBySexAge(String sex, int minage, int maxage) {
		String sql = "SELECT COUNT(*) FROM users WHERE sex LIKE ? and age BETWEEN ? and ?";
		long l = this.getCount(sql, sex,minage, maxage);
		return (int) l;
	}
//limit
	public ArrayList<Users> getAllUsersBySexAge(String sex, int minage,
			int maxage, int start, int pageSize) {
		String sql = "SELECT * FROM users WHERE sex LIKE ? and age BETWEEN ? and ? LIMIT ?,?";
		return this.query(sql, sex, minage, maxage, start, pageSize);
	}


	
	/**
	 * 性别,省/市,年龄
	 * 
	 * @param sex
	 * @return
	 */
	public int getTotalBySexAddAge(String sex,String add,int minage,int maxage) {
		String sql = "SELECT COUNT(*) FROM users WHERE sex LIKE ? and address LIKE ? and age BETWEEN ? and ?";
		long l = this.getCount(sql, sex,add,minage,maxage);
		return (int) l;
	}
//limit
	public ArrayList<Users> getAllUsersBySexAddAge(String sex,String add,int minage,int maxage, int start, int pageSize) {
		String sql = "SELECT * FROM users WHERE sex LIKE ? and address LIKE ? and age BETWEEN ? and ? limit ?,?";
		return this.query(sql, sex,add,minage,maxage, start, pageSize);
	}
	
	/**
	 * 通过省份/市 ,年龄查找
	 * 
	 * @param sex
	 * @return
	 */
	public int getTotalByAddAge(String add,int minage,int maxage) {
		String sql = "SELECT COUNT(*) FROM users WHERE address LIKE ? and age BETWEEN ? and ?";
		long l = this.getCount(sql, add);
		return (int) l;
	}
//limit
	public ArrayList<Users> getAllUsersByAddAge(String add,int minage,int maxage, int start, int pageSize) {
		String sql = "SELECT * FROM users WHERE address LIKE ? and age BETWEEN ? and ? limit ?,?;";
		return this.query(sql, add,minage,maxage, start, pageSize);
	}
	
	
	
	
	
}
