package com.pal.service;

import java.util.ArrayList;
import java.util.List;

import com.pal.dao.InformDao;
import com.pal.dao.ReasonTypeDao;
import com.pal.dao.UsersDao;
import com.pal.entity.Inform;
import com.pal.entity.Page;

public class InformService {
	
	InformDao informDao=new InformDao();
	UsersDao usersDao=new UsersDao();
	ReasonTypeDao rTypeDao=new ReasonTypeDao();
	
	public ArrayList<Inform> getAllInform(){
		return informDao.getAllInform();
	}
	
	public ArrayList<Inform> getHotInform(){
		return informDao.getHotInform();
	}
	
	public boolean getNewInform(){
		return informDao.getNewInform()>0?true:false;
	}
	
	
	public Inform getInform(int i_id){
		return informDao.getInform(i_id);
	}
	
	public boolean addInform(Inform inform){
		return informDao.addInform(inform)>0?true:false;
	}
	
	public boolean delInform(int i_id){
		return informDao.delInform(i_id)>0?true:false;
	}
	
	public boolean modInform(Inform inform){
		return informDao.modInform(inform)>0?true:false;
	}
	
	public Page<Inform> getPage(int currPage,int pageSize){
		
		Page<Inform> page=new Page<Inform>();
		
		//设置属性
		page.setPageSize(pageSize);
		
		int total=informDao.getTotal();
		page.setTotal(total);
		
		//设置当前页
		page.setCurrPage(currPage);
		
		int start =(page.getCurrPage()-1)*page.getPageSize();
		
		List<Inform> informList=informDao.getAllInform(start, pageSize);
		
		for(Inform inform:informList){
			
			inform.setUsers(usersDao.getUser(inform.getU_id()));
			inform.setrType(rTypeDao.getReasonType(inform.getRt_typeId()));
		}
		
		page.setList(informList);
		
		return page;
	}
	
	/**
	 * 已处理的被举报人
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public Page<Inform> getBanPage(int currPage,int pageSize){
		
		Page<Inform> page=new Page<Inform>();
		
		//设置属性
		page.setPageSize(pageSize);
		
		int total=informDao.getBanTotal();
		page.setTotal(total);
		
		//设置当前页
		page.setCurrPage(currPage);
		
		int start =(page.getCurrPage()-1)*page.getPageSize();
		
		List<Inform> informList=informDao.getIsBanInform(start, pageSize);
		
		for(Inform inform:informList){
			
			inform.setUsers(usersDao.getUser(inform.getU_id()));
			inform.setrType(rTypeDao.getReasonType(inform.getRt_typeId()));
		}
		
		page.setList(informList);
		
		return page;
	}
	
	public Page<Inform> getHotPage(int currPage,int pageSize){
		
		Page<Inform> page=new Page<Inform>();
		
		//设置属性
		page.setPageSize(pageSize);
		
		int total=informDao.getTotal();
		page.setTotal(total);
		
		//设置当前页
		page.setCurrPage(currPage);
		
		int start =(page.getCurrPage()-1)*page.getPageSize();
		
		List<Inform> informList=informDao.getHotInform(start, pageSize);
		
		for(Inform inform:informList){
			
			inform.setUsers(usersDao.getUser(inform.getU_id()));
			inform.setrType(rTypeDao.getReasonType(inform.getRt_typeId()));
		}
		
		page.setList(informList);
		
		return page;
	}
	
	public boolean getInformU_Id(int i_id){
		return informDao.getInformU_Id(i_id)>0?true:false;
	}
	
	/**
	 * 是否已经处理被举报人操作
	 * @param i_id
	 * @return
	 */
	/*public boolean isDealInform(int i_id){
		return informDao.isDealInform(i_id)>0?true:false;
	}*/
	
	/**
	 * 已经处理并封禁了举报人的操作
	 * @param i_id
	 * @return
	 */
	public boolean isDealBanInform(int i_id){
		return informDao.isDealBanInform(i_id)>0?true:false;
	}
	
	public boolean freeInform(int i_id){
		return informDao.freeInform(i_id)>0?true:false;
	}
}
