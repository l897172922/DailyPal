package com.pal.dao;

import java.util.ArrayList;

import com.pal.entity.ChatMessages;

public class ChatMessagesDao extends BaseDao<ChatMessages>{

	/**
	 * 获取所有聊天信息
	 * @return
	 */
	public ArrayList<ChatMessages> getAllChatMessages(){
		String sql="select * from chatmessages order by cmid desc ";
		return this.query(sql);
	}
	
	/**
	 * 获取我的聊天信息 和(friid)
	 * @param myId
	 * @return
	 */
	public ArrayList<ChatMessages> getAllChatMessagesW(int myId,int friId){
		String sql="select * from chatmessages where touserid=? and fromuserid=? or fromuserid=? and touserid=?";
		return this.query(sql,myId,friId,myId,friId);
	}
	
	/**
	 * 获取我和好友的未读信息
	 * @param myId
	 * @param friId
	 * @return
	 */
	public ArrayList<ChatMessages> getAllChatMessagesWN(int myId ,int friId){
		String sql="select * from chatmessages where touserid=? and fromuserid=? and readstate=0";
		return this.query(sql,myId,friId);
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 获取 我的所有消息数
	 * @param myId
	 * @return
	 */
	public int getTotal(int myId){
		String sql = "select count(*) from chatmessages where touserid=?";
		long l = this.getCount(sql,myId);
		return (int)l;
	}
	
	
	/**
	 * 获取我的聊天信息(未读)
	 * @param myId
	 * @return
	 */
	public ArrayList<ChatMessages> getAllChatMessagesE(int myId){
		String sql="select * from chatmessages where touserid=? and readstate='0' order by cmid desc ";
		return this.query(sql,myId);
	}
	/**
	 * 获取 我的所有消息数(未读)
	 * @param myId
	 * @return
	 */
	public int getTotalE(int myId){
		String sql = "select count(*) from chatmessages where touserid=? and readstate='0'";
		long l = this.getCount(sql,myId);
		return (int)l;
	}
	
	public int modChatMessages(int cmid){
		String sql="update chatmessages set readstate='1' where cmid=?";
		return this.exeSql(sql, cmid);
	}
	
	
	/**
	 * 获取单个聊天信息
	 * @param cmId
	 * @return
	 */
	public ChatMessages getChatMessages(int cmId){
		String sql="select * from chatmessages where cmid=?";
		return this.queryOne(sql, cmId);
	}
	
	/**
	 * 增加聊天信息
	 * @param cMessages
	 * @return
	 */
	public int addChatMessages(ChatMessages cMessages){
		String sql="INSERT INTO `chatmessages` (`fromuserid`,`touserid`,`sendtime`,`content`,`readstate`) VALUES (?,?,?,?,?)";
		return this.exeSql(sql, cMessages.getFromuserId(),cMessages.getTouserId(),cMessages.getSendTime(),cMessages.getContent(),cMessages.getReadstate());
	}
	
	/**
	 * 删除聊天信息
	 * @param cmId
	 * @return
	 */
	public int delChatMessages(int cmId){
		String sql="delete from chatmessages where cmid=?";
		return this.exeSql(sql, cmId);
	}
	
	/**
	 * 获取总笔数
	 * @return
	 */
	public int getTotal(){
		String sql = "select count(*) from chatmessages";
		long l = this.getCount(sql);
		return (int)l;
	}
	
	public ArrayList<ChatMessages> getAllChatMessages(int start,int pageSize){
		String sql = "select * from chatmessages order by cmid asc limit ?,?";
		return this.query(sql, start,pageSize);
	}
	
	
	
	
}
