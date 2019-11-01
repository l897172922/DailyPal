package com.pal.dao;

import java.util.ArrayList;

import com.pal.entity.Messages;
import com.pal.entity.Page;

public class MessagesDao extends BaseDao<Messages>{
	
	/**
	 * 获取别人给我的留言
	 * @return
	 */
	public ArrayList<Messages> getMyMessages(int myId,int start,int pageSize){
		String sql = "select * from messages where touserId=? order by m_id desc limit ?,?";
		return this.query(sql, myId,start,pageSize);
	}
	
	/**
	 * 获取我的留言总数
	 * @param myId
	 * @return
	 */
	public int getMyMessagesTotal(int myId){
		String sql="select count(*) from messages where touserid = ?";
		long l=this.getCount(sql,myId);
		return (int)l;
	}
	
	
	
	/**
	 * 获取所有留言信息
	 * @return
	 */
	public ArrayList<Messages> getAllMessages(){
		String sql="select * from messages order by m_id desc ";
		return this.query(sql);
	}
	public ArrayList<Messages> getMessagesByuserid(int touserId){
		String sql ="select * from messages where touserid=?";
		return this.query(sql, touserId);
	}
	/**
	 * 获取单条留言信息
	 * @param m_id
	 * @return
	 */
	public Messages getMessages(int m_id){
		String sql="select * from messages where m_id=?";
		return this.queryOne(sql, m_id);
	}
	
	/**
	 * 增加留言信息
	 * @param messages
	 * @return
	 */
	public int addMessages(Messages messages){
		String sql="INSERT INTO `messages` (`fromuserid`,`touserid`,`content`,`time`) VALUES (?,?,?,?)";
		return this.exeSql(sql, messages.getFromuserId(),messages.getTouserId(),messages.getContent(),messages.getTime());
	}
	
	/**
	 * 删除留言信息
	 * @param m_id
	 * @return
	 */
	public int delMessages(int m_id){
		String sql="delete from messages where m_id=?";
		return this.exeSql(sql, m_id);
	}
	
	/**
	 * 获取总笔数
	 * @return
	 */
	public int getTotal(){
		String sql="select count(*) from messages";
		long l=this.getCount(sql);
		return (int)l;
	}
	
	public ArrayList<Messages> getAllMessages(int start,int pageSize){
		String sql="select * from messages order by m_id asc limit ?,?";
		return this.query(sql, start,pageSize);
	}
	
}
