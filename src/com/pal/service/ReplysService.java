package com.pal.service;

import java.util.ArrayList;
import java.util.List;

import com.pal.dao.CommentsDao;
import com.pal.dao.ReplysDao;
import com.pal.dao.UsersDao;
import com.pal.entity.Page;
import com.pal.entity.Replys;

public class ReplysService {
	
	ReplysDao replysDao=new ReplysDao();
	UsersDao usersDao=new UsersDao();
	CommentsDao commentsDao=new CommentsDao();
	
	public ArrayList<Replys> getAllReplys(){
		return replysDao.getAllReplys();
	}
	
	public Replys getReplys(int replyId){
		return replysDao.getReplys(replyId);
	}
	
	public boolean addReplys(Replys replys){
		return replysDao.addReplys(replys)>0?true:false;
	}
	
	public boolean delReplys(int replyId){
		return replysDao.delReplys(replyId)>0?true:false;
	}
	
	public boolean modReplys(Replys replys){
		return replysDao.modReplys(replys)>0?true:false;
	}
	
	public Page<Replys> getPage(int currPage,int pageSize){
		
		Page<Replys> page=new Page<Replys>();
		
		page.setPageSize(pageSize);
		int total=replysDao.getTotal();
		page.setTotal(total);
		
		page.setCurrPage(currPage);
		
		int start =(page.getCurrPage()-1)*page.getPageSize();

		List<Replys> replysList=replysDao.getAllReplys(start, pageSize);
		
		for(Replys replys:replysList){
			replys.setFromUsers(usersDao.getUser(replys.getFromuserId()));
			replys.setToUsers(usersDao.getUser(replys.getTouserId()));
			replys.setComments(commentsDao.getComments(replys.getCommentsId()));
		}
		
		page.setList(replysList);
		
		return page;
	}
}
