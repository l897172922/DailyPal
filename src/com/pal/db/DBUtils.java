package com.pal.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBUtils {
	
	private static DataSource dataSource;
	
	static{
		//加载配置文件，获取文件流
		InputStream is=DBUtils.class.getResourceAsStream("/dbcp.properties");
		//使用properties类处理配置文件
		Properties props=new Properties();
		
		try {
			props.load(is);
			
			//使用工具类创建连接池
			dataSource=BasicDataSourceFactory.createDataSource(props);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 返回数据源对象
	 * @return
	 */
	public static DataSource getDataSource(){
		return dataSource;
	}
	
	public static Connection getConnection(){
		Connection conn=null;
		try {
			conn= dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	
	
}
