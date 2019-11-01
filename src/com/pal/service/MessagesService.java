package com.pal.service;

import java.util.ArrayList;
import java.util.List;

import com.pal.dao.MessagesDao;
import com.pal.dao.UsersDao;
import com.pal.entity.Messages;
import com.pal.entity.Page;

public class MessagesService {
	
	MessagesDao messagesDao=new MessagesDao();
	UsersDao usersDao=new UsersDao();
	
	public ArrayList<Messages> getAllMessages(){
		return messagesDao.getAllMessages();
	}
	public ArrayList<Messages> getMessagesByuserId(int touserId){
		return messagesDao.getMessagesByuserid(touserId);
	}
	
	public Messages getMessages(int m_id){
		return messagesDao.getMessages(m_id);
	}
	
	public boolean addMessages(Messages messages){
		return messagesDao.addMessages(messages)>0?true:false;
	}
	
	public boolean delMessages(int m_id){
		return messagesDao.delMessages(m_id)>0?true:false;
	}
	/**
	 * 所有留言信息分页查询
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public Page<Messages> getPage(int currPage,int pageSize){
		
		Page<Messages> page=new Page<Messages>();
		
		//设置属性
		page.setPageSize(pageSize);
		
		int total=messagesDao.getTotal();
		page.setTotal(total);
		
		//设置当前页
		page.setCurrPage(currPage);
		
		int start =(page.getCurrPage()-1)*page.getPageSize();
		
		List<Messages> messagesList=messagesDao.getAllMessages(start, pageSize);
		
		for(Messages messages:messagesList){
			
			messages.setFromUsers(usersDao.getUser(messages.getFromuserId()));
			messages.setToUsers(usersDao.getUser(messages.getTouserId()));
			
		}
		
		page.setList(messagesList);
		
		return page;
	}
	
	
	public Page<Messages> getPage(int myId, int currPage , int pageSize){
		Page<Messages> page=new Page<Messages>();
		//设置属性
		page.setPageSize(pageSize);
		
		
//		获取我的总留言数
		int total = messagesDao.getMyMessagesTotal(myId);
		page.setTotal(total);
		
//		设置当前页
		page.setCurrPage(currPage);
//		总数
		int start = (page.getCurrPage()-1)*page.getPageSize();
		
		List<Messages> msgList = messagesDao.getMyMessages(myId, start, pageSize);
		
		page.setList(msgList);
		return page;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
