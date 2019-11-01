package com.pal.dao;

import java.util.ArrayList;

import com.pal.entity.ReasonType;

public class ReasonTypeDao extends BaseDao<ReasonType>{
	
	/**
	 * 获取所有原因类别
	 * @return
	 */
	public ArrayList<ReasonType> getAllReasonType(){
		String sql="select * from reasontype order by rt_typeid desc ";//降序
		return this.query(sql);
	}
	
	/**
	 * 获取单个原因类别
	 * @param rt_typeId
	 * @return
	 */
	public ReasonType getReasonType(int rt_typeId){
		String sql="select * from reasontype where rt_typeid=?";
		return this.queryOne(sql, rt_typeId);
	}
	
	/**
	 * 增加原因类别
	 * @param rType
	 * @return
	 */
	public int addReasonType(ReasonType rType){
		String sql="INSERT INTO `reasontype` (`rt_name`) VALUES (?)";
		return this.exeSql(sql, rType.getRt_name());
	}
	
	/**
	 * 删除原因类别
	 * @param rt_typeId
	 * @return
	 */
	public int delReasonType(int rt_typeId){
		String sql="delete from reasontype where rt_typeid=?";
		return this.exeSql(sql, rt_typeId);
	}
	
	/**
	 * 修改原因类别
	 * @param rType
	 * @return
	 */
	public int modReasonType(ReasonType rType){
		String sql="update reasontype set rt_name=? where rt_typeid=?";
		return this.exeSql(sql, rType.getRt_name());
	}
	
	/**
	 * 获取总笔数
	 * @return
	 */
	public int getTotal(){
		String sql="select count(*) from reasontype";
		long l=this.getCount(sql);
		return (int)l;
	}
	
	public ArrayList<ReasonType> getAllReasonType(int start,int pageSize){
		String sql="select * from reasontype order by rt_id asc limit ?,?";
		return this.query(sql, start,pageSize);
	}
	
	
	
}
