package com.pal.dao;

import java.util.ArrayList;

import com.pal.entity.FriendRequest;

public class FriendRequestDao extends BaseDao<FriendRequest>{
	
	/**
	 * 获取所有好友请求信息
	 * @return
	 */
	public ArrayList<FriendRequest> getAllFriendRequest(){
		String sql="select * from friendrequest order by fr_id desc";
		return this.query(sql);
	}
	
	/**
	 * 获取加好友的请求(未处理)
	 * @param myId
	 * @return
	 */
	public ArrayList<FriendRequest> getAllFriendRequest(int myId){
		String sql="select * from friendrequest where touserid=? and readstate='0' order by fr_id desc";
		return this.query(sql,myId);
	}
	
	/**
	 * 请求添加好友的个数(未处理)
	 * @param myId
	 * @return
	 */
	public int getTotal(int myId){
		String sql = "select count(*) from friendrequest where touserid=? and readstate='0'";
		long l = this.getCount(sql,myId);
		return (int)l;
	}
	
	public int modFriendRequest(int fr_id){
		String sql="update friendrequest set readstate='1' where fr_id=?";
		return this.exeSql(sql, fr_id);
	}
	
	
	/**
	 * 获取单个好友请求信息
	 * @param fr_id
	 * @return
	 */
	public FriendRequest getFriendRequest(int fr_id){
		String sql="select * from friendrequest where fr_id=?";
		return this.queryOne(sql, fr_id);
	}
	
	/**
	 * 增加好友请求信息
	 * @param fRequest
	 * @return
	 */
	public int addFriendRequest(FriendRequest fRequest){
		String sql="INSERT INTO `friendrequest` (`fromuserid`,`touserid`,`sendtime`,`readstate`) VALUES (?,?,?,?)";
		return this.exeSql(sql, fRequest.getFromuserId(),fRequest.getTouserId(),fRequest.getSendTime(),fRequest.getReadState());
	}
	
	/**
	 * 删除好友请求信息
	 * @param fr_id
	 * @return
	 */
	public int delFriendRequest(int fr_id){
		String sql="delete from friendrequest where fr_id=?";
		return this.exeSql(sql, fr_id);
	}
	
	/**
	 * 获取总笔数
	 * @return
	 */
	public int getTotal(){
		String sql = "select count(*) from friendrequest";
		long l = this.getCount(sql);
		return (int)l;
	}
	
	public ArrayList<FriendRequest> getAllFriendRequest(int start,int pageSize){
		String sql = "select * from friendrequest order by fr_id asc limit ?,?";
		return this.query(sql, start,pageSize);
	}
}
