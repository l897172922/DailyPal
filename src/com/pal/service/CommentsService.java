package com.pal.service;

import java.util.ArrayList;
import java.util.List;

import com.pal.dao.CommentsDao;
import com.pal.dao.UsersDao;
import com.pal.entity.Blogs;
import com.pal.entity.Comments;
import com.pal.entity.Page;

public class CommentsService {
	
	CommentsDao commentsDao=new CommentsDao();
	UsersDao usersDao=new UsersDao();
	
	public ArrayList<Comments> getAllComments(){
		return commentsDao.getAllComments();
	}
	public ArrayList<Comments> getCommentsByblogId(Blogs blog){
		return commentsDao.getCommentsByblogId(blog);
	}
	
	public Comments getComments(int commentsId){
		return commentsDao.getComments(commentsId);
	}
	
	public boolean addComments(Comments comments){
		return commentsDao.addComments(comments)>0?true:false;
	}
	
	public boolean delComments(int commentsId){
		return commentsDao.delComments(commentsId)>0?true:false;
	}
	
public Page<Comments> getPage(int currPage,int pageSize){
		
		Page<Comments> page=new Page<Comments>();
		
		page.setPageSize(pageSize);
		int total=commentsDao.getTotal();
		page.setTotal(total);
		
		page.setCurrPage(currPage);
		
		int start =(page.getCurrPage()-1)*page.getPageSize();

		List<Comments> commentsList=commentsDao.getAllComments(start, pageSize);
		
		for(Comments comments:commentsList){
			comments.setFromUsers(usersDao.getUser(comments.getFromuserId()));
			comments.setToUsers(usersDao.getUser(comments.getToblogsId()));
		}
		
		page.setList(commentsList);
		
		return page;
	}
	
	
}
