package com.pal.service;

import java.util.ArrayList;
import java.util.List;

import com.pal.dao.UsersDao;
import com.pal.entity.Page;
import com.pal.entity.Users;

public class UsersService {
	
	UsersDao usersDao =new UsersDao();

	
	
	public ArrayList<Users> getAllUsers(){
		return usersDao.getAllUsers();
	}

	public boolean getNewUsers(){
		return usersDao.getNewUsers()>0?true:false;
	}
	
	public ArrayList<Users> getIndexPeople(){
		return usersDao.getIndexPeople();
	}
	
	public boolean getNumUser(){
		return usersDao.getNewUsers()>0?true:false;
	}

	public Users getUser(int u_id){
		return usersDao.getUser(u_id);
	}
	
	public Users getUser(String u_name){
		return usersDao.getUser(u_name);
	}
	
	public Users getUserPwd(String u_name){
		return usersDao.getUserPwd(u_name);
	}
	
	public Users getUser(String u_name,String u_pwd){
		return usersDao.getUser(u_name, u_pwd);
	}
	
	public boolean addUser(Users users){
		return usersDao.addUser(users)>0?true:false;
	}
	
	public boolean delUser(String u_name){
		return usersDao.delUser(u_name)>0?true:false;
	}
	
	public boolean delUser(int u_id){
		return usersDao.delUser(u_id)>0?true:false;
	}
	
	public boolean modUser(Users users){
		return usersDao.modUser(users)>0?true:false;
	}
	/**
	 * 修改头像路径
	 * @param picPath
	 * @param u_id
	 * @return
	 */
	public boolean modUser(String picPath,int u_id){
		return usersDao.modUser(picPath, u_id)>0?true:false;
	}
	
	
	//实现分页查询的实现
	public Page<Users> getPage(int currPage,int pageSize){
		
		Page<Users> page=new Page<Users>();
		//设置页面容量
		page.setPageSize(pageSize);
		//设置总笔数
		int total=usersDao.getTotal();
		
		page.setTotal(total);
		page.setCurrPage(currPage);
		
		int start=(page.getCurrPage()-1)*page.getPageSize();
		
		List<Users> userList =usersDao.getAllUsers(start, pageSize);
		page.setList(userList);
		
		return page;
	}
	
	/**
	 * 是否封禁用户
	 * @param u_id
	 * @return
	 */
	public boolean isBanUSer(int u_id){
		return usersDao.isBanUser(u_id)>0?true:false;
	}
	
	public boolean isBanUSerByName(String u_name){
		return usersDao.isBanUserByName(u_name)>0?true:false;
	}
	
	/**
	 * 是否释放用户
	 * @param u_id
	 * @return
	 */
	public boolean freeUser(int u_id){
		return usersDao.freeUser(u_id)>0?true:false;
	}
	
	//实现被封禁用户的分页查询
	public Page<Users> getPageByIsBan(int currPage,int pageSize){
		
		Page<Users> page=new Page<Users>();
		//设置页面容量
		page.setPageSize(pageSize);
		//设置总笔数
		int total=usersDao.getBanTotal();
		
		page.setTotal(total);
		page.setCurrPage(currPage);
		
		//int start=(page.getCurrPage()-1)*page.getPageSize();
		int start=(page.getCurrPage()-1)*pageSize;
		
		List<Users> userList =usersDao.getIsBanUsers(start, pageSize);
		page.setList(userList);
		
		return page;
	}
	
	// 多条件模糊查找

		/**
		 * 昵称
		 * 
		 * @param currPage
		 * @param pageSize
		 * @return
		 */
		public Page<Users> getPage(String nickName, int currPage, int pageSize) {
			Page<Users> page = new Page<Users>();
			// 设置页面容量
			page.setPageSize(pageSize);
			// 设置总笔数
			int total = usersDao.getTotal(nickName);
			page.setTotal(total);
			page.setCurrPage(currPage);
			int start = (page.getCurrPage() - 1) * page.getPageSize();
			List<Users> userList = usersDao.getAllUsers(nickName,start, pageSize);
			page.setList(userList);
			return page;
		}

		/**
		 * 性别
		 * 
		 * @param currPage
		 * @param pageSize
		 * @return
		 */
		public Page<Users> getPageBySex(String sex,int currPage, int pageSize) {
			Page<Users> page = new Page<Users>();
			// 设置页面容量
			page.setPageSize(pageSize);
			// 设置总笔数
			int total = usersDao.getTotalBySex(sex);
			page.setTotal(total);
			page.setCurrPage(currPage);
			int start = (page.getCurrPage() - 1) * page.getPageSize();
			List<Users> userList = usersDao.getAllUsersBySex(sex,start, pageSize);
			page.setList(userList);
			return page;
		}
		
