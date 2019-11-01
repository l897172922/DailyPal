package com.pal.service;

import java.util.ArrayList;
import java.util.List;

import com.pal.dao.AdminDao;
import com.pal.entity.Admin;
import com.pal.entity.Page;

public class AdminService {
	
	AdminDao adminDao=new AdminDao();
	
	public ArrayList<Admin> getAllAdmin(){
		return adminDao.getAllAdmin();
	}
	
	public Admin getAdmin(int a_id){
		return adminDao.getAdmin(a_id);
	}
	
	public Admin getAdmin(String a_name){
		return adminDao.getAdmin(a_name);
	}
	
	public Admin getAdminPwd(String a_name){
		return adminDao.getAdminPwd(a_name);
	}
	
	public boolean addAdmin(Admin admin){
		return adminDao.addAdmin(admin)>0?true:false;
	}
	
	public boolean delAdmin(int a_id){
		return adminDao.delAdmin(a_id)>0?true:false;
	}
	
	public boolean modAdmin(Admin admin){
		return adminDao.modAdmin(admin)>0?true:false;
	}
	
	//实现分页查询的实现
		public Page<Admin> getPage(int currPage,int pageSize){
			
			Page<Admin> page=new Page<Admin>();
			//设置页面容量
			page.setPageSize(pageSize);
			//设置总笔数
			int total=adminDao.getTotal();
			
			page.setTotal(total);
			page.setCurrPage(currPage);
			
			int start=(page.getCurrPage()-1)*pageSize;
			
			List<Admin> adminList =adminDao.getAllAdmin(start, pageSize);
			page.setList(adminList);
			
			return page;
		}
}
