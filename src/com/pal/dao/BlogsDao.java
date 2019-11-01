package com.pal.dao;

import java.util.ArrayList;

import com.pal.entity.Blogs;

public class BlogsDao extends BaseDao<Blogs>{
	
	/**
	 * 获取我的总笔数
	 * @return
	 */
	public int getMyTotal(int myId){
		String sql="select count(*) from blogs where userid=?";
		long l=this.getCount(sql,myId);
		return (int)l;
	}
	/**
	 * 获取我的所有动态
	 * @param start
	 * @param pageSize
	 * @return
	 */
	public ArrayList<Blogs> getMyBlogs(int myId , int start,int pageSize){
		String sql="select * from blogs where userid=? order by b_id desc limit ?,?";
		return this.query(sql,myId,start,pageSize);
	}
	public ArrayList<Blogs> getMyBlogs(int myId){
		String sql="select * from blogs where userid=?";
		return this.query(sql,myId);
	}
	
	/**
	 * 两天内的热门动态数量
	 * @return
	 */
	public int getHotBlog(){
		String sql="SELECT count(*) FROM blogs WHERE likecounts+commentcounts>400 and sendtime>=DATE_SUB(NOW(),INTERVAL 2 DAY)";
		long l=this.getCount(sql);
		return (int)l;
	}
	
	/**
	 * 所有动态数量
	 * @return
	 */
	public int getNumBlog(){
		String sql="SELECT count(*) FROM blogs";
		long l=this.getCount(sql);
		return (int)l;
	}
	
	
	/**
	 * 获取所有动态信息
	 * @return
	 */
	public ArrayList<Blogs> getAllBlogs(){
		String sql="select * from blogs order by b_id desc ";
		return this.query(sql);
	}
	
	
	public ArrayList<Blogs> getHotBlogs(){
		String sql="SELECT * FROM blogs WHERE likecounts+commentcounts>400 and sendtime>=DATE_SUB(NOW(),INTERVAL 2 DAY);";
		return this.query(sql);		
	}
	
	/**
	 * 查询近两天动态信息
	 * @return
	 */
	public int getNewBlogs(){
		String sql="SELECT COUNT(*) FROM blogs WHERE  sendtime>=DATE_SUB(NOW(),INTERVAL 2 DAY);";
		return this.exeSql(sql);		
	}
	
	/**
	 * 获取单个动态信息
	 * @param b_id
	 * @return
	 */
	public Blogs getBlogs(int b_id){
		String sql="select * from blogs where b_id=?";
		return this.queryOne(sql, b_id);
	}
	
	/**
	 * 增加动态信息
	 * @param blogs
	 * @return
	 */
	public int addBlogs(Blogs blogs){
		String sql="INSERT INTO `blogs` (`userid`,`picturepath`,`text`,`sendtime`,`likecounts`,`commentcounts`) VALUES (?,?,?,?,?,?)";
		return this.exeSql(sql, blogs.getUserId(),blogs.getPicturePath(),blogs.getText(),blogs.getSendTime(),blogs.getLikeCounts(),blogs.getCommentCounts());
	}
	
	/**
	 * 删除动态信息
	 * @param b_id
	 * @return
	 */
	public int delBlogs(int b_id){
		String sql1="delete from blogs where b_id=?";
		String sql2="delete from comments where toblogsid=?";
		this.exeSql(sql2, b_id);
		return this.exeSql(sql1, b_id);
	}
	
	/**
	 * 修改动态信息
	 * @param blogs
	 * @return
	 */ 
	public int modBlogs(Blogs blogs){
		String sql="update blogs set userid=?,picturepath=?,text=? where b_id=?";
		return this.exeSql(sql, blogs.getUserId(),blogs.getPicturePath(),blogs.getText());
	}
	public int addBlogslike(Blogs blogs){
		String sql="update blogs set likecounts=? where b_id=?";
		return this.exeSql(sql, blogs.getLikeCounts(),blogs.getB_id());
	}
	public int addBlogscomment(Blogs blogs){
		String sql="update blogs set commentcounts=? where b_id=?";
		return this.exeSql(sql, blogs.getCommentCounts(),blogs.getB_id());
	}
	
	/**
	 * 获取总笔数
	 * @return
	 */
	public int getTotal(){
		String sql="select count(*) from blogs";
		long l=this.getCount(sql);
		return (int)l;
	}

	public ArrayList<Blogs> getAllBlogs(int start,int pageSize){
		String sql="select * from blogs order by b_id asc limit ?,?";
		return this.query(sql, start,pageSize);
	}
	
	public ArrayList<Blogs> getHotBlogs(int start,int pageSize){
		String sql="SELECT * FROM blogs WHERE likecounts+commentcounts>400 and sendtime>=DATE_SUB(NOW(),INTERVAL 2 DAY) order by b_id asc limit ?,?";
		return this.query(sql, start,pageSize);
	}
}
