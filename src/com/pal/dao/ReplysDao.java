package com.pal.dao;

import java.util.ArrayList;

import com.pal.entity.Replys;

public class ReplysDao extends BaseDao<Replys>{
	
	/**
	 * 获取所有评论回复信息
	 * @return
	 */
	public ArrayList<Replys> getAllReplys(){
		String sql="select * from replys order by replyid desc ";
		return this.query(sql);
	}
	
	/**
	 * 获取单条评论回复信息
	 * @param replyId
	 * @return
	 */
	public Replys getReplys(int replyId){
		String sql="select * from replys where replyid=?";
		return this.queryOne(sql, replyId);
	}
	
	/**
	 * 增加评论回复信息
	 * @param replys
	 * @return
	 */
	public int addReplys(Replys replys){
		String sql="INSERT INTO `replys` (`commentsid`,`fromuserid`,`touserid`,`content`,`time`) VALUES (?,?,?,?,?)";
		return this.exeSql(sql, replys.getComments().getCommentsId(),replys.getFromuserId(),replys.getTouserId(),replys.getContent(),replys.getTime());
	}
	
	/**
	 * 删除评论回复信息
	 * @param replyId
	 * @return
	 */
	public int delReplys(int replyId){
		String sql="delete from replys where replyid=?";
		return this.exeSql(sql, replyId);
	}
	
	public int delReplysById(int replyId){
		String sql="delete from replys where replyid=?";
		return this.exeSql(sql, replyId);
	}
	
	/**
	 * 修改评论回复信息
	 * @param replys
	 * @return
	 */
	public int modReplys(Replys replys){
		String sql="update replys set content=? where replyid=?";
		return this.exeSql(sql, replys.getContent());
	}
	
	/**
	 * 获取总笔数
	 * @return
	 */
	public int getTotal(){
		String sql="select count(*) from replys";
		long l=this.getCount(sql);
		return (int)l;
	}
	
	public ArrayList<Replys> getAllReplys(int start,int pageSize){
		String sql="select * from replys order by replyid asc limit ?,?";
		return this.query(sql, start,pageSize);
	}
}
