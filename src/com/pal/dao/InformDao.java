package com.pal.dao;

import java.util.ArrayList;

import com.pal.entity.Inform;

public class InformDao extends BaseDao<Inform>{
	
	/**
	 * 获取所有被举报人
	 * @return
	 */
	public ArrayList<Inform> getAllInform(){
		String sql="select * from inform order by i_id desc ";//降序
		return this.query(sql);
	}
	
	public ArrayList<Inform> getHotInform(){
		String sql="SELECT * FROM inform WHERE time>=DATE_SUB(NOW(),INTERVAL 2 DAY);";
		return this.query(sql);
	}
	
	/**
	 * 获取最近两天的举报信息
	 * @return
	 */
	public int getNewInform(){
		String sql ="SELECT COUNT(*) FROM inform WHERE time>=DATE_SUB(NOW(),INTERVAL 2 DAY)";
		long l=this.getCount(sql);
		return (int)l;
	}
	
	
	
	/**
	 * 获取单个被举报人
	 * @param i_id
	 * @return
	 */
	public Inform getInform(int i_id){
		String sql="select * from inform where i_id=?";
		return this.queryOne(sql, i_id);
	}
	
	/**
	 * 增加被举报人
	 * @param inform
	 * @return
	 */
	public int addInform(Inform inform){
		String sql="INSERT INTO `inform` (`u_id`, `rt_typeid`, `time`) VALUES (?,?,?)";
		return this.exeSql(sql, inform.getU_id(),inform.getRt_typeId(),inform.getTime());
	}
	
	/**
	 * 删除被举报人
	 * @param i_id
	 * @return
	 */
	public int delInform(int i_id){
		String sql="delete from inform where i_id=?";
		return this.exeSql(sql, i_id);
	}
	
	/**
	 * 修改被举报人
	 * @param inform
	 * @return
	 */
	public int modInform(Inform inform){
		String sql="update inform set u_id=?,rt_typeid=?,time=? where i_id=?";
		return this.exeSql(sql, inform.getI_id(),inform.getRt_typeId(),inform.getTime());
	}
	
	/**
	 * 获取总笔数
	 * @return
	 */
	public int getTotal(){
		String sql="select count(*) from Inform";
		long l=this.getCount(sql);
		return (int)l;
	}
	
	public int getBanTotal(){
		String sql="select count(*) from Inform where isdeal=1";
		long l=this.getCount(sql);
		return (int)l;
	}
	
	public ArrayList<Inform> getAllInform(int start,int pageSize){
		String sql="select * from inform order by i_id asc limit ?,?";
		return this.query(sql, start,pageSize);
	}
	
	public ArrayList<Inform> getHotInform(int start,int pageSize){
		String sql="SELECT * FROM inform WHERE  sendtime>=DATE_SUB(NOW(),INTERVAL 2 DAY) order by b_id asc limit ?,?";
		return this.query(sql, start,pageSize);
	}
	
	/**
	 * 已处理的被举报人
	 * @param start
	 * @param pageSize
	 * @return
	 */
	public ArrayList<Inform> getIsBanInform(int start,int pageSize){
		String sql="select * from inform where isdeal=1 order by i_id asc limit ?,?";
		return this.query(sql, start,pageSize);
	}
	
	/**
	 * 根据被举报id得到用户id
	 * @param i_id
	 * @return
	 */
	public int getInformU_Id(int i_id){
		String sql="select u_id from inform where i_id=?";
		return this.exeSql(sql, i_id);
	}
	
	public Inform getInformUser(int i_id){
		String sql="select * from users INNER JOIN  inform on users.u_id=inform.u_id  where i_id=?";
		return this.queryOne(sql, i_id);
	}
	
	/**
	 * 处理被举报人的操作
	 * @param i_id
	 * @return
	 */
	/*public int isDealInform(int i_id){
		String sql="update inform set isdeal=1 where i_id=?";
		return this.exeSql(sql,i_id);
	}*/
	public int isDealBanInform(int i_id){
		String sql="update users join inform on users.u_id=inform.u_id set users.isban=1,inform.isdeal=1 where inform.i_id=?";
		return this.exeSql(sql,i_id);
	}
	
	/**
	 * 已处理的被举报人移除（释放）操作
	 * @param u_id
	 * @return
	 */
	public int freeInform(int i_id){
		String sql="update users join inform on users.u_id=inform.u_id set users.isban=0,inform.isdeal=0 where inform.i_id=?";
		return this.exeSql(sql,i_id);
	}
	
}
