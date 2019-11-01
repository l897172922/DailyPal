package com.pal.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.pal.db.DBUtils;

public class BaseDao<T> {
	
	/**
	 * 执行增删改操作
	 * @param sql
	 * @param params
	 * @return
	 */
	public int exeSql(String sql,Object...params){
		int row=0;
		//创建核心类QueryRunner对象
		QueryRunner qr=new QueryRunner(DBUtils.getDataSource());
		try {
			row =qr.update(sql,params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	
	/**
	 * 获取单个对象
	 * @param sql
	 * @param params
	 * @return
	 */
	public T queryOne(String sql,Object...params){
		
		//创建核心类QueryRunner对象
		QueryRunner qr=new QueryRunner(DBUtils.getDataSource());
		
		T t=null;
		Type type=((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		
		//获取泛型对应的Class对象
		Class<T> tClass=(Class<T>)type;
		
		try {
			t=qr.query(sql, new BeanHandler<T>(tClass),params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return t;
	}
	
	
	/**
	 * 执行查询所有对象，返回对象的集合
	 * @param sql
	 * @param params
	 * @return
	 */
	public ArrayList<T> query(String sql,Object...params){
		List<T> list =null;
		//创建核心类QueryRunner对象
		QueryRunner qr=new QueryRunner(DBUtils.getDataSource());
			
		Type t=((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		
		//获取泛型对应的Class对象
		Class<T> tClass=(Class<T>)t;
		
		try {
			list = qr.query(sql,new BeanListHandler<T>(tClass),params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (ArrayList<T>)list;
	}
	
	/**
	 * 返回总笔数
	 * @param sql
	 * @param parameter
	 * @return
	 */
	public Long getCount(String sql,Object...params){
		QueryRunner qr =new QueryRunner(DBUtils.getDataSource());
		
		try {
			return (Long)qr.query(sql, new ScalarHandler(),params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
