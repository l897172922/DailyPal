package com.pal.dao;

import java.util.ArrayList;

import com.pal.entity.Blogs;
import com.pal.entity.Comments;

public class CommentsDao extends BaseDao<Comments>{
	
	/**
	 * 获取所有动态评论信息
	 * @return
	 */
	public ArrayList<Comments> getAllComments(){
		String sql="select * from comments order by commentsId desc";
		return this.query(sql);
	}
	/**
	 * 查询某条动态的所有评论信息
	 * @param blog
	 * @return
	 */
	public ArrayList<Comments> getCommentsByblogId(Blogs blog){
		String sql="select * from comments where toblogsid=?";
		return this.query(sql,blog.getB_id());
	}
	
	/**
	 * 获取单条动态评论信息
	 * @param commentsId
	 * @return
	 */
	public Comments getComments(int commentsId){
		String sql="select * from comments where commentsId=?";
		return this.queryOne(sql, commentsId);			
	}
	
	/**
	 * 增加动态评论信息
	 * @param comments
	 * @return
	 */
	public int addComments(Comments comments){
		String sql="INSERT INTO `comments` (`toblogsid`,`fromuserid`,`content`,`time`) VALUES (?,?,?,?)";
		return this.exeSql(sql, comments.getToblogsId(),comments.getFromuserId(),comments.getContent(),comments.getTime());
	}
	
	/**
	 * 删除动态评论信息
	 * @param commentsId                                               
	 * @return
	 */
	public int delComments(int commentsId){
		String sql="delete from comments where commentsId=?";
		return this.exeSql(sql, commentsId);
	}
	
	public int delCommentsById(int toblogsId){
		String sql="delete from comments where toblogsid=?";
		return this.exeSql(sql, toblogsId);
	}
	/**
	 * 获取总笔数
	 * @return
	 */
	public int getTotal(){
		String sql = "select count(*) from comments";
		long l = this.getCount(sql);
		return (int) l;
	} 
	
	public ArrayList<Comments> getAllComments(int start,int pageSize){
		String sql = "select * from comments order by commentsId asc limit ?,?";
		return this.query(sql, start,pageSize);
	}
	
	
}