		/**
		 * 省/市
		 * 
		 * @param currPage
		 * @param pageSize
		 * @return
		 */
		public Page<Users> getPageByAdd(String add,int currPage, int pageSize) {
			Page<Users> page = new Page<Users>();
			// 设置页面容量
			page.setPageSize(pageSize);
			// 设置总笔数
			int total = usersDao.getTotalByAdd(add);
			page.setTotal(total);
			page.setCurrPage(currPage);
			int start = (page.getCurrPage() - 1) * page.getPageSize();
			List<Users> userList = usersDao.getAllUsersByAdd(add,start, pageSize);
			page.setList(userList);
			return page;
		}
		
		/**
		 * 年龄
		 * 
		 * @param currPage
		 * @param pageSize
		 * @return
		 */
		public Page<Users> getPageByAge(int minage,int maxage,int currPage, int pageSize) {
			Page<Users> page = new Page<Users>();
			// 设置页面容量
			page.setPageSize(pageSize);
			// 设置总笔数
			int total = usersDao.getTotalByAge(minage,maxage);
			page.setTotal(total);
			page.setCurrPage(currPage);
			int start = (page.getCurrPage() - 1) * page.getPageSize();
			List<Users> userList = usersDao.getAllUsersByAge(minage,maxage,start, pageSize);
			page.setList(userList);
			return page;
		}
		

		/**
		 * 性别,省/市
		 * 
		 * @param currPage
		 * @param pageSize
		 * @return
		 */
		public Page<Users> getPageBySexAdd(String sex,String add,int currPage, int pageSize) {
			Page<Users> page = new Page<Users>();
			// 设置页面容量
			page.setPageSize(pageSize);
			// 设置总笔数
			int total = usersDao.getTotalBySexAdd(sex,add);
			page.setTotal(total);
			page.setCurrPage(currPage);
			int start = (page.getCurrPage() - 1) * page.getPageSize();
			List<Users> userList = usersDao.getAllUsersBySexAdd(sex,add,start, pageSize);
			page.setList(userList);
			return page;
		}



		/**
		 * 性别,年龄
		 * 
		 * @param currPage
		 * @param pageSize
		 * @return
		 */
		public Page<Users> getPageBySexAge(String sex,int minage,int maxage,int currPage, int pageSize) {
			Page<Users> page = new Page<Users>();
			// 设置页面容量
			page.setPageSize(pageSize);
			// 设置总笔数
			int total = usersDao.getTotalBySexAge(sex,minage,maxage);
			page.setTotal(total);
			page.setCurrPage(currPage);
			int start = (page.getCurrPage() - 1) * page.getPageSize();
			List<Users> userList = usersDao.getAllUsersBySexAge(sex,minage,maxage,start, pageSize);
			page.setList(userList);
			return page;
		}


		/**
		 * 性别,省/市,年龄
		 * 
		 * @param currPage
		 * @param pageSize
		 * @return
		 */
		public Page<Users> getPageBySexAddAge(String sex,String add,int minage,int maxage,int currPage, int pageSize) {
			Page<Users> page = new Page<Users>();
			// 设置页面容量
			page.setPageSize(pageSize);
			// 设置总笔数
			int total = usersDao.getTotalBySexAddAge(sex,add,minage,maxage);
			page.setTotal(total);
			page.setCurrPage(currPage);
			int start = (page.getCurrPage() - 1) * page.getPageSize();
			List<Users> userList = usersDao.getAllUsersBySexAddAge(sex,add,minage,maxage,start, pageSize);
			page.setList(userList);
			return page;
		}


		
		/**
		 * 省/市,年龄
		 * 
		 * @param currPage
		 * @param pageSize
		 * @return
		 */
		public Page<Users> getPageByAddAge(String add,int minage,int maxage,int currPage, int pageSize) {
			Page<Users> page = new Page<Users>();
			// 设置页面容量
			page.setPageSize(pageSize);
			// 设置总笔数
			int total = usersDao.getTotalByAddAge(add,minage,maxage);
			page.setTotal(total);
			page.setCurrPage(currPage);
			int start = (page.getCurrPage() - 1) * page.getPageSize();
			List<Users> userList = usersDao.getAllUsersByAddAge(add, minage, maxage, start, pageSize);
			page.setList(userList);
			return page;
		}
		
		
	
	
}
