package com.pal.dao;

import java.util.ArrayList;

import com.pal.entity.Friend;

public class FriendDao extends BaseDao<Friend>{
	
	/**
	 * 获取所有好友信息
	 * @return
	 */
	public ArrayList<Friend> getAllFriend(){
		String sql="select * from friend order by f_id desc ";//降序
		return this.query(sql);
	}
	
	/**
	 * 获取单个朋友信息
	 * @param f_id
	 * @return
	 */
	public Friend getFriend(int f_id){
		String sql="select * from friend where f_id=?";
		return this.queryOne(sql, f_id);
	}

	
	/**
	 * 增加朋友信息
	 * @param friend
	 * @return
	 */
	public int addFriend(Friend friend){
		String sql="INSERT INTO `friend` (`fromuserid`,`touserid`) VALUES (?,?)";
		return this.exeSql(sql, friend.getFromuserId(),friend.getTouserId());
	}
	
	/**
	 * 删除朋友信息
	 * @param f_id
	 * @return
	 */
	public int delFriend(int fid,int tid){
		String sql="delete from friend where fromuserid=? and touserid=? or fromuserid=? and touserid=?";
		return this.exeSql(sql,fid,tid,tid,fid);
	}
	
	/**
	 * 修改朋友信息
	 * @param friend
	 * @return
	 */
	public int modFriend(Friend friend){
		String sql="update friend set fromuserid=?,touserid=? where f_id=?";
		return this.exeSql(sql, friend.getFromuserId(),friend.getTouserId());
	}
	
	/**
	 * 获取总笔数
	 * @return
	 */
	public int getTotal(){
		String sql = "select count(*) from friend";
		long l = this.getCount(sql);
		return (int)l;
	
	}
	public ArrayList<Friend> getAllFriend(int start,int pageSize){
		String sql = "select * from friend order by f_id asc limit ?,?";
		return this.query(sql, start,pageSize);
	}
	
	
	/**
	 * 获取个人好友总笔数
	 * @return
	 */
	public int getTotal(int userId){
		String sql = "select count(*) from friend where fromuserid=? or touserid=?";
		long l = this.getCount(sql,userId,userId);
		return (int)l;
	}
	
	/**
	 * 获取个人好友列表
	 * @param start
	 * @param pageSize
	 * @return
	 */
	public ArrayList<Friend> getAllFriend(int userId,int start,int pageSize){
		String sql = "select * from friend where fromuserid = ? or touserid = ? order by f_id asc limit ?,?";
		return this.query(sql,userId,userId,start,pageSize);
	}
	/**
	 * 获取个人好友列表
	 * @param start
	 * @param pageSize
	 * @return
	 */
	public ArrayList<Friend> getAllFriend(int userId){
		String sql = "select * from friend where fromuserid = ? or touserid = ?";
		return this.query(sql,userId,userId);
	}
	
	
}
