package com.pal.dao;

import java.util.ArrayList;

import com.pal.entity.Pictures;

public class PicturesDao extends BaseDao<Pictures>{
	
	/**
	 * 获取所有相册信息
	 * @return
	 */
	public ArrayList<Pictures> getAllPictures(){
		String sql="select * from pictures order by pictureid desc";
		return this.query(sql);
	}
	
	/**
	 * 获取单个相册信息
	 * @param pictureId
	 * @return
	 */
	public Pictures getPictures(int userId){
		String sql="select * from pictures where userid=?";
		return this.queryOne(sql, userId);
	}
	
	/**
	 * 增加相册信息
	 * @param pictures
	 * @return
	 */
	public int addPictures(Pictures pictures){
		String sql="INSERT INTO `pictures` (`userid`,`path`,`time`) VALUES (?,?,?)";
		return this.exeSql(sql, pictures.getUserId(),pictures.getPath(),pictures.getTime());
	}
	
	/**
	 * 删除相册信息
	 * @param pictureId
	 * @return
	 */
	public int delPictures(int pictureId){
		String sql="delete from pictures where pictureid=?";
		return this.exeSql(sql, pictureId);
	}
	
	/**
	 * 修改相册信息
	 * @param pictures
	 * @return
	 */
	public int modPictures(Pictures pictures){
		String sql="update pictures set userid=?,path=?,rs_id=? time pictureid=?";
		return this.exeSql(sql, pictures.getUserId(),pictures.getPath(),pictures.getTime());
	}
	
	/**
	 * 获取总笔数
	 * @return
	 */
	public int getTotal(){
		String sql="select count(*) from pictures";
		long l=this.getCount(sql);
		return (int)l;
	}
	/**
	 * 所有照片分页
	 * @param start
	 * @param pageSize
	 * @return
	 */
	public ArrayList<Pictures> getAllPictures(int start,int pageSize){
		String sql="select * from pictures order by pictureid asc limit ?,?";
		return this.query(sql, start,pageSize);
	}
	
	/**
	 * 个人相册分页
	 * @param userId
	 * @param start
	 * @param pageSize
	 * @return
	 */
	public ArrayList<Pictures> getAllPictures(int userId , int start , int pageSize){
		String sql="select * from pictures where userid=? order by pictureid desc limit ?,?";
		return this.query(sql, userId ,start,pageSize);
	}
	/**
	 * 获取我的照片
	 * @param userId
	 * @return
	 */
	public ArrayList<Pictures> getMyPictures(int userId){
		String sql="select * from pictures where userid=?";
		return this.query(sql, userId);
	}
	
	/**
	 * 获取个人相册总笔数
	 * @param userId
	 * @return
	 */
	public int getTotal(int userId){
		String sql="select count(*) from pictures where userid=?";
		long l=this.getCount(sql,userId);
		return (int)l;
	}
}
