package com.pal.service;

import java.util.ArrayList;
import java.util.List;

import com.pal.dao.FriendRequestDao;
import com.pal.dao.UsersDao;
import com.pal.entity.FriendRequest;
import com.pal.entity.Page;

public class FriendRequestService {
	
	FriendRequestDao fRequestDao=new FriendRequestDao();
	UsersDao usersDao=new UsersDao();
	
	
	public ArrayList<FriendRequest> getAllFriendRequest(){
		return fRequestDao.getAllFriendRequest();
	}
	
	/**
	 * 获取添加好友请求个数
	 * @param myId
	 * @return
	 */
	public int getTotal(int myId){
		return fRequestDao.getTotal(myId);
	}
	
	/**
	 * 添加好友请求
	 * @param myId
	 * @return
	 */
	public ArrayList<FriendRequest> getAllFriendRequest(int myId){
		ArrayList<FriendRequest> fRequest = new ArrayList<FriendRequest>();
		fRequest = fRequestDao.getAllFriendRequest(myId);
		for(FriendRequest fr:fRequest){
			fr.setFromUsers(usersDao.getUser(fr.getFromuserId()));
			//fr.setToUsers(usersDao.getUser(fr.getTouserId()));
		}
		return fRequest;
	}
	
	public boolean modFriendRequest(int fr_id){
		return fRequestDao.modFriendRequest(fr_id)>0?true:false;
	}
	
	public FriendRequest getFriendRequest(int fr_id){
		return fRequestDao.getFriendRequest(fr_id);
	}
	
	public boolean addFriendRequest(FriendRequest fRequest){
		return fRequestDao.addFriendRequest(fRequest)>0?true:false;
	}
	
	public boolean delFriendRequest(int fr_id){
		return fRequestDao.delFriendRequest(fr_id)>0?true:false;
	}
	
	
	public Page<FriendRequest> getPage(int currPage,int pageSize){
		
		Page<FriendRequest> page=new Page<FriendRequest>();
		
		page.setPageSize(pageSize);
		int total=fRequestDao.getTotal();
		page.setTotal(total);
		
		page.setCurrPage(currPage);
		
		int start =(page.getCurrPage()-1)*page.getPageSize();

		List<FriendRequest> fRequestList=fRequestDao.getAllFriendRequest(start, pageSize);
		
		for(FriendRequest fRequest:fRequestList){
			fRequest.setFromUsers(usersDao.getUser(fRequest.getFromuserId()));
			fRequest.setToUsers(usersDao.getUser(fRequest.getTouserId()));
		}
		
		page.setList(fRequestList);
		
		return page;
	}
	
	
	
	
}
