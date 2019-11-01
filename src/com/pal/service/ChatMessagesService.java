package com.pal.service;

import java.util.ArrayList;
import java.util.List;

import com.pal.dao.ChatMessagesDao;
import com.pal.dao.UsersDao;
import com.pal.entity.ChatMessages;
import com.pal.entity.Page;

public class ChatMessagesService {
	
	ChatMessagesDao cMessagesDao=new ChatMessagesDao();
	UsersDao usersDao=new UsersDao();
	
	public ArrayList<ChatMessages> getAllChatMessages(){
		return cMessagesDao.getAllChatMessages();
	}
	
	public int getTotal(int myId){
		return cMessagesDao.getTotal(myId);
	}
	
	public int getTotalE(int myId){
		return cMessagesDao.getTotalE(myId);
	}
	
	/**
	 * 获取我和好友的所有消息
	 * @param myId
	 * @return
	 */
	public ArrayList<ChatMessages> getAllChatMessagesW(int myId,int friId){
		ArrayList<ChatMessages> cm = new ArrayList<ChatMessages>();
		cm = cMessagesDao.getAllChatMessagesW(myId,friId);
		for(ChatMessages cMessages:cm){
			cMessages.setFromUsers(usersDao.getUser(cMessages.getFromuserId()));
			//cMessages.setToUsers(usersDao.getUser(cMessages.getTouserId()));
		}	
		return cm;
	}

	/**
	 * 获取我和好友未读消息
	 * @param myId
	 * @param friId
	 * @return
	 */
	public ArrayList<ChatMessages> getAllChatMessagesWN(int myId,int friId){
		ArrayList<ChatMessages> cm = new ArrayList<ChatMessages>();
		cm = cMessagesDao.getAllChatMessagesWN(myId,friId);
		for(ChatMessages cMessages:cm){
			cMessages.setFromUsers(usersDao.getUser(cMessages.getFromuserId()));
			//cMessages.setToUsers(usersDao.getUser(cMessages.getTouserId()));
		}	
		return cm;
	}
	
	
	
	/**
	 * 获取未读消息
	 * @param myId
	 * @return
	 */
	public ArrayList<ChatMessages> getAllChatMessagesE(int myId){
		ArrayList<ChatMessages> cm = new ArrayList<ChatMessages>();
		cm = cMessagesDao.getAllChatMessagesE(myId);
		for(ChatMessages cMessages:cm){
			cMessages.setFromUsers(usersDao.getUser(cMessages.getFromuserId()));
			//cMessages.setToUsers(usersDao.getUser(cMessages.getTouserId()));
		}	
		return cm;
	}
	
	public boolean modChatMessages(int cmid){
		return cMessagesDao.modChatMessages(cmid)>0?true:false;
	}
	
	public ChatMessages getChatMessages(int cmId){
		return cMessagesDao.getChatMessages(cmId);
	}
	
	public boolean addChatMessages(ChatMessages cMessages){
		return cMessagesDao.addChatMessages(cMessages)>0?true:false;
	}
	
	public boolean delChatMessages(int cmId){
		return cMessagesDao.delChatMessages(cmId)>0?true:false;
	}
	
	public Page<ChatMessages> getPage(int currPage,int pageSize){
		
		Page<ChatMessages> page=new Page<ChatMessages>();
		
		page.setPageSize(pageSize);
		int total=cMessagesDao.getTotal();
		page.setTotal(total);
		
		page.setCurrPage(currPage);
		
		int start =(page.getCurrPage()-1)*page.getPageSize();

		List<ChatMessages> cMessagesList=cMessagesDao.getAllChatMessages(start, pageSize);
		
		for(ChatMessages cMessages:cMessagesList){
			cMessages.setFromUsers(usersDao.getUser(cMessages.getFromuserId()));
			cMessages.setToUsers(usersDao.getUser(cMessages.getTouserId()));
		}
		
		page.setList(cMessagesList);
		
		return page;
	}
	
}
